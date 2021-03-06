package de.peeeq.wurstscript.translation.imtranslation;

import com.google.common.collect.Lists;
import de.peeeq.wurstscript.WurstOperator;
import de.peeeq.wurstscript.ast.*;
import de.peeeq.wurstscript.ast.Element;
import de.peeeq.wurstscript.attributes.CompileError;
import de.peeeq.wurstscript.attributes.names.FuncLink;
import de.peeeq.wurstscript.attributes.names.NameLink;
import de.peeeq.wurstscript.jassIm.*;
import de.peeeq.wurstscript.jassIm.ImExprs;
import de.peeeq.wurstscript.jassIm.ImFunction;
import de.peeeq.wurstscript.jassIm.ImFunctionCall;
import de.peeeq.wurstscript.jassIm.ImIf;
import de.peeeq.wurstscript.jassIm.ImSet;
import de.peeeq.wurstscript.jassIm.ImStatementExpr;
import de.peeeq.wurstscript.jassIm.ImStmts;
import de.peeeq.wurstscript.jassIm.ImTupleExpr;
import de.peeeq.wurstscript.jassIm.ImTupleSelection;
import de.peeeq.wurstscript.jassIm.ImVar;
import de.peeeq.wurstscript.jassIm.ImVarAccess;
import de.peeeq.wurstscript.jassIm.ImVarArrayAccess;
import de.peeeq.wurstscript.jassIm.ImVarArrayMultiAccess;
import de.peeeq.wurstscript.types.TypesHelper;
import de.peeeq.wurstscript.types.WurstType;
import de.peeeq.wurstscript.types.WurstTypeVararg;

import java.util.List;
import java.util.Optional;

import static de.peeeq.wurstscript.jassIm.JassIm.*;

public class StmtTranslation {

    public static ImStmt translate(Expr s, ImTranslator t, ImFunction f) {
        return s.imTranslateExpr(t, f);
    }

    public static ImStmt translate(LocalVarDef s, ImTranslator t, ImFunction f) {
        ImVar v = t.getVarFor(s);
        f.getLocals().add(v);
        if (s.getInitialExpr() instanceof Expr) {
            Expr inital = (Expr) s.getInitialExpr();
            return ImSet(s, v, inital.imTranslateExpr(t, f));
        } else if (s.getInitialExpr() instanceof ArrayInitializer) {
            ArrayInitializer ai = (ArrayInitializer) s.getInitialExpr();
            ImStmts stmts = ImStmts();
            for (int i = 0; i < ai.getValues().size(); i++) {
                Expr expr = ai.getValues().get(i);
                ImExpr translatedExpr = expr.imTranslateExpr(t, f);
                stmts.add(JassIm.ImSetArray(s, v, JassIm.ImIntVal(i), translatedExpr));
            }
            return JassIm.ImStatementExpr(stmts, ImNull());
        } else {
            return ImNull();
        }
    }


    public static ImStmt translate(StmtErr s, ImTranslator t, ImFunction f) {
        throw new CompileError(s.getSource(), "Source contains errors.");
    }


    public static ImStmt translate(StmtExitwhen s, ImTranslator t, ImFunction f) {
        return ImExitwhen(s, s.getCond().imTranslateExpr(t, f));
    }


