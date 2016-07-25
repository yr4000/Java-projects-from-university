package il.ac.tau.cs.sw1.ex9.riddles.third;

public class B extends A {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public B(String s) {
		super(s);
	}
	
	@Override
	public String getMessage(){
		return this.s;
	}

	public void foo(String s) throws B{
		if (s.equals(this.s)){
			throw new B(this.s);
		}
	}
}
