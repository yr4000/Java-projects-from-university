package il.ac.tau.cs.sw1.ex4;

import java.util.Arrays;
import java.util.Scanner;

public class SpellingCorrectorTest {
	public static void main(String[] args){
		
		//sentence splitting/building
		String e1 = "this is a test";
		String[] words = SpellingCorrector.splitSentence(e1);
		if (!Arrays.equals(words, new String[] {"this", "is", "a", "test"})){
			System.out.println("ERROR1");
		}
		String sent1 = SpellingCorrector.buildSentence(new String[] {"this", "is", "a", "test"});
		if (!sent1.equals(e1)){
			System.out.println("ERROR2");
		}
		
		//vocabulary
		String vocString = "this is a test this it it";
		Scanner scanner = new Scanner(vocString); //this scanner read the content of e1
		String[] vocabulary = SpellingCorrector.scanVocabulary(scanner);
		scanner.close();
		//System.out.println(Arrays.toString(vocabulary));
		if (!Arrays.equals(vocabulary, new String[] {"a", "is", "it", "test", "this"})){
			System.out.println("ERROR3");
			
		}
		if (!SpellingCorrector.isInVocabulary(vocabulary, "a")){
			System.out.println("ERROR4");
		}
		
		//hamming distance
		if (SpellingCorrector.calcHammingDistance("is", "is") != 0){
			System.out.println("ERROR5");		
		}
		if (SpellingCorrector.calcHammingDistance("beast", "test") != 4){
			System.out.println("ERROR6");		
		}
		
		//familiar similarWords
		String[][] similarWords = SpellingCorrector.findSimilarWords("in", vocabulary);
		if (similarWords[0].length != 0){
			System.out.println("ERROR7");	
		}
		if (similarWords[1].length != 2){ //should contain "is", "it"
			System.out.println("ERROR8");	
		}
		if (similarWords[2].length != 1){ //should contain "at"
			System.out.println("ERROR9");	
		}
		
		System.out.println("Done!");

	}
}
