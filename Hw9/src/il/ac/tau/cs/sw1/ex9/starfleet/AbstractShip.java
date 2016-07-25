package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public abstract class AbstractShip implements Spaceship {
	
	protected String name;
	protected int commissionYear;
	protected float maxSpeed;
	protected Set<CrewMember> crewMembers;
	protected final int BASIC_FIRE_POWER=10;
	

	public AbstractShip(String name, int commissionYear, float maxSpeed, Set<CrewMember> crewMembers) {
		this.name = name;
		this.commissionYear = commissionYear;
		this.maxSpeed = maxSpeed;
		this.crewMembers = crewMembers;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getCommissionYear() {
		return commissionYear;
	}

	@Override
	public float getMaximalSpeed() {
		return maxSpeed;
	}

	@Override
	public int getFirePower() {
		return BASIC_FIRE_POWER;
	}

	@Override
	public Set<CrewMember> getCrewMembers() {
		return crewMembers;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		sb.append(this.getClass().getSimpleName()+"\n"+
				"\t"+"Name="+this.getName()+"\n"+
		        "\t"+"CommissionYear="+this.commissionYear+"\n"+
				"\t"+"MaximalSpeed="+this.maxSpeed+"\n"+
				"\t"+"FirePower="+this.getFirePower()+"\n"+
				"\t"+"CrewMembers="+this.crewMembers.size()+"\n"+
				"\t"+"AnnualMaintenanceCost="+this.getAnnualMaintenanceCost());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commissionYear;
		result = prime * result + ((crewMembers == null) ? 0 : crewMembers.hashCode());
		result = prime * result + Float.floatToIntBits(maxSpeed);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractShip other = (AbstractShip) obj;
		if (commissionYear != other.commissionYear)
			return false;
		if (crewMembers == null) {
			if (other.crewMembers != null)
				return false;
		} else if (!crewMembers.equals(other.crewMembers))
			return false;
		if (Float.floatToIntBits(maxSpeed) != Float.floatToIntBits(other.maxSpeed))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
