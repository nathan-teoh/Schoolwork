
public class GoodGuy {
	private int hobbit, elves, dwarves, men;
	
	public GoodGuy(int hobbitIn, int elvesIn, int dwarvesIn, int menIn) {
		if(hobbitIn  < 0 || elvesIn <0 || dwarvesIn <0 || menIn <0) {
			throw new IllegalArgumentException("Can't pass in negative numbers for enemies.");
		}
		this.hobbit = hobbitIn;
		this.elves = elvesIn;
		this.dwarves = dwarvesIn;
		this.men = menIn;
	}
	
	public int getHobbit() {
		return this.hobbit;
	}
	
	public int getElves() {
		return this.elves;
	}
	
	public int getDwarves() {
		return this.dwarves;
	}
	
	public int getMen() {
		return this.men;
	}
	
	@Override
	public String toString() {
		return this.hobbit + " hobbits, " + this.elves + " elves, " + this.dwarves + " dwarves, and " + this.men + " men"; 
	}
}
