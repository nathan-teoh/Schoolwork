public class CDoublyLinkedList {

	private class Node {
		private Object data;   //Assume data implemented Comparable
		private Node next, prev;
		private Node(Object data, Node pref, Node next)
		{
			this.data = data;
			this.prev = pref;
			this.next = next;
		}
	}

	private Node head;
	private int size;

	public CDoublyLinkedList() {
		this.head = new Node(null, null, null );
		this.head.next = this.head;
		this.head.prev=this.head;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.head == this.head.next;
	} 
	
	// Add Object data to start of this LinkedList
	// Please DO NOT change this addFirst() method.
	// You must keep and include this provided addFirst() method
	//      in your source code.
	public void addFirst(Object data) {
		Node nn = new Node(data, this.head, this.head.next);
		this.head.next.prev = nn;
		this.head.next = nn;
		this.size ++;
	}

	// write a method void addLast(Object data) that will insert 
	// the piece of data at the end of the current list.
	// Note: this list is allowed to store null data element in its list node.
	public void addLast(Object data) {
     Node cur = this.head;
     for (int i = 0; i < this.size; i++){
      cur=cur.next;
     }
         Node nn = new Node (data,cur,cur.next);
         cur.next.prev=nn;
         cur.next=nn;
         this.size++;
       
	}
	
	// Write the subListOfSmallerValues method.  
	// It should return a CDoublyLinkedList object 
	//     containing data that is smaller than the value passed to the method.
        // If a null data element in this list is encountered, you can skip it.
	public CDoublyLinkedList subListOfSmallerValues(Comparable data) {
	   CDoublyLinkedList toReturn = new CDoublyLinkedList();
      Node cur = this.head;   //cursor/current node
      for(int i =0; i < this.size; i++){
         cur = cur.next;   //move in the CDLL
         if(data.compareTo(cur.data) > 0){
           toReturn.addLast(cur.data); //add data to the CDLL
         } 
      }
      return toReturn;

		//return null; //change this as needed.
	}
	
	// This method should remove the first occurrence of the data from the list, 
        //      starting at the *BACK* of the list. 
        // It scans the list from back to the front by following the prev links. 
	// The method should return true if successful, false otherwise. 
	// Note that list node may contain null data element. Please handle this edge case.
	public boolean removeStartingAtBack(Object dataToRemove) {
	   Node cur = this.head;
      boolean toReturn = false;
      for(int i = 0 ; i < this.size; i++){
         cur = cur.prev;
         
         
         if(cur.data==dataToRemove&&toReturn==false){ //check for data and if one is removed. if yes, wont continue. this prevents removing multiple nodes with the same data.
            cur.next.prev=cur.prev;
            cur.prev.next=cur.next;
            this.size--;
            toReturn = true;
         }
      }
		return toReturn;//change this as needed.
	}
	
	// Returns the index of the last occurrence of the specified element in this list, 
	//     or -1 if this list does not contain the element. 
	// More formally, returns the highest index i 
	//     such that (o==null ? get(i)==null : o.equals(get(i))), 
	//     or -1 if there is no such index.
	// Note: a list node may store a null data element. Please handle this edge case.
	public int lastIndexOf(Object o) {
      Node cur = this.head;
      int index = -1;   //default index as per instructions
      for ( int i = 0; i < this.size; i++){
         cur = cur.next;
         if(cur.data==o){
            index = i;
         }
      }
		return index; //change this as needed.
	}
	
	
	// Removes from this list all of its elements that 
	//    are NOT contained in the specified linkedlist other.
	// If any element has been removed from this list,
	//    returns true. Otherwise returns false.
	// If other list is null, throws NullPointerException.
        // Helper methods are allowed.
	public boolean retainAll(CDoublyLinkedList other) throws NullPointerException {
   Boolean anyDeleted = false;
      if(other.size==0){
         throw new NullPointerException("Null CDLL!");
      }else{
         Boolean toDelete = true;
         Node thisCur = this.head.next, otherCur = other.head.next;
         for(thisCur = this.head.next; thisCur!=head; thisCur = thisCur.next){   //for loop to increment THIS CDLL
            for(otherCur = other.head.next; otherCur!=other.head; otherCur = otherCur.next){ //for loop to increment other CDLL
               if(thisCur.data==otherCur.data){
                  toDelete = false; //if match, don't delete.
               }
            }
            if(toDelete == true){
               thisCur.next.prev = thisCur.prev;  
               thisCur.prev.next = thisCur.next;
               this.size--;
               anyDeleted = true;
               
            }
            toDelete = true; //reset toDelete after each time deleting;
         }
      }

	    return anyDeleted; //change this as needed.
	}
	

        // Write this method to sort this list using insertion sort algorithm, 
        //      as we have learned in the classroom.
	public void insertionSort() {
   Node lastSorted, sortedWalker;
   Comparable firstUnsortedData;
   for(lastSorted=this.head.next; lastSorted!=this.head.prev; lastSorted=lastSorted.next){
      firstUnsortedData = (Comparable)lastSorted.next.data;
      for(sortedWalker=lastSorted; sortedWalker!=head && ((Comparable)sortedWalker.data).compareTo(firstUnsortedData) >0; sortedWalker = sortedWalker.prev){
         sortedWalker.next.data = sortedWalker.data;
      }
     sortedWalker.next.data = firstUnsortedData;
   }

}
	
	@Override
	public String toString() {
		String result = "{";
	    for (Node node = this.head.next; node != this.head; node = node.next) {
	    		if(node.next != this.head)
	    			result += node.data + "->"; 
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}
			