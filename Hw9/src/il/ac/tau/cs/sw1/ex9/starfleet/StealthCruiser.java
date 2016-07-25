package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter{
	
	private final static List<Weapon> LASER_CANNONS = Arrays.asList(new Weapon ("Laser Cannons",10,100));
	private static int numberOfStealthEngines = 0;
	
	public StealthCruiser(String name, int commissionYear, float maxSpeed, Set<CrewMember> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maxSpeed, crewMembers, weapons);
		numberOfStealthEngines+=1;//TODO how to decrease if needed?
	}
	
	public StealthCruiser(String name, int commissionYear, float maxSpeed, Set<CrewMember> crewMembers){
		this(name,commissionYear,maxSpeed,crewMembers,LASER_CANNONS);
	}
	
	@Override
	public int getAnnualMaintenanceCost(){
		return super.getAnnualMaintenanceCost()+100*numberOfStealthEngines;
		
	}

}
