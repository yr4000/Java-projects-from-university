package il.ac.tau.cs.sw1.ex5;

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class User {
	int userID;
	String location;
	int age;
	
	private static final int NO_AGE = -1;
	private static final int MAX_USERS_IN_FILE = 20000;
	

	public User(int userID, String location, int age){
		this.userID = userID;
		this.location = location;
		this.age = age;
	}
	
	public User(int userID, String location){
		this.userID = userID;
		this.location = location;
		this.age = NO_AGE;
	}
	
	public String toString(){
		StringBuilder sb= new StringBuilder("");
		char sep=',';
		sb.append(this.userID).append(sep).append(this.location).append(sep);
		if(this.age==-1) sb.append("No age");
		else sb.append(this.age);
		return sb.toString();
	}
	
	
	public int getUserID() {
		return userID;
	}

	public String getLocation() {
		return location;
	}

	public int getAge() {
		return age;
	}

	/**
	 * 
	 * @return
	 * @post ($ret == true) <=> (this.age != NO_AGE)
	 */
	public boolean hasAge(){
		if(this.age!=-1) return true;
		return false;

	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @pre fileName is a legal fileName, the format of the file is as expected
	 * @post $ret is an Arrays of User objects read from the file fileName
	 */

	public static String noQuotationMark(String sentence){
		return sentence.substring(1, sentence.length()-1);
	}
	
	public static User[] loadUsersData(String fileName) throws Exception{
		int counter = 0;
		User[] users = new User[MAX_USERS_IN_FILE];
		Scanner scanfile = new Scanner(new File(fileName));
		while(scanfile.hasNextLine()){
			if(counter==0){
				scanfile.nextLine();
				counter++;
				continue;
			}
			String[] temp = scanfile.nextLine().split(";");
			if(temp[2].equals("NULL")){
				users[counter] = new User(Integer.parseInt(noQuotationMark(temp[0])),noQuotationMark(temp[1]));
			}
			else{
				users[counter] = new User(Integer.parseInt(noQuotationMark(temp[0])),noQuotationMark(temp[1]),Integer.parseInt(noQuotationMark(temp[2])));
			}
			counter++;
		}
		scanfile.close();
		return Arrays.copyOfRange(users, 1, counter);
	}
	
	
}