    public static ImStmt translate(StmtForFrom s, ImTranslator t, ImFunction f) {
        Expr iterationTarget = s.getIn();
        WurstType iteratorType = iterationTarget.attrTyp();
        // Type of loop Variable:
        WurstType loopVarType = s.getLoopVar().attrTyp();
        List<ImStmt> result = Lists.newArrayList();
        Optional<FuncLink> nextFuncOpt = s.attrGetNextFunc();
        Optional<FuncLink> hasNextFuncOpt = s.attrHasNextFunc();
        if (nextFuncOpt.isPresent() && hasNextFuncOpt.isPresent()) {
            FuncLink nextFunc = nextFuncOpt.get();
            FuncLink hasNextFunc = hasNextFuncOpt.get();

            // get the iterator function in the intermediate language
            ImFunction nextFuncIm = t.getFuncFor((TranslatedToImFunction) nextFunc.getDef());
            ImFunction hasNextFuncIm = t.getFuncFor((TranslatedToImFunction) hasNextFunc.getDef());

            f.getLocals().add(t.getVarFor(s.getLoopVar()));

            ImExprs fromTarget;
            if (iterationTarget.attrTyp().isStaticRef()) {
                fromTarget = ImExprs();
            } else {
                // store from-expression in variable, so that it is only evaluated once
                ImExpr iterationTargetTr = iterationTarget.imTranslateExpr(t, f);
                ImVar fromVar = ImVar(s, iterationTargetTr.attrTyp(), "from", false);
                f.getLocals().add(fromVar);
                result.add(ImSet(s, fromVar, iterationTargetTr));
                fromTarget = JassIm.ImExprs(ImVarAccess(fromVar));
            }

            ImStmts imBody = ImStmts();
            // exitwhen not #hasNext()
            imBody.add(ImExitwhen(s, JassIm.ImOperatorCall(WurstOperator.NOT, JassIm.ImExprs(JassIm.ImFunctionCall(s, hasNextFuncIm, fromTarget, false, CallType
                    .NORMAL)))));
            // elem = next()
            ImFunctionCall nextCall = JassIm.ImFunctionCall(s, nextFuncIm, fromTarget.copy(),
                    false, CallType.NORMAL);

            WurstType nextReturn = nextFunc.getReturnType();
            ImExpr nextCallWrapped = ExprTranslation.wrapTranslation(s, t, nextCall, nextReturn, loopVarType);

            imBody.add(JassIm.ImSet(s, t.getVarFor(s.getLoopVar()), nextCallWrapped));

            imBody.addAll(t.translateStatements(f, s.getBody()));

            result.add(ImLoop(s, imBody));
        }

        return ImStatementExpr(ImStmts(result), ImNull());
    }


    public static ImStmt translate(StmtForIn s, ImTranslator t, ImFunction f) {
        Expr iterationTarget = s.getIn();
        WurstType itrType = iterationTarget.attrTyp();
        if (itrType instanceof WurstTypeVararg) {
            return case_StmtForVararg(s, t, f);
        }
        List<ImStmt> result = Lists.newArrayList();

        Optional<FuncLink> iteratorFuncOpt = s.attrIteratorFunc();
        Optional<FuncLink> nextFuncOpt = s.attrGetNextFunc();
        Optional<FuncLink> hasNextFuncOpt = s.attrHasNextFunc();
        if (iteratorFuncOpt.isPresent() && nextFuncOpt.isPresent() && hasNextFuncOpt.isPresent()) {
            FuncLink iteratorFunc = iteratorFuncOpt.get();
            FuncLink nextFunc = nextFuncOpt.get();
            FuncLink hasNextFunc = hasNextFuncOpt.get();

            // Type of iterator variable:
            WurstType iteratorType = iteratorFunc.getReturnType();
            // Type of loop Variable:
            WurstType loopVarType = s.getLoopVar().attrTyp();

            // get the iterator function in the intermediate language
            ImFunction iteratorFuncIm = t.getFuncFor((TranslatedToImFunction) iteratorFunc.getDef());
            ImFunction nextFuncIm = t.getFuncFor((TranslatedToImFunction) nextFunc.getDef());
            ImFunction hasNextFuncIm = t.getFuncFor((TranslatedToImFunction) hasNextFunc.getDef());

            // translate target:
            ImExprs iterationTargetList;
            if (s.getIn().attrTyp().isStaticRef()) {
                iterationTargetList = ImExprs();
            } else {
                ImExpr iterationTargetIm = s.getIn().imTranslateExpr(t, f);
                iterationTargetList = JassIm.ImExprs(iterationTargetIm);
            }

            // call XX.iterator()
            ImFunctionCall iteratorCall = JassIm.ImFunctionCall(s, iteratorFuncIm, iterationTargetList, false, CallType.NORMAL);
            // create IM-variable for iterator
            ImVar iteratorVar = JassIm.ImVar(s.getLoopVar(), iteratorCall.attrTyp(), "iterator", false);

            f.getLocals().add(iteratorVar);
            f.getLocals().add(t.getVarFor(s.getLoopVar()));
            // create code for initializing iterator:

            ImSet setIterator = JassIm.ImSet(s, iteratorVar, iteratorCall);

            result.add(setIterator);

            ImStmts imBody = ImStmts();
            // exitwhen not #hasNext()
            imBody.add(ImExitwhen(s, JassIm.ImOperatorCall(WurstOperator.NOT, JassIm.ImExprs(JassIm.ImFunctionCall(s, hasNextFuncIm, JassIm.ImExprs(JassIm
                    .ImVarAccess(iteratorVar)), false, CallType.NORMAL)))));
            // elem = next()
            ImFunctionCall nextCall = JassIm.ImFunctionCall(s, nextFuncIm, JassIm.ImExprs(JassIm.ImVarAccess(iteratorVar)), false,
                    CallType.NORMAL);
            WurstType nextReturn = nextFunc.getReturnType();
            ImExpr nextCallWrapped = ExprTranslation.wrapTranslation(s, t, nextCall, nextReturn, loopVarType);

            imBody.add(JassIm.ImSet(s, t.getVarFor(s.getLoopVar()), nextCallWrapped));

            imBody.addAll(t.translateStatements(f, s.getBody()));

            result.add(ImLoop(s, imBody));

            // close iterator
            Optional<FuncLink> closeFunc = s.attrCloseFunc();
            closeFunc.ifPresent(nameLink -> result.add(JassIm.ImFunctionCall(s, t.getFuncFor(nameLink.getDef()), JassIm.ImExprs(JassIm
                    .ImVarAccess(iteratorVar)), false, CallType.NORMAL)));

        }


        return ImStatementExpr(ImStmts(result), ImNull());
    }

