//generated by parseq
package de.peeeq.wurstscript.ast;

class NoTypeExprImpl implements NoTypeExpr, SortPosIntern {
	NoTypeExprImpl() {
	}

	private SortPos parent;
	public SortPos getParent() { return parent; }
	public void setParent(SortPos parent) {
		if (parent != null && this.parent != null) { 			throw new Error("Parent of " + this + " already set: " + this.parent + "\ntried to change to " + parent); 		}
		this.parent = parent;
	}

	public SortPos get(int i) {
		switch (i) {
			default: throw new IllegalArgumentException("Index out of range: " + i);
		}
	}
	public int size() {
		return 0;
	}
	public NoTypeExpr copy() {
		return new NoTypeExprImpl();
	}
	@Override public void accept(WEntities.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(WPackage.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(StmtIf.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(NativeFunc.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(WStatements.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(NativeType.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(StmtWhile.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(ClassMember.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(CompilationUnit.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(GlobalVarDef.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(NoTypeExpr.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(InitBlock.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(ClassDef.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(LocalVarDef.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(WScope.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(FunctionDefinition.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(JassGlobalBlock.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(FuncDef.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(OptTypeExpr.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(TopLevelDeclaration.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(WEntity.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(ConstructorDef.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(WStatement.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(JassToplevelDeclaration.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(TypeDef.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(OnDestroyDef.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(VarDef.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(ClassSlots.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(FuncSignature.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(ClassSlot.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(StmtLoop.Visitor v) {
		v.visit(this);
	}
	@Override public void accept(PackageOrGlobal.Visitor v) {
		v.visit(this);
	}
	@Override public <T> T match(OptTypeExpr.Matcher<T> matcher) {
		return matcher.case_NoTypeExpr(this);
	}
	@Override public void match(OptTypeExpr.MatcherVoid matcher) {
		matcher.case_NoTypeExpr(this);
	}

}