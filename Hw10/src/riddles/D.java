package riddles;

public class D implements Comparable<D> {
	
	private int x;
	
	public D(int x, int y){
		this.x=x;
	}

	@Override
	public int compareTo(D d) {
		if(x<d.x) return -1;
		else if(x>d.x) return 1;
		else return 0;
	}
	
	

}
