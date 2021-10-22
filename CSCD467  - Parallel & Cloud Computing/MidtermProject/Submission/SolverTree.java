
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
public class SolverTree {
       
    //--member fields--//
    private Node root;
    
    //--construction--//
    public SolverTree(int[] numbers){
        this.root = new Node("");
        this.BuildTree(numbers);
    }
    
    private void BuildTree(int[] numbers){
        List<Node> list = new ArrayList<>();
        list.add(root);
        
        for(int i = 0; i < numbers.length; i++){
            list = AddLayer(numbers[i], list);
        }        
        
    }
    private List<Node> AddLayer(int number, List<Node> nodeFrontier){
        if(number < 1 || number > 9)
            throw new IllegalArgumentException(number + " cannot be maped to a letter!");
        
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < nodeFrontier.size(); i++){
            Node curr = nodeFrontier.get(i);
            switch(number){
                case 2:
                    list.add(curr.child1 = new Node("a"));
                    list.add(curr.child2 = new Node("b"));
                    list.add(curr.child3 = new Node("c"));
                    
                    break;
                case 3:
                    list.add(curr.child1 = new Node("d"));
                    list.add(curr.child2 = new Node("e"));
                    list.add(curr.child3 = new Node("f"));
                    break;
                case 4:
                    list.add(curr.child1 = new Node("g"));
                    list.add(curr.child2 = new Node("h"));
                    list.add(curr.child3 = new Node("i"));
                    break; 
                case 5:
                    list.add(curr.child1 = new Node("j"));
                    list.add(curr.child2 = new Node("k"));
                    list.add(curr.child3 = new Node("l"));
                    break;
                case 6:
                    list.add(curr.child1 = new Node("m"));
                    list.add(curr.child2 = new Node("n"));
                    list.add(curr.child3 = new Node("o"));
                    break;
                case 7:
                    list.add(curr.child1 = new Node("p"));
                    list.add(curr.child2 = new Node("q"));
                    list.add(curr.child3 = new Node("r"));
                    list.add(curr.child4 = new Node("s"));
                    break; 
                case 8:
                    list.add(curr.child1 = new Node("t"));
                    list.add(curr.child2 = new Node("y"));
                    list.add(curr.child3 = new Node("v"));
                    break; 
                case 9:
                    list.add(curr.child1 = new Node("w"));
                    list.add(curr.child2 = new Node("x"));
                    list.add(curr.child3 = new Node("y"));
                    list.add(curr.child4 = new Node("z"));
                    break; 
            }
        }
        return list;
    }
    
 
    public List<String> getPotentialWords(){
        if(root == null){
            System.out.println("BST is empty!");
            return null;
        }

        List<String> possibleStrings = new ArrayList<>();
        printAllPathInner(root, "", possibleStrings);
        
        return possibleStrings;
    }

    private void printAllPathInner(Node node, String path, List<String> possibleStrings){
        if(node == null)
            return;

        path += node.data;
        if(node.isLeaf()){
            possibleStrings.add(path.trim());
            return;
        }
        else {
            printAllPathInner(node.child1, path,possibleStrings);
            printAllPathInner(node.child2, path,possibleStrings);
            printAllPathInner(node.child3, path,possibleStrings);
            printAllPathInner(node.child4, path,possibleStrings);

        }
    }
    
    //--private inner node class--//
    private class Node{
        public String data;
        
        public Node child1;
        public Node child2;
        public Node child3;
        public Node child4;
     
        public Node(String data){
            this.data = data;
        }
        
        public boolean isLeaf(){
            return (child1 == null && child2 == null && child3 == null && child4 == null);
        }
    }
}
