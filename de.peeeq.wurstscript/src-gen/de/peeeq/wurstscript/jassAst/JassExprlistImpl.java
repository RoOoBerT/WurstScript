//generated by parseq
package de.peeeq.wurstscript.jassAst;

class JassExprlistImpl extends JassExprlist implements JassAstElementIntern {
 	private JassAstElement parent;
	public JassAstElement getParent() { return parent; }
	public void setParent(JassAstElement parent) {
		if (parent != null && this.parent != null) { 			throw new Error("Parent of " + this + " already set: " + this.parent + "\ntried to change to " + parent); 		}
		this.parent = parent;
	}

	protected void other_setParentToThis(JassExpr t) {
		((JassAstElementIntern) t).setParent(this);
	}
	protected void other_clearParent(JassExpr t) {
		((JassAstElementIntern) t).setParent(null);
	}
	@Override public void accept(JassStatement.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassStmtCall.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassFunction.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassExprlist.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassExpr.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassStmtIf.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassStatements.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassStmtExitwhen.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassExprFunctionCall.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassStmtSetArray.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassExprBinary.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassExprUnary.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassExprVarArrayAccess.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassStmtLoop.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassStmtReturn.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassProg.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassStmtSet.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassFunctions.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassExprAtomic.Visitor v) {
		for (JassExpr i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public String toString() {
		String result =  "JassExprlist(";
		boolean first = true;
		for (JassExpr i : this ) {
			if (!first) { result +=", "; }
			if (result.length() > 1000) { result +="..."; break; }
			result += i;
			first = false;
		}
		result +=  ")";
		return result;
	}
}
