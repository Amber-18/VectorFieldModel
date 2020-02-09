package objects;

import java.util.Arrays;

public class Matrix {
	
	private double[][] array;
	private int height;
	private int width;
	
	public Matrix() {
		this.array = new double[0][0];
		this.height = 0;
		this.width = 0;
	}
	
	public Matrix(int height, int width) {
		this.array = new double[height][width];
		this.height = height;
		this.width = width;
	}
	
	public Matrix(Matrix matrix) {
		this.height = matrix.getHeight();
		this.width = matrix.getWidth();
		
		double[][] old_array = matrix.getArray();
		this.array = new double[old_array.length][old_array[0].length];
		for(int array = 0; array < old_array.length; ++array) {
			this.array[array] = old_array[array].clone();
		}
	}
	
	public static Matrix fromArray(double[][] array) {
		int width = array[0].length;
		for(int row_index = 0; row_index < array.length; ++row_index) {
			if(width != array[row_index].length) {
				return null;
			} else {
				width = array[row_index].length;
			}
		}
		
		return new Matrix(array);
	}
	
	protected Matrix(double[][] array) {
		this.height = array.length;
		this.width = array[0].length;
		this.array = array;
	}
	
	public Matrix clone() {
		return new Matrix(this);
	}
	
	public static Matrix getZeroMatrix(int height, int width) {
		return new Matrix(height, width);
	}
	
	public static Matrix getZeroMatrix(Matrix matrix) {
		return getZeroMatrix(matrix.getHeight(), matrix.getWidth());
	}
	
	public double[][] getArray(){
		return this.array;
	}
	
	public double[][] getArrayClone(){
		
		double[][] new_array = new double[array.length][array[0].length];
		for(int index = 0; index < array.length; ++index) {
			new_array[index] = array[index].clone();
		}
		
		return new_array;
	}
	
	public double[] getRow(int row_index) {
		try {
			return array[row_index].clone();
		} catch(Exception e) {
			return null;
		}
		
	}
	
	public double[] getColumn(int column_index){
		try {
			double[] column = new double[height];
			for(int i = 0; i < this.height; ++i) {
				column[i] = array[i][column_index];
			}
		
			return column;
		} catch(Exception e) {
			return null;
		}
		
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int[] getDimensions() {
		return new int[] {height, width};
	}
	
	public boolean set(int row_index, int column_index, double value) {
		return this.setEntry(row_index, column_index, value);
	}
	
	public boolean setEntry(int row_index, int column_index, double value) {
		try {
			this.array[row_index][column_index] = value;
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public double getEntry(int row_index, int column_index) {
		try {
			return this.array[row_index][column_index];
		} catch(Exception e) {
			e.printStackTrace();
			return Double.NaN;
		}
	}
	
	public double get(int row_index, int column_index) {
		return this.getEntry(row_index, column_index);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < array.length; ++i) {
			sb.append(Arrays.toString(array[i]));
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
	public void transpose() {
		int temp = this.width;
		this.width = this.height;
		this.height = temp;
		
		double[][] new_array = new double[height][width];
		
		for(int row = 0; row < new_array[0].length; ++row) {
			new_array[row] = this.getColumn(row);	
		}
		
		this.array = new_array;
	}

}
