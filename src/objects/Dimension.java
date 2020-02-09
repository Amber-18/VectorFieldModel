package objects;

public enum Dimension {
	
	X(0), Y(1), Z(2);
	
	private int dim;
	
	private Dimension(int dimension) {
		dim = dimension;
	}
	
	public int getDimension() {
		return dim;
	}

}
