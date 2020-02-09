package tests;

import objects.Matrix;
import objects.Vector;
import java.util.Arrays;

import math.MatrixMath;

public class MatrixMathTest {

	public static void main(String[] args) {
		
		Matrix a = Matrix.fromArray(new double[][] {
			{2, 0, 3},
			{4, 3, 6},
			{8, 2, 9}
		});
		
		Matrix b = Matrix.fromArray(new double[][] {
			{4, 1, 4},
			{2, 5, 8},
			{3, 6, 0}
		});
		
		Matrix c = Matrix.fromArray(new double[][] {
			{3, 2},
			{4, 5}
		});
		
		Matrix d = Matrix.fromArray(new double[][] {
			{1, 4},
			{9, 7}
		});
		
		Vector v = new Vector(new double[] {1, 2, 3});
		Vector z = new Vector(new double[] {4, 5, 6});
		
		testMatrixMath(a, b);
		testMatrixMath(a, c);
		testMatrixMath(a, v);
		testMatrixMath(v, z);

	}
	
	public static void testMatrixMath(Matrix a, Matrix b) {
		print("Matrix A");
		print(a);
		print("Matrix B");
		print(b);
		
		print("");
		
		print("A + B");
		Matrix temp = MatrixMath.add(a, b);
		print(temp);
		
		print("determinant of A");
		print(MatrixMath.determinant(a));
		print("determinant of B");
		print(MatrixMath.determinant(b));
		
		print("Dim of A: " + Arrays.toString(a.getDimensions()));
		print("Dim of B: " + Arrays.toString(b.getDimensions()));
		print("Has Same Dim: ");
		print(MatrixMath.hasSameDimensions(a, b));
		print("Has Dim For Mult: ");
		print(MatrixMath.hasDimForMult(a, b));
		print("is square for Matrix A: ");
		print(MatrixMath.isSquare(a));
		print("is square for Matrix B: ");
		print(MatrixMath.isSquare(b));
		
		print("Mult: A * B: ");
		print(MatrixMath.multiply(a, b));
		
		print("Mult: B * A: ");
		print(MatrixMath.multiply(b, a));
		
		Vector v = new Vector(a.getRow(0));
		Vector z = new Vector(b.getRow(0));
		
		print("Vector V: ");
		print(v);
		print("Vector Z: ");
		print(z);
		
		print("Dot product V * Z");
		print(MatrixMath.dotProduct(v, z));
		
		print("Dot product Z * V");
		print(MatrixMath.dotProduct(z, v));
		
		print("");
		print("Matrix A:");
		print(a);
		
		for(int i = 0; i < a.getWidth(); ++i) {
			for(int j = 0; j < a.getHeight(); ++j) {
				print("Minor of a: ");
				print("row: " + i + " col: " + j);
				print(MatrixMath.getMinor(a, i, j));
			}
		}
		
	}
	
	public static void print(Object obj) {
		System.out.println(obj);
	}

}
