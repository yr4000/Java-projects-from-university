package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public interface Spaceship { // mabe should create an abstract class of space ships.
	public String getName();
	public int getCommissionYear();
	public float getMaximalSpeed(); //TODO maybe should insert an HAGBALA?
	public int getFirePower();
	public Set<CrewMember> getCrewMembers();
	public int getAnnualMaintenanceCost();
	public List<Weapon> getWeapon();

}
