package shapes;

public class Square extends Shapes{
	private double length;
	
	Square(double lengthIn){
		this.length = lengthIn;
	}
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return Math.pow(this.length, 2);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Square";
	}

}
