package il.ac.tau.cs.sw1.ip;

public class TestIPAddress {

	public static void main(String[] args) {
		int address1 = -1062731775; // 192.168.0.1
		short[] address2 = { 10, 1, 255, 1 }; // 10.1.255.1
		short[] address4 = { 192, 168, 0, 1 }; // 192.168.0.1
		short[] address5 = { 172, 32, 255, 1 }; // 172.32.255.1
		int address6 = -1442993624;//169.253.170.40

		IPAddress ip1 = IPAddressFactory.createAddress(address1);
		IPAddress ip2 = IPAddressFactory.createAddress(address2);
		IPAddress ip3 = IPAddressFactory.createAddress("172.31.255.255");
		IPAddress ip4 = IPAddressFactory.createAddress(address4);
		IPAddress ip5 = IPAddressFactory.createAddress("192.168.0.1");
		IPAddress ip6 = IPAddressFactory.createAddress(address5);
		IPAddress ip7 = IPAddressFactory.createAddress(address6);

		for (int i = 0; i < 4; i++) {
			System.out.println(ip1.getOctet(i));
		}
		for (int i = 0; i < 4; i++) {
			System.out.println(ip2.getOctet(i));
		}
		for (int i = 0; i < 4; i++) {
			System.out.println(ip3.getOctet(i));
		}
		for (int i = 0; i < 4; i++) {
			System.out.println(ip7.getOctet(i));
		}

		System.out.println("1. equals: " + ip1.equals(ip2));//false
		System.out.println("2. equals: " + ip1.equals(ip4));//true
		System.out.println("3. equals: " + ip4.equals(ip5));//true
		System.out.println("2. equals: " + ip1.equals(ip5));//true
		System.out.println("4. equals: " + ip7.equals(ip1));//false
		System.out.println("1. Is private Network: " + ip1.isPrivateNetwork());//true
		System.out.println("2. Is private Network: " + ip2.isPrivateNetwork());//true
		System.out.println("5. Is private Network: " + ip7.isPrivateNetwork());//false
		System.out.println("3. Is private Network: " + ip3.isPrivateNetwork());//true
		System.out.println("4. Is private Network: " + ip6.isPrivateNetwork());//false
		
		
		/**
		* There are four classes of private networks
		* (http://en.wikipedia.org/wiki/IPv4#Private_networks)
		* 10.0.0.0 – 10.255.255.255
		* 172.16.0.0 – 172.31.255.255
		* 192.168.0.0 – 192.168.255.255
		* 169.254.0.0 – 169.254.255.255
		*
		* This query returns true if this object is a private network address
		*/
	}
}
