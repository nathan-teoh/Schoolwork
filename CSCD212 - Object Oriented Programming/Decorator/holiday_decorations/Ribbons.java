package holiday_decorations;

public class Ribbons extends TreeDecorator{
	public Ribbons(Tree treeIn) {
		this.name = treeIn.name;
		this.thisTree = treeIn;
	}

	@Override
	public String getDecorations() {
		// TODO Auto-generated method stub
		return this.thisTree.getDecorations() + " Ribbons,";
	}

	@Override
	public double Cost() {
		// TODO Auto-generated method stub
		return thisTree.Cost() + 2;
	}
	
}
