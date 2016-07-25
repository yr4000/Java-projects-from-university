package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class C {
	public static void main(String[] args){
		int randNum = new Random().nextInt(10)+1;
		A a = new A(args, randNum);
		List<String> lst= new ArrayList<String>();
		for (String s : a){
			lst.add(s);
		}
		for (int i = 0; i < args.length; i++){
			for (int j = 0; j < randNum; j++){
				if (!lst.get(i*randNum+j).equals(args[i])){
					return;
				}
			}
		}
		System.out.println("success!");
	}
}
