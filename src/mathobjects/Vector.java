package mathobjects;

import math.MatrixMath;

public class Vector extends Matrix {
	
	public Vector(int length) {
		super(length, 1);
	}
	
	public Vector(Vector v) {
		super(to2d(v.toArray()));
	}
	
	public Vector(double... array) {
		super(to2d(array));
	}
	
	private static double[][] to2d(double[] array){
		double[][] temp = new double[array.length][1];
		
		for(int index = 0; index < temp.length; ++index) {
			temp[index][0] = array[index];
		}
		
		return temp;
	}
	
	public int length() {
		return this.getHeight();
	}
	
	public void set(int index, double value) {
		set(index, 0, value);
	}
	
	public double get(int index) {
		return get(index, 0);
	}
	
	public double[] toArray() {
		return this.getColumn(0);
	}
	
	public Vector clone() {
		return new Vector(this);
	}
	
	public void add(Vector a) {
		if(!MatrixMath.hasSameDimensions(this, a)) return;
		double value = 0;
		for(int i = 0; i < this.length(); ++i) {
			value = this.get(i) + a.get(i);
			this.set(i, value);
		}
	}
	
	public static Vector add(Vector a, Vector b) {
		if(!MatrixMath.hasSameDimensions(a, b)) return null;
		
		
		Vector result = new Vector(a.length());
		double value = 0;
		for(int i = 0; i < a.length(); ++i) {
			value = a.get(i) + b.get(i);
			result.set(i, value);
		}
		
		return result;
	}
	
	public void scale(double scalar) {
		MatrixMath.scalarMult(this, scalar);
	}
}
