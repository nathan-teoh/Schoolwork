package shapes;

public abstract class Shapes implements Comparable<Shapes>{
	public abstract double getArea(); //all shapes should be able to return area
	
	public abstract String getName();

	@Override
	public int compareTo(Shapes objCompare) {
		if(objCompare == null) {
			throw new NullPointerException();
		}
		if(this.getName().substring(0,1).compareTo(objCompare.getName().substring(0,1))>0) {
			return 1;
		}else if(this.getName().substring(0,1).compareTo(objCompare.getName().substring(0,1))==0) {
			if(this.getArea() > objCompare.getArea()) {
				return 1;
			}else if(this.getArea() == objCompare.getArea()) {
				return 0;
			}else {
				return -1;
			}
		}else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return "Area of " + this.getName() + " is " + this.getArea();
	}
}