    /**
     * Translate a for in vararg loop. Unlike the other for loops we don't need
     * an iterator etc. because the loop is unrolled in the VarargEliminator
     */
    private static ImStmt case_StmtForVararg(StmtForIn s, ImTranslator t, ImFunction f) {
        List<ImStmt> result = Lists.newArrayList();
        ImVar loopVar = t.getVarFor(s.getLoopVar());

        result.add(ImVarargLoop(s, ImStmts(t.translateStatements(f, s.getBody())), loopVar));

        f.getLocals().add(loopVar);
        return ImStatementExpr(ImStmts(result), ImNull());
    }


    public static ImStmt translate(StmtForRangeUp s, ImTranslator t, ImFunction f) {
        return case_StmtForRange(t, f, s.getLoopVar(), s.getTo(), s.getStep(), s.getBody(), WurstOperator.PLUS,
                WurstOperator.GREATER, s);
    }


    public static ImStmt translate(StmtForRangeDown s, ImTranslator t, ImFunction f) {
        return case_StmtForRange(t, f, s.getLoopVar(), s.getTo(), s.getStep(), s.getBody(),
                WurstOperator.MINUS, WurstOperator.LESS, s);
    }

    private static ImStmt case_StmtForRange(ImTranslator t, ImFunction f, LocalVarDef loopVar,
                                            Expr to, Expr step, WStatements body, WurstOperator opStep, WurstOperator opCompare, Element trace) {
        ImVar imLoopVar = t.getVarFor(loopVar);
        f.getLocals().add(imLoopVar);

        Expr from = (Expr) loopVar.getInitialExpr();
        ImExpr fromExpr = from.imTranslateExpr(t, f);
        List<ImStmt> result = Lists.newArrayList();
        result.add(ImSet(loopVar, imLoopVar, fromExpr));

        ImExpr toExpr = addCacheVariableSmart(t, f, result, to, TypesHelper.imInt());
        ImExpr stepExpr = addCacheVariableSmart(t, f, result, step, TypesHelper.imInt());

        ImStmts imBody = ImStmts();
        // exitwhen imLoopVar > toExpr
        imBody.add(ImExitwhen(trace, ImOperatorCall(opCompare, ImExprs(ImVarAccess(imLoopVar), toExpr))));
        // loop body:
        imBody.addAll(t.translateStatements(f, body));
        // set imLoopVar = imLoopVar + stepExpr
        imBody.add(ImSet(trace, imLoopVar, ImOperatorCall(opStep, ImExprs(ImVarAccess(imLoopVar), stepExpr))));
        result.add(ImLoop(trace, imBody));
        return ImStatementExpr(ImStmts(result), ImNull());
    }


