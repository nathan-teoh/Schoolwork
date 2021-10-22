
public class LinkedListGeneric2 {

	private class Node {
		private Object data;
		private Node next;
		
		public Node(Object d, Node nextLink) {
			this.data = d;
			this.next = nextLink;
		}
	}
	
	private int size;
	private Node head;
	
	public LinkedListGeneric2() {
		this.head = new Node(null, null);
		this.size = 0;
	}
	
	public void addFirst(Object data) {
		Node nn = new Node(data, this.head.next);
		this.head.next = nn;
		this.size ++;
	}
	
	@Override
	public String toString() {
		String r = "Size:" + this.size + "\n";
		r += "[";
		Node cur = this.head.next;
		while(cur != null) {
			r += cur.data + ",";
			cur = cur.next;
		}
		r += "]";
		return r;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListGeneric2 glist = new LinkedListGeneric2();
		glist.addFirst("Tony");
		glist.addFirst("John");
		glist.addFirst("Abby");
		glist.addFirst(new Integer(6));
		glist.addFirst(new Character('b'));
		//glist.addFirst(12); //older version java before 1.7 does NOT support his syntax by default.
		System.out.println(glist);
	}

}
