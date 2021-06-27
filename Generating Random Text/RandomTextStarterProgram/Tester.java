
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class Tester
{
    public void testGetFollows(){
        MarkovOne m1 = new MarkovOne();
        String set = "this is a test yes this is a test.";
        m1.setTraining(set);
        ArrayList<String> follows = m1.getFollows("t");
        for (String s : follows) {
            System.out.println(s);
        }
    }
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setRandom(42);
        markov.setTraining(st);
        ArrayList<String> m1 = markov.getFollows("he");
        System.out.println("Size of getFollows : "+m1.size());

        // for(int k=0; k < 3; k++){
        //     String text = markov.getRandomText(500);
        //     printOut(text);
        // }
    }
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
    }
      
}
