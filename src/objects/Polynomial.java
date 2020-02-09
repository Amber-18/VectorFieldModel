package objects;

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
		this.coefficients = coefficients;
	}
	
	public double calculate(double input) {
		double output = 0;
		for(int i = 0; i < coefficients.length; ++i) {
			output += coefficients[i] * raiseTo(input, i);
		}
		
		return output;
	}
	
	private double raiseTo(double input, int exponent) {
		double value = 1;
		
		if(exponent == 0) return 1;
		
		if(exponent < 0) {
			return 1 / raiseTo(input, -1*exponent);
		}
		
		for(int i = 0; i < exponent; ++i) {
			value *= input;
		}
		
		return value;
	}

}
