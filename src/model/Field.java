package model;

import mathobjects.DynamicFunction;
import mathobjects.Vector;

public class Field {
	
	private DynamicFunction[] functions;
	private int dim;
	
	public Field(int dimensions) {
		functions = new DynamicFunction[dimensions];
		dim = dimensions;
	}
	
	public Vector vectorAt(double time, double... location) {
		if(location.length != dim) return null;
		
		Vector result = new Vector(dim);
		
		for(int i = 0; i < dim; ++i) {
			if(functions[i] == null)
				result.set(i,0);
			else
				result.set(i, functions[i].calculate(time, location));
		}
		
		return result;
	}
	
	public Vector vectorAt(double time, Vector location) {
		return vectorAt(time, location.toArray());
	}
	
	public void setFunctions(DynamicFunction... f) {
		if(f.length != dim) return;
		
		for(int i = 0; i < f.length; ++i) {
			functions[i] = f[i];
		}
	}
}
