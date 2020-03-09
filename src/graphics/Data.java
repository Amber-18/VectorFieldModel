package graphics;

import mathobjects.Polynomial;
import mathobjects.Vector;
import objects.Field;

public class Data {
	
	private Polynomial x_to_x;
	private Polynomial x_to_y;
	private Polynomial y_to_x;
	private Polynomial y_to_y;
	private int num_steps;
	private int duration;
	
	public Data() {
		
	}
	
	public void setPoly(String... poly) {
		// parse every string and convert to a polynomial
	}
	
	public void getPoly(Field field) {
		return;
	}
	
	public Vector getInitialPosition() {
		return new Vector();
	}
	
	public Vector getInitialVelocity() {
		return new Vector();
	}
	public double getStepSize() {
		return 0;
	}
	public int getNumStep() {
		return 0;
	}
	public double getMaxVelocity() {
		return 0;
	}
	public int getDuration() {
		return 0;
	}

}
