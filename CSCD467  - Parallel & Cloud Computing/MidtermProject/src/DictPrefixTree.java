
import java.util.HashMap;

public class DictPrefixTree {

    private Node root;
    
    public DictPrefixTree(){
        this.root = new Node();
    }

    //HELPER METHODS BELOW
    public boolean checkWord(String word){
        return checkWord(word, root);
    }
    
    public void addWord(String stringIn){
        this.addWord(stringIn, root);
    }
    
    private void addWord(String stringIn, Node nodeIn) {
        Node cur = new Node(); 
        Character ch = stringIn.charAt(0);	//get first character of string
        if(nodeIn.childs.containsKey(ch)){	//if the node contains a child with the character as key
            cur = nodeIn.childs.get(ch);	//cur gets the node associated with the key
        }else{	//if it doesnt contain child with key
            nodeIn.childs.put(ch, cur);	//we add
        }
        if((stringIn.length() > 1))	//if there are more charcters left in the string
            addWord(stringIn.substring(1), cur);	//recursive call
        else
            cur.eow = true;	//otherwise signify end of line.
    }


    //PRIVAT METHODS BELOW
    private boolean checkWord(String stringIn, Node nodeIn){
        if(stringIn.length()== 0)
            return nodeIn.eow;
        
        if(nodeIn.childs.containsKey(stringIn.charAt(0)))
            return checkWord(stringIn.substring(1), nodeIn.childs.get(stringIn.charAt(0)));
        
        return false;
    }
    

    private class Node{
        public boolean eow;
        public HashMap<Character, Node> childs;
        
        public Node(){
            this.eow = false;
            this.childs = new HashMap<>();
        }
    }
}
