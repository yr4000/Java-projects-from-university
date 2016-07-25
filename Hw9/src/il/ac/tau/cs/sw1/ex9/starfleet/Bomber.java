package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends AbstractFighter {
	
	private int numberOfTechnicians;
	private final int BASIC_MAINTENANCE=5000;

	public Bomber(String name, int commissionYear, float maxSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons,
			int numberOfTechnicians) {
		super(name, commissionYear, maxSpeed, crewMembers, weapons);
		this.numberOfTechnicians = numberOfTechnicians;
	}
	
	public int getNumberOfTechnicians() {//TODO can i assume the number is legal?
		return numberOfTechnicians;
	}
	
	@Override
	public int getAnnualMaintenanceCost(){
		int sum = super.getAnnualMaintenanceCost();
		if(numberOfTechnicians>0)sum*=(this.numberOfTechnicians*0.1);
		return sum+BASIC_MAINTENANCE;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		sb.append(super.toString()+"\n"+
				  "\t"+"NumberOfTechnicians="+this.numberOfTechnicians);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numberOfTechnicians;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bomber other = (Bomber) obj;
		if (numberOfTechnicians != other.numberOfTechnicians)
			return false;
		return true;
	}
	
	
}
