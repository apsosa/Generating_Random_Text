
/**
 * Write a description of class EfficientMarkovModel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel
{
    private int n;
    private HashMap<String,ArrayList<String>> map;
    
    public EfficientMarkovModel(int n_character) {
        myRandom = new Random();
        n = n_character;
        map = new  HashMap<String,ArrayList<String>>();
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
        buildMap();
        for(int k=0; k < numChars-n; k++){
            // System.out.println("Key : "+key+" "+follows);
            if (!map.containsKey(key)) {
                // System.out.println("ciclos: "+k);
                break;
            }
            ArrayList<String> follows = map.get(key);
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

    private void buildMap(){
        int pos = 0;
        while(pos + n <= myText.length()){
            String key = myText.substring(pos,pos+n);
            if (!map.containsKey(key)) {
                ArrayList<String> follows = getFollows(key);
                map.put(key,follows);
            }
            pos++;
        }

    }

    public ArrayList<String> getFollows(String key){
        if (!map.containsKey(key)) {
            ArrayList<String> follows = new ArrayList<String>();
            int pos = 0;
            while(pos < myText.length()){
                int start = myText.indexOf(key,pos);
                if (start == -1) {
                    // System.out.println("pos: "+pos);
                    break;
                }
                if (start+ key.length()+1 > myText.length()-1) {
                    break;
                }
                String next = myText.substring(start+key.length(),start+ key.length()+1);
                follows.add(next);
                pos = start + key.length();
            }
            return follows;
        }else{
            return map.get(key);
        }
        
    }

    public void printHashMapinfo(){
        //MarkovWord markovWord = new MarkovWord(3); 
        System.out.println("Number of keys in the HashMap is " + map.size());
        if (map.size() < 100) {
            for (String key : map.keySet() ) {
                System.out.println(key+":"+map.get(key));
            }
        }
        int max = 0;
        String maxkey = "";
        for (String key : map.keySet() ) {
            int currValue =  map.get(key).size();
            if (currValue > max) {
                max = map.get(key).size();
                maxkey = key;
            }
        }
        System.out.println("Size of the largest value in the HashMap:" + max);
        System.out.println("keys that have the maximum size value.:" + maxkey);

        
        
    }

    public String toString(){
        return "is the EfficientMarkovModel class of orden "+n+".";
    }

}
