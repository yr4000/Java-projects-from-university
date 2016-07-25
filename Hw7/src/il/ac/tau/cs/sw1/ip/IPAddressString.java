package il.ac.tau.cs.sw1.ip;


public class IPAddressString implements IPAddress {
	private String address;

	IPAddressString(String address) {
		this.address=address;
	}

	@Override
	public String toString() {
		return address;
	}

	@Override
	public boolean equals(IPAddress other) {
		if(this.address.equals(other.toString()))
			return true;
		return false;
	}

	@Override
	public int getOctet(int index) {
		String[] octets=address.split("\\.");
		return Integer.parseInt(octets[index]);
	}

	@Override
	public boolean isPrivateNetwork(){
		for(int i=0;i<256;i++){
			for(int j=0;j<256;j++){
				if(this.address.equals(("192."+"168."+i+"."+j))) return true;
				if(this.address.equals(("169."+"254."+i+"."+j))) return true;
				for(int k=0;k<256;k++){
					if(this.address.equals(("10."+i+"."+j+"."+k))) return true;
				}
			}
		}
		for(int i=16;i<32;i++){
			for(int j=0;j<256;j++){
				for(int k=0;k<256;k++){
					if(this.address.equals(("172."+i+"."+j+"."+k))) return true;
				}
			}
			}
		return false;
	}
	
}
