//generated by parseq
package de.peeeq.wurstscript.ast;

public interface SortPos {
	SortPos getParent();
	int size();
	SortPos get(int i);
}

interface SortPosIntern {
	void setParent(SortPos pos);
}
