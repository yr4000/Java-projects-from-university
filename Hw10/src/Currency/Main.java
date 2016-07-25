package Currency;


public class Main {
	
	public enum Currency {
		PENNY,
		NICKLE,
		DIME,
		QUARTER;
		
		@Override
		public String toString(){
			return super.toString()+ " value: " +this.getValue();
		}
		
		int value;
		public int getValue(){
			switch(this){
			case PENNY: value = 1;
						break;
			case NICKLE: value = 5;
						break;
			case DIME: value = 10;
						break;
			case QUARTER: value = 25;
						break;
			default: value = -1;
						break;
			
			}
			return value;
		}
			
		
		String color;
		public String color(){
			switch(this){
			case PENNY: color = "copper";
						break;
			case NICKLE: color = "bronze";
						break;
			case DIME: color = "silver";
						break;
			case QUARTER: color = "silver";
						break;
			default: color = "invalid coin";
						break;
			
			}
			return color;
		}
		
	}
	
	
	public static void main(String [] args)
	{
		
		for(Currency coin: Currency.values())
		{ 
			System.out.println("coin: " + coin + " color: " + coin.color()); 
		}

	
	}

	

}
