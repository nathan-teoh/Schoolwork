package Lab3;

public class Alternation {
	
	boolean isT1turn;
	final Monitor lock = new Monitor();
	Thread t1;
	Thread t2;
	
	public Alternation() {
		
		t1 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	        	while(lock.getNum()<50) {
	        		while(!lock.getTurn()) {
	        			
	        		}
	        		try {
	        			Thread.sleep(100);
	        		}catch(InterruptedException e) {
	        			
	        		}
	        		System.out.println("Message "+ lock.getNum() + " from Thread T1");
	        		lock.incNum();
	        		lock.setTurn(false);
	        	}
	        }
	    });
	    t2 = new Thread(new Runnable() {

	        @Override
	        public void run() {
	        	while(lock.getNum()<50) {
	        		while(lock.getTurn()) {
	        			
	        		}
	        		try {
	        			Thread.sleep(100);
	        		}catch(InterruptedException e) {
	        			
	        		}
	        		System.out.println("Message "+ lock.getNum() + " from Thread T2");
	        		lock.incNum();
	        		lock.setTurn(true);
	        	}
	        }
	    });
	    t1.start();
	    t2.start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Alternation();
	}
}


