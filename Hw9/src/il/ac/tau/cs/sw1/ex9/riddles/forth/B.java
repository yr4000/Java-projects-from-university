package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;

public class B implements Iterator<String> {
	private int counter;
	private int arrow=0;
	private int k;
	private String[] strings;
	public B(String[] strings, int k){
		this.k=k;
		this.strings=strings;
		this.counter=k;
	}


	@Override
	public boolean hasNext() {
		if(arrow<strings.length) return true;
		return false;
	}

	@Override
	public String next() {
		String crnt = strings[arrow];
		if(counter!=1){
			counter--;
		}
		else{
			arrow++;
			counter=k;
		}
		return crnt;
	}

	@Override
	public void remove() {
		return;
		
	}
	
}
