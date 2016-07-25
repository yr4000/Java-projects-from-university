package il.ac.tau.cs.sw1.ex5;

import il.ac.tau.cs.sw1.ex5.User;
import il.ac.tau.cs.sw1.ex5.Book;
import il.ac.tau.cs.sw1.ex5.BookRecommendations;
public class BookRecommendationsTester {
	public static String USERS_CSV_FILE = "BX-Users.csv"; // edit with the correct directories on your computer
	public static String BOOKS_CSV_FILE = "BX-Books.csv"; // edit with the correct directories on your computer
	public static String RATINGS_CSV_FILE = "BX-Ratings.csv"; // edit with the correct directories on your computer
	
	public static void main(String[] args) throws Exception{
		User[] users = User.loadUsersData(USERS_CSV_FILE);
		if (users.length != 3002){
			System.out.println("ERROR 1");
		}
		if (users[users.length-2].getAge() != 32 ){
			System.out.println("ERROR 2");
		}
		
		Book[] books = Book.loadBooksData(BOOKS_CSV_FILE);
		if (books.length != 17390){
			System.out.println("ERROR 3");
		}
		if (!books[2].getISBN().equals("0060973129")){
			System.out.println("ERROR 4");
		}
		int[][] ratings = BookRecommendations.loadRatingsData(RATINGS_CSV_FILE, users, books);
		if (ratings.length != users.length || ratings[0].length != books.length){
			System.out.println("ERROR 5");
		}
		
		BookRecommendations bR = new BookRecommendations(books, users, ratings);
		//System.out.println(users[2164]);
		//System.out.println(books[11785]);
		//System.out.println(bR.ratings[2164][11785]);
		
		if ((int)(bR.getAverageRatingForUser(2501)*1000) != 2923){
			//average rating should be 2.923076923076923 
			//userId = 277681, all ratings are: 0,8,0,7,5,0,4,0,0,0,7,7
			System.out.println("ERROR 6");
		}
		if ((int)bR.getAverageRatingForBook(39) != 8){
			//book isbn = 0060168013, a single rating: 8
			System.out.println("ERROR 7");
		}
		
		boolean[] ageGroup = bR.getUsersInAgeGroup(users[0]);
		if (!ageGroup[18] || ageGroup[19]){
			System.out.println("ERROR 8");
		}
		
		ageGroup = bR.getUsersInAgeGroup(users[1899]);
		if (bR.getAverageRatingForBookInAgeGroup(7438, ageGroup) != 9.75){
			//4 users in this age group rated this book with the ratings: 10, 10, 10, 9
			System.out.println("ERROR 9");
		}

		String recommenderISBN = bR.getHighestRatedBookInAgeGroup(users[1899]).getISBN(); //userID = 5358
		if (!recommenderISBN.equals("0140361219")){
			System.out.println("ERROR 10");
		}
		//System.out.println(recommenderISBN);
		
		bR.printRecommendationToFile(users[1899], "userRecommendation.txt"); //check the created file
		
		System.out.println("Done!");
	}
}
