
/**
 * Write a description of class MarkovModel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
public class MarkovModel extends AbstractMarkovModel
{
    private int n;
    
    public MarkovModel(int n_character) {
        myRandom = new Random();
        n = n_character; 
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(index,index+n);
        sb.append(key);
        for(int k=0; k < numChars-n; k++){
            ArrayList<String> follows = getFollows(key);
            // System.out.println("Key : "+key+" "+follows);
            if (follows.size() == 0) {
                // System.out.println("ciclos: "+k);
                break;
            }
            int currIndex = myRandom.nextInt(follows.size());
            String next = follows.get(currIndex);
            // System.out.println("next : "+next);
            sb.append(next);
            key = key.substring(1)+next;   
        }
        return sb.toString();
    }
    public String toString(){
        return "MarkovModel of order "+n+".";
    }


}
