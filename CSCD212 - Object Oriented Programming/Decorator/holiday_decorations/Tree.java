package holiday_decorations;

public abstract class Tree {	//chose tree instead of HolidayDecoration as we are only dealing with trees in this assignment
	public abstract double Cost();	//every tree has a cost
	
	protected String name;	//and a name 
	
	public String getDecorations() {	//and has to return the decorations
		return this.name + " is decorated with";
	}

}
