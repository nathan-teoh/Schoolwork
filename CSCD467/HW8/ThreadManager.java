import java.io.IOException;
import java.net.ServerSocket;

public class ThreadManager extends Thread {
	private final int T1 = 10, T2 = 20;
	private int wait;
	private ThreadPool p;
	private boolean terminated;
	private ServerSocket ss;

	public ThreadManager(ThreadPool p, ServerSocket ss) {
		this.p = p;
		this.terminated = false;
		this.wait = 1000;
		this.ss = ss;
	}

	@Override
	public void run() {
		this.p.startPool();

		while(terminated!=true) {
			try {
				Thread.sleep(this.wait);
			
			if (p.jobCount() > T1 && p.jobCount() <= T2 &&  p.numThreadsRunning() == 5) {
				p.increasePool();
				while (p.jobCount() >= T1 && p.jobCount() <= T2) {}
				if (p.jobCount() < T1) {
					p.decreasePool();
				}
			}
			if (p.jobCount() > T2 && p.numThreadsRunning() <= 10) {
				p.increasePool();
				if (p.numThreadsRunning() == 10) {
					p.increasePool();
				}
				while (p.jobCount() > T2) {}
				p.decreasePool();
			}
			if (p.jobCount() < T1 && p.numThreadsRunning() == 10) {
				p.decreasePool();
			}
		} catch (InterruptedException e) {
			break;
		}
			
		}
		System.out.println("Thread Manager: POOL STOPPING");
		this.p.stopPool();
		System.out.println("Thread Manager: POOL STOPPED");
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Thread Manager: THREAD MANAGER CLOSED");
	}

	public boolean isTerminated() {
		return this.terminated;
	}

	public void terminate() {
		this.terminated = true;
		this.interrupt();
	}

}