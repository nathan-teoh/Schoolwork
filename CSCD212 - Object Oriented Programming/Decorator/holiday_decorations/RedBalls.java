package holiday_decorations;

public class RedBalls extends TreeDecorator{
	
	public RedBalls(Tree treeIn) {
		this.name = treeIn.name;
		this.thisTree = treeIn;
	}

	@Override
	public String getDecorations() {
		// TODO Auto-generated method stub
		return thisTree.getDecorations() + " Red Balls,";
	}

	@Override
	public double Cost() {
		// TODO Auto-generated method stub
		return thisTree.Cost() + 1;
	}
	
	
}
