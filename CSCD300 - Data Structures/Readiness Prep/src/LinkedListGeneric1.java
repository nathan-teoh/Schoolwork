
public class LinkedListGeneric1<E> {

	private class Node<E> {
		private E data;
		private Node<E> next;
		
		public Node(E d, Node<E> nextLink) {
			this.data = d;
			this.next = nextLink;
		}
	}
	
	private int size;
	private Node<E> head;
	
	public LinkedListGeneric1() {
		this.head = new Node<E>(null, null);
		this.size = 0;
	}
	
	public void addFirst(E data) {
		Node<E> nn = new Node<E>(data, this.head.next);
		this.head.next = nn;
		this.size ++;
	}
	
	@Override
	public String toString() {
		String r = "Size:" + this.size + "\n";
		r += "[";
		Node<E> cur = this.head.next;
		while(cur != null) {
			r += cur.data + ",";
			cur = cur.next;
		}
		r += "]";
		return r;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListGeneric1<String> glist = new LinkedListGeneric1<String>();
		glist.addFirst("Tony");
		glist.addFirst("John");
		glist.addFirst("Abby");
		//glist.addFirst(new Integer(6));
		//glist.addFirst(new Character('b'));
		System.out.println(glist);
	}

}
