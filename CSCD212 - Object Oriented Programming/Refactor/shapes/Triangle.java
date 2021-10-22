package shapes;

public class Triangle extends Shapes{
	private double base, height;
	
	Triangle(double baseIn, double heightIn){
		this.base = baseIn;
		this.height = heightIn;
	}
	
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return base*height;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Triangle";
	}

}
