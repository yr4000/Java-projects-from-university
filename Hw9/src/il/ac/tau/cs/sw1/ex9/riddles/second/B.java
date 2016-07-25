package il.ac.tau.cs.sw1.ex9.riddles.second;

public class B extends A {
	
	public A getA(boolean bool){
		if(bool) return new B();
		else return new A();
	}
	
	public String foo(String s) {
		return s.toUpperCase();
	}
}
