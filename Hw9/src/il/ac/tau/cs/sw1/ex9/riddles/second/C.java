package il.ac.tau.cs.sw1.ex9.riddles.second;

import java.util.Random;

public class C {

	public static void main(String[] args) {
		String input = args[0];
		B b = new B();
		Random random = new Random();
		boolean randomBool = random.nextBoolean();
		A a = b.getA(randomBool);
		
		if (randomBool) {
			if (!input.toUpperCase().equals(a.foo(input))) {
				return;
			}
		} else {
			if (!(input.toLowerCase()).equals(a.foo(input))) {
				return;
			}
		}
		System.out.println("success!");
	}
}
