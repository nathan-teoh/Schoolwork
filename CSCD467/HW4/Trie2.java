import java.util.*;
import java.util.Map.Entry;

public class Trie2 {
	
	private class TrieNode {
		Map<Character, TrieNode> children = new TreeMap<>();//TreeMap is java build-in structure, 
		boolean aword = false;		//Basically it acts like a Hashtable or Hashmap, establishing a mapping between Key and Value
		                                //Unlike hash table, keys in TreeMap are sorted!
	}
	
	private TrieNode root;
	public Trie2() {
		this.root = new TrieNode();
	}

	public void insertString(String s) {
		insertString(root, s);
	}
	
	private void insertString(TrieNode root, String s) {
		TrieNode cur = root;
		for (char ch : s.toCharArray()) {
			TrieNode next = cur.children.get(ch);
			if (next == null)
				cur.children.put(ch, next = new TrieNode());
			cur = next;
		}
		cur.aword = true;
	}
	
	public void printSorted() {
		printSorted(root, "");
	}

	private void printSorted(TrieNode node, String s) {
		if (node.aword) {
			System.out.println(s);
		}
		for (Character ch : node.children.keySet()) {
			printSorted(node.children.get(ch), s + ch);
		}
	}
	
	public boolean findWord(String s) {
		return findWord(root, s);
	}
	
	private boolean findWord(TrieNode node, String s) {
		if(s != null) {
			String rest = s.substring(1); //rest is a substring of s, by excluding the first character in s
			char ch = s.charAt(0);        //ch is the first letter of s
			TrieNode child = node.children.get(ch);	//return the child that ch associated with. 
			if(s.length() == 1 && child != null) //if s contains only one letter, and current node has a child associated with that letter, we find the prefix in Trie!
				return true;	                 //base case
			if(child == null)
				return false;
			else
				return findWord(child, rest);    //recursive, In this way, we follow the path of the trie from root down towards leaf
		}
		return false;
	}
	
	public LinkedList wordsPrefixedBy(String p) {
		 return wordsPrefixedBy(this.root,p);
	}
	
	private LinkedList wordsPrefixedBy(TrieNode root, String p) {
		LinkedList LL = wordsPrefixedBy(root, "", new LinkedList()), NL = new LinkedList();
		
		for(int i = 0; i < LL.size()-1;i++) {
			if(((String) LL.get(i)).startsWith(p)) {
				NL.addLast(LL.get(i));
			}
		}
		return NL;
	}
	
	private LinkedList wordsPrefixedBy(TrieNode root, String p, LinkedList LL) {
		if (root.aword) {
			LL.addLast(p);
		}
		for(Character ch : root.children.keySet()) {
			wordsPrefixedBy(root.children.get(ch), p+ch, LL);
		}
		return LL;
	}
	

	// Usage example
	public static void main(String[] args) {
		
		Trie2 tr = new Trie2();
		
		tr.insertString("apple");
		tr.insertString("bike");
		tr.insertString("bake");
		tr.insertString("pen");
		tr.insertString("ape");
		
		
		System.out.print(tr.wordsPrefixedBy("ap"));
		
		System.out.println(tr.findWord("ant"));
		System.out.println(tr.findWord("an"));
		System.out.println(tr.findWord("hello"));
		System.out.println(tr.findWord("cant"));
		System.out.println(tr.findWord("hig"));
		System.out.println(tr.findWord("he"));
		
		//tr.printSorted();
	}
}
