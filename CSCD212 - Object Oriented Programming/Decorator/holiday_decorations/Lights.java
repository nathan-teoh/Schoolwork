package holiday_decorations;

public class Lights extends TreeDecorator{
	public Lights(Tree treeIn) {
		this.name = treeIn.name;
		this.thisTree = treeIn;
	}
	
	@Override
	public String getDecorations() {
		return thisTree.getDecorations() + " Lights,";
	}
	
	@Override 
	public double Cost() {
		return thisTree.Cost() + 5;
	}
}