    private static ImExpr addCacheVariableSmart(ImTranslator t, ImFunction f, List<ImStmt> result, Expr toCache, ImType type) {
        ImExpr r = toCache.imTranslateExpr(t, f);
        if (r instanceof ImConst) {
            return r;
        }
        ImVar tempVar = JassIm.ImVar(toCache, type, "temp", false);
        f.getLocals().add(tempVar);
        result.add(ImSet(toCache, tempVar, r));
        return ImVarAccess(tempVar);
    }

    public static ImStmt translate(StmtIf s, ImTranslator t, ImFunction f) {
        return ImIf(s, s.getCond().imTranslateExpr(t, f), ImStmts(t.translateStatements(f, s.getThenBlock())), ImStmts(t.translateStatements(f, s
                .getElseBlock())));
    }


    public static ImStmt translate(StmtLoop s, ImTranslator t, ImFunction f) {
        return ImLoop(s, ImStmts(t.translateStatements(f, s.getBody())));
    }


    public static ImStmt translate(StmtReturn s, ImTranslator t, ImFunction f) {
        return ImReturn(s, s.getReturnedObj().imTranslateExprOpt(t, f));
    }


    public static ImStmt translate(StmtSet s, ImTranslator t, ImFunction f) {
        // 4 cases for left side:
        // 	1. normal var
        // 	2. array var
        // 	3. tuple var
        // 	4. tuple array var

        ImExpr updated = s.getUpdatedExpr().imTranslateExpr(t, f);

        List<ImStmt> statements = Lists.newArrayList();
        updated = flatten(updated, statements);

        ImExpr right = s.getRight().imTranslateExpr(t, f);

        return translateAssignment(s, updated, right);
    }

    private static ImStmt translateAssignment(AstElementWithSource s, ImExpr updated, ImExpr right) throws CompileError {
        if (updated instanceof ImTupleSelection) {
            ImTupleSelection tupleSelection = (ImTupleSelection) updated;
            ImExpr tupleExpr = tupleSelection.getTupleExpr();

            if (tupleExpr instanceof ImVarAccess) {
                // case: tuple var
                ImVarAccess va = (ImVarAccess) tupleExpr;
                return ImSetTuple(s, va.getVar(), tupleSelection.getTupleIndex(), right);
            } else if (tupleExpr instanceof ImVarArrayAccess) {
                // case: tuple array var
                ImVarArrayAccess va = (ImVarArrayAccess) tupleExpr;
                return ImSetArrayTuple(s, va.getVar(), (ImExpr) va.getIndex().copy(), tupleSelection.getTupleIndex(), right);
            } else {
                throw new CompileError(s.getSource(), "Cannot translate tuple access");
            }
        } else if (updated instanceof ImVarAccess) {
            ImVarAccess va = (ImVarAccess) updated;
            return ImSet(s, va.getVar(), right);
        } else if (updated instanceof ImVarArrayAccess) {
            ImVarArrayAccess va = (ImVarArrayAccess) updated;
            return ImSetArray(s, va.getVar(), (ImExpr) va.getIndex().copy(), right);
        } else if (updated instanceof ImVarArrayMultiAccess) {
            ImVarArrayMultiAccess va = (ImVarArrayMultiAccess) updated;
            return JassIm.ImSetArrayMulti(s, va.getVar(), JassIm.ImExprs((ImExpr) va.getIndex1().copy(), (ImExpr) va.getIndex2().copy()), right);
        } else if (updated instanceof ImTupleExpr) {
            // TODO this could lead to expressions being evaluated twice
            ImTupleExpr te = (ImTupleExpr) updated;
            ImStmts stmts = JassIm.ImStmts();
            for (int i = 0; i < te.getExprs().size(); i++) {
                ImExpr l = (ImExpr) te.getExprs().get(i).copy();
                ImExpr r;
                if (right instanceof ImTupleExpr) {
                    ImTupleExpr rt = (ImTupleExpr) right;
                    r = (ImExpr) rt.getExprs().get(i).copy();
                } else {
                    r = JassIm.ImTupleSelection((ImExpr) right.copy(), i);
                }
                stmts.add(translateAssignment(s, l, r));
            }
            return JassIm.ImStatementExpr(stmts, JassIm.ImNull());
        } else {
            throw new CompileError(s.getSource(), "Cannot translate set statement, updated = " + updated.getClass().getSimpleName());
        }
    }


