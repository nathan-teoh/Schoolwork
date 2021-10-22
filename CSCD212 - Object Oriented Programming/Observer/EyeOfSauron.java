
import java.util.*;
public class EyeOfSauron extends Observable{
	
	public EyeOfSauron() {
		//empty constructor
	}
	public void setEnemies(GoodGuy goodGuysIn) {
		this.setChanged(); //object has changed
		this.notifyObservers(goodGuysIn);	//notify every observer
	}
	
}
