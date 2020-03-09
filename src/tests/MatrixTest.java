package tests;

import java.util.Arrays;

import mathobjects.Matrix;

public class MatrixTest {

	public static void main(String[] args) {
		
		Matrix matrix = Matrix.fromArray(new double[][] {
			{2, 0, 3},
			{4, 3, 6},
			{8, 2, 9}
		});
		
		testMatrixDimensions(matrix);
		testMatrixCopyAndModify(matrix);
		testMatixRowsAndColumns(matrix);
		

	}
	
	private static void testMatrixDimensions(Matrix a) {
		print("Matrix Test Height: " + a.getHeight());
		print("Matrix Test Width : " + a.getWidth());
		print("Dim of Matrix: " + Arrays.toString(a.getDimensions()));
	}
	
	private static void testMatrixCopyAndModify(Matrix a) {
		print("Matrix Test If Clone Is Copy: ");
		print("original matrix: ");
		print(a);
		Matrix b = a.clone();
		a.setEntry(a.getHeight()/2, a.getWidth()/2, 500);
		print("Matrix after modify: ");
		print(a);
		print("clone of the original matrix, before mods: ");
		print(b);
		print("if the orig equals the copy: " + a.equals(b));
	}
	
	
	private static void testMatixRowsAndColumns(Matrix a) {
		print("Matrix is: ");
		print(a);
		print("Test the getting of columns/rows: ");
		for(int i = 0; i < a.getWidth(); ++i) {
			print("column of a #" + i + " " + Arrays.toString(a.getColumn(i)));
		}
		for(int i = 0; i < a.getHeight(); ++i) {
			print("row of a #" + i + " " + Arrays.toString(a.getRow(i)));
		}
		
		for(int row_index = 0; row_index < a.getHeight(); ++row_index) {
			for(int col_index = 0; col_index < a.getWidth(); ++col_index) {
				print("index of a: (" + row_index + ", " + col_index + ") is " + a.getEntry(row_index, col_index));
				print("test if above also equals get method: " + a.get(row_index, col_index));
			}
		}	
		
	}
	
	public static void print(Object obj) {
		System.out.println(obj);
	}

}
