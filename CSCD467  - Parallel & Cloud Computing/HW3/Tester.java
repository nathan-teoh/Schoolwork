public class Tester {
	public static void main (String[] args) {
		BST tree = new BST();
		
		int[] originalInOrderArray   ={ 9, 5, 1, 7, 2,12, 8, 4, 3,11};
        int[] originalPostOrderArray = { 9, 1, 2,12, 7, 5, 3,11, 4, 8};
	 
        	System.out.println("Printing Original Arrays:");
        	System.out.println("Original InOrder Array is as follows: ");
        	for(int i : originalInOrderArray) {
        		System.out.print(i + " ");
        	}
    		System.out.println();
        	System.out.println("Original PostOrder Array is as follows: ");
        	for(int k : originalPostOrderArray) {
        		System.out.print(k + " ");
        	}
        	System.out.println();
        	System.out.println();
        	System.out.println("Now building the tree from postOrder and inOrder arrays..");
        	System.out.println();
        	System.out.println();
	        tree.buildTree(originalInOrderArray, originalPostOrderArray);
	        System.out.println("Tree built! Now running postOrder and inOrder Traversal to verify tree");
	        System.out.println("Running inOrderTraversal on the BST");
	        tree.inOrderTraversal();
	        System.out.println("");
	        System.out.println("Running postOrderTraversal on the BST");
	        tree.postOrderTraversal();
	}
}
