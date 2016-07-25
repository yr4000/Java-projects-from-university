package il.ac.tau.cs.sw1.shapes;

public class Circle implements Shape {
	private int x;
	private int y;
	private int r;
	
	public Circle (int x, int y, int radius){
		this.x = x;
		this.y=y;
		this.r=radius;
	}

	@Override
	public float getArea() {
		return (float)(Math.round((Math.PI*(Math.pow(r,2))*100)))/100;
	}

	@Override
	public float getPerimeter() {
		return (float)(Math.round((2*Math.PI*r*100)))/100;
	}

	@Override
	public String getDetails() {
		return "Circle: X="+x+", Y="+y+", Radius="+r;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
	

}
