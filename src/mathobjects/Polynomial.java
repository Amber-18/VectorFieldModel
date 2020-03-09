package mathobjects;

import math.BasicMath;

public class Polynomial {
	
	private double[] coefficients;
	
	/**
	 * Input the coefficients as such:
	 * The leftmost coefficient is multiplied by x^0
	 * The next is multiplied by x^1, and the next x^2, etc.
	 * Do not skip any coefficients
	 * If any are equal to zero, input zero
	 * **/
	public Polynomial(double... coefficients) {
		super();
		this.coefficients = coefficients;
	}
	
	public double calculate(double input) {
		double output = 0;
		for(int i = 0; i < coefficients.length; ++i) {
			output += coefficients[i] * BasicMath.raiseTo(input, i);
		}
		
		return output;
	}



}
