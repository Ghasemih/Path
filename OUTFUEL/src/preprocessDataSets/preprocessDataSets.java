package preprocessDataSets;
import UserInfo.OpenStreetMapUtils; /////USE THIS TO GET LATITUDE 
import java.io.BufferedReader;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
// Work cited: getLatLongPostions() --- https://stackoverflow.com/questions/18455394/java-function-that-accepts-address-and-returns-longitude-and-latitude-coordinate  
import UserInfo.GasStationADT;

import UserInfo.VehicleADT;

public class preprocessDataSets {
	
	private static int randInt(int min, int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	private static double genLat(){
		double greatestLat = -12.524351;
		double leastLat = -37.694103;
		double random = new Random().nextDouble();
		double result = leastLat + (random * (greatestLat - leastLat));
		return result;
	}
	
	private static double genLong(){
		double greatestLong = 153.416119;
		double leastLong = 113.337997;
		double random = new Random().nextDouble();
		double result = leastLong + (random * (greatestLong - leastLong));
		return result;
	}
	
	
	private static void preprocessGasStations() throws Exception{
		
		String line;
		String stationName, address, suburb;
		String gasTypes = "r";
		boolean rewards = false;
		double price;
		
		GasStationADT[] tempArray = new GasStationADT[12]; // 12 is the maximum # of data lines on the same station
		int tempArrayIndex = 0;
		
		int size = 35715; //number of stations in data set (not duplicated in a row)
		GasStationADT[] stationArray = new GasStationADT[size];
		int stationArrayIndex = 0;
			
		BufferedWriter writer = new BufferedWriter(new FileWriter("./data/preprocessedGasStationsDATA.txt"));
		
        BufferedReader input = new BufferedReader(new FileReader("./data/GasStationsDATA.txt"));
        
        int q = 0;
        while ((line = input.readLine()) != null) {
	
            String[] stationInfo = line.split(",");
            stationName = stationInfo[0];
            int e = 0;
            if (stationInfo.length == 6){
            	e = 1;
            }
            
            address = stationInfo[1+e].substring(1);
            int bracketIndex = address.indexOf("(");
            if (bracketIndex != -1)
            	address = address.substring(0, bracketIndex);
            int andIndex = address.indexOf("&");
            if (andIndex != -1)
            	address = address.substring(0, andIndex);
            
            suburb = stationInfo[3+e];
            price = Double.parseDouble(stationInfo[4+e]);
            
            tempArray[tempArrayIndex] = new GasStationADT(stationName, price, suburb, address, 0, gasTypes, rewards, 0, 0);
            tempArrayIndex++;
            
            if (tempArrayIndex > 1)
            	if (!(tempArray[tempArrayIndex-1].getStationName().equals(tempArray[tempArrayIndex-2].getStationName()) && tempArray[tempArrayIndex-1].getAddress().equals(tempArray[tempArrayIndex-2].getAddress()) && tempArray[tempArrayIndex-1].getSuburb().equals(tempArray[tempArrayIndex-2].getSuburb()))){
            		double averagePrice = 0;
            		for (int j = 0 ; j < tempArrayIndex-1 ; j++){
            			averagePrice += tempArray[j].getPrice();
            		}
            		averagePrice = averagePrice / (tempArrayIndex - 1);
            		
            		GasStationADT object = tempArray[0];
            		
            		stationArray[stationArrayIndex] = new GasStationADT(object.getStationName(), averagePrice, object.getSuburb(), object.getAddress(), 0, gasTypes, rewards, 0, 0); 
                    stationArrayIndex++;
                    
            		tempArray[0] = tempArray[tempArrayIndex-1];
            		tempArrayIndex = 1;
            }
        }
        // at the end you will have an array of the stations just line preprocessedGasStationsDATA but with out duplicates or lat/long. IT WORKS
        
        
		int numberRemoved = 0;
        for (int i = 0 ; i < stationArray.length-numberRemoved ; i++){
        	
        	int averagePrice = 0;
    		int numStations = 0;
    		
        	GasStationADT currentStation = stationArray[i];
        	
        	for (int j = i+1 ; j < stationArray.length-numberRemoved ; j++){
        		
        		if (currentStation.getAddress().equals(stationArray[j].getAddress())){
        			
        			averagePrice += stationArray[j].getPrice();
        			numStations+=1;
        			
        			//remove item from array
        			
        			for (int k = j ; k < stationArray.length-1-numberRemoved ; k++){
        				stationArray[k] = stationArray[k+1];
        			}
        			numberRemoved++;
        		    
        		}
        	}
        	if (numStations == 0){
        		averagePrice = (int) stationArray[i].getPrice();
        	}
        	else
        		averagePrice = averagePrice / numStations;
        	
        	int gasTypesInt = randInt(1,3);
            if (gasTypesInt == 1) { gasTypes = "rpd"; }
            if (gasTypesInt == 2) { gasTypes = "rp"; }
            if (gasTypesInt == 3) { gasTypes = "r"; }
            
            int rewardsInt = randInt(1,2);
            if (rewardsInt == 1) { rewards = true; }
            if (rewardsInt == 2) { rewards = false; }
    		
            //System.out.println(currentStation.getStationName() + ", " + currentStation.getAddress() + ", " + currentStation.getSuburb() + ", " + averagePrice + ", " + gasTypes + ", " + rewards + ", " + OpenStreetMapUtils.getLatitude(currentStation.getAddress()) + ", " + OpenStreetMapUtils.getLongitude(currentStation.getAddress()) + "\n");
            writer.write(currentStation.getStationName() + ", " + currentStation.getAddress() + ", " + currentStation.getSuburb() + ", " + averagePrice + ", " + gasTypes + ", " + rewards + ", " + genLat() + ", " + genLong() + "\n");
        }
        input.close();
        writer.close();
	}
	
	
	private static void preprocessVehicles() throws NumberFormatException, IOException{
		// declare variables
        String line;
        int year;
        double MPGHighway, MPGCity, tankCapacity;
        String make, model, gasType;
        
        int size = 24991; // number of cars in the data set 
        VehicleADT[] vehicleArray = new VehicleADT[size];
        int vehicleArrayIndex = 0;
         
        VehicleADT[] tempArray = new VehicleADT[11]; // 11 is the maximum # of data lines on cars of the same year, make, model
        int tempArrayIndex = 0;
			
        BufferedReader input = new BufferedReader(new FileReader("./data/VehiclesDATA.txt"));
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("./data/preprocessedVehiclesDATA.txt"));
      
        int q = 0;
        while ((line = input.readLine()) != null) {
        	//System.out.println(q++);
        	
            String[] carInfo = line.split(",");
            
            // read and save a lines car data
            year = Integer.parseInt(carInfo[0]); 
            make = carInfo[1];
            model = carInfo[2];
            gasType = carInfo[3];
            MPGHighway = Double.parseDouble(carInfo[4]);
            MPGCity = Double.parseDouble(carInfo[5]);
            tankCapacity = Double.parseDouble(carInfo[6]);
            
            //put car object into the temporary array
            tempArray[tempArrayIndex] = new VehicleADT(year, make, model, gasType, tankCapacity, tankCapacity, (MPGHighway+MPGCity)/2); // assumes the tank is full when constructed
            tempArrayIndex++;
            
            //discover if a new type of car data is being read in (no longer reading the same year make model)
            if (tempArrayIndex > 1)
            	if (!((tempArray[tempArrayIndex-1].getYear() == tempArray[tempArrayIndex-2].getYear()) && (tempArray[tempArrayIndex-1].getBrand().equals(tempArray[tempArrayIndex-2].getBrand())) && (tempArray[tempArrayIndex-1].getModel().equals(tempArray[tempArrayIndex-2].getModel())))){
            		
            		//add a car to the vehicle array with average mpg and average fuel tank capacity
            		double averageTankCapacity = 0, averageMPG = 0;
            		for (int j = 0 ; j < tempArrayIndex-1 ; j++){
            			averageTankCapacity += tempArray[j].getFuelTankCapac();
            			averageMPG += tempArray[j].getAverageMPG();
            		}
            		averageTankCapacity = averageTankCapacity / (tempArrayIndex - 1);
            		averageMPG = averageMPG / (tempArrayIndex - 1);
            		
            		VehicleADT object = tempArray[0];
            		
            		//put car object into the temporary array
                    vehicleArray[vehicleArrayIndex] = new VehicleADT(object.getYear(), object.getBrand(), object.getModel(), object.getGasType(), averageTankCapacity, averageTankCapacity, averageMPG); // assumes the tank is full when constructed
                    vehicleArrayIndex++;
            		
            		tempArray[0] = tempArray[tempArrayIndex-1];
            		tempArrayIndex = 1;
            	}
        }
        
        // at the end you will have an array of the vehicle just like preprocessedVehiclesDATA but with out duplicates removed. IT WORKS

        int numberRemoved = 0;
        for (int i = 0 ; i < vehicleArray.length-numberRemoved ; i++){
        	
        	int averageMPG = 0;
    		int averageTankCapacity = 0;
    		int numVehicles = 0;
    		
        	VehicleADT currentVehicle = vehicleArray[i];
        	
        	for (int j = i+1 ; j < vehicleArray.length-numberRemoved ; j++){
        		
        		if (currentVehicle.getModel().equals(vehicleArray[j].getModel()) && currentVehicle.getYear() == vehicleArray[j].getYear()){
        			
        			averageMPG += vehicleArray[j].getAverageMPG();
        			averageTankCapacity += vehicleArray[j].getFuelTankCapac();
        			numVehicles+=1;
        			
        			//remove item from array
        			
        			for (int k = j ; k < vehicleArray.length-1-numberRemoved ; k++){
        				vehicleArray[k] = vehicleArray[k+1];
        			}
        			numberRemoved++;
        		    
        		}
        	}
        	if (numVehicles == 0){
        		averageMPG = (int) vehicleArray[i].getAverageMPG();
        		averageTankCapacity = (int) vehicleArray[i].getFuelTankCapac();
        	}
        	else{
        		averageMPG = averageMPG / numVehicles;
        		averageTankCapacity = averageTankCapacity / numVehicles;
        	}
        	
        	writer.write(currentVehicle.getYear() + ", " + currentVehicle.getBrand() + ", " + currentVehicle.getModel() + ", " + currentVehicle.getGasType() + ", " + averageTankCapacity + ", " + averageTankCapacity + ", " + averageMPG + "\n");
           
        }
        
        input.close();
        writer.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		//System.out.println(genLat());
		preprocessGasStations();
		//preprocessVehicles();
		 
	}
}
