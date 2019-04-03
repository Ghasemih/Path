package UserInfo;

import java.io.IOException;

public class UsingDistance {
	//array will be the sorted one from lat n long, make sure still sorted with new distances
	  public static GasStationADT[] getArray(GasStationADT[] array, String origin) throws IOException {
		    for(int i =0; i < 10; i++) {
				String location = array[i].getFullAddress(); 
				if(location.indexOf("&") > 0) continue; 
				double distance = DistanceGoogle.distance(origin, location);
				array[i].setDistance(distance); 
				System.out.println(array[i]);
				System.out.println(i); 
		    
	  }
			return array;
	  } 
	  
	  public static GasStationADT[] updateDistances(GasStationADT[] array, double startingLatitude, double startingLongitude) throws IOException {
		  double endingLatitude, endingLongitude, distance;
		  
		  for (int i = 0; i < array.length; i++) {
		    	
				endingLatitude = array[i].getLatitude(); 
				endingLongitude = array[i].getLatitude(); 
		
				distance = distance(startingLatitude, endingLatitude, startingLongitude, endingLongitude);
				array[i].setDistance(distance); 
				System.out.println(array[i]);
				System.out.println(i); 
		}
		return array;
	  } 
	  
	  private static double distance(double lat1, double lat2, double lon1, double lon2) {

		    final int R = 6371; // Radius of the earth

		    double latDistance = Math.toRadians(lat2 - lat1);
		    double lonDistance = Math.toRadians(lon2 - lon1);
		    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
		            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double distance = R * c * 1000; // convert to meters

		    return distance;
		}

}