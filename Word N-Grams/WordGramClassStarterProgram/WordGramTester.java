import java.util.*;

public class WordGramTester {
	public void testWordGram(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			System.out.println(index+"\t"+wg.length()+"\t"+wg);
		}
	}
	
	public void testWordGramEquals(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			list.add(wg);
		}
		WordGram first = list.get(0);
		System.out.println("checking "+first);
		for(int k=0; k < list.size(); k++){
			//if (first == list.get(k)) {
			  if (first.equals(list.get(k))) {
				System.out.println("matched at "+k+" "+list.get(k));
			}
		}
	}
	public void tester(){
    	String st = "this is just a test yes this is a simple test";
    	String[] prueba = st.split(" ");
    	WordGram test = new WordGram(prueba,0,prueba.length);
    	WordGram test2 = new WordGram(prueba,0,prueba.length-1);
    	System.out.println("Length test1 : "+ test.length());
    	System.out.println("Length test2 : "+ test2.length());
    	System.out.println("ToString test1 : "+ test);
    	System.out.println("ToString test2 : "+ test2);
    	System.out.println("equals false : "+ test.equals(test2));
    	System.out.println("shiftAdd  : "+ test.shiftAdd("test"));
    	System.out.println("equals true : "+ test.equals(test2));
    	System.out.println("shiftAdd  : "+ test.shiftAdd("twice"));

    }
	
}
