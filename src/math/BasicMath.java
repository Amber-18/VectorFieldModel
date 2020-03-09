package math;

import mathobjects.Vector;

public class BasicMath {
	
	public static double size(Vector v) {
		double sum = 0;
		for(int i = 0; i < v.length(); ++i) {
			sum = sum + (v.get(i) * v.get(i));
		}
		return sqrt(sum, 0.0001);
	}
	
	public static double sqrt(double x, double acc) {
		
		if(x == 0) return 0;
	    if(x < 0) return Double.NaN;
	    
	    // this for loop finds the first integer i such that ii > x
	    // basically, the first i such that ii is no longer < x
	    double root = 1;
	    double x_est = 1;
	    while(x_est < x) {
	    	++root;
	    	x_est = root * root;
	    }
	    
	    // we now have the first integer i such that ii > x
	    // we will now decrement i until ii < x
	    while(x_est > x) {
	      root = root - acc;
	      x_est = root*root;
	    }
	    
	    // basically, this whole functions works like this
	    // find the "ceiling" on the root, the root can be no bigger
	    // than this ceiling. This ceiling-root is an integer
	    
	    // we will then take this integer which decrement by a small
	    // amount, like 0.1 until we can find a double-type square root
	    // that approximates the actual square root

	    return root;
	}

	public static double raiseTo(double input, int exponent) {
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
