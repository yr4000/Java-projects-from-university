package sw1.polynomials;
import java.lang.Math;

/**
 * Represents a multiplication of variables in a-z with an integral coefficient
 */
public class Monomial {
	
	private char[] coeffs={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	int[] degrees;
	private int coefficient;
	/**
	 * @post this.getCoefficient() == coefficient
	 * @post for every v, 'a'<=v<='z', isVariable(v) == false
	 */
	public Monomial(int coefficient) {
		this.coefficient=coefficient;
		this.degrees=new int[26];
	}

	/**
	 * @return the coefficient of this monomial
	 */
	public int getCoefficient() {
		return this.coefficient;
	}

	/**
	 * @post getCoefficient() == coefficient
	 */
	public void setCoefficient(int coefficient) {
		this.coefficient=coefficient;
	}

	/**
	 * @return true iff the input is a variable of this monomial (and appears in
	 *         toString).
	 */
	public boolean isVariable(char variable) {
		return degrees[indexOfArr(coeffs,variable)]!=0;
	}

	/**
	 * @pre isVariable(variable)
	 * @return the degree of variable in this monomial
	 */
	public int getDegree(char variable) {
		return degrees[indexOfArr(coeffs,variable)];
	}

	/**
	 * @pre degree >= 0
	 * @pre 'a'<=variable<='z'
	 * @post getDegree(variable) = degree
	 */
	public void setDegree(char variable, int degree) {
		degrees[indexOfArr(coeffs,variable)]=degree;
	}

	/**
	 * @pre other!= null
	 * @return true iff the set of variables and the degree of each variable is
	 *         the same for this and other.
	 */
	public boolean hasSameDegrees(Monomial other) {
		for (int i=0;i<degrees.length;i++){
			if (this.degrees[i]!=other.getDegree(coeffs[i])) return false;
		}
		return true;
	}
	
	/**
	 * @pre assignment != null
	 * @pre assignment.length == 26
	 * @return the result of assigning assignment[0] to a, assignment[1] to b
	 *         etc., and computing the value of this Monomial
	 */
	public int evaluate(int[] assignment) {
		int sum=this.getCoefficient();
		if(sum==0) return 0;
		for(int i=0;i<26;i++){
			if(assignment[i]!=0){
				sum*=Math.pow(assignment[i], this.degrees[i]);
			}
		}
		return sum;
	}

	/**
	 * Returns a string representation of this monomial by the mathematical
	 * convention. I.e., the coefficient is first (if not 1), then every
	 * variable in an alphabetic order followed by ^ and its degree (if > 1).
	 * For example, 13b^2x^3z
	 */
	public String toString() {
		if(this.getCoefficient()==0) return "0";
		StringBuilder sb = new StringBuilder("");
		if(this.coefficient!=1) sb.append((this.coefficient));
		for(int i=0;i<26;i++){
			if (this.degrees[i]!=0){
				sb.append(coeffs[i]);
				if(this.degrees[i]>1){
					sb.append("^"+ this.degrees[i]);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * Returns a "safe" copy of this monomial, i.e., if the copy is changed,
	 * this will not change and vice versa
	 */
	public Monomial getCopy() {
		Monomial copy = new Monomial(this.getCoefficient());
		for(int i=0;i<degrees.length;i++){
			if (this.degrees[i]!=0) copy.setDegree(coeffs[i],this.degrees[i]);
		}
		return copy;
	}
	/**
	 * Returns the index of char x in array arr. if x is not in arr, returns -1. 
	 */
	public static int indexOfArr(char[] arr,char x){
		int cnt=0;
		for(char car: arr){
			if(car==x) return cnt;
			cnt++;
		}
			return -1;
		}
	/**
	 * assuming the two monomials coeffs are identical!
	 * returns the sum of the two monomials.
	 */
	public Monomial add(Monomial other){
		Monomial res = this.getCopy();
		res.setCoefficient(res.getCoefficient()+other.getCoefficient());
		return res;
		
	}
	
	public Monomial multiply(Monomial other){
		Monomial res = new Monomial(this.getCoefficient()*other.getCoefficient());
		if(res.getCoefficient()==0) return new Monomial(0);
		for(int i=0;i<26;i++){
			res.degrees[i]=this.degrees[i]+other.degrees[i];
		}
		return res;
	}
	}
