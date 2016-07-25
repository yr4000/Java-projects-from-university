package il.ac.tau.cs.sw1.ex5;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;

public class BookRecommendations {
	
	private static final int NO_RATING = -1;
	private static final int AGE_GROUP_MARGINE_SIZE = 3;

	
	Book[] books;
	User[] users;
	int[][] ratings;
	
	/**
	 * 
	 * @param books 
	 * @param users
	 * @param ratings
	 * @pre ratings.length == users.length
	 * @pre ratings[0].length == books.length
	 */
	public BookRecommendations(Book[] books, User[] users, int[][] ratings){
		this.books=books;
		this.users=users;
		this.ratings=ratings;
	}
	
	/**
	 * 
	 * @param fileName
	 * @param usersArray
	 * @param booksArray
	 * @return
	 * @throws Exception
	 * @pre usersArray.length != 0
	 * @pre booksArray.length != 0
	 * @pre fileName is a legal fileName, the format of the file is as expected
	 * @post $ret.length = usersArray.length
	 * @post $ret[0].length = booksArray.length
	 * @post $res[i][j] == the rating of usersArray[i] to the booksArray[j]
	 */
	public static int[][] loadRatingsData(String fileName, User[] usersArray, Book[] booksArray) throws Exception{
		int[][] ratemat=new int[usersArray.length][booksArray.length];
		for(int k=0;k<ratemat.length;k++){//Initialise the default index of the matrix
			Arrays.fill(ratemat[k], -1);}
		int crntUser = 0;
		int index = 0;
		Scanner scanfile = new Scanner(new File(fileName));
		scanfile.nextLine();
		while(scanfile.hasNextLine()){ //starts run over the rate file
			int j=0;
			String[] data = scanfile.nextLine().split(";");
			if(crntUser!=Integer.parseInt(noQuotationMark(data[0]))){ //checks if it is the same user to avoid double search
				crntUser = Integer.parseInt(noQuotationMark(data[0]));
				for(int i=0;true;i++){
					if(crntUser==usersArray[i].getUserID()){
						index=i;
						break;
					}
				}
			}
			while(true){//finds the index of the book...
				if (noQuotationMark(data[1]).equals(booksArray[j].getISBN())){
					break;}
				j++;
			}
			ratemat[index][j]=Integer.parseInt(noQuotationMark(data[2]));
		}
		scanfile.close();
		return ratemat;
		
	}
	public static String noQuotationMark(String sentence){
		return sentence.substring(1, sentence.length()-1);
	}
	
	/**
	 * 
	 * @param userIndex
	 * @return
	 * @pre userIndex >0 0 && userIndex < this.users.length
	 * @post $ret = average rating of all the books this.users[userIndex] rated
	 */
	public double getAverageRatingForUser(int userIndex){
		double sum=0.0;
		double counter=0.0;
		for(int i=0;i<ratings[0].length;i++){
			if(ratings[userIndex][i]>=0){
				sum+=ratings[userIndex][i];
				counter++;
			}
		}
		return sum/counter;
		
	}
	
