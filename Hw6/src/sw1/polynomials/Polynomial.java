package sw1.polynomials;

import java.util.Arrays;

public class Polynomial {
	private Monomial[] monomials;
	private Monomial[] MONOZERO={new Monomial(0)};
	/**
	 * Creates a polynomial with (safe copies of) the given monomials
	 * 
	 * @pre monomials != null
	 * @pre for all i, 0 <= i < monmials.length : monomials[i] != null
	 * @post for all i, 0 <= i < monmials.length : monomials[i].getCoefficient()
	 *       == getMonomial(i).getCoefficient()
	 * @post for all i,v, 0 <= i < monmials.length, 'a'<=v<='z' :
	 *       monomials[i].getDegree(v) == getMonomial(i).getDegree(v)
	 */
	public Polynomial(Monomial[] monomials) {
		this.monomials=monomials;
	}

	/**
	 * @return the number of monomials in this polynomial
	 */
	public int getMonomialCount() {
		return this.monomials.length;
	}

	/**
	 * @pre 0<=index < getMonomialCount()
	 * @return a safe copy of the monomial at the given index
	 */
	public Monomial getMonomial(int index) {
		return this.monomials[index].getCopy();
	}

	/**
	 * @pre other != null
	 * @post Creates a new Polynomial which is the sum of this polynomial and
	 *       other. E.g., the sum of 13b^2x^3z+15 and -4b^2x^3z is
	 *       9b^2x^3z+15
	 */
	public Polynomial add(Polynomial other) {
		if(this.isPolynomZero()) return other; //no need to add if one of them is 0.
		if(other.isPolynomZero()) return this;
		
		int n=this.getMonomialCount(); //here we get ready.
		int m=other.getMonomialCount();
		Monomial[] monoArr=new Monomial[n+m];
		int[] used = new int[m];
		int counter=0;
		
		outer: for(int i=0;i<n;i++){//here we create the new polynom.
			for(int j=0;j<m;j++){
				if(used[j]==0&&Arrays.equals(this.getMonomial(i).degrees,other.getMonomial(j).degrees)){
					Monomial newMono=this.getMonomial(i).add(other.getMonomial(j));
					if (newMono.toString()=="0") {continue outer;} //avoiding  from adding 0.
					monoArr[i]=newMono;
					used[j]=1;
					counter+=1;
					continue outer;
				}
			}
			monoArr[i]=this.getMonomial(i);
			counter+=1;
		}
		for(int i=0;i<used.length;i++){
			if(used[i]==0&&other.getMonomial(i).getCoefficient()!=0){
				monoArr[counter]=other.getMonomial(i).getCopy();
				counter+=1;
			}
		}
		return new Polynomial(Arrays.copyOfRange(monoArr, 0, counter));
	}

	/**
	 * @pre other != null
	 * @post Creates a new Polynomial which is the product of this polynomial
	 *       and other. E.g., the product of 13b^2x^3z+15 and -4b^2x^3z is
	 *       -52b^4x^6z^2-60b^2x^3z
	 */
	public Polynomial multiply(Polynomial other) {
		if(this.isPolynomZero()||other.isPolynomZero())
			return new Polynomial(MONOZERO);
		int n=this.getMonomialCount();
		int m=other.getMonomialCount();
		Monomial[][] monoMat = new Monomial[n][m];
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				monoMat[i][j]=this.getMonomial(i).multiply(other.getMonomial(j));
			}
		}
		Polynomial res=new Polynomial(monoMat[0]);
		for(int i=1;i<monoMat.length;i++){
			res=res.add(new Polynomial(monoMat[i]));
		}
		return res;
	}

	/**
	 * @pre assignment != null
	 * @pre assignment.length == 26
	 * @return the result of assigning assignment[0] to a, assignment[1] to b
	 *         etc., and computing the value of this Polynomial
	 */
	public int evaluate(int[] assignment) {
		int sum=0;
		for(Monomial monom: this.monomials){
			sum+=monom.evaluate(assignment);
		}
		return sum;
	}


	/**
	 * Returns a string representation of this polynomial by the mathematical
	 * convention, but without performing normalization (summing of monomials).
	 * I.e., each monomial is printed according to Monomial.toString(), for
	 * example 13b^2x^3z+15-4b^2x^3z
	 */
	public String toString() {
		if(this.isPolynomZero()) return "0";
		StringBuilder sb = new StringBuilder("");
		for(int i=0;i<this.getMonomialCount();i++){
			Monomial crnt=this.getMonomial(i);
			sb.append(crnt.toString());
			if(crnt.getCoefficient()>0 && i<this.getMonomialCount()-1)
				sb.append("+");
		}
		return sb.toString();
	}
	
	/*
	 * returns true if Polynomial is zero, else false.
	 */
	public boolean isPolynomZero(){
		if(this.getMonomialCount()==1&&this.getMonomial(0).getCoefficient()==0)
			return true;
		return false;
	}
}
