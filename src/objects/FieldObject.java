package objects;

public class FieldObject {
	
	// TODO when implementing dynamic fields,
	// perhaps a dynamic field class will be needed
	// when measuring duration, duration will be 
	// number of steps
	
	private Vector velocity; // speed and direction
	private Vector position; // current position
	private Field[] fields; // fields that affect the object
	private double step; // when updating the position, how big  step?
	private int dimensions;
	
	public FieldObject(Field... fields) {
		
		if(fields == null) return;
		// we require at least 1 field for the object to reside in
		dimensions = fields[0].getDimensions();
		velocity = new Vector(dimensions);
		position = new Vector(dimensions);
		step = 0;
		this.fields = fields;
	}
	
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	/*
	 * Step must be between 0 and 1
	 */
	public void setStep(double step) {
		if(step < 0 || step > 1) 
			step = 0;
		else
			this.step = step;
	}
	
	/*
	 * This method updates the object's position and velocity with
	 * respect to the various field's this object is "in". 
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
	 * Scale new velocity by step
	 * Scaled new-velocity is new position
	 * 
	 */
	public void update() {
		if(step == 0) return;
		
		// Updating velocity:
		// Get the current velocity
		// Get all force-vectors from the fields
		// Sum all force-vectors
		// Scale the sum by the step
		// Add scaled-scum to current velocity
		// Now have new velocity
		
		Vector force = fields[0].vectorAt(this.position);
		for(int i = 1; i < fields.length; ++i) {
			force = Vector.add(force, fields[i].vectorAt(this.position));
		}
		
		
		force.scale(step);
		
		this.velocity = Vector.add(velocity, force);
		
		// Updating position:
		// Get new velocity
		// Scale new velocity by step
		// Add scaled new-velocity to position
		// Now have new position
		Vector new_velocity = new Vector(this.velocity);
		new_velocity.scale(step);
		this.position = Vector.add(new_velocity, this.position);
		
	}

	public Vector getPosition() {
		return this.position;
	}
	
	public Vector getVelocity() {
		return this.velocity;
	}
	
	public Vector getForce() {
		Vector force = fields[0].vectorAt(this.position);
		for(int i = 1; i < fields.length; ++i) {
			force = Vector.add(force, fields[i].vectorAt(this.position));
		}
		return force;
	}

}
