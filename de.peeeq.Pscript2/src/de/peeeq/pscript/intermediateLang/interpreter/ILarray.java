package de.peeeq.pscript.intermediateLang.interpreter;

import java.util.Vector;

import de.peeeq.pscript.intermediateLang.ILconst;
import de.peeeq.pscript.types.PscriptType;

public class ILarray extends ILconst {

	private static final int maxsize = 8191;
	Vector<ILconst> values = new Vector<ILconst>(16);
	PscriptType type;
	
	public ILarray(PscriptType pscriptType) {
		this.type = pscriptType;
	}
	
	
	public void set(int index, ILconst value) {
		if (index < 0 || index >= maxsize) {
			throw new Error("Array index out of bounds: " + index);
		}
		if (index >= values.size()) {
			values.setSize(index+1);
		}
		values.set(index, value);
	}
	
	public ILconst get(int index) {
		if (index < 0 || index >= maxsize) {
			throw new Error("Array index out of bounds: " + index);
		}
		ILconst result = null;
		if (index < values.size()) {
			result = values.get(index);
		} 
		if (result == null) {
			// TODO return default value for arraytype
		}		
		return result;
	}
	
	
	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (ILconst c : values) {
			if (!first) {
				sb.append(", ");
				first = false;
			}
			sb.append(c);
		}
		return "array(" + sb + ")";
	}

}