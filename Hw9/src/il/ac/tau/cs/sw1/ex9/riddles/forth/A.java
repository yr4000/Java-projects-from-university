package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;

public class A implements Iterable<String> {
	
	protected String[] strings;
	private int k;
	public A(String[] strings, int k){
		this.strings = strings;
		this.k = k;
	}

	@Override
	public Iterator<String> iterator() {
		return new B(strings, k);
	}
}
