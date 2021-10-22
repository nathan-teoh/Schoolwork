import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BST<E> {
	//Taken from class slides on Canvas, but slightly modified.
	
	private Node root; //every BST has a root. 
	
//Public methods below!
	
	public void insert(Comparable dataIn) {	//helper method
		root = insert(root,dataIn);
	}

	public void postOrderTraversal() {
		postOrderTraversal(this.root);
	}
	
	public void inOrderTraversal() {
		inOrderTraversal(this.root);
	}
	

	public boolean delete(Comparable toDelete) {
		Node toRet = delete(this.root, toDelete);
		if(toRet == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public Node delete(Node rootIn,Comparable toDelete) {
		if(rootIn==null || toDelete.equals(null)) {
			return null;
		}
		Comparable rootData = rootIn.data;
		if(toDelete == rootData) {
			rootIn=removeFromRoot(rootIn);
		}
		else if(toDelete.compareTo(rootData)<0) {
			rootIn.left = delete(rootIn.left, toDelete);
		}
		else {
			rootIn.right = delete(rootIn.right, toDelete);
		}
		return rootIn;
	}
	
//private methods below!
	
	private BST<E>.Node removeFromRoot(Node rootIn) {
		if(rootIn.left!=null && rootIn.right!=null) {
			Node temp = findLargest(rootIn.left);
			
			rootIn.data = temp.data;
			removeLargest(rootIn.left);
		}
		else if(rootIn.left!=null&&rootIn.right ==null) {
			rootIn = rootIn.left;
			return rootIn;
		}
		else if(rootIn.right!=null&&rootIn.left==null) {
			rootIn = rootIn.right;
			return rootIn;
		}
		else if(rootIn.left ==null && rootIn.right == null) {
			rootIn=null;
			return rootIn;
		}
		return rootIn;
	}
	
	 class Node{
		Node left, right, parent;	
		Comparable<E> data;	//As per spec.
		
		Node(Comparable dataIn){
			left = null;
			right = null;
			data = dataIn;
		}
	}

	private Node insert(Node node, Comparable dataIn) {
		if(node==null) {
			node = new Node(dataIn);	//if empty tree, insert as root.
		}else {
			if(dataIn.compareTo(node.data)<1) {	//data is less than root, check left subtree
				node.left = insert(node.left,dataIn);
			}else {	//data is more than root, check right subtree 
				node.right = insert(node.right,dataIn);
			}
		}
		return node;	//return new reference to caller.
	}
	
	
	private void postOrderTraversal(BST<E>.Node nodeIn) {
		if(nodeIn==null) {
			return;
		}else {
			postOrderTraversal(nodeIn.left);
			postOrderTraversal(nodeIn.right);
			System.out.print(nodeIn.data + " ");
		}
	}
	

	private void inOrderTraversal(BST<E>.Node nodeIn) {
		if(nodeIn==null) {
			return;
		}else {
			inOrderTraversal(nodeIn.left);
			System.out.print(nodeIn.data + " ");
			inOrderTraversal(nodeIn.right);
		}
	}
	
	private Node findLargest(Node rootIn) {
		if(rootIn.right!=null) {
			rootIn = findLargest(rootIn.right);
		}
		return rootIn;
	}
	
	private Node removeLargest(Node rootIn) {
		if(rootIn.right !=null) {
			rootIn.right = removeLargest(rootIn.right);
		}else {
			rootIn = rootIn.left;
		}
		return rootIn;
	}

	
	public void buildTree(int[] inOrderIn, int[] postOrderIn)
	   {
		int ioStart = 0;
		int ioEnd = inOrderIn.length-1;
		int poStart = 0;
		int poEnd = postOrderIn.length-1;
		
		this.root=buildTree(inOrderIn, ioStart, ioEnd, postOrderIn, poStart, poEnd);
		
	   }
	
	public BST<E>.Node buildTree(int[] inorderArray, int ioStart, int ioEnd, int[] postorder, int poStart, int poEnd) {
		if (ioStart > ioEnd || poStart > poEnd ) {
			return null;
		}
		
		int rootData = postorder[poEnd];
		Node root = new Node(rootData);
	 
		int count = 0;
		for (int i = 0; i < inorderArray.length; i++) {
			if (inorderArray[i] == rootData) {
				count = i;
				break;
			}
		}
	 
		root.left = buildTree(inorderArray, ioStart, count - 1, postorder, poStart, poStart + count - (ioStart + 1));
		root.right = buildTree(inorderArray, count + 1, ioEnd, postorder, poStart + count- ioStart, poEnd - 1);
	 
		return root;
	}
	

}
