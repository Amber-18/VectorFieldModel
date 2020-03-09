package model;

public abstract class FieldFunction {
	
	// need access to
	// list of objects and properties in them
	// location
	// time
	// all fields
	protected double time;
	protected Object[] objects;
	protected Field[] fields;
	
	public FieldFunction() {
		// do nothing
	}
	
	public void setVars(Object[] o, Field[] f) {
		objects = o;
		fields = f;
	}
	
	public abstract double calculate(double time, double... location);

}
