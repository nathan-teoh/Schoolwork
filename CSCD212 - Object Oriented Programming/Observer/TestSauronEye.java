public class TestSauronEye {
    public static void main(String[] args) {
    	
    	System.out.println("##Initializing goodguy army with [1,1,2,0]##");
    	GoodGuy gg = new GoodGuy(1,1,2,0);	//initial army
    	
        EyeOfSauron eye = new EyeOfSauron();	//subject 
        BadGuy saruman = new BadGuy(eye, "Saruman");	//observer 1
        BadGuy witchKing = new BadGuy(eye, "Witch King");	//observer 2
        eye.setEnemies(gg); //change the status of our subject 
        System.out.println("");

        //some messages should be printed by our observers here
        
        System.out.println("##Killing Saruman, then initializing new army with [4,2,2,100]##");
        gg = new GoodGuy(4,2,2,100);	//new army
        saruman.defeated(); //killed saruman, should unregister from subject
        eye.setEnemies(gg); //change the status of our subject again
        System.out.println("");
        
        //only one message should be printed here by observer 2
        
        System.out.println("##Killing WitchKing##");
        witchKing.defeated(); //killed witchking, should unregister from subject
        

    }//end main
}//end class