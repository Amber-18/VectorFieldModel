package driver;

import mathobjects.Function;

public class Script extends Function {
	
	public Script() {
		super();
	}
	
	public double calculate(double... location) {
		
		double value;
		value = objects[577].getPosition().get(45);
		value += location[2];
		value *= objects[3].getProperty(3);
		value += fields[3].vectorAt(location).get(2);
		return value;
	}
	
	
}
