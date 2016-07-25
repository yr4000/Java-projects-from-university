package il.ac.tau.cs.sw1.ex5;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Book {
	
	private static final int MAX_BOOK_IN_FILE = 20000;

	
	String ISBN;
	String bookName;
	String bookAuthor;
	String yearOfPublication;
	String publisher;
	
	public Book(String ISBN, String bookName, String bookAuthor, String yearOfPUblication, String publisher){
		this.ISBN = ISBN;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.yearOfPublication = yearOfPUblication;
		this.publisher = publisher;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public String getYearOfPublication() {
		return yearOfPublication;
	}

	public String getPublisher() {
		return publisher;
	}
	
	public String toString(){
		StringBuffer sB = new StringBuffer();
		char sep = ',';
		sB.append(this.ISBN).append(sep).append(this.bookName).append(sep).append(this.bookAuthor);
		return sB.toString();
	}
	
	public static String noQuotationMark(String sentence){
		return sentence.substring(1, sentence.length());
	}
	
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @pre fileName is a legal fileName, the format of the file is as expected
	 * @post $ret is an Arrays of Book objects read from the file fileName
	 */
	public static Book[] loadBooksData(String fileName) throws Exception{
		int counter = 0;
		Book[] books = new Book[MAX_BOOK_IN_FILE];
		Scanner scanfile = new Scanner(new File(fileName));
		while(scanfile.hasNextLine()){
			if(counter==0){
				scanfile.nextLine();
				counter++;
				continue;
			}
			String[] temp = scanfile.nextLine().split("\";");
			//System.out.println(Arrays.toString(temp));
			books[counter] = new Book(noQuotationMark(temp[0]),noQuotationMark(temp[1]),noQuotationMark(temp[2]),noQuotationMark(temp[3]),noQuotationMark(temp[4]));
			counter++;
		}
		scanfile.close();
		return Arrays.copyOfRange(books, 1, counter);
	}
	
}
