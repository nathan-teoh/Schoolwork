package hw2;

public class MyPrimeTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		if (args.length < 3) {
			System.out.println("Usage: MyPrimeTest numThread low high \n");
			return;
		}
		int nthreads = Integer.parseInt(args[0]);
		int low = Integer.parseInt(args[1]);
		int high = Integer.parseInt(args[2]);
		Counter c = new Counter();
		
		//test cost of serial code
		long start = System.currentTimeMillis();
		int numPrimeSerial = SerialPrime.numSerailPrimes(low, high);
		long end = System.currentTimeMillis();
		long timeCostSer = end - start;
		System.out.println("Time cost of serial code: " + timeCostSer + " ms.");
		
		//test of concurrent code
		// **************************************
        // Write me here
		long timeCostCon, conStart, conEnd;
		
		ThreadPrime[] threadArray = new ThreadPrime[nthreads];
		int perThread = high / nthreads; //how many per thread
		int startLow = low; //lower bound for threads
		int startHigh;		//initially set as nothing as higher bound is constantly variable
		
		conStart=System.currentTimeMillis();
		
		for(int i = 0; i < nthreads-1; i++) {
			startHigh = startLow + perThread ; //how we constantly update higher bound
			threadArray[i]= new ThreadPrime(startLow,startHigh,c);	//create n threads
			threadArray[i].start();
			startLow = startHigh+1;	//at end of loop, update lower bounds to +1 of previous high bound.
		}
		threadArray[threadArray.length-1] = new ThreadPrime(startLow, high,c); //have to do this due to how i set up the previous loop.
		threadArray[threadArray.length-1].start();
		
		for(int i = 0; i < nthreads; i++) {
			threadArray[i].join();	//make all of them wait for one another
		}
		
		conEnd = System.currentTimeMillis();
		
		timeCostCon = conEnd - conStart;
		// **************************************
		System.out.println("Time cost of parallel code: " + timeCostCon + " ms.");
		System.out.format("The speedup ration is by using concurrent programming: %5.2f. %n", (double)timeCostSer / timeCostCon);
		
		System.out.println("Number prime found by serial code is: " + numPrimeSerial);
		System.out.println("Number prime found by parallel code is " + c.total());
	}
		

}
