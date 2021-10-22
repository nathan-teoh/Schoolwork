package lab4;
//Junyu Teoh Lab 4
//CSCD 467 Cloud Computing
public class Printer implements Runnable {
	private Monitor lock;
	private int threadNum;
	
	public Printer(Monitor m, int n) {
		this.lock=m;
		this.threadNum=n;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1; i <= 10; i++) {
			if(lock.isTurn(threadNum)==true) {
				System.out.println(lock.returnMessageNum() + " Message from thread " + threadNum);
				lock.incThread();
			}
		}
	}
	
	public static void main (String[] args) {
		int numThreads = 0;
		if(args.length<1 || Integer.parseInt(args[0])<1) {	//check 
			System.out.println("Sorry! Please enter an integer larger than 0 to specify number of threads to use.");
			System.exit(0);
		}else {
			numThreads = Integer.parseInt(args[0]);
		}
		
		Thread[] threadArray = new Thread[numThreads]; //always guaranteed to have array bigger than 0 due to check above. Otherwise might unwanted behavior due to user entering 0 and program creating array of size 0.
		Monitor m = new Monitor(numThreads);
		for(int i = 0; i < numThreads; i++) {
			threadArray[i] = new Thread(new Printer(m,i));
			threadArray[i].start();
		}
		
		for(int i = 0; i < numThreads; i++) {
			try {
				threadArray[i].join();
			}catch(InterruptedException e) {
				
			}
		}
		
		
	}

}
