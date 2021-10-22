
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
public class DictPrefixTree {
    //--member fields--//
    private final Node root;
    
    public DictPrefixTree(){
        this.root = new Node();
    }
    
    public void insertWord(String string){
        this.insertWord(string, root);
    }
    //Recursive method that inserts a new word into the prefix/trie tree.
    private void insertWord(String string, Node node) {
        //insert into the next child (or create the relevant child 
        //node if it doesn't already exist!
        Node next; 
        Character key = string.charAt(0);
        if(node.children.containsKey(key)){
            next = node.children.get(key);
        }
        else
        {
            next = new Node();
            node.children.put(key, next);
        }

        //now trim the string and recurse!
        if((string.length() > 1))
            insertWord(string.substring(1), next);
        else
            next.isEndOfWord = true;
    }

    public boolean isAWord(String word){
        return isAWord(word, root);
    }
    //recursive method that exhaustivly checks if the given string is a word
    private boolean isAWord(String string, Node node){
        if(string.length()== 0)
            return node.isEndOfWord;
        
        if(node.children.containsKey(string.charAt(0)))
            return isAWord(string.substring(1), node.children.get(string.charAt(0)));
        
        return false;
    }
    
    //--private inner node class--/
    private class Node{
        public boolean isEndOfWord;
        public HashMap<Character, Node> children;
        
        public Node(){
            this.isEndOfWord = false;
            this.children = new HashMap<>();
        }
    }
}
