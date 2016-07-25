package il.ac.tau.cs.sw1.ex9.starfleet;

public abstract class AbCrewMember implements CrewMember {
	
	protected String name;
	protected int age;
	protected int vetek;
	
	public AbCrewMember(String name, int age, int vetek) {
		this.name = name;
		this.age = age;
		this.vetek = vetek;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getAge() {
		return this.age;
	}

	@Override
	public int getYearsInService() {
		return this.vetek;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		sb.append(this.getClass().getSimpleName()+"\n"+
		        "\t"+"Name="+this.name+"\n"+
				"\t"+"Age="+this.age+"\n"+
				"\t"+"Years in service="+this.vetek+"\n");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + vetek;
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
		AbCrewMember other = (AbCrewMember) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vetek != other.vetek)
			return false;
		return true;
	}
	

}
