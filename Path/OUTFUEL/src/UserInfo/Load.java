package UserInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Load {
	
	public static GasStationADT[] LoadGasStations() throws NumberFormatException, IOException{
		
		String line;
		String stationName, address, suburb, gasTypes;
		boolean rewards;
		double price, latitude, longitude;
		
		int size = 35714; //number of stations in data set
		GasStationADT[] stationArray = new GasStationADT[size];
		int stationArrayIndex = 0;
			
        BufferedReader input = new BufferedReader(new FileReader("./data/preprocessedGasStationsDATA.txt"));
        
        int q = 0;
        while ((line = input.readLine()) != null) {
        	//System.out.println(q++ + " " + line);
        	System.out.println(q++);
        	
            String[] stationInfo = line.split(",");
            
            stationName = stationInfo[0];
            address = stationInfo[1];
            suburb = stationInfo[2];
            price = Double.parseDouble(stationInfo[3]);
            gasTypes = stationInfo[4];
            rewards = Boolean.parseBoolean(stationInfo[5]);
            latitude = Double.parseDouble(stationInfo[6]);
            longitude = Double.parseDouble(stationInfo[7]);
            
            stationArray[stationArrayIndex] = new GasStationADT(stationName, price, suburb, address, 0, gasTypes, rewards, latitude, longitude); // assumes the tank is full when constructed
            stationArrayIndex++;
            
        }
        
        input.close();
        
		return stationArray;
	}
	
	public static VehicleADT[] LoadVehicles() throws NumberFormatException, IOException{
		// declare variables
        String line;
        int year;
        double MPGHighway, MPGCity, tankCapacity;
        String make, model, gasType;
        
        int size = 24991; // number of cars in the data set 
        VehicleADT[] vehicleArray = new VehicleADT[size];
        int vehicleArrayIndex = 0;
			
        BufferedReader input = new BufferedReader(new FileReader("./data/preprocessedVehiclesDATA.txt"));
      
        int q = 0;
        while ((line = input.readLine()) != null) {
        	System.out.println(q++);
        	
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
            vehicleArray[vehicleArrayIndex] = new VehicleADT(year, make, model, gasType, tankCapacity, tankCapacity, (MPGHighway+MPGCity)/2); // assumes the tank is full when constructed
            vehicleArrayIndex++;
        }
        input.close();
            
		return vehicleArray;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		GasStationADT[] x = LoadGasStations();
		VehicleADT[] y = LoadVehicles();
		
	}
}
