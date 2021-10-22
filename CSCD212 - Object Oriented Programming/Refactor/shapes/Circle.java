package shapes;

public class Circle extends Shapes{
	private double radius;
	
	Circle(double radiusIn) { //package level visibility
		this.radius = radiusIn;
	}
	
	@Override
	public double getArea() {
		return Math.PI * Math.pow(this.radius, 2);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Circle";
	}

}
