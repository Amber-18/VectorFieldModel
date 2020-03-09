package model;

import math.BasicMath;
import mathobjects.Function;
import mathobjects.Vector;

public class Space {
	
	private int dim;
	private double step_size; // when updating the position, how big step?
	private double max_accel = 1; // if things go fast, step may not be enough
	
	private Object[] objects;
	private Field[] fields;
	
	public Space(int dimensions, int numFields, int numObjects, int numProp) {
		dim = dimensions;
		step_size = 0.1;
		max_accel = 1;
		objects = new Object[numObjects];
		fields = new Field[numFields];
		
		for(int i = 0; i < numFields; ++i) {
			fields[i] = new Field(dim);
		}
		
		for(int i = 0; i < numFields; ++i) {
			objects[i] = new Object(dim, numProp);
		}
		
		
	}
	
	public void setFieldFunctions(int index, Function... f) {
		if(f.length != dim) return;
		
		for(int i = 0; i < f.length; ++i) {
			f[i].setVars(objects, fields);
		}
		
		fields[index].setFunctions(f);
		
	}
	public void setObjectFieldFunctions(int obj, Function... f) {
		if(f.length != dim) return;
		
		for(int i = 0; i < f.length; ++i) {
			f[i].setVars(objects, fields);
		}
		
		objects[obj].setFieldFunctions(f);
	}
	
	public void setObjectPosVel(int i, Vector ip, Vector iv) {
		objects[i].setPosition(ip);
		objects[i].setVelocity(ip);
	}
	public void setObjectProperty(int obj, int prop, double value) {
		objects[obj].setProperty(prop, value);
	}
	
	public void setStepSize(double x) {
		if(x < 0 || x > 1) 
			return;
		else
			step_size = x;
		
	}
	public void setMaxAcceleration(double x) {
		if(x > 0) 
			max_accel = x;
	}
	
	/*
	 * Want to run the simulation for a given period of time
	 * 
	 * Take small steps (step_size) for each objects until
	 * the elapsed_time is equal to the given time
	 * 
	 * Each step will be less than or equal to step_size
	 * Thus "step", or run-again, the update() method until
	 * time_elapse == time_given
	 * 
	 * Ex.
	 * Given: Step_size = 0.1, Time = 1
	 * We want to run the model for 1 unit of time taking steps of 0.1
	 * How many steps for time? Time = (step_size)*(num_of_iter)
	 * (1)=(0.1)*(10); Thus, run the algorithm for 10 times
	 * 
	 * But each step isn't the same, perhaps the step is only 0.05
	 * Thus increment elapsed time by 0.05, instead of 0.1
	 * Keep count of actually elapsed_time until you reach the given time
	 * 
	 */
	public void runFor(double time) {
		
		double time_elapsed = 0; // start with time_elapsed = 0;
		// until time_elapsed is "basically" equal to time
		while(time_elapsed < time) {
			
			// if the remaining time is greater than step size
			if((time-time_elapsed) > step_size) {
				// update with step size and add the actual step taken to t_e
				time_elapsed += update(step_size);
				
			} else {
				// if the remaining time is not greater, then update with
				// whatever distance/time is left and again add the actual
				time_elapsed += update(time-time_elapsed);
			}
		}
		
	}
	
	/* This method updates each object's position and velocity with
	* respect to the various field's in this space
	* 
	* Updating velocity: 
	* Get the current velocity
	* Get all force-vectors from the fields
	* Sum all force-vectors
	* Scale the sum by the step
	* Add scaled-scum to current velocity
	* Now have new velocity
	* 
	* Updating position:
	* Get new velocity
	* Scale new velocity by step_size
	* Add scaled new-velocity to position
	* Now have new position
	* 
	*/
	private double update(double step_size) {
				
		// each object will be going diff speeds
		// we will have a max acceleration of some number
		// we need to make sure that the proposed _changes_
		// to each object's speed (the acceleration) is not greater 
		// than the max accel(max change in speed)
		// if it is greater than this then:
		// scale the step so that the proposed acceleration is 
		// less than the max_acceleration
		// then take this new step, apply it to all objects
		
		// we will add the size of each step (always 0 < {step} < 1)
		// to the "total elapsed time" and do this until the 
		// "total elapsed time" is equal to the given time
		
		// 1. Get the proposed accelerations to each object
		// 2. Check all proposed accelerations and update step_size
		// 3. Apply accelerations w/ the new step_size
		// 4. Update "total elapsed time"
		
		
		// Get the proposed accelerations for each object
		Vector[] prop_accel = new Vector[objects.length];
		int max_prop_accel = -1; // for keeping track of the 
		// biggest prop_accel that is greater than max_accel
		for(int i = 0; i < objects.length; ++i) {
			prop_accel[i] = getForce(objects[i].getPosition());
			if(BasicMath.size(prop_accel[i]) > max_accel) {
				if(max_prop_accel == -1) {
					max_prop_accel = i;
				} else {
					double curr_max_size = BasicMath.size(prop_accel[max_prop_accel]);
					double prop_max_size = BasicMath.size(prop_accel[i]);
					if(prop_max_size > curr_max_size) {
						max_prop_accel = i;
					} // else do nothing
				}
			}
		}
		
		// update step_size (if necessary)
		if(max_prop_accel != -1) {
			// how to scale step size to make all prop_accel < max_accel?
			// multiply the biggest prop_accel by some factor X such that
			// max_accel = prop*(X), X = step_size
			// step_size = max_accel / prop_accel = max / size
			double size = BasicMath.size(prop_accel[max_prop_accel]);
			step_size = max_accel / size;
		}
		
		// update each object's velocity and position 
		// using the new step_size and the prop_accel
		
		// Updating velocity:
		// Get the current velocity
		// Get all force-vectors from all the fields {getForce(loc)}
		// Scale the force by the step_size
		// Add scaled-force to current velocity
		// Now have new velocity
		for(int i = 0; i < objects.length; ++i) {
			// scale each prop_accel by the step_size
			prop_accel[i].scale(step_size);
			// add scaled-force to the object's velocity
			objects[i].getVelocity().add(prop_accel[i]);
		}
		
		// Updating position:
		// Get new velocity
		// Scale new velocity by step_size
		// Add scaled new-velocity to position
		// Now have new position
		Vector new_velocity;
		for(int i = 0; i < objects.length; ++i) {
			new_velocity = objects[i].getVelocity().clone();
			new_velocity.scale(step_size);
			objects[i].getPosition().add(new_velocity);
		}
		
		return step_size;
		
		
	}
	
	/*
	 * Return the force (as a Vector) at the 
	 * given location
	 * 
	 * Automatically takes into account fields of objects
	 * and the multiple fields in this space
	 */
	private Vector getForce(Vector location) {
		Vector force = new Vector(dim);
		
		Vector obj_position;
		for(int i = 0; i < objects.length; ++i) {
			// get force from each field in objects
			// need a location w/ respect to each object
			obj_position = objects[i].getPosition().clone();
			obj_position.scale(-1);
			force.add(objects[i].getFieldForce(Vector.add(obj_position, location)));
		}
		
		// now have the forces from each of the object's fields
		// now need to get the forces from of the fields in the space
		// then return the final sum, the final resulting force vector
		
		for(int i = 0; i < fields.length; ++i) {
			force.add(fields[i].vectorAt(location));
		}
		
		return force;
	}
}
