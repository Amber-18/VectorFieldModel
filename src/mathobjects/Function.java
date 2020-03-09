package mathobjects;

import model.Field;
import model.Object;

public abstract class Function {
	
	// access to
	// list of objects and properties in them
	// location
	// all fields
	
	protected Object[] objects;
	protected Field[] fields;
	
	public Function() {
		// empty
	}
	
	public void setVars(Object[] o, Field[] f) {
		objects = o;
		fields = f;
	}
	
	public abstract double calculate(double... location);
}
