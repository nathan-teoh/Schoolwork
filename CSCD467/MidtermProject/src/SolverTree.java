
import java.util.ArrayList;
import java.util.List;

public class SolverTree {
    private Node root;

    public SolverTree(int[] numbers){
        this.root = new Node("");
        this.BuildTree(numbers);
    }
    
    private void BuildTree(int[] numbers){
        List<Node> list = new ArrayList<>();
        list.add(root);
        
        for(int i = 0; i < numbers.length; i++){
            list = generateNewLayer(numbers[i], list);
        }        
        
    }
    private List<Node> generateNewLayer(int numberIn, List<Node> nodeIn){
      //input sanitization taken care by tester. We can assume that all input here is clean and within spec.
              
        List<Node> stringBuilder = new ArrayList<>();
        for(int i = 0; i < nodeIn.size(); i++){
            Node curr = nodeIn.get(i); 
            if(numberIn==2){
              stringBuilder.add(curr.C1 = new Node("a"));
              stringBuilder.add(curr.C2 = new Node("b"));
              stringBuilder.add(curr.C3 = new Node("c"));
            }
            else if(numberIn==3){
              stringBuilder.add(curr.C1 = new Node("d"));
              stringBuilder.add(curr.C2 = new Node("e"));
              stringBuilder.add(curr.C3 = new Node("f"));
            }
            else if(numberIn==4){
              stringBuilder.add(curr.C1 = new Node("g"));
              stringBuilder.add(curr.C2 = new Node("h"));
              stringBuilder.add(curr.C3 = new Node("i"));
            }
            else if(numberIn==5){
              stringBuilder.add(curr.C1 = new Node("j"));
              stringBuilder.add(curr.C2 = new Node("k"));
              stringBuilder.add(curr.C3 = new Node("l"));
            }
            else if(numberIn==6){
              stringBuilder.add(curr.C1 = new Node("m"));
              stringBuilder.add(curr.C2 = new Node("n"));
              stringBuilder.add(curr.C3 = new Node("o"));
            }
            else if(numberIn==7){
              stringBuilder.add(curr.C1 = new Node("p"));
              stringBuilder.add(curr.C2 = new Node("q"));
              stringBuilder.add(curr.C3 = new Node("r"));
              stringBuilder.add(curr.C4 = new Node("s"));
            }
            else if(numberIn==8){
              stringBuilder.add(curr.C1 = new Node("t"));
              stringBuilder.add(curr.C2 = new Node("y"));
              stringBuilder.add(curr.C3 = new Node("v"));
            }
            else{
              stringBuilder.add(curr.C1 = new Node("w"));
              stringBuilder.add(curr.C2 = new Node("x"));
              stringBuilder.add(curr.C3 = new Node("y"));
              stringBuilder.add(curr.C4 = new Node("z"));
            }
        }
        return stringBuilder;
    }
    
 
    public List<String> possibleWords(){
        if(root == null){
            System.out.println("No words.");
            return null;
        }else {
        	List<String> toRet = new ArrayList<>();
        	getPath(root,"",toRet);
        	return toRet;
        }
    }

    private void getPath(Node nodeIn, String pathIn, List<String> stringListIn){
        if(nodeIn == null)
            return;
        pathIn += nodeIn.data;
        if(nodeIn.checkIfLeaf()){
            stringListIn.add(pathIn.trim());
            return;
        }
        else {
           getPath(nodeIn.C1, pathIn,stringListIn);
           getPath(nodeIn.C2, pathIn,stringListIn);
           getPath(nodeIn.C3, pathIn,stringListIn);
           getPath(nodeIn.C4, pathIn,stringListIn);
        }
    }

    private class Node{
        public String data;
        public Node C1,C2,C3,C4;
        public Node(String dataIn){
            this.data = dataIn;
        }
        
        public boolean checkIfLeaf(){
        	if(C1 == null && C2 == null && C3 == null && C4==null) {
        		return true;
        	}else {
        		return false;
        	}
        }
    }
}