	/**
	 * 
	 * @param bookIndex
	 * @return
	 * @pre bookIndex >= 0 && bookIndex < this.books.length
	 * @post $ret = NO_RATING if no user had rated this.books[bookIndex]
	 * @post otherwise, $ret = average rating of this.books[bookIndex] among all the users who rated it
	 */
	public double getAverageRatingForBook(int bookIndex){
		double sum=0.0;
		double counter=0.0;
		for(int i=0;i<ratings.length;i++){
			if(ratings[i][bookIndex]>=0){
				sum+=ratings[i][bookIndex];
				counter++;
			}
		}
		if(counter==0.0) return NO_RATING;
		return sum/counter;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 * @pre there exist i s.t. (such that) this.users[i] == user && user.age != NO_AGE
	 * @post $ret.lenght = this.users.lenght
	 * @post $ret[i] == true <=> this.users[i] in the user group of "user" ( user.age - AGE_GROUP_MARGINE_SIZE  <= this.users.age <= user.age + AGE_GROUP_MARGINE_SIZE
	 */
	public boolean[] getUsersInAgeGroup(User user){
		boolean[] boolArr = new boolean[users.length];
		for(int i=0;i<users.length;i++){
			if (users[i].age>= user.age-AGE_GROUP_MARGINE_SIZE && users[i].age<=user.age+AGE_GROUP_MARGINE_SIZE)
				boolArr[i]=true;
		}
		return boolArr;
	}
	
	/**
	 * 
	 * @param bookIndex
	 * @param ageGroup
	 * @return
	 * @pre ageGroup.length == this.users.length
	 * @pre bookIndex >= 0 && bookIndex < this.books.length
	 * @post $res = NO_RATING if there is no user in the age group that rated book[bookIndex]
	 * @post otherwise, $res = average ratings for all users this.users[i] s.t. ageGroup[i] == true
	 */
	public double getAverageRatingForBookInAgeGroup(int bookIndex, boolean[] ageGroup){
		double sum=0.0;
		double counter=0.0;
		for(int i=0;i<ratings.length;i++){
			if(ageGroup[i])
			if(ratings[i][bookIndex]>=0){
				sum+=ratings[i][bookIndex];
				counter++;
			}
		}
		if(counter==0.0) return NO_RATING;
		return sum/counter;
	}
	/**
	 * 
	 * @param user
	 * @return
	 * @pre there exist i s.t. this.users[i] == user  && user.age != NO_AGE
	 * @pos $res = NO_RATING if there is no user in the age group that rated book[bookIndex]
	 * @post $res = this.books[i] s.t. average for book[i] in the age group of user is maximum
	 */
	public Book getHighestRatedBookInAgeGroup(User user){
		Book winner=null;
		double highestAverage = 0.0;
		boolean[] ages = getUsersInAgeGroup(user);
		for(int i=0;i<ratings[0].length;i++){
			double temp=getAverageRatingForBookInAgeGroup(i,ages);
			if(temp>highestAverage){
				winner = this.books[i];
				highestAverage=temp;
			}	
		}
		return winner;
	}
	
	
	/**
	 * 
	 * @param user
	 * @param fileName
	 * @throws Exception
	 * @pre fileName is a legal fileName, the format of the file is as expected
	 * @pre there exist i s.t. this.users[i] == user  && user.age != NO_AGE
	 */
	public void printRecommendationToFile(User user, String fileName) throws Exception{
	StringBuilder sb= new StringBuilder("");
	Book recommendation = getHighestRatedBookInAgeGroup(user);
	sb.append(getRecommendedBookString(recommendation)).append("\r\n");
	boolean[] ages = getUsersInAgeGroup(user);
	int index=0;
	for(int i=0;true;i++){
		if(recommendation.ISBN.equals(this.books[i].ISBN)){
			index=i;
			break;
		}
	}
	sb.append( getRecommendedBookAverageInUserGroup(getAverageRatingForBookInAgeGroup(index,ages))).append("\r\n");
	for(int i=0;true;i++){
		if(user.getUserID()==this.users[i].getUserID()){
			index=i;
			break;
		}
	}
	sb.append(getRecommendedBookAverageFoAllUsers(getAverageRatingForBook(index)));
	FileWriter fw= new FileWriter(new File(fileName));
	fw.write(sb.toString());
	fw.close();
	
	
	
	}
	
	
	
	private String getRecommendedBookString(Book b){
		return "The recommended Book for you is: " + b.toString();
	}
	
	private String getRecommendedBookAverageInUserGroup(double average){
		return String.format("The book's average rating among its age group is: %.2f",average);
	}
	
	private String getRecommendedBookAverageFoAllUsers(double average){
		return String.format("The book's average rating among all the users is: %.2f",average);
	}
	
}
