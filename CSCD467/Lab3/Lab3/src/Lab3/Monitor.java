package Lab3;

public class Monitor {
	private boolean turn = true;
	private int num = 1;
	
	public synchronized boolean getTurn() {
		return turn;
	}
	
	public synchronized void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public synchronized int getNum() {
		return this.num;
	}
	
	public synchronized void incNum() {
		this.num++;
	}
}
