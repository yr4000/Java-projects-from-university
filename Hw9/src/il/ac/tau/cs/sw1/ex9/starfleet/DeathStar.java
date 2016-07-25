package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class DeathStar extends AbstractFighter {
	
	private int BASIC_MAINTENANCE_COST = 1000000000;

	public DeathStar(String name, int commissionYear, float maxSpeed, Set<CrewMember> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maxSpeed, crewMembers, weapons);
		
	}
	
	public void makeDarthVaderYourFather(){
		System.out.println("You are now the child of Darth Vader!");
	}
	
	public boolean isDarthVaderOnTheShip(){
		List<Officer> officers = StarfleetManager.createOfficerList(this.getCrewMembers());
		for(Officer officer: officers){
			if(officer.getRank()==OfficerRank.Sith && officer.getName().equals("Darth Vader")) return true;
		}
		return false;
	}
	
	public int countStormTroopers(){
		List<Officer> officers = StarfleetManager.createOfficerList(this.getCrewMembers());
		int counter = 0;
		for(Officer officer: officers){
			if(officer.getRank()==OfficerRank.StormTrooper) counter++;
		}
		return counter;
	}
	
	public int getAnnualMaintenanceCost(){
		int sum = super.getAnnualMaintenanceCost()+BASIC_MAINTENANCE_COST;
		if(isDarthVaderOnTheShip()) sum*=0.4;
		return sum;
	}
	
	public void blowUpAPlanet(){
		if(countStormTroopers()>= 25984)
			System.out.println("The planet was blown successfully, sir!");
		else
			System.out.println("You must construct additional pylons!");
	}

}
