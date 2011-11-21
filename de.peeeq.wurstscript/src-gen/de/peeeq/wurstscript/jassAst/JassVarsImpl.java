//generated by parseq
package de.peeeq.wurstscript.jassAst;

class JassVarsImpl extends JassVars implements JassAstElementIntern {
 	private JassAstElement parent;
	public JassAstElement getParent() { return parent; }
	public void setParent(JassAstElement parent) {
		if (parent != null && this.parent != null) { 			throw new Error("Parent of " + this + " already set: " + this.parent + "\ntried to change to " + parent); 		}
		this.parent = parent;
	}

	protected void other_setParentToThis(JassVar t) {
		((JassAstElementIntern) t).setParent(this);
	}
	protected void other_clearParent(JassVar t) {
		((JassAstElementIntern) t).setParent(null);
	}
	@Override public void accept(JassFunction.Visitor v) {
		for (JassVar i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassVars.Visitor v) {
		for (JassVar i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassProg.Visitor v) {
		for (JassVar i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public void accept(JassFunctions.Visitor v) {
		for (JassVar i : this ) {
			i.accept(v);
		}
		v.visit(this);
	}
	@Override public String toString() {
		String result =  "JassVars(";
		boolean first = true;
		for (JassVar i : this ) {
			if (!first) { result +=", "; }
			if (result.length() > 1000) { result +="..."; break; }
			result += i;
			first = false;
		}
		result +=  ")";
		return result;
	}
}
