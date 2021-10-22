package holiday_decorations;

public class LEDs extends TreeDecorator{
	
	public LEDs(Tree treeIn) {
		this.name = treeIn.name;
		this.thisTree = treeIn;
	}
	
	@Override
	public String getDecorations() {
		// TODO Auto-generated method stub
		return thisTree.getDecorations() + " LEDs,";
	}

	@Override
	public double Cost() {
		// TODO Auto-generated method stub
		return thisTree.Cost() + 10;
	}

}
