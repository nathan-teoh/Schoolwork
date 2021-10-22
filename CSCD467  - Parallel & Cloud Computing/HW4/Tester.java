import java.util.LinkedList;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie2 myTrie = new Trie2();
		
		String[] toAdd = {"apple","bike","bake","pen","did","ape","child","cat","file","hello","he","hell"};
		
		for(String txt : toAdd) {
			myTrie.insertString(txt);
		}
		
		LinkedList wordsStartWithAp = myTrie.wordsPrefixedBy("ap");	//expect apple, ape
		LinkedList wordsStartWithHe = myTrie.wordsPrefixedBy("he");	//expect he, hell, hello
		
		System.out.println(wordsStartWithAp);
		System.out.println(wordsStartWithHe);
	}

}
