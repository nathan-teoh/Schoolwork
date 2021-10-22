package lab2;

public class Printer implements Runnable {

	private Thread waiter;
	public Printer(Thread waiter) {
		super();
		this.waiter=waiter;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1; i <= 50; i++) {	//start from 1 and end at 50. ran into bug here where it would start at 0.	
			System.out.println("Message i = " + i + " , from Thread Printer");
			waiter.interrupt();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				return;
			}
		}
		
	}

	public static void main (String[] args) {
		Thread w = new Thread(new Waiter());
		Thread p = new Thread(new Printer(w));
		w.start();
		p.start();
		try {
			w.join();
			p.join();
		}catch(InterruptedException e) {
		}
		System.out.println("Both Waiter and Printer have finished their work!");
	}
}
