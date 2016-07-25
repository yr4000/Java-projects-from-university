package sw1.polynomials;

public class TestPolynomials {
	private static final int VALUE = 10140;

	public static void main(String[] args) {
		Monomial monoZeroTest = new Monomial(0);
		System.out.println("monoZeroTest=" +monoZeroTest);
		// 13b^2x^3z
		Monomial m1 = new Monomial(13);
		m1.setDegree('b', 2);
		m1.setDegree('x', 3);
		m1.setDegree('z', 1);
		System.out.println("m1 = " + m1);

		// 15
		Monomial m2 = new Monomial(15);
		System.out.println("m2 = " + m2);

		// -4b^2x^3z
		Monomial m3 = new Monomial(-4);
		m3.setDegree('b', 2);
		m3.setDegree('x', 3);
		m3.setDegree('z', 1);
		System.out.println("m3 = " + m3);
		
		Monomial m4 = new Monomial(5);
		m4.setDegree('x', 2);
		System.out.println("m4 = " + m4);
		
		Monomial m5 = new Monomial(-3);
		m5.setDegree('y', 1);
		System.out.println("m5 = " + m5);
		
		System.out.println("m3.5 = " + m3.multiply(m1));
		

		printError(!m1.hasSameDegrees(m2), m1
				+ " should not have the same degrees as " + m2);
		printError(m1.hasSameDegrees(m3), m1
				+ " should have the same degrees as " + m3);
		printError(m3.hasSameDegrees(m1), m3
				+ " should have the same degrees as " + m1);
		
		Polynomial polyZeroTest = new Polynomial(new Monomial[] {new Monomial(0)});
		System.out.println("polyZeroTest=" +polyZeroTest);// should be 0.
		
		Polynomial p = new Polynomial(new Monomial[] { m1, m2 }); //13b^2x^3z+15
		Polynomial p2 = new Polynomial(new Monomial[] { m3 });//-4b^2x^3z
		Polynomial p3 = p.add(p2);//9b^2x^3z+15
		Polynomial p4 = p.multiply(p2);//-52b^4x^6z^2-60b^2x^3z
		System.out.println("p = " + p); // should be 13b^2x^3z+15 up to
										// reordering the monomials
		System.out.println("p2= " + p2); // should be -4b^2x^3z
		System.out.println("p3= " + p3); // should be 9b^2x^3z+15 up
											// to reordering the monomials
		System.out.println("p4= " + p4); // should be -52b^4x^6z^2-60b^2x^3z up
											// to reordering the monomials
		int[] assignment = { 0, 1, 6, 4, 3, 0, 0, 2, 3, 5, 2, 6, 3,
				8, 7, 0, 0, 4, 2, 6, 0, 4, 1, 5, 1, 9 };
		printError(p3.evaluate(assignment) == VALUE,
				"p3 value for b=1, x=5 and z=9  should be " + VALUE);
		System.out.println("p3 value = "
				+ p3.evaluate(assignment));
		
		Polynomial p5=p4.multiply(polyZeroTest);
		Polynomial p6=p4.add(polyZeroTest);
		Polynomial p7=polyZeroTest.add(p4);
		Polynomial p8=polyZeroTest.multiply(p4);
		Polynomial p9= new Polynomial(new Monomial[] {});
		Polynomial p10 = new Polynomial(new Monomial[] { m4, m5 });
		System.out.println("p5= "+p5);
		System.out.println("p6= "+p6);
		System.out.println("p7= "+p7);
		System.out.println("p9= "+p9);
		System.out.println("p10= "+p10);
		System.out.println(polyZeroTest.equals(new Polynomial(new Monomial[] {new Monomial(0)})));
	}
	public static void printError(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException("[ERROR] " + message);
		}
	}

}
