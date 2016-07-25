package riddles;

public class C implements Comparable<C> {
	
	private int x;
	private int y;
	
	public C(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(C c) {
		if(x<c.x) return -1;
		else if(x>c.x) return 1;
		else{
			if(y<c.y) return -1;
			else if(y>c.y) return 1;
			else return 0;
				
		}
	}


}
