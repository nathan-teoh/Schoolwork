
public class ThreadPool {
	private int maxCapacity;
	private int actualNumberThreads;
	private WorkerThread[] holders;
	private boolean stopped;
	
	private SharedQueue<Runnable> jobq;
	
	private class WorkerThread extends Thread{
		private boolean stopped;
		private Runnable toRun;
		private SharedQueue<Runnable> jq;
		
		private WorkerThread(SharedQueue<Runnable> jIn, boolean sIn, int tID) {
			this.jq = jIn;
			this.stopped= sIn;
			Thread.currentThread().setName("Thread-" + tID);
		}
		
		public void run() {
			while(stopped!=true) {
				try {
					toRun = this.jq.pop();
					System.out.println("Worker Thread:" +Thread.currentThread().getName() + "PROCESS:");
					toRun.run();
				}catch(Exception e) {
					if(stopped) {
						System.out.println("Worker Thread:" +Thread.currentThread().getName()+"INTERRUPTED,EXITTING");
						return;
					}
				}
			}
			System.out.println("Worker Thread:" +Thread.currentThread().getName() + "EXIT");
		}
	}
	
	public ThreadPool() {
		this.maxCapacity = 40;
		this.actualNumberThreads = 5;
		this.holders=new WorkerThread[maxCapacity];
		this.jobq = new SharedQueue<Runnable>(50);
		this.stopped=false;
	}
	
	public synchronized void startPool() {
		System.out.println("!!T!!!!!HREAD POOL STARTING!!!!!!!!");
		for( int i = 0; i < 5; i++) {
			this.holders[i] = new WorkerThread(this.jobq, this.stopped, i);
			this.holders[i].start();
		}
	}
	
	public synchronized void stopPool() {
		System.out.println("!!!!!THREAD POOL STOPPING!!!!!");
		for(int i = 0; i < 40; i++) {
			if(this.holders[i]!=null) {
				this.holders[i].stopped = true;
				this.holders[i].interrupt();
				System.out.println("!!!!!THREAD POOL CLOSING THREAD"+i);
			}
		}
		System.out.println("!!!!THREAD POOL WAITING FOR JOBS!!!!!");
		for(int i = 0; i < 40; i++) {
			if(this.holders[i]!=null) {
				try {
					this.holders[i].join();
					this.holders[i] = null;
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("!!!!!!!POOL CLOSED!!!!!!");
	}

	
	public synchronized void increasePool() {
		System.out.println("!!!!THREAD POOL DOUBLING!!!!!");
		if(this.actualNumberThreads <=20) {
			int start = this.actualNumberThreads;
			this.actualNumberThreads *=2;
			System.out.println("!!!THREAD POOL CURRENT THREADS RUNNING " + this.actualNumberThreads);
			for (int i = start; i < actualNumberThreads; i++) {
				this.holders[i] = new WorkerThread(this.jobq, this.stopped, i);
				this.holders[i].start();
			}
		} else {
			System.out.println("DOUBLE ERROR");
		}
	}

	public synchronized void decreasePool() {
		System.out.println("!!!!!THREAD POOL HALVING");
		if (this.actualNumberThreads > 5) {
			int end = this.actualNumberThreads;
			this.actualNumberThreads /= 2;
			System.out.println("!!!!!THREAD POOL CURRENT THREADS" + this.actualNumberThreads);
			for (int i = actualNumberThreads; i < end; i++) {
				this.holders[i].stopped = true;
				this.holders[i].interrupt();
				System.out.println("Closing thread " + i);
				try {
					this.holders[i].join();
					System.out.println("Closed " + i);
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.holders[i] = null;
			}
		}
		
	}

	public boolean execute(Runnable r) throws InterruptedException {
		if (this.jobq.isFull()) {
			return false;
		}
		this.jobq.enqueue(r);
		return true;
		
	}

	public synchronized int numThreadsRunning() {
		return this.actualNumberThreads;
	}

	public synchronized int jobCount() {
		return this.jobq.returnSize();
	}

}