
/**
 * Write a description of class MarkovWord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class MarkovWord implements IMarkovModel
{
    // instance variables - replace the example below with your own
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    /**
     * Constructor for objects of class MarkovWord
     */
     public MarkovWord(int order) {
        myRandom = new Random();
        myOrder  = order;
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
        
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
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

}
