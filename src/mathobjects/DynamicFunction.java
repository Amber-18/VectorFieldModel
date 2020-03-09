package mathobjects;

public abstract class DynamicFunction extends Function {
	
	protected double time;
	
	public DynamicFunction() {
		super(); // do nothing
	}
	
	public abstract double calculate(double time, double... location);

}
