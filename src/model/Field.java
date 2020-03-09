package model;

import mathobjects.Function;
import mathobjects.Vector;

public class Field {
	
	private Function[] functions;
	private int dim;
	
	public Field(int dimensions) {
		functions = new Function[dimensions];
		dim = dimensions;
	}
	
	public Vector vectorAt(double... location) {
		if(location.length != dim) return null;
		
		Vector result = new Vector(dim);
		
		for(int i = 0; i < dim; ++i) {
			if(functions[i] == null) 
				result.set(i,0);
			else
				result.set(i, functions[i].calculate(location));
		}
		
		return result;
	}
	
	public Vector vectorAt(Vector location) {
		return vectorAt(location.toArray());
	}
	
	public void setFunctions(Function... f) {
		if(f.length != dim) return;
		
		for(int i = 0; i < f.length; ++i) {
			functions[i] = f[i];
		}
	}
}
