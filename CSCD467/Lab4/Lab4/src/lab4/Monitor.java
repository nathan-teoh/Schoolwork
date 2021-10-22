package lab4;
//Junyu Teoh Lab 4
//CSCD 467 Cloud Computing
public class Monitor {
	private int perThread;
	private int currentThread;
	private int numThreads;
	private int messageNum = 1;
	public Monitor(int numThreads) {
		this.numThreads = numThreads;
	}
	
	public synchronized void incThread() {
		if(this.currentThread+1 >= numThreads) {
			this.currentThread = 0;
			this.messageNum++;
		}else {
			this.currentThread++;
		}
		notifyAll();
	}
	
	public synchronized String returnMessageNum() {
		switch(messageNum) {
		case 1:
			return 1+"st";
		case 2:
			return 2+"nd";
		case 3:
			return 3+"rd";
		default:
			return this.messageNum+"th";
		}
	}
	
	public synchronized boolean isTurn(int threadNum) {
		while(threadNum != currentThread) {
			try {
				wait();
			}catch (InterruptedException e) {
				
			}
		}
		return true;
	}
}
