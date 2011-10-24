//generated by parseq
package de.peeeq.wurstscript.ast;

class WStatementsImpl extends WStatements implements SortPosIntern {
 	private SortPos parent;
	public SortPos getParent() { return parent; }
	public void setParent(SortPos parent) {
		if (parent != null && this.parent != null) { 			throw new Error("Parent of " + this + " already set: " + this.parent + "\ntried to change to " + parent); 		}
		this.parent = parent;
	}

	protected void other_setParentToThis(WStatement t) {
		((SortPosIntern) t).setParent(this);
	}
	protected void other_clearParent(WStatement t) {
		((SortPosIntern) t).setParent(null);
	}
	@Override public void accept(WEntities.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(WPackage.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(StmtIf.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(WStatements.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(StmtWhile.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(ClassMember.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(CompilationUnit.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(InitBlock.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(ClassDef.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(WScope.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(FunctionDefinition.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(FuncDef.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(TopLevelDeclaration.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(WEntity.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(ConstructorDef.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(WStatement.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassToplevelDeclaration.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(TypeDef.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(OnDestroyDef.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(ClassSlots.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(ClassSlot.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(StmtLoop.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(PackageOrGlobal.Visitor v) {
		for (WStatement i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
}