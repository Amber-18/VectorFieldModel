package tests;

import objects.Dimension;
import objects.Field;
import objects.Polynomial;
import objects.Vector;

public class FieldTest {
	
	public static void main(String[] args) {
		testField();
	}
	
	private static void testField() {
		Field field = new Field(2);
		
		Polynomial x_to_x = new Polynomial(0, 0);
		Polynomial x_to_y = new Polynomial(0, -2);
		Polynomial y_to_x = new Polynomial(0, 2);
		Polynomial y_to_y = new Polynomial(0, 0);
		
		field.addPolynomial(Dimension.X, Dimension.X, x_to_x);
		field.addPolynomial(Dimension.Y, Dimension.X, x_to_y);
		field.addPolynomial(Dimension.X, Dimension.Y, y_to_x);
		field.addPolynomial(Dimension.Y, Dimension.Y, y_to_y);
		
		
		print("Vector at location: ");
		
	}
	
	private static void print(Object obj) {
		System.out.println(obj);
	}

}
