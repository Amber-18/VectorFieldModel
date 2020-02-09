package objects;

public class FieldPoly {
	
	private Polynomial[] poly;
	private int dim; // number of dimensions of the Field
	
	/*
	 * This class is only to be used by a Field
	 * Field's have a vector associated with every point
	 * in the field. The dimensions of the field and
	 * the dimensions of the vectors are the same. However,
	 * the values of a vector along a particular dimension can depend
	 * on the location of that vector in the field. 
	 * 
	 * That is to say this, for example: The x-value of any
	 * particular vector in the field can depend on the 
	 * x and/or y and/or z variables (etc) that describe the
	 * location of the vector. Thus we can find that to describe
	 * the value of a vector, we may need equations that looks
	 * like so: x = x+y+z; y = x+z; z = x+y;
	 * 
	 * Thus, it is necessary for every dimension in the Field, for every
	 * dimensions that any particular vector has, the number of polynomials
	 * that are to be used to describe that vector is equal to the 
	 * number of dimensions in the Field. 
	 * 
	 * Therefore, if we have X-dimensions, we need X*X polynomials;
	 * 
	 * This class allows abstraction from the particular storage and access
	 * particulars when the poly's are being used in the Field class
	 */
	public FieldPoly(int dim) {
		this.dim = dim;
		this.poly = new Polynomial[dim * dim];
	}
	
	private int getLocationInArray(int output_dim, int input_dim) {
		// the polynomials are stored like such:
		// The first dim-num of polynomials have an output for dimension 0
		// the second dim-num of poly's have output for dim 1
		// each poly in each block of output-dim poly's is associated with a 
		// specific input dimension
		// the first poly has input dim 0, the second has input dim 1
		// until you get to poly with input-dim X and then the next block of
		// poly's with a different output-dim is in the array
		return (output_dim * dim) + input_dim;
	}
	
	/*
	 * Note that equations can several various forms:
	 * x = x+y+z; y = x+z; z = x+y;
	 * 
	 * Thus, it is necessary to specify the output and input dimensions
	 * when adding the Polynomial
	 * 
	 * @param input_dim The specific location of the vector along the dim
	 * @param output_dim The value of the vector along the dim
	 */
	public void addPolynomial(int output_dim, int input_dim, Polynomial poly) {
		this.poly[getLocationInArray(output_dim, input_dim)] = poly;
		
	}
	
	/*
	 * Make sure inputs.length is equal to number of dimensions and 
	 * that the inputs are entered from first dimension to last
	 */
	public double getValueFor(int output_dim, double... inputs) {
		if(inputs.length != dim) {
			System.out.println("Error retrieving value in FieldPoly");
			return Double.NaN;
		}
		
		double value = 0;
		
		for(int i = 0; i < dim; ++i) {
			value += poly[getLocationInArray(output_dim, i)].calculate(inputs[i]);
		}
		
		return value;
	}
}
