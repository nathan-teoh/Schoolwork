
import java.util.Hashtable;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
@SuppressWarnings("UseOfObsoleteCollectionType")
public class DictHashTable {
        
    private Hashtable<String, String> table;
    
    public DictHashTable(){
        this.table = new Hashtable<>();
    }
    
    public void insertWord(String string){
        if(isAWord(string))
            throw new IllegalArgumentException("Word " + string + " already in dictonary!");       
        table.put(string, "1");
    }
    
    public boolean isAWord(String string){
        return table.containsKey(string);
    }
}
