package holiday_decorations;

public class Ruffles extends TreeDecorator{
	
	public Ruffles(Tree treeIn) {
		this.name = treeIn.name;
		this.thisTree = treeIn;
	}
	@Override
	public String getDecorations() {
		
		return this.thisTree.getDecorations() + " Ruffles,";
	}

	@Override
	public double Cost() {
		// TODO Auto-generated method stub
		return thisTree.Cost() + 1;
	}

	
}
