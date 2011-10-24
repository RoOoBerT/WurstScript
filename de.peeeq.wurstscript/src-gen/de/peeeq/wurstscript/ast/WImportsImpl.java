//generated by parseq
package de.peeeq.wurstscript.ast;

class WImportsImpl extends WImports implements SortPosIntern {
 	private SortPos parent;
	public SortPos getParent() { return parent; }
	public void setParent(SortPos parent) {
		if (parent != null && this.parent != null) { 			throw new Error("Parent of " + this + " already set: " + this.parent + "\ntried to change to " + parent); 		}
		this.parent = parent;
	}

	protected void other_setParentToThis(WImport t) {
		((SortPosIntern) t).setParent(this);
	}
	protected void other_clearParent(WImport t) {
		((SortPosIntern) t).setParent(null);
	}
	@Override public void accept(WPackage.Visitor v) {
		for (WImport i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(CompilationUnit.Visitor v) {
		for (WImport i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(WScope.Visitor v) {
		for (WImport i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(TopLevelDeclaration.Visitor v) {
		for (WImport i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(WImports.Visitor v) {
		for (WImport i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(PackageOrGlobal.Visitor v) {
		for (WImport i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
}