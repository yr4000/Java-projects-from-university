package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class TransportShip extends AbstractShip{
	
	private int cargoCapacity;
	private int passengerCapacity;
	private final int BASIC_MAINTENANCE=3000;

	public TransportShip(String name, int commissionYear, float maxSpeed, Set<CrewMember> crewMembers,
						 int cargoCapacity,int passengerCapacity) {
		super(name, commissionYear, maxSpeed, crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}

	public int getCargoCapacity() {
		return cargoCapacity;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return BASIC_MAINTENANCE+5*this.cargoCapacity+3*this.passengerCapacity;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		sb.append(super.toString()+"\n"+
				"\t"+"CargoCapacity="+this.cargoCapacity+"\n"+
				"\t"+"PassengerCapacity="+this.passengerCapacity);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cargoCapacity;
		result = prime * result + passengerCapacity;
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
		TransportShip other = (TransportShip) obj;
		if (cargoCapacity != other.cargoCapacity)
			return false;
		if (passengerCapacity != other.passengerCapacity)
			return false;
		return true;
	}

	@Override
	public List<Weapon> getWeapon() {
		//System.out.println("TransportShip can't carry weapons");
		return null;
	}

	
	
	

}
