package math;

import mathobjects.Matrix;
import mathobjects.Vector;

public class MatrixMath {
	
	public static boolean hasSameDimensions(Matrix a, Matrix b) {
		boolean has_sd = 
				a.getHeight() == b.getHeight() && 
				a.getWidth() == b.getWidth();
		return has_sd;
	}
	
	public static boolean hasDimForMult(Matrix a, Matrix b) {
		boolean can_mult = 
				a.getHeight() == b.getWidth() &&
				a.getWidth() == b.getHeight();
		return can_mult;
	}
	
	public static boolean isSquare(Matrix a) {
		return a.getHeight() == a.getWidth();
	}
	
	public static Matrix add(Matrix a, Matrix b) {
		if(!hasSameDimensions(a, b)) {
			return null;
		}
		
		Matrix sum = new Matrix(a.getHeight(), a.getWidth());
		double value;
		
		for(int row_index = 0; row_index < a.getHeight(); ++row_index) {
			double[] a_row = a.getRow(row_index);
			double[] b_row = b.getRow(row_index);
			
			for(int column_index = 0; column_index < a.getWidth(); ++column_index) {
				value = a_row[column_index] + b_row[column_index];
				sum.setEntry(row_index, column_index, value);
				value = 0;
			}
		}
		
		return sum;
	}
	
	public static Matrix subtract(Matrix a, Matrix b) {
		negate(b);
		return MatrixMath.add(a, b);
	}
	
	public static Matrix multiply(Matrix a, Matrix b) {
		if(!hasDimForMult(a, b)) {
			return null;
		}
		
		Matrix product = new Matrix(a.getHeight(), b.getWidth());
		double value;
		double[] a_row;
		double[] b_col;
		for(int row_index = 0; row_index < a.getHeight(); ++row_index) {
			a_row = a.getRow(row_index);
			
			for(int column_index = 0; column_index < b.getWidth(); ++column_index) {
				b_col = b.getColumn(column_index);
				value = dotProduct(new Vector(a_row), new Vector(b_col));
				product.setEntry(row_index, column_index, value);
			}
		}
		
		return product;
		
	}
	
	public static double dotProduct(Vector a, Vector b) {
		if(!hasSameDimensions(a, b)) {
			return Double.NaN;
		}
		
		double product = 0;
		for(int index = 0; index < a.getHeight(); ++index) {
			product += a.getEntry(index, 0) * b.getEntry(index, 0);
		}
		
		return product;
	}
	
	public static Vector crossProduct(Vector a, Vector b) {
		if(!hasSameDimensions(a, b)) {
			return null;
		}
		// Vector product = new Vector(a.length());
		return null;
	}
	
	public static double determinant(Matrix matrix) {
		double determinant = 0;
		
		if(!isSquare(matrix)) {
			return Double.NaN;
		}
		
		if(matrix.getHeight() == 2 && matrix.getWidth() == 2) {
			determinant = 
					matrix.get(0, 0) * matrix.get(1, 1) - 
					matrix.get(0, 1) * matrix.get(1, 0);
			return determinant;
		} else {
			int iterations = matrix.getWidth();
			int sign = -1;
			for(int index = 0; index < iterations; ++index) {
				sign *= -1;
				Matrix temp = getMinor(matrix, 0, index);
				determinant += sign * matrix.get(0, index) * determinant(temp);
			}
			return determinant;
			
		}
	}
	
	public static Matrix getIdentity(int dimensions) {
		Matrix identity = new Matrix(dimensions, dimensions);
		int row = 0;
		int col = 0;
		while(row < dimensions) {
			identity.set(row, col, 1);
			++row;
			++col;
		}
		
		return identity;
	}
	
	public static Matrix getMinor(Matrix matrix, int row_to_skip, int col_to_skip) {
		Matrix temp = new Matrix(matrix.getHeight()-1, matrix.getWidth()-1);
		double value;
		int new_col_index;
		int new_row_index = 0;
		
		for(int old_row_index = 0; old_row_index < matrix.getHeight(); ++old_row_index) {
			
			if(old_row_index == row_to_skip) {
				continue;
			} else {
				new_col_index = 0;
			
				for(int old_col_index = 0; old_col_index < matrix.getWidth(); ++old_col_index) {
					if(old_col_index == col_to_skip) {
						continue;
					} else {
						value = matrix.get(old_row_index, old_col_index);
						temp.setEntry(new_row_index, new_col_index, value);
						++new_col_index;
					}
				}
				++new_row_index;
			}
			
			
		}
		
		return temp;
	}
	
	public static void scalarMult(Matrix matrix, double scalar) {
		double value;
		for(int row_index = 0; row_index < matrix.getHeight(); row_index++) {
			for(int col_index = 0; col_index < matrix.getWidth(); col_index++) {
				value = matrix.get(row_index, col_index) * scalar;
				matrix.set(row_index, col_index, value);
			}
		}
	}
	
	public static void negate(Matrix a) {
		MatrixMath.scalarMult(a, -1);
	}
	
	public static void vectorInverse(Vector v) {
		scalarMult(v, -1.0);
	}
	
	public static Matrix transpose(Matrix matrix) {
		Matrix transposed_copy = new Matrix(matrix);
		transposed_copy.transpose();
		return transposed_copy;
	}

}
