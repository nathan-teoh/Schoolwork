import java.util.NoSuchElementException;

public class MyLinkedList {
	
	private ListNode head;
	private int size;
	
	//inner class for ListNode
	private class ListNode {
		private Object data;
		private ListNode next;
		private ListNode(Object d) {
			this.data = d;
			this.next = null;
		}
	}
	
	public MyLinkedList() {
		this.head = new ListNode(null); //with a dummy head node
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// Add Object e to start of this LinkedList
	// Please DO NOT change this addFirst() method.
	// You must keep and include this provided addFirst() method in your source code.
	public void addFirst(Object e)
	{
		ListNode node = new ListNode(e);
		node.next = head.next;
		head.next = node;
		size++;
	}
	
	// Remove(cut out) the first data node(the node succeeding the dummy node) 
	//       in this list, then returns the data in the node removed.
	// If the size of this list is zero, throws an Exception.
	public Object removeFirst() throws Exception {
      ListNode cur = head.next;
      if (this.size!=0){
         head.next = cur.next;
         size--;
         return cur.data;
      }
      
      else if (this.isEmpty()){
      
         throw new Exception("Exception: LinkedList is Empty!");
      }
    
		return null; //change this as you need.
	}
   
	
	// Returns true if this list contains the specified element o. 
	// More formally, returns true if and only if this list contains at least one element e 
	// such that (o==null ? e==null : o.equals(e)).
	// Note: you have to handle the case where a list node stores null data element.
	public boolean contains(Object o) {
      ListNode tmp = new ListNode(o); //typecast object o into listnode tmp
       ListNode cur = head.next;  //current Node
       ListNode prev = null;   //previous Node
      
      for (int i = 0; i < this.size-1; i++){    //for iterator
         if(tmp.data==cur.data){ //if first one is what we're looking for
            return true;
         }
         else {   //if it isn't
            prev = cur;   //move up
            cur = cur.next;     //move up
            if(tmp.data==cur.data){    //if true
             return true;
            }
         }
      }
      
      
		return false; //change this as you need.
	}
	
	// Removes the first occurrence of the specified element o from this list and returns true, if it is present. 
	// If this list does not contain the element o, it is unchanged and the method returns false.
	// More formally, removes the element o with the lowest index i such that 
	//     (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
	// Note: you have to handle the case where a list node stores null data element.
	public boolean remove(Object o) {
   
   
		for(ListNode prev=this.head, cur=this.head.next; cur!=null; prev=cur, cur=cur.next){
         if(cur.data==o){
            prev.next=cur.next;
            this.size--;
            return true;
         }
         else if (cur.data==null&&o==null){
            return false;
         }
      }
		return false; //change this as you need.
	}

	// Removes all copies of o from this linked list.
	// You have to handle the cases where Object o may 
	//        have zero, one or multiple copies in this list.
	// If any element has been removed from this list, returns true. 
	//        Otherwise returns false.
	// Note: be careful when multiple copies of Object o are stored
	//        in consecutive(adjacent) list nodes.
	//        E.g. []->["A"]->["A"]->["B"]. 
	//        Be careful when removing all "A"s in this example.
	// Note: This list may contains zero, one or multiple copies of null data elements.
	public boolean removeAllCopies( Object o ) { //passed test
		ListNode tmp = new ListNode(o);
      ListNode cur = head.next;
      ListNode prev = this.head;
      
    while(this.head.next!=null&&cur!=null){
      prev=this.head;   //to reset. otherwise it will get lost.
          if(cur!=null){   //make sure cur is not null. if it's null, at the last node.
            if(cur.data==tmp.data){
               prev.next=cur.next;  //moving previous pointer to cur.nexts.
               this.size--;   //reduce size
            }
          }
          //prev=cur;  //move up
          cur=cur.next; //move up
          
      }     
		return false; //change this as you need.
	}
	
	// Insert data elements from linkedlist A and B alternately into 
	//    a new linkedlist C, then returns C.
        // Follow the pattern to pick items in list A and B, 
        //        linkedlist A->linkedlist B->linkedlist A->linkedlist B …
	// If A is longer than B, append remaining items in A to C
	//     when the end of B is first reached.
	// If B is longer than A, append remaining items in B to C
	//     when the end of A is first reached.
	// E.g1 A = {1, 3, 5, 7, 9} and B = {2, 4, 6}; and 
	//       C will be {1, 2, 3, 4, 5, 6, 7, 9}.
        //
	// E.g2 A = {1, 3, 5} and B = {2, 4, 6, 8, 10}; and 
	//       C will be {1, 2, 3, 4, 5, 6, 8, 10}.
	// Note: after this method is called, both list A and B are UNCHANGED.
	public static MyLinkedList interleave(MyLinkedList A, MyLinkedList B) {
         MyLinkedList C = new MyLinkedList();
         //ListNode curA = A.head, curB=B.head, prevC=C.head, curC=C.head.next;
         ListNode  curA=A.head.next, curB=B.head.next, curC=C.head.next;
         int maxNum = ((A.size + B.size)-2);
         
         
         while(C.size<maxNum+2){
            if(curA!=null){
               C.add(curA.data);
               curA = curA.next;
               
   

            }
            if(curB!=null){
               C.add(curB.data);
               curB = curB.next;
            
            }
           
            
         }
         if(!C.isEmpty()){
            return C;
         }
         
          
           // curA = curA.next; // move curA up
           // curB=curB.next;   //move curB up
     		return null; //change this as you need.
	}
  
	
	// Inserts the specified element at the specified position in this list. 
	// Shifts the element currently at that position (if any) and any subsequent
	//     elements to the right (adds one to their indices).
	// if(index < 0 or index > this.size), throws IndexOutOfBoundsException.
	
	// E.g, if this list is [dummy]->["A"]->["B"]->["C"] with size = 3.
	//   add(0,D) will result in [dummy]->["D"]->["A"]->["B"]->["C"].
	//   Continuing on the previous add() call, add(1,"E") will
	//   change the existing list to [dummy]->["D"]->["E"]->["A"]->["B"]->["C"].
	public void add(int index, Object o) {
      ListNode prev=this.head, cur=this.head.next;
      
      if(index<0 || index>this.size){
         throw new IndexOutOfBoundsException("Index passed in not valid!");
      }
      if(index==0){
         prev.next = new ListNode(o); 
         prev.next.next=cur;
         this.size++;
      }
      else{
         for(int i =0;i<index;i++){
            prev=cur;
            cur=cur.next;
         }
   		prev.next = new ListNode(o);
         prev.next.next=cur;
         this.size++;
      }
	}
	

	// Returns the element at the specified index in this list.
	// Be noted that the listnode at head.next has index 0 and 
	//      the last list node has index of size()-1.
	// if index < 0 or index >= this.size, throws IndexOutOfBoundsException.
	public Object get(int index) throws IndexOutOfBoundsException{
      if (index<0 || index >= this.size){
         throw new IndexOutOfBoundsException("Provided index is out of bounds! "+index);
      }
      
      ListNode prev = this.head, cur = this.head.next;
      
      if(index==0){
         return cur.data;
      }
      else{
         for (int i = 0; i < index; i++){
            prev = cur;
            cur = cur.next;
         }
         return cur.data;
      }
		//return null; //change this as you need.
	}
	
	// Removes (cuts out) the list node at the specified index in this list. 
	// Returns the data element in the node that is removed.
	// Be noted that the list node at head.next has index 0 and 
	//      the last list node has index of size()-1.
	// if index < 0 or index >= this.size, throws IndexOutOfBoundsException.
	public Object remove(int index) throws IndexOutOfBoundsException {
		ListNode prev = this.head, cur=this.head.next;
      if(index < 0 || index>=this.size){
         throw new IndexOutOfBoundsException("Provided index is out of bounds! " + index);
      }
      if(index==0){
         Object d = this.head.data;
         this.head=this.head.next;
         this.size--;
         return d;
      }
      for(int i = 0; i < index; i++){
         prev = cur;
         cur = cur.next;
      }
      prev.next = cur.next;
      this.size--;
      return cur.data;
      
	}

	
	//Add the object e to the end of this list.
	// it returns true, after e is successfully added.
	public boolean add(Object e) {
      //if data is empty
      
      ListNode cur = head.next;  //current Node
      ListNode prev = null;   //previous Node
      
      if(cur == null){  //if head.next/cur is empty
         head.next = new ListNode(e);  //head.next will get a new ListNode with object E
         this.size++;   //increase size
      }
      
      else {
         while(cur != null){
            prev = cur; //move upwards
            cur = cur.next;   //move upwards
            
            if (cur == null){ //if head.next/cur is empty
               prev.next = new ListNode(e); //prev.next will get new ListNode with object E
               this.size++;   //increase size
            }
         }
      }      
         
		return true; //change this as you need.
	}
	
        //Please DO NOT Change the following toString() method!!!
        //You must include the following toString() method in your source code.
	@Override
	public String toString() {
		String result = "{";
	    for (ListNode node = this.head.next; node != null; node = node.next) {
	    		if(node.next != null)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}
