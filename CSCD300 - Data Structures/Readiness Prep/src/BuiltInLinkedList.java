import java.util.LinkedList;

public class BuiltInLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> glist = new LinkedList<String>();
		glist.addFirst("Tony");
		glist.addFirst("John");
		glist.addFirst("Abby");
		//glist.addFirst(new Integer(6));
		//glist.addFirst(new Character('b'));
		System.out.println(glist);
		
		//LinkedList<int> glist2 = new LinkedList<int>();
		LinkedList<Integer> glist2 = new LinkedList<Integer>();
		glist2.addFirst(6); // Older version java does not support his syntax.
		//glist.addFirst(new Integer(6)); //In older version of java, you have to do this.
		glist2.addFirst(4);
		glist2.addFirst(2);
		
		//glist.addFirst(new Character('b'));
		System.out.println(glist2);
	}

}
