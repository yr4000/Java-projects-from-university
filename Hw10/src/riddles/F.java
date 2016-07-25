package riddles;

public class F implements Comparable<F> {

	private int x;
	private int y;
	
	public F(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(F f) {
		if(x<f.x) return -1;
		else if(x>f.x) return 1;
		else{
			if(y<f.y) return -1;
			else if(y>f.y) return 1;
			else return -1;
	}
	}
	
	

}
