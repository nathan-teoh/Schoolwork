package lab2;

public class Waiter implements Runnable {
	public Waiter() {
		super();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		while(count <= 25) {
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				count++;
				if(count==25) {
					System.out.println("Printer got his work half done!");
				}
			}
		}
		System.out.println("Waiter has done its work, terminating.");
		
	}

}
