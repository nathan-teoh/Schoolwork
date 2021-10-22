import java.util.*;

public class BadGuy implements Observer{
	private EyeOfSauron subject;	//subject we observe
	private String name;	//name of badguy
	
	
	public BadGuy(EyeOfSauron sIn, String nIn){
		this.subject = sIn;
		this.name = nIn;
		this.subject.addObserver(this); //add itself to the list of observers 
	}

	@Override
	public void update(Observable o, Object arg) {
		GoodGuy temp = (GoodGuy) arg;	//typecast for easier manipulation
		System.out.println(name + " noticed " + temp.toString());	//print changes of our subject 
		
	}

	public void defeated() {
		System.out.println(name + " has been defeated!");	
		this.subject.deleteObserver(this);	//delete itself from list of observers
	}


}
