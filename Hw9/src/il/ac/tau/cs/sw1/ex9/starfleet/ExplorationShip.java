package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ExplorationShip extends AbstractShip{
	
	private int numberOfResearchLabs;
	private final int BASIC_MAINTENANCE = 4000;

	public ExplorationShip(String name, int commissionYear, float maxSpeed, Set<CrewMember> crewMembers, int numberOfResearchLabs) {
		super(name, commissionYear, maxSpeed, crewMembers);
		this.numberOfResearchLabs = numberOfResearchLabs;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return BASIC_MAINTENANCE+numberOfResearchLabs*2500;
	}

	public int getNumberOfResearchLabs() {
		return numberOfResearchLabs;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		sb.append(super.toString()+"\n"+
				  "\t"+"NumberOfResearchLabs="+this.numberOfResearchLabs);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numberOfResearchLabs;
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
		ExplorationShip other = (ExplorationShip) obj;
		if (numberOfResearchLabs != other.numberOfResearchLabs)
			return false;
		return true;
	}

	@Override
	public List<Weapon> getWeapon() {
		//System.out.println("ExplorationShip can't carry weapons");
		return null;
	}

	
	
	
	

}
