
import holiday_decorations.*;

public class TreeTester {
	public static void main(String args[]) {
		
		System.out.println("## Creating 1 FraserFir and adding one of all decorations. ##");
		Tree allDecoTree = new FraserFir(); //35
		allDecoTree = new Ruffles(allDecoTree);	//1
		allDecoTree = new Ribbons(allDecoTree);	//2
		allDecoTree = new Lights(allDecoTree);	//5
		allDecoTree = new LEDs(allDecoTree);	//10
		allDecoTree = new RedBalls(allDecoTree); //1
		allDecoTree = new BlueBalls(allDecoTree); //2
		allDecoTree = new SilverBalls(allDecoTree);  //3
		allDecoTree = Star.getStar(allDecoTree); //4
		System.out.println(allDecoTree.getDecorations());
		System.out.println("Current cost is : "+ allDecoTree.Cost() + "\n");  //63
		System.out.println("## Attempting to add another star ##");
		allDecoTree = Star.getStar(allDecoTree);
		System.out.println("\n## Add more decorations to same tree after being denied 2 or more stars ##");
		allDecoTree = new Ruffles(allDecoTree); //1
		allDecoTree = new LEDs(allDecoTree); //10
		allDecoTree = new SilverBalls(allDecoTree); //3
		System.out.println(allDecoTree.getDecorations());
		System.out.println("Current cost is : "+ allDecoTree.Cost() + "\n"); //77
		
		System.out.println("############################################################ \n");
		
		System.out.println("## Creating 1 BalsamFir and adding one of all decorations. ##");
		Tree balsamFirTree = new BalsamFir(); 
		balsamFirTree = new Ruffles(balsamFirTree);	//1
		balsamFirTree = new Ribbons(balsamFirTree);	//2
		balsamFirTree = new Lights(balsamFirTree);	//5
		balsamFirTree = new LEDs(balsamFirTree);	//10
		balsamFirTree = new RedBalls(balsamFirTree); //1
		balsamFirTree = new BlueBalls(balsamFirTree); //2
		balsamFirTree = new SilverBalls(balsamFirTree);  //3
		balsamFirTree = Star.getStar(balsamFirTree); //4
		System.out.println(balsamFirTree.getDecorations());
		System.out.println("Current cost is : "+ balsamFirTree.Cost() + "\n");  //63
		System.out.println("## Attempting to add another star ##");
		balsamFirTree = Star.getStar(balsamFirTree);
		System.out.println("\n## Add more decorations to same tree after being denied 2 or more stars ##");
		balsamFirTree = new Ribbons(balsamFirTree); 
		balsamFirTree = new Ruffles(balsamFirTree); 
		balsamFirTree = new RedBalls(balsamFirTree); 
		System.out.println(balsamFirTree.getDecorations());
		System.out.println("Current cost is : "+ balsamFirTree.Cost() + "\n"); 
		
		System.out.println("############################################################ \n");

		System.out.println("## Creating 1 Douglas Fir and adding one of all decorations. ##");
		Tree douglasFirTree = new DouglasFir(); 
		douglasFirTree = new Ruffles(douglasFirTree);	//1
		douglasFirTree = new Ribbons(douglasFirTree);	//2
		douglasFirTree = new Lights(douglasFirTree);	//5
		douglasFirTree = new LEDs(douglasFirTree);	//10
		douglasFirTree = new RedBalls(douglasFirTree); //1
		douglasFirTree = new BlueBalls(douglasFirTree); //2
		douglasFirTree = new SilverBalls(douglasFirTree);  //3
		douglasFirTree = Star.getStar(douglasFirTree); //4
		System.out.println(douglasFirTree.getDecorations());
		System.out.println("Current cost is : "+ douglasFirTree.Cost() + "\n");  //63
		System.out.println("## Attempting to add another star ##");
		douglasFirTree = Star.getStar(douglasFirTree);
		System.out.println("\n## Add more decorations to same tree after being denied 2 or more stars ##");
		douglasFirTree = new LEDs(douglasFirTree); 
		douglasFirTree = new LEDs(douglasFirTree); 
		douglasFirTree = new LEDs(douglasFirTree); 
		System.out.println(douglasFirTree.getDecorations());
		System.out.println("Current cost is : "+ douglasFirTree.Cost() + "\n"); 
		
		System.out.println("############################################################ \n");

		System.out.println("## Creating 1 ColoradoBlueSpruce and adding one of all decorations. ##");
		Tree coloradoTree = new ColoradoBlueSpruce(); 
		coloradoTree = new Ruffles(coloradoTree);	//1
		coloradoTree = new Ribbons(coloradoTree);	//2
		coloradoTree = new Lights(coloradoTree);	//5
		coloradoTree = new LEDs(coloradoTree);	//10
		coloradoTree = new RedBalls(coloradoTree); //1
		coloradoTree = new BlueBalls(coloradoTree); //2
		coloradoTree = new SilverBalls(coloradoTree);  //3
		coloradoTree = Star.getStar(coloradoTree); //4
		System.out.println(coloradoTree.getDecorations());
		System.out.println("Current cost of FraserFir is : "+ coloradoTree.Cost() + "\n");  //63
		System.out.println("## Attempting to add another star ##");
		coloradoTree = Star.getStar(coloradoTree);
		System.out.println("\n## Add more decorations to same tree after being denied 2 or more stars ##");
		coloradoTree = new Ribbons(coloradoTree); 
		coloradoTree = new Ribbons(coloradoTree); 
		coloradoTree = new Ribbons(coloradoTree); 
		System.out.println(coloradoTree.getDecorations());
		System.out.println("Current cost is : "+ coloradoTree.Cost() + "\n"); 
		
		
		

	}
}
