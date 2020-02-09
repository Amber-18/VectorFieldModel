package objects;

public class Field {
	
	private FieldPoly poly;
	private int dim;
	
	public Field(int dimensions) {
		poly = new FieldPoly(dimensions);
		dim = dimensions;
	}
	
	public int getDimensions() {
		return dim;
	}
	
	public void addPolynomial(int output_dim, int input_dim, Polynomial poly) {
		this.poly.addPolynomial(output_dim, input_dim, poly);
	}

	public void addPolynomial(Dimension output, Dimension input, Polynomial poly) {
		addPolynomial(output.getDimension(), input.getDimension(), poly);
	}
	
	public Vector vectorAt(double... location) {
		if(location.length != dim) return null;
		
		Vector result = new Vector(dim);
		
		for(int output_dim = 0; output_dim < dim; ++output_dim) {
			result.set(output_dim, poly.getValueFor(output_dim, location));
		}
		
		return result;
	}
	
	public Vector vectorAt(Vector location) {
		return vectorAt(location.toArray());
	}
	
	
	
	
	
	
	
	
}





//	
//	private PolyList[] systems;
//	private PolyList dynamic_systems;
//	private int dimensions;
//	private Vector origin;
//	private LinkedList<Field> fields = new LinkedList<Field>();
//	
//	public Field(int num_of_dimensions, int num_terms) {
//		dimensions = num_of_dimensions;
//		origin = new Vector(num_of_dimensions);
//		systems = new PolyList[num_of_dimensions];
//		
//		for(int i = 0; i < systems.length; ++i) {
//			systems[i] = new PolyList(num_of_dimensions);
//		}
//	}
//	
//	/**
//	 * Every entry of any specific vector in this field is described by a
//	 * system of equations
//	 * 
//	 * This equations can have multiple inputs
//	 * For example, given a 2d vector field, the x-value of any given
//	 * vector can be calculated using the function.
//	 * 
//	 * x = x^2 + y^2
//	 * 
//	 * This function to calculate the x-value of any vector in this vector
//	 * field has inputs from both the x-dimension and y-dimension;
//	 * The output dimension is also in the x-dimension. Take care
//	 * not to confuse input and output dimensions! Inputs can be thought
//	 * of as coordinates, while output the values of the resulting vector
//	 * along that dimension.
//	 * 
//	 * In this program, each polynomial is only associated with one input 
//	 * dimension and one output dimension. To input the above function
//	 * into the program, two separate polynomials will need to be created.
//	 * One polynomial for the input from the x-dimension, the other for 
//	 * the y-dimension. The results from every dimension's polynomial are
//	 * added together, as seen in the above function.
//	 * 
//	 * @param poly The function
//	 * @param input The dimension for the input of the function
//	 * @param output The dimension of the output of the function
//	 */
//	public void addPolynomial(SimplePoly poly, Dimension input, Dimension output) {
//		systems[output.value()].addPoly(input.value(), poly);
//	}
//	
//	public int getDimensions() {
//		return dimensions;
//	}
//	
//	public boolean setOrigin(double... coordinates) {
//		if(coordinates.length < dimensions) {
//			return false;
//		} else {
//			origin = new Vector(coordinates);
//			return true;
//		}
//	}
//	
//	public boolean setOrigin(Vector coordinates) {
//		if(coordinates.length() < dimensions) {
//			return false;
//		} else {
//			origin = coordinates.clone();
//			return true;
//		}
//	}
//	
//	public Vector getOrigin() {
//		return this.origin;
//	}
//	
//	public Vector vectorAt(Vector v) {
//		return vectorAt(v.toArray());
//	}
//	
//	/**
//	 * 
//	 * @param coordinates The coordinates of the vector to be returned
//	 * @return
//	 */
//	public Vector vectorAt(double... coordinates) {
//		if(coordinates.length < dimensions) {
//			return null;
//		} else {
//			
//			Vector main = new Vector(dimensions);
//			double value = 0;
//			double[] outputs;
//			
//			/**
//			 * for every dimension in the output vector
//			 * This loop goes through every dim of the vector at the 
//			 * given coordinates
//			 * 
//			 * It gets the systems of polynomials to calculate the 
//			 * double value of the vector along each dim
//			 * 
//			 * Each system of poly's is a group of polynomials that 
//			 * takes in the coordinates and outputs their own value
//			 * 
//			 * Every value is added together and put into the output
//			 * vector
//			 */
//			for(int dim = 0; dim < dimensions; ++dim) {
//				outputs = systems[dim].calculate(coordinates);
//				
//				for(int i = 0; i < outputs.length; ++i) {
//					value += outputs[i];
//				}
//				
//				main.set(dim, value);
//				value = 0;
//			}
//			
//			if(dynamic_systems != null) {
//				calculateDynamic(main);
//			}
//			
//			Field sub_field;
//			Vector sub_field_vector;
//			Vector result_vector_of_sub;
//			for(int field_index = 0; field_index < fields.size(); ++field_index) {
//				sub_field = fields.get(field_index);
//				sub_field_vector = (Vector)MatrixMath.
//						subtract(new Vector(coordinates), sub_field.getOrigin());
//				result_vector_of_sub = sub_field.vectorAt(sub_field_vector);
//				main = (Vector)MatrixMath.add(main, result_vector_of_sub);
//			}
//			
//			return main;
//		}
//	}
//	
//	/**
//	 * 
//	 * @param field The field
//	 * @param origin The origin of the given field in relation to this field's origin (0,0)
//	 * @return The index of the field if added to this Field, -1 otherwise
//	 */
//	public int addField(Field field, Vector origin) {
//		if(field.getDimensions() == dimensions) {
//			field.setOrigin(origin);
//			fields.add(field);
//			return fields.size();
//		} else {
//			return -1;
//		}
//	}
//	
//	public void makeDynamic() {
//		// need a dynamic equation for each dimension
//		// the x-dim changes by by some f(x) over time
//		
//		// a poly for every dimension
//		dynamic_systems = new PolyList(dimensions);
//		
//	}
//	
//	public void addDynamicSystem(Polynomial poly, Dimension x) {
//		dynamic_systems.addPoly(x.value(), poly);
//	}
//	
//	private void calculateDynamic(Vector main) {
//		for(int i = 0; i < main.length(); ++i) {
//			main.set(i, dynamic_systems.calculate(i, main.get(i)));
//		}
//	}
//
//}
