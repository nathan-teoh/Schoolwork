
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
public class Tester {
    
    private static DictPrefixTree prefixDict;
    private static DictHashTable hashDict;
    
    public static void main(String[] args){
        try{
        fillDictionaries();
        } catch (Exception ex){
            System.out.println("failed to load dictionary!");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter any number of integers between 2 and 9. Press Q to exit");
            
            String line = scanner.nextLine();
            if(line.equals("Q") || line.equals("q"))
                break;
            
            int[] numbers = new int[line.length()];
            try{
                for(int i = 0; i < numbers.length; i++){
                    numbers[i] = Integer.parseInt(line.charAt(i)+"");
                    
                    if(numbers[i] < 2){
                        System.out.println("I said any number between 2 and 9.");
                        throw new IllegalArgumentException();
                    }
                }  

                SolverTree solver = new SolverTree(numbers);
                List<String> potentialWords = solver.getPotentialWords();
                
                //find the words that are actually words
                List<String> prefixList = new ArrayList<>();
                for(String word : potentialWords){
                    if(prefixDict.isAWord(word))
                        prefixList.add(word);
                }
                System.out.println("Prefix Words: " + prefixList);
                
                List<String> hashList = new ArrayList<>();
                for(String word : potentialWords){
                    if(hashDict.isAWord(word))
                        hashList.add(word);
                }
                System.out.println("Hash Words: " + hashList);

            } catch (Exception e){
                System.out.println("bad data");
                e.printStackTrace();
            }
        }
    }
    
    private static void fillDictionaries() throws FileNotFoundException, IOException{
            
        System.out.print("Loading dictionary from file...");

        prefixDict = new DictPrefixTree();        
        hashDict = new DictHashTable();
        
        String fileName = "dictionary.txt";
        String line = null;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while((line = bufferedReader.readLine()) != null){
                line = line.split(",")[0];

                prefixDict.insertWord(line);
                hashDict.insertWord(line);
            }           

    }
    
    
}
