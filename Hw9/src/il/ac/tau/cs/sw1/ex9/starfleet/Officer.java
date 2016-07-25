package il.ac.tau.cs.sw1.ex9.starfleet;

public class Officer extends AbCrewMember{

	private OfficerRank rank;
	
	public Officer(String name, int age, int vetek, OfficerRank rank) {
		super(name, age, vetek);
		this.rank=rank;
	}

	public OfficerRank getRank() {
		return rank;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		sb.append(super.toString()+
				  "\t"+"OfficerRank="+this.rank);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
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
		Officer other = (Officer) obj;
		if (rank != other.rank)
			return false;
		return true;
	}
	
	
}
