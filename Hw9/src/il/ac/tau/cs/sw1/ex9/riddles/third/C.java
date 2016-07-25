package il.ac.tau.cs.sw1.ex9.riddles.third;



public class C {
	public static void main(String[] args) {
		A a = new B(args[0]);
		try{
			a.foo(args[0]);
		}
		catch(B b){
			if (b.getMessage().equals(args[0])){
				System.out.println("success!");
			}
		}
		catch(Exception exp){
			return;
		}
	}
}
