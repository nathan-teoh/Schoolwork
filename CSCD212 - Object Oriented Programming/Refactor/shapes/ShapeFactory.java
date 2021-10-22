package shapes;

public class ShapeFactory{	//singleton pattern. to me it felt like we should only have one factory.
	private static ShapeFactory factory = null;
	
	private ShapeFactory() {
		
	}
	
	public static ShapeFactory getFactory() {
		if (factory == null) {
			factory = new ShapeFactory();
		}
		return factory;
	}
	
	public Circle createCircle(double radiusIn) {
		return new Circle(radiusIn);
	}
	
	public Square createSquare(double lengthIn) {
		return new Square(lengthIn);
	}
	
	public Rectangle createRectangle(double lengthIn,  double widthIn) {
		return new Rectangle(lengthIn,widthIn);
	}
	
	public Triangle createTriangle(double base, double height) {
		return new Triangle(base,height);
	}

}
