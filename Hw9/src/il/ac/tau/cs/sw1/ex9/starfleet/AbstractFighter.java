package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public abstract class AbstractFighter extends AbstractShip {
	
	protected List<Weapon> weapons;

	public AbstractFighter(String name, int commissionYear, float maxSpeed, Set<CrewMember> crewMembers,
							List<Weapon> weapons) {
		super(name, commissionYear, maxSpeed, crewMembers);
		this.weapons=weapons;
	}
	
	@Override
	public int getFirePower(){
		int sum = 0;
		for(Weapon weapon: this.weapons) sum+=weapon.getFirePower();
		return super.BASIC_FIRE_POWER+sum;
	}

	public List<Weapon> getWeapon() {
		return weapons;
	}
	
	public int getAnnualMaintenanceCost(){
		int sum=0;
		for(Weapon weapon: this.weapons) sum+=weapon.getAnnualMaintenanceCost();
		return sum;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		sb.append(super.toString()+"\n"+
				  "\t"+"WeaponArray="+weapons.toString());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((weapons == null) ? 0 : weapons.hashCode());
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
		AbstractFighter other = (AbstractFighter) obj;
		if (weapons == null) {
			if (other.weapons != null)
				return false;
		} else if (!weapons.equals(other.weapons))
			return false;
		return true;
	}
	
	
	
	
	

}
