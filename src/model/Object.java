package model;

import mathobjects.Function;
import mathobjects.Vector;

public class Object {
	
	private double[] properties; // various properties of the object
	private Vector velocity; // speed and direction
	private Vector position; // current position
	private Field field; // a single field associated with the object
	
	public Object(int dim, int numProp) {
		field = new Field(dim);
		properties = new double[numProp];
		velocity = new Vector(0,0);
		position = new Vector(0,0);
	}
	
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	public void setPosition(Vector position) {
		this.position = position;
	}

	public Vector getVelocity() {
		return this.velocity;
	}	
	public Vector getPosition() {
		return this.position;
	}

	public void setFieldFunctions(Function... f) {
		field.setFunctions(f);
	}
	
	public Vector getFieldForce(double... location) {
		return getFieldForce(new Vector(location));
	}
	
	public Vector getFieldForce(Vector location) {
		if(field == null)
			return new Vector(location.length());
		else
			return field.vectorAt(location);
	}
	
	public void setProperty(int index, double value) {
		properties[index] = value;
	}
	public double getProperty(int index) {
		return properties[index];
	}

}
