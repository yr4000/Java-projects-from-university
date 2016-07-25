package il.ac.tau.cs.sw1.ip;

public class IPAddressInt implements IPAddress {
	private int address;
	private int[] octets=new int[4];

	IPAddressInt(int address) {
		this.address=address;
		for(int i=0;i<4;i++){
			this.octets[3-i]=address>>(8*i)&255;
		}
	}

	@Override
	public String toString() {
		return ""+octets[0]+"."+octets[1]+"."+octets[2]+"."+octets[3];
	}

	@Override
	public boolean equals(IPAddress other) {
		if(this.toString().equals(other.toString())) return true;
		return false;
	}

	@Override
	public int getOctet(int index) {
		return octets[index];
	}

	@Override
	public boolean isPrivateNetwork(){
		for(int i=0;i<256;i++){
			for(int j=0;j<256;j++){
				if(this.address==(192<<24)+(168<<16)+(i<<8)+j) return true;
				if(this.address==(169<<24)+(254<<16)+(i<<8)+j) return true;
				for(int k=0;k<256;k++){
					if(this.address==(10<<24)+(i<<16)+(j<<8)+k) return true;
				}
			}
		}
		for(int i=16;i<32;i++){
			for(int j=0;j<256;j++){
				for(int k=0;k<256;k++){
					if(this.address==(172<<24)+(i<<16)+(j<<8)+k) return true;
				}
			}
			}
		return false;
	}
	
}
