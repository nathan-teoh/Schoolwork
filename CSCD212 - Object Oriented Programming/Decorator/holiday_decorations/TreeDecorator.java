package holiday_decorations;

public abstract class TreeDecorator extends Tree {	//abstract because it makes no sense to have a decorator object 
	protected Tree thisTree;	//every "decorator" has to decorate a tree
	public abstract String getDecorations();	//every decorator has to add to the string 
	
}
