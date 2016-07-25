package il.ac.tau.cs.sw1.shapes;


import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class ShapeDimensionCalculator {




	public static void main(String[] args) throws Exception {

		Shape[] shapes = getShapesFromUser();
		writeShapesToFile("shapes_output.txt", shapes);
	
	}

	public static Shape[] getShapesFromUser() {
		Scanner user = new Scanner(System.in);
		Shape[] shapes = new Shape[20];
		int counter = 0;
		while(counter<20){
			printOptions();
			String str=user.next();
			if (str.equals("X")) break;
			else if (str.equals("E")){
				printEnterXCoordinate();
				int x = user.nextInt();
				printEnterYCoordinate();
				int y=user.nextInt();
				printEnterSemiMajor();
				int semiMajor = user.nextInt();
				printEnterSemiMinor();
				int semiMinor=user.nextInt();
				shapes[counter]=new Ellipse(x,y,semiMajor,semiMinor);
				System.out.println("Shape added: ["+shapes[counter].getDetails()+"]");
				counter+=1;
			}
			else if (str.equals("C")){
				printEnterXCoordinate();
				int x = user.nextInt();
				printEnterYCoordinate();
				int y=user.nextInt();
				printEnterRadius();
				int r=user.nextInt();
				shapes[counter]=new Circle(x,y,r);
				System.out.println("Shape added: ["+shapes[counter].getDetails()+"]");
				counter+=1;
			}
			else if (str.equals("R")){
				printEnterXCoordinate();
				int x = user.nextInt();
				printEnterYCoordinate();
				int y=user.nextInt();
				printEnterWidth();
				int W = user.nextInt();
				printEnterHeight();
				int H = user.nextInt();
				shapes[counter]=new Rectangle(x,y,W,H);
				System.out.println("Shape added: ["+shapes[counter].getDetails()+"]");
				counter+=1;
			}
			else if(str.equals("easterEgg")){
				System.out.println("The End Is Nigh");
			}
			else{
				System.out.println("Unknown command. Please try again.");
			}
		}
		user.close();
		return Arrays.copyOf(shapes, counter);
	}

	public static void writeShapesToFile(String outputFilename, Shape[] shapes) throws Exception {
		PrintWriter pw = new PrintWriter(new File(outputFilename));
		pw.write("Shape Dimension Calculator"+"\r\n"+"\r\n");
		int numOfEllipses=0;
		int numOfRectangles=0;
		int numOfCircles=0;
		float areaSum=0;
		float perimeterSum=0;
		for (int i=0;i<shapes.length;i++){
			if (shapes[i]==null) break;
			float area = shapes[i].getArea();
			float perimeter = shapes[i].getPerimeter();
			pw.write(shapes[i].getDetails()+"\r\n" +"Area: "+area+","+" Perimeter: "+perimeter+"\r\n"+"\r\n");
			if(shapes[i].getClass().getName().equals("il.ac.tau.cs.sw1.shapes.Rectangle")){
				numOfRectangles+=1;
			}
			else if(shapes[i].getClass().getName().equals("il.ac.tau.cs.sw1.shapes.Ellipse")){
				numOfEllipses+=1;
			}
			else if(shapes[i].getClass().getName().equals("il.ac.tau.cs.sw1.shapes.Circle")){
				numOfCircles+=1;
			}
			areaSum+=area;
			perimeterSum+=perimeter;
		}
		pw.write("Total number of shapes: "+shapes.length+"\r\n"
		+"Number of Circles: "+numOfCircles+"\r\n"
		+"Number of Ellipses: "+numOfEllipses+"\r\n"
		+"Number of Rectangles: "+numOfRectangles+"\r\n"
		+"Total Area sum: "+(float)(Math.round(areaSum*100))/100+"\r\n"
		+"Total Perimeter sum: "+(float)(Math.round(perimeterSum*100))/100);
		pw.close();
	}
	
	public static void printOptions(){
		System.out.println("Please choose shape type:");
		System.out.println("E – Ellipse");
		System.out.println("R – Rectangle");
		System.out.println("C – Circle");
		System.out.println("X - Exit");
	}
	
	public static void printEnterXCoordinate(){
		System.out.println("Please enter X coordinate: ");
	}
	
	public static void printEnterYCoordinate(){
		System.out.println("Please enter Y coordinate: ");
	}

	public static void printEnterSemiMajor(){
		System.out.println("Please enter semi-major axis length: ");
		
	}
	
	public static void printEnterSemiMinor(){
		System.out.println("Please enter semi-minor axis length: ");
	}
	
	public static void printEnterRadius(){
		System.out.println("Please enter radius: ");
	}
	
	public static void printEnterWidth(){
		System.out.println("Please enter width: ");
	}
	
	public static void printEnterHeight(){
		System.out.println("Please enter height: ");
	}
}
