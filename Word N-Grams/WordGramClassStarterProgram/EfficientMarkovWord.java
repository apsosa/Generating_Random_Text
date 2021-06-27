
/**
 * Write a description of class EfficientMarkovWord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class EfficientMarkovWord implements IMarkovModel
{
    // instance variables - replace the example below with your own
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<String,ArrayList<String>> myMap;
    /**
     * Constructor for objects of class MarkovWord
     */
     public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder  = order;
        myMap = new  HashMap<String,ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int start = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText,start,myOrder);
        for (int k = 0 ;k < kGram.length(); k++) {
            sb.append(kGram.wordAt(k));
            sb.append(" ");
        }
        // System.out.println("First Key : "+ kGram.toString());
        buildMap();
        for(int k=0; k < numWords - myOrder; k++){
            if (!myMap.containsKey(kGram.toString())) {
                // System.out.println("ciclos: "+k);
                break;
            }
            ArrayList<String> follows = myMap.get(kGram.toString());
            // System.out.println("Key : "+kGram.toString()+" "+follows);
            if (follows.size() == 0) {
                break;
            }
            int index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            kGram = kGram.shiftAdd(next);
            sb.append(next);
            sb.append(" ");
        }
        
        return sb.toString().trim();
    }
    private int indexOf(String[] words, WordGram target, int start){
        // System.out.println("First Key : "+ target.toString());
        // System.out.println("start : "+ start);
        for (int k = start; k <= words.length - target.length() ;k++ ) {
            int mathes = 0;
            for (int i = k; i < k + target.length(); i++) {
                // System.out.println("word at pos K : "+ words[i]);
                // System.out.println("target at pos i-K : "+ target.wordAt(i-k));
                // System.out.println("mathes: "+ mathes);
                if (words[i].equals(target.wordAt(i-k))) {
                    mathes++;
                }
            }
            if (mathes == target.length()) {
                return k;
            }
        }
        return -1;

    }
    
    public void testerIndexOF(){

        /*
        String test = "this is just a test yes this is a simple test";
        String [] toArray =test.split(" ");
        System.out.println(indexOf(toArray,"this",0));
        System.out.println(indexOf(toArray,"this",3));
        System.out.println(indexOf(toArray,"frog",0));
        System.out.println(indexOf(toArray,"frog",5));
        System.out.println(indexOf(toArray,"simple",2));
        System.out.println(indexOf(toArray,"test",5));
        */
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText,kGram,pos);
            // System.out.println("start: "+start);
            if (start == -1) {
                break;
            }
            if (start + kGram.length() > myText.length-1) {
                break;
            }
            String next = myText[start + kGram.length()];
            follows.add(next);
            pos = start + kGram.length();
        }
        return follows;
    }


    private void buildMap(){
        int pos = 0;
        while(pos + myOrder <= myText.length){
            WordGram kGram = new WordGram(myText,pos,myOrder);
            // System.out.println("pos first key: "+pos);
            // System.out.println("first key: "+kGram.toString());
            if (!myMap.containsKey(kGram.toString())) {
                ArrayList<String> follows = getFollows(kGram);
                myMap.put(kGram.toString(),follows);
            }
            pos++;
        }

    }

    public void printHashMapinfo(){
        
        System.out.println("Number of keys in the HashMap is " + myMap.size());
        if (myMap.size() < 100) {
            for (String kGram : myMap.keySet() ) {
                System.out.println(kGram+":"+myMap.get(kGram));
            }
        }
        int max = 0;
        String maxHaskey = "";
        for (String kGram : myMap.keySet() ) {
            int currValue =  myMap.get(kGram).size();
            if (currValue > max) {
                max = myMap.get(kGram).size();
                maxHaskey = kGram;
            }
        }
        System.out.println("Size of the largest value in the HashMap:" + max);
        System.out.println("keys that have the maximum size value.:" + maxHaskey); 
        
    }

}
