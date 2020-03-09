package driver;

import model.Space;

public class Driver {
	
	public static void main(String[] args) {
		
		// Field
		// Object
		// Function
		
		int dim = 2;
		int numFields = 1;
		int numObjects = 1;
		int numProp = 0;
		double step = 0.1;
		double max_accel = 1;
		Space space = new Space(dim, numFields, numObjects, numProp);
		space.setStepSize(step);
		space.setMaxAcceleration(max_accel);
		
		Script script = new Script();
		space.setObjectFieldFunctions(0, script, script);
	}
}
