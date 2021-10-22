
import java.util.Hashtable;

public class DictHashTable {
        
    private Hashtable<String, String> table;
    
    public DictHashTable(){
        this.table = new Hashtable<>();
    }
    
    public void addWord(String string){
        if(checkWord(string)) {
        	System.out.println("Word already exists!");
        }else {
        	table.put(string,"ok");
        }
    }
 
    public boolean checkWord(String string){
        return table.containsKey(string);
    }
}
