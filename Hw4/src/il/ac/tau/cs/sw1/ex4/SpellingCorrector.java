package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class SpellingCorrector {
	
	public static void main(String[] args)throws Exception{
		String filename=buildSentence(args);
		if(!filename.contains(".txt")){
			throw new Exception("[ERROR] FOOL! insert .txt files only!!!");	
		}
		
		File file = new File(filename);
		Scanner scanVoc = new Scanner(file);
		String[] vocabulary = scanVocabulary(scanVoc);
		scanVoc.close();
		printReadVocabulary(file.getName(),vocabulary.length);
		
		Scanner s = new Scanner(System.in); //this paragraph gets the sentence
		
		outer: while(true){
			printEnterYourSentence();
			if(s.hasNext("quit")){
				break;
		}
			String[] input = splitSentence(s.nextLine().trim());
			
			//this loop "corrects" the sentence
			int count=0;
			StringBuilder sb=new StringBuilder("");
			for (String word: input){ 
				String[][] correctionOptions = findSimilarWords(word,vocabulary);
				if(correctionOptions[0].length!=0){
					sb.append(word+" ");
					continue;
				}
				printWordIncorrect(word);
				int countZero=0;
				for(int i=1;i<correctionOptions.length;i++){
					if(correctionOptions[i].length==0){
						countZero++;
						printNumOfCorrections(0,i);
						continue;}
					printNumOfCorrections(correctionOptions[i].length,i);
					//System.out.println(Arrays.toString(correctionOptions[i]));
				}
				if(countZero==correctionOptions.length-1){
					System.out.println("no possible correction found. sorry :(");
					sb.append(word+ " ");
					continue;
				}
				
				//this code will combine the options list.
				String[] offers=returnOffers(correctionOptions);
				
				for(int i=0;i<offers.length;i++){
					printCorrectionOption(i+1,offers[i]);
				}
				
				//this loop will choose the correction.
				while(true){
				printEnterYourChoice();
				if(s.hasNext("quit")){
						break outer;
				}
				else if(s.hasNextInt()){
				String num=s.nextLine();
				int n = Integer.parseInt(num);
				if(0<n && n<offers.length+1){
					sb.append(offers[n-1]+" ");
					count++;
					break;}
				else printInvalidChoice();
				}
				else{
					printInvalidChoice();
					s.nextLine();
				}
				}
			}
			//here we got out of the for loop who runs over the sentence input.
			printCorrectSentence(sb.toString().trim());
			printNumOfCorrectedWords(count);
				}
		s.close();
	}
	
	
	
	public static String[] returnOffers(String[][] voc){
		StringBuilder sb = new StringBuilder("");
		int j=1;
		for(int i=0 ;i<8;){
			for(String val: voc[j]){
				sb.append(val+" ");
				i++; if(i==8) break;
			}
			j++;
			if(j == voc.length) break;
			}
		return sb.toString().split(" ");
	}
	
	public static String[] scanVocabulary(Scanner scanner){
		int i=0;
		StringBuilder sb = new StringBuilder(" ");
		while(i<3000 && scanner.hasNext()){
			String crnt = scanner.next();
			if (crnt.equals(" ")){
				continue;}
			crnt = crnt.toLowerCase();
			if(sb.indexOf(" "+crnt+" ")== -1){
				sb.append(crnt+" ");
				i++;
			}	
		}
		sb.deleteCharAt(0);
		String[] arr = sb.toString().split(" ");
		Arrays.sort(arr);
		return arr;
	}
	
	 
	public static int calcHammingDistance(String word1, String word2){
		int hamDis=0;
		if(word1.equals(word2)){
			return hamDis;
		}
		char[] arr1 = word1.toCharArray();
		char[] arr2 = word2.toCharArray();
		int minimum = min(arr1.length, arr2.length);
		int maximum = max(arr1.length, arr2.length);
		
		for(int i=0;i<minimum;i++){
			if(arr1[i]!=arr2[i]){
				hamDis++;
			}
		}
		hamDis += maximum-minimum;
		return hamDis;
	}
	
	public static int min(int num1, int num2){
		if (num1>=num2){
			return num2;
		}
		return num1;
	}
	public static int max(int num1, int num2){
		if (num1>=num2){
			return num1;
		}
		return num2;
	}
	
	public static String[][] findSimilarWords(String word, String[] vocabulary){
		StringBuilder sb1 = new StringBuilder("");
		StringBuilder sb2 = new StringBuilder("");
		String[][] correctionOptions = new String[3][0];
		for (String mila: vocabulary){
			int hamDis = calcHammingDistance(word,mila);
			if(hamDis==0){
				String[] noChoice = {word};
				correctionOptions[0]= noChoice;
				
			}
			if(hamDis==1){
				sb1.append(mila+" ");
			}
			if(hamDis==2){
				sb2.append(mila+" ");
			}
		}
		if(sb1.length()!=0)
		correctionOptions[1]=sb1.toString().split(" ");
		if(sb2.length()!=0)
		correctionOptions[2]=sb2.toString().split(" ");
		return correctionOptions;
	}
	
	public static String[] splitSentence(String sentence){
		StringBuilder sb = new StringBuilder("");
		String[] arr = sentence.split(" ");
		for(String word: arr){
			if(word.length()!=0){
				word = word.toLowerCase();
				sb.append(word+" ");
			}
		}
		String[] splited = sb.toString().split(" ");
		return splited;
	}
	
	public static String buildSentence(String[] words){
		StringBuilder sentence = new StringBuilder("");
		for(String word: words){
			sentence.append(word+" ");
		}
		return sentence.toString().trim();
	}
	
	public static boolean isInVocabulary(String[] vocabulary, String word){
		for(String mila: vocabulary){
			if(mila.equals(word)) return true;
		}
		return false;
	}
	
	/****  use these method to print all your output messages ****/
	public static void printWordIncorrect(String word){
		System.out.println("the word " + word + " is incorrect");
	}
	
	public static void printReadVocabulary(String vocabularyFileName, int numOfWords){
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}
	
	public static void printNumOfCorrections(int num, int distance){
		System.out.println(num+" corrections of distance " + distance);
	}
	
	public static void printEnterYourSentence(){
		System.out.println("Enter your sentence:");
	}
	public static void printEnterYourChoice(){
		System.out.println("Enter your choice:");
	}
	
	public static void printCorrectionOption(int i, String correction){
		System.out.println(i + ". " + correction);
	}
	
	public static void printInvalidChoice(){
		System.out.println("[WARNING] Invalid choice, try again.");
	}
	
	public static void printCorrectSentence(String sentence){
		System.out.println("The correct sentence is: " + sentence);
	}
	
	public static void printNumOfCorrectedWords(int numOfWords){
		System.out.println("Corrected "+ numOfWords + " words.");
	}
}
