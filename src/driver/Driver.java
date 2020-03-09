package driver;

import model.Space;

public class Driver {
	
	public static void main(String[] args) {
		
		// Field
		// Object
		// Function
		
		// allow functions to access time_elapsed as a total,
		// not a relative amount, this is to make fields dynamic
		
		// perhaps do this to make dynamic fields
		// in space.run(time_start, time_to_elapse)
		// give (time_start + elapsed_time) to functions to
		// allow them the current actual time
		// thus we have, space.run(0,40)
		// run the model starting at time 0 for 40 units of time
		// or space.run(40,41), start at time 40, go for 41 units of time
		
		// thus we can have dynamic fields
		
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
		
		double start_time = 0;
		double time_to_elapse = 1;
		space.run(start_time, time_to_elapse);
	}
}
