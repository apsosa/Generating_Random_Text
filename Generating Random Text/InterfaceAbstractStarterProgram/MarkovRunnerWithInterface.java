
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 25;
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,seed);

    }

    public void testHashMap(){
    	FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 531;
    	EfficientMarkovModel markov = new EfficientMarkovModel(5);
        runModel(markov, st, size,seed);
    	// String text = "yes-this-is-a-thin-pretty-pink-thistle";
    	// int seed = 42;
    	// markov.setTraining(text);
     	// markov.setRandom(seed);
        // System.out.println("running with " + markov);
        // String st= markov.getRandomText(50);
        // System.out.println(st);
        markov.printHashMapinfo();
    }
    public void compareMethods(){
    	FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 42;
        EfficientMarkovModel mz = new EfficientMarkovModel(2);
        MarkovModel mOne = new MarkovModel(2);
    	long timeBefore = System.nanoTime();
        runModel(mz, st, size,seed);
    	long timeAfter = System.nanoTime();
    	long eff =  timeAfter - timeBefore;

    	timeBefore = System.nanoTime();
        runModel(mOne, st, size,seed);
        timeAfter = System.nanoTime();
    	long model =  timeAfter - timeBefore;

    	System.out.println("Time in nanoseconds of "+mz+" "+ eff);
    	System.out.println("Time in nanoseconds of "+mOne+" "+ model);
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
		System.out.println("\n----------------------------------");
	}
	
}
