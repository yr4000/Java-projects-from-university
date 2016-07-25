package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends AbstractFighter{
	
	protected final int BASIC_MAINTENANCE=2500;

	public Fighter(String name, int commissionYear, float maxSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maxSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int motors = Math.round(1000*this.maxSpeed);
		return BASIC_MAINTENANCE+motors+super.getAnnualMaintenanceCost();
	}
	
	
	
}
