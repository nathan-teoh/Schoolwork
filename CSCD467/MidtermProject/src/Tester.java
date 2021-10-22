
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester {
    
    private static DictPrefixTree DPT = new DictPrefixTree();
    private static DictHashTable DHT = new DictHashTable();
    
    private static boolean constructDict(){
        System.out.println("Attempting to load dictionary file..");
        String dictionaryName = "dictionary.txt";
        String temp = null;
        BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(dictionaryName));	//try to open our dictionary file
				while((temp = br.readLine()) != null){	//while dictionary has line
				    temp = temp.split(",")[0];	//we add that to our string
				    //and add to our dictionaries
				    DHT.addWord(temp);	
				    DPT.addWord(temp);
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}   
			return false;

    }
    
    public static void main(String[] args){
    	boolean constructed = constructDict();
    	Scanner scanner = new Scanner(System.in);
    	if(constructed) {
    		while(constructed) {
    			System.out.println("Please enter integers between 2 and 9. Q to exit.");
                String userInput = scanner.nextLine();
                if(userInput.toLowerCase().equalsIgnoreCase("q")) {	//quit option. convert everything to lowercase.
                	System.exit(0);
                }
                int[] combination = new int[userInput.length()];
                    for(int i = 0; i < combination.length; i++){	//loop to check all the numbers. Make sure all of them are > 2.
                        combination[i] = Integer.parseInt(userInput.charAt(i)+"");	//add every number to array
                        if(combination[i] < 2){	//check every array index if it's less than 2
                            throw new IllegalArgumentException("!!INVALID INPUT!!");	//if it's not
                        }
                    }  
                    SolverTree solver = new SolverTree(combination);	//create a new solver tree with user combination
                    List<String> potentialWords = solver.possibleWords();	//get a list of potential words from solver.
                    //checking for actual words
                    List<String> prefixTreeDict = new ArrayList<>();		//using prefix
                    for(int i = 0; i < potentialWords.size(); i++) {
                    	if(DPT.checkWord(potentialWords.get(i))){
                    		prefixTreeDict.add(potentialWords.get(i));
                    	}
                    }

                    System.out.println("Found the following words in the prefix dict: " + prefixTreeDict);
                    List<String> hashList = new ArrayList<>();	//similar to above, but use 
                    for(int i = 0; i < potentialWords.size(); i++) {
                    	if(DHT.checkWord(potentialWords.get(i))) {
                    		hashList.add(potentialWords.get(i));
                    	}
                    }
                    System.out.println("Found the following words in the hash dict: " + hashList);
    		}
    	}else {
    		System.out.println("Failed to load dictionary file!");
    	}
    }
  
}
