package UserInfo;

import java.util.Comparator;

public class GasStationADT { // took away comparable 

		private String StateCountry = "NSW, Australia";
		private String suburb;
		private String StationName;
		private double price; // measured in $/L
		private String Address;
		private double distance; 
		private String gasTypes;
		private boolean rewards;
		private double latitude;
		private double longitude;
		
		public GasStationADT(String Stationname, double price, String suburb, String Address, double distance, String gasTypes, boolean rewards, double latitude, double longitude) {
			this.StationName = Stationname;
			this.price = price;
			this.suburb = suburb;
			this.Address = Address;
			this.distance = distance; 
			this.gasTypes = gasTypes;
			this.rewards = rewards;
			this.latitude = latitude;
			this.longitude = longitude;
			
		}
		public void setDistance(double distance) {
			this.distance = distance; 
		}
		public String getFullAddress() {
			return this.Address + ", " + this.suburb + ", " + this.StateCountry;  //add suburb
		}
		
		public String getSuburb() {
			return suburb;
		}
		
		public String getStationName() {
			return StationName;
		}

		public double getPrice() {
			return price;
		}
		
		public String getAddress() {
			return Address;
		}

		public double getDistance() {
			return distance;
		}
		
		public String getGasTypes() {
			return gasTypes;
		}
		
		public boolean getRewards() {
			return rewards;
		}
		
		public double getLatitude(){
			return this.latitude;
		}
		
		public double getLongitude(){
			return this.longitude;
		}
	
		public int compareTo(GasStationADT x, String rank) {
			if(rank.equals("price")) {
			if (this.price < x.getPrice())
				return -1;
			if (this.price > x.getPrice())
				return 1;
			else if(this.StationName.compareTo(x.getStationName()) <= 0) return 0; 
			} 
			if(rank.equals("distance")) {
				if (this.distance < x.getDistance()) return -1;
				if (this.distance > x.getDistance()) return 1;
				else if(this.StationName.compareTo(x.getStationName()) <= 0) return 0;
			}
			return 10; 
		} 

		public String toString()
		{
			String str = StationName + ", " + Address + ", " + suburb + ", " + StateCountry + " " + "$" + Double.toString(price) + " " + Double.toString(distance) + "km"; 
			return str; 
		}
		/*
		public static void main(String[] args) {
			GasStationADT hello = new GasStationADT("name", 112.5, "sub", "269 potts", 1.77); 
			System.out.println(hello);
			System.out.println(hello.getFullAddress());
			// TODO Auto-generated method stub
		}*/
}
