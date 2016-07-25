package il.ac.tau.cs.sw1.ip;

public class IPAddressShort implements IPAddress {
	private short[] address;


	IPAddressShort(short[] address) {
		this.address=address;
	}

	@Override
	public String toString() {
		return ""+address[0]+"."+address[1]+"."+address[2]+"."+address[3];
	}

	@Override
	public boolean equals(IPAddress other) {
			if(this.toString().equals(other.toString())) return true;
		return false;
	}

	@Override
	public int getOctet(int index) {
		return (int)this.address[index];
	}

	@Override
	public boolean isPrivateNetwork(){
		for(int i=0;i<256;i++){
			for(int j=0;j<256;j++){
				if(address[0]==192 &&address[1]==168 &&address[2]==i &&address[3]==j) return true;
				if(address[0]==169 &&address[1]==254 &&address[2]==i &&address[3]==j) return true;
				for(int k=0;k<256;k++){
					if(address[0]==10 &&address[1]==i &&address[2]==j &&address[3]==k) return true;
				}
			}
		}
		for(int i=16;i<32;i++){
			for(int j=0;j<256;j++){
				for(int k=0;k<256;k++){
					if(address[0]==172 &&address[1]==i &&address[2]==j &&address[3]==k) return true;
				}
			}
			}
		return false;
	}
}
	/*
	for(int i=0;i<256;i++){TODO
		for(int j=0;j<256;j++){//address[0]== &&address[1]== &&address[2]== &&address[3]==
			if(this.toString().equals(("192."+"168."+i+"."+j))) return true;
			if(this.toString().equals(("169."+"254."+i+"."+j))) return true;
			for(int k=0;k<256;k++){
				if(this.toString().equals(("10."+i+"."+j+"."+k))) return true;
			}
		}
	}
	for(int i=16;i<32;i++){
		for(int j=0;j<256;j++){
			for(int k=0;k<256;k++){
				if(this.toString().equals(("172."+i+"."+j+"."+k))) return true;
			}
}

 */
	
