package riddles;

import java.util.HashSet;
import java.util.Set;

public class testeron {
	
	public static void main(String[] args){
		Set<A> set1 = new HashSet<A>();
		set1.add(new A(1,2));
		set1.add(new A(1,2));
		set1.add(new A(1,3));
		int num1 = set1.size();
		System.out.println(num1);
	}

}
