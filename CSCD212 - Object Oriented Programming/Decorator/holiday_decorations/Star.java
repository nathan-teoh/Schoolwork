package holiday_decorations;

public class Star extends TreeDecorator{
	
	private Star(Tree treeIn) {	//private constructor 
		this.name = treeIn.name;
		this.thisTree = treeIn;
	}
	
	@Override
	public String getDecorations() {
		// TODO Auto-generated method stub
		return thisTree.getDecorations() + " a Star,";
	}

	@Override
	public double Cost() {
		// TODO Auto-generated method stub
		return thisTree.Cost() + 4;
	}
	
	private static boolean hasStar(Tree treeIn) {
		return treeIn.getDecorations().contains("Star");	//check decoration string to see if star exists 
	}
	
	public static Tree getStar(Tree treeIn) {	//borrowed from singleton
		if(!hasStar(treeIn)) {	//enforce single star per tree
			return new Star(treeIn);	//create new object. reference changes
		}else {
			System.out.println("Sorry! Cannot add more than 1 Star per tree");
			return treeIn;	//return original object. reference does not change.
		}
	}

}