    private static ImExpr flatten(ImExpr updated, List<ImStmt> statements) {
        while (updated instanceof ImStatementExpr) {
            ImStatementExpr se = (ImStatementExpr) updated;
            statements.addAll(se.getStatements().removeAll());
            updated = se.getExpr();
        }
        return updated;
    }

    public static ImStmt translate(StmtWhile s, ImTranslator t, ImFunction f) {
        List<ImStmt> body = Lists.newArrayList();
        // exitwhen not while_condition
        body.add(ImExitwhen(s.getCond(), ImOperatorCall(WurstOperator.NOT, ImExprs(s.getCond().imTranslateExpr(t, f)))));
        body.addAll(t.translateStatements(f, s.getBody()));
        return ImLoop(s, ImStmts(body));
    }

    public static ImStmt translate(StmtSkip s, ImTranslator translator, ImFunction f) {
        return JassIm.ImNull();
    }

    public static ImStmt translate(SwitchStmt switchStmt, ImTranslator t, ImFunction f) {
        List<ImStmt> result = Lists.newArrayList();
        ImType type = switchStmt.getExpr().attrTyp().imTranslateType();
        ImExpr tempVar = addCacheVariableSmart(t, f, result, switchStmt.getExpr(), type);
        // generate ifs
        // leerer Block:
        //ImStmts();
        // if else
        //ImIf(trace, condition, thenBlock, elseBlock);
        // vergleich
        //ImOperatorCall(Ast.OpEquals(), ImExprs(a,b))

        ImIf lastIf = null;
        SwitchCase cse;
        for (int i = 0; i < switchStmt.getCases().size(); i++) {
            cse = switchStmt.getCases().get(i);
            if (lastIf == null) {
                lastIf = ImIf(switchStmt, ImOperatorCall(WurstOperator.EQ, ImExprs((ImExpr) tempVar.copy(), cse.getExpr().imTranslateExpr(t, f))), ImStmts(t
                        .translateStatements(f, cse.getStmts())), ImStmts());
                result.add(lastIf);
            } else {
                ImIf tmp = ImIf(switchStmt, ImOperatorCall(WurstOperator.EQ, ImExprs((ImExpr) tempVar.copy(), cse.getExpr().imTranslateExpr(t, f))), ImStmts
                        (t.translateStatements(f, cse.getStmts())), ImStmts());
                lastIf.setElseBlock(ImStmts(tmp));
                lastIf = tmp;
            }
        }

        if (lastIf == null) {
            throw new CompileError(switchStmt.attrSource(), "No cases in switch?");
        }

//		WLogger.info("it is a " + switchStmt.getSwitchDefault().getClass());
        if (switchStmt.getSwitchDefault() instanceof SwitchDefaultCaseStatements) {

//			WLogger.info("indeed it is");
            SwitchDefaultCaseStatements dflt = (SwitchDefaultCaseStatements) switchStmt.getSwitchDefault();
            lastIf.setElseBlock(ImStmts(t.translateStatements(f, dflt.getStmts())));
        } else if (switchStmt.getSwitchDefault() instanceof NoDefaultCase) {
//			WLogger.info("wtf?");
        }


        return ImStatementExpr(ImStmts(result), ImNull());
    }

    public static ImStmt translate(EndFunctionStatement endFunctionStatement, ImTranslator translator, ImFunction f) {
        return ImNull();
    }

    public static ImStmt translate(StartFunctionStatement startFunctionStatement, ImTranslator translator, ImFunction f) {
        return ImNull();
    }

    public static ImStmt translate(WBlock block, ImTranslator translator, ImFunction f) {
        ImStmts stmts = ImStmts();
        for (WStatement s : block.getBody()) {
            stmts.add(s.imTranslateStmt(translator, f));
        }
        return JassIm.ImStatementExpr(stmts, ImNull());
    }


}
