package shapes;

public class Rectangle extends Shapes{
	private double length, width;
	
	Rectangle(double lengthIn, double widthIn){
		this.length = lengthIn;
		this.width = widthIn;
	}
	
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return length * width;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Rectangle";
	}

}
