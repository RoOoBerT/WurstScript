package de.peeeq.wurstscript.ast;

import java.util.List;
import katja.common.*;
import java.io.IOException;

public interface ExprFunctionCallPos extends de.peeeq.wurstscript.ast.ExprPos, de.peeeq.wurstscript.ast.FuncRefPos, de.peeeq.wurstscript.ast.StmtCallPos, de.peeeq.wurstscript.ast.AST.TuplePos<de.peeeq.wurstscript.ast.ExprFunctionCall> {

    //----- methods of ExprFunctionCallPos -----

    public de.peeeq.wurstscript.ast.ExprFunctionCall termExpr();
    public de.peeeq.wurstscript.ast.ExprFunctionCall termFuncRef();
    public de.peeeq.wurstscript.ast.ExprFunctionCall termStmtCall();
    public de.peeeq.wurstscript.ast.ExprFunctionCall termOptExpr();
    public de.peeeq.wurstscript.ast.ExprFunctionCall termWStatement();
    public de.peeeq.wurstscript.ast.ExprFunctionCall term();
    public de.peeeq.wurstscript.ast.WPosPos source();
    public de.peeeq.wurstscript.ast.ExprFunctionCallPos replaceSource(de.peeeq.wurstscript.ast.WPos source);
    public de.peeeq.wurstscript.ast.StringPos funcName();
    public de.peeeq.wurstscript.ast.ExprFunctionCallPos replaceFuncName(java.lang.String funcName);
    public de.peeeq.wurstscript.ast.ArgumentsPos args();
    public de.peeeq.wurstscript.ast.ExprFunctionCallPos replaceArgs(de.peeeq.wurstscript.ast.Arguments args);
    public de.peeeq.wurstscript.ast.AST.TermPos<?> get(int i);
    public int size();
    public de.peeeq.wurstscript.ast.ExprFunctionCallPos replace(de.peeeq.wurstscript.ast.ExprFunctionCall term);
    public de.peeeq.wurstscript.ast.AST.NodePos<?> parent();
    public de.peeeq.wurstscript.ast.AST.SortPos lsib();
    public de.peeeq.wurstscript.ast.AST.SortPos rsib();
    public de.peeeq.wurstscript.ast.AST.SortPos preOrder();
    public de.peeeq.wurstscript.ast.AST.SortPos preOrderSkip();
    public de.peeeq.wurstscript.ast.AST.SortPos postOrder();
    public de.peeeq.wurstscript.ast.AST.SortPos postOrderStart();
    public de.peeeq.wurstscript.ast.AST.SortPos follow(List<Integer> path);
    public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.ExprPos.Switch<CT, E> switchClass) throws E;
    public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.FuncRefPos.Switch<CT, E> switchClass) throws E;
    public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.StmtCallPos.Switch<CT, E> switchClass) throws E;
    public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.OptExprPos.Switch<CT, E> switchClass) throws E;
    public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.WStatementPos.Switch<CT, E> switchClass) throws E;

    //----- nested classes of ExprFunctionCallPos -----

    static interface VisitorType<E extends Throwable> {

        //----- methods of VisitorType<E extends Throwable> -----

        public void visit(de.peeeq.wurstscript.ast.WPosPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.StringPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ArgumentsPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.IntegerPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprAssignablePos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprBinaryPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprUnaryPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprMemberVarPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprMemberArrayVarPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprMemberMethodPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprFunctionCallPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprNewObjectPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprAtomicPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprVarAccessPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprVarArrayAccessPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprIntValPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprRealValPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprStringValPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprBoolValPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprFuncRefPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprThisPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.ExprNullPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpBinaryPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpUnaryPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.IndexesPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.DoublePos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.BooleanPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpOrPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpAndPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpEqualsPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpUnequalsPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpLessEqPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpLessPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpGreaterEqPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpGreaterPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpPlusPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpMinusPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpMultPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpDivRealPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpModRealPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpModIntPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpDivIntPos term) throws E;
        public void visit(de.peeeq.wurstscript.ast.OpNotPos term) throws E;
    }

    public static abstract class Visitor<E extends Throwable> implements de.peeeq.wurstscript.ast.ExprFunctionCallPos.VisitorType<E> {

        //----- attributes of Visitor<E extends Throwable> -----

        private final de.peeeq.wurstscript.ast.ExprAssignablePos.Switch<Object, E> variantVisit$ExprAssignablePos = new de.peeeq.wurstscript.ast.ExprAssignablePos.Switch<Object, E>() { public final Object CaseExprMemberVarPos(de.peeeq.wurstscript.ast.ExprMemberVarPos term) throws E { visit(term); return null; } public final Object CaseExprMemberArrayVarPos(de.peeeq.wurstscript.ast.ExprMemberArrayVarPos term) throws E { visit(term); return null; } public final Object CaseExprVarAccessPos(de.peeeq.wurstscript.ast.ExprVarAccessPos term) throws E { visit(term); return null; } public final Object CaseExprVarArrayAccessPos(de.peeeq.wurstscript.ast.ExprVarArrayAccessPos term) throws E { visit(term); return null; } };
        private final de.peeeq.wurstscript.ast.ExprAtomicPos.Switch<Object, E> variantVisit$ExprAtomicPos = new de.peeeq.wurstscript.ast.ExprAtomicPos.Switch<Object, E>() { public final Object CaseExprIntValPos(de.peeeq.wurstscript.ast.ExprIntValPos term) throws E { visit(term); return null; } public final Object CaseExprRealValPos(de.peeeq.wurstscript.ast.ExprRealValPos term) throws E { visit(term); return null; } public final Object CaseExprStringValPos(de.peeeq.wurstscript.ast.ExprStringValPos term) throws E { visit(term); return null; } public final Object CaseExprBoolValPos(de.peeeq.wurstscript.ast.ExprBoolValPos term) throws E { visit(term); return null; } public final Object CaseExprFuncRefPos(de.peeeq.wurstscript.ast.ExprFuncRefPos term) throws E { visit(term); return null; } public final Object CaseExprVarAccessPos(de.peeeq.wurstscript.ast.ExprVarAccessPos term) throws E { visit(term); return null; } public final Object CaseExprVarArrayAccessPos(de.peeeq.wurstscript.ast.ExprVarArrayAccessPos term) throws E { visit(term); return null; } public final Object CaseExprThisPos(de.peeeq.wurstscript.ast.ExprThisPos term) throws E { visit(term); return null; } public final Object CaseExprNullPos(de.peeeq.wurstscript.ast.ExprNullPos term) throws E { visit(term); return null; } };
        private final de.peeeq.wurstscript.ast.ExprPos.Switch<Object, E> variantVisit$ExprPos = new de.peeeq.wurstscript.ast.ExprPos.Switch<Object, E>() { public final Object CaseExprBinaryPos(de.peeeq.wurstscript.ast.ExprBinaryPos term) throws E { visit(term); return null; } public final Object CaseExprUnaryPos(de.peeeq.wurstscript.ast.ExprUnaryPos term) throws E { visit(term); return null; } public final Object CaseExprMemberVarPos(de.peeeq.wurstscript.ast.ExprMemberVarPos term) throws E { visit(term); return null; } public final Object CaseExprMemberArrayVarPos(de.peeeq.wurstscript.ast.ExprMemberArrayVarPos term) throws E { visit(term); return null; } public final Object CaseExprMemberMethodPos(de.peeeq.wurstscript.ast.ExprMemberMethodPos term) throws E { visit(term); return null; } public final Object CaseExprFunctionCallPos(de.peeeq.wurstscript.ast.ExprFunctionCallPos term) throws E { visit(term); return null; } public final Object CaseExprNewObjectPos(de.peeeq.wurstscript.ast.ExprNewObjectPos term) throws E { visit(term); return null; } public final Object CaseExprVarAccessPos(de.peeeq.wurstscript.ast.ExprVarAccessPos term) throws E { visit(term); return null; } public final Object CaseExprVarArrayAccessPos(de.peeeq.wurstscript.ast.ExprVarArrayAccessPos term) throws E { visit(term); return null; } public final Object CaseExprIntValPos(de.peeeq.wurstscript.ast.ExprIntValPos term) throws E { visit(term); return null; } public final Object CaseExprRealValPos(de.peeeq.wurstscript.ast.ExprRealValPos term) throws E { visit(term); return null; } public final Object CaseExprStringValPos(de.peeeq.wurstscript.ast.ExprStringValPos term) throws E { visit(term); return null; } public final Object CaseExprBoolValPos(de.peeeq.wurstscript.ast.ExprBoolValPos term) throws E { visit(term); return null; } public final Object CaseExprFuncRefPos(de.peeeq.wurstscript.ast.ExprFuncRefPos term) throws E { visit(term); return null; } public final Object CaseExprThisPos(de.peeeq.wurstscript.ast.ExprThisPos term) throws E { visit(term); return null; } public final Object CaseExprNullPos(de.peeeq.wurstscript.ast.ExprNullPos term) throws E { visit(term); return null; } };
        private final de.peeeq.wurstscript.ast.OpBinaryPos.Switch<Object, E> variantVisit$OpBinaryPos = new de.peeeq.wurstscript.ast.OpBinaryPos.Switch<Object, E>() { public final Object CaseOpOrPos(de.peeeq.wurstscript.ast.OpOrPos term) throws E { visit(term); return null; } public final Object CaseOpAndPos(de.peeeq.wurstscript.ast.OpAndPos term) throws E { visit(term); return null; } public final Object CaseOpEqualsPos(de.peeeq.wurstscript.ast.OpEqualsPos term) throws E { visit(term); return null; } public final Object CaseOpUnequalsPos(de.peeeq.wurstscript.ast.OpUnequalsPos term) throws E { visit(term); return null; } public final Object CaseOpLessEqPos(de.peeeq.wurstscript.ast.OpLessEqPos term) throws E { visit(term); return null; } public final Object CaseOpLessPos(de.peeeq.wurstscript.ast.OpLessPos term) throws E { visit(term); return null; } public final Object CaseOpGreaterEqPos(de.peeeq.wurstscript.ast.OpGreaterEqPos term) throws E { visit(term); return null; } public final Object CaseOpGreaterPos(de.peeeq.wurstscript.ast.OpGreaterPos term) throws E { visit(term); return null; } public final Object CaseOpPlusPos(de.peeeq.wurstscript.ast.OpPlusPos term) throws E { visit(term); return null; } public final Object CaseOpMinusPos(de.peeeq.wurstscript.ast.OpMinusPos term) throws E { visit(term); return null; } public final Object CaseOpMultPos(de.peeeq.wurstscript.ast.OpMultPos term) throws E { visit(term); return null; } public final Object CaseOpDivRealPos(de.peeeq.wurstscript.ast.OpDivRealPos term) throws E { visit(term); return null; } public final Object CaseOpModRealPos(de.peeeq.wurstscript.ast.OpModRealPos term) throws E { visit(term); return null; } public final Object CaseOpModIntPos(de.peeeq.wurstscript.ast.OpModIntPos term) throws E { visit(term); return null; } public final Object CaseOpDivIntPos(de.peeeq.wurstscript.ast.OpDivIntPos term) throws E { visit(term); return null; } };
        private final de.peeeq.wurstscript.ast.OpUnaryPos.Switch<Object, E> variantVisit$OpUnaryPos = new de.peeeq.wurstscript.ast.OpUnaryPos.Switch<Object, E>() { public final Object CaseOpNotPos(de.peeeq.wurstscript.ast.OpNotPos term) throws E { visit(term); return null; } public final Object CaseOpMinusPos(de.peeeq.wurstscript.ast.OpMinusPos term) throws E { visit(term); return null; } };

        //----- methods of Visitor<E extends Throwable> -----

        public final void visit(de.peeeq.wurstscript.ast.ExprAssignablePos term) throws E {
            term.Switch(variantVisit$ExprAssignablePos);
        }

        public final void visit(de.peeeq.wurstscript.ast.ExprAtomicPos term) throws E {
            term.Switch(variantVisit$ExprAtomicPos);
        }

        public final void visit(de.peeeq.wurstscript.ast.ExprPos term) throws E {
            term.Switch(variantVisit$ExprPos);
        }

        public final void visit(de.peeeq.wurstscript.ast.OpBinaryPos term) throws E {
            term.Switch(variantVisit$OpBinaryPos);
        }

        public final void visit(de.peeeq.wurstscript.ast.OpUnaryPos term) throws E {
            term.Switch(variantVisit$OpUnaryPos);
        }
    }

    static class Impl extends KatjaTuplePosImpl<de.peeeq.wurstscript.ast.CompilationUnitPos, de.peeeq.wurstscript.ast.ExprFunctionCall> implements de.peeeq.wurstscript.ast.ExprFunctionCallPos {

        //----- attributes of Impl -----

        private de.peeeq.wurstscript.ast.WPosPos _source = null;
        private de.peeeq.wurstscript.ast.StringPos _funcName = null;
        private de.peeeq.wurstscript.ast.ArgumentsPos _args = null;

        //----- methods of Impl -----

        public de.peeeq.wurstscript.ast.ExprFunctionCall termExpr() {
            return term();
        }

        public de.peeeq.wurstscript.ast.ExprFunctionCall termFuncRef() {
            return term();
        }

        public de.peeeq.wurstscript.ast.ExprFunctionCall termStmtCall() {
            return term();
        }

        public de.peeeq.wurstscript.ast.ExprFunctionCall termOptExpr() {
            return term();
        }

        public de.peeeq.wurstscript.ast.ExprFunctionCall termWStatement() {
            return term();
        }

        public de.peeeq.wurstscript.ast.WPosPos source() {
            if(_source == null)
                _source = de.peeeq.wurstscript.ast.AST.WPosPos(this, _term.source(), 0);

            return _source;
        }

        public de.peeeq.wurstscript.ast.ExprFunctionCallPos replaceSource(de.peeeq.wurstscript.ast.WPos source) {
            return replace(_term.replaceSource(source));
        }

        public de.peeeq.wurstscript.ast.StringPos funcName() {
            if(_funcName == null)
                _funcName = de.peeeq.wurstscript.ast.AST.StringPos(this, _term.funcName(), 1);

            return _funcName;
        }

        public de.peeeq.wurstscript.ast.ExprFunctionCallPos replaceFuncName(java.lang.String funcName) {
            return replace(_term.replaceFuncName(funcName));
        }

        public de.peeeq.wurstscript.ast.ArgumentsPos args() {
            if(_args == null)
                _args = de.peeeq.wurstscript.ast.AST.ArgumentsPos(this, _term.args(), 2);

            return _args;
        }

        public de.peeeq.wurstscript.ast.ExprFunctionCallPos replaceArgs(de.peeeq.wurstscript.ast.Arguments args) {
            return replace(_term.replaceArgs(args));
        }

        Impl(KatjaNodePos<de.peeeq.wurstscript.ast.CompilationUnitPos, ?> parent, de.peeeq.wurstscript.ast.ExprFunctionCall term, int pos) {
            super(parent, term, pos);
        }

        public de.peeeq.wurstscript.ast.AST.TermPos<?> get(int i) {
            int ith = i;

            if(ith < 0) ith += 3;

            switch(ith) {
                case 0:
                    if(_source == null)
                        _source = AST.WPosPos(this, _term.source(), 0);
                    
                    return _source;
                case 1:
                    if(_funcName == null)
                        _funcName = AST.StringPos(this, _term.funcName(), 1);
                    
                    return _funcName;
                case 2:
                    if(_args == null)
                        _args = AST.ArgumentsPos(this, _term.args(), 2);
                    
                    return _args;
                default:
                    if(ith < 0) {
                        throw new IllegalArgumentException("get on sort ExprFunctionCallPos invoked with negative parameter "+i);
                    } else {
                        throw new IllegalArgumentException("get on sort ExprFunctionCallPos invoked with too large parameter "+i+", sort has only 3 components");
                    }
            }
        }

        public int size() {
            return 3;
        }

        public de.peeeq.wurstscript.ast.ExprFunctionCallPos replace(de.peeeq.wurstscript.ast.ExprFunctionCall term) {
            return (de.peeeq.wurstscript.ast.ExprFunctionCallPos) super.replace(term);
        }

        protected de.peeeq.wurstscript.ast.CompilationUnitPos freshRootPosition(KatjaSort term) {
            if(!(term instanceof de.peeeq.wurstscript.ast.CompilationUnit))
                throw new IllegalArgumentException("given term to replace root position has not the correct sort CompilationUnit");

            return AST.CompilationUnitPos((CompilationUnit) term);
        }

        public de.peeeq.wurstscript.ast.AST.NodePos<?> parent() {
            return (de.peeeq.wurstscript.ast.AST.NodePos<?>) super.parent();
        }

        public de.peeeq.wurstscript.ast.AST.SortPos lsib() {
            return (de.peeeq.wurstscript.ast.AST.SortPos) super.lsib();
        }

        public de.peeeq.wurstscript.ast.AST.SortPos rsib() {
            return (de.peeeq.wurstscript.ast.AST.SortPos) super.rsib();
        }

        public de.peeeq.wurstscript.ast.AST.SortPos preOrder() {
            return (de.peeeq.wurstscript.ast.AST.SortPos) super.preOrder();
        }

        public de.peeeq.wurstscript.ast.AST.SortPos preOrderSkip() {
            return (de.peeeq.wurstscript.ast.AST.SortPos) super.preOrderSkip();
        }

        public de.peeeq.wurstscript.ast.AST.SortPos postOrder() {
            return (de.peeeq.wurstscript.ast.AST.SortPos) super.postOrder();
        }

        public de.peeeq.wurstscript.ast.AST.SortPos postOrderStart() {
            return (de.peeeq.wurstscript.ast.AST.SortPos) super.postOrderStart();
        }

        public de.peeeq.wurstscript.ast.AST.SortPos follow(List<Integer> path) {
            return (de.peeeq.wurstscript.ast.AST.SortPos) super.follow(path);
        }

        public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.ExprPos.Switch<CT, E> switchClass) throws E {
            return switchClass.CaseExprFunctionCallPos(this);
        }

        public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.FuncRefPos.Switch<CT, E> switchClass) throws E {
            return switchClass.CaseExprFunctionCallPos(this);
        }

        public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.StmtCallPos.Switch<CT, E> switchClass) throws E {
            return switchClass.CaseExprFunctionCallPos(this);
        }

        public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.OptExprPos.Switch<CT, E> switchClass) throws E {
            return switchClass.CaseExprFunctionCallPos(this);
        }

        public <CT, E extends Throwable> CT Switch(de.peeeq.wurstscript.ast.WStatementPos.Switch<CT, E> switchClass) throws E {
            return switchClass.CaseExprFunctionCallPos(this);
        }

        public Appendable toJavaCode(Appendable builder) throws IOException {
            builder.append("AST.CompilationUnitPos");
            builder.append("( ");
            root().term().toJavaCode(builder);
            builder.append(" )");
            for(int pos : path()) builder.append(".get("+pos+")");

            return builder;
        }

        public Appendable toString(Appendable builder) throws IOException {
            term().toString(builder);
            builder.append("@CompilationUnit");
            for(int pos : path()) builder.append("."+pos);

            return builder;
        }

        public final String sortName() {
            return "ExprFunctionCallPos";
        }
    }
}
