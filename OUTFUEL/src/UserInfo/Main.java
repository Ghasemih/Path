package UserInfo;

import preprocessDataSets.preprocessDataSets;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String [ ] args) throws Exception {
        GasStationADT[] stationArray = Load.LoadGasStations();
    	  	GasStationADT ar1 = new GasStationADT("station1", 1.4, "subs", "269 Potts Terrace",1.5, "cool",true,43.486756,-79.877208); 
    	  	GasStationADT ar2 = new GasStationADT("station2", 1.4, "subs", "281 Potts Terrace",2.5, "cool",true,43.486793, -79.876889); 
    	  	GasStationADT ar3 = new GasStationADT("station3", 1.4, "subs", "689 Savoline Blvd",2.5, "cool",true,43.487091, -79.878294); 
    	  	GasStationADT[] array = {ar1,ar2,ar3}; 
    		WeightedDigraph G = Compiler.getWeightedDigraph("6520 Derry Rd", "735 Reece Ct", array); 
        
        Scanner name = new Scanner(System.in);
        int year;
        String brand, model, inf, location;
        GasStationADT[] b = new GasStationADT[10];
        
        System.out.print("Put your's car information: 1-brand: ");
        brand = name.next();
        System.out.print(" 2-model: ");
        model = name.next();
        System.out.print(" 3-year: ");
        year = name.nextInt();
        System.out.println("Enter your location: ");
        location = name.next();
        
    //LoadVehicles.search(brand,  model, year); 
        
    //Search based on the array that has been made in the reading file, using if statement if the car is not in the array the user can do it manually
    //calculate the mpg and print it
    //System.out.print("mpg calculation %d", mpg);
        
        UsingDistance.getArray(stationArray, location);
        
        
        System.out.print("Choose a number between price or distance based on:  0-cheapest price  1- closest location to the gas station");
        inf = name.next();
        
        
        QuickSort.sort(stationArray, "distance");
        
        
        if (inf.equals("price")) {
        	b = stationArray;
        	QuickSort.sort(b, "price");
            //using sort function based on the price
        	System.out.println("The order based on the cheapest price nearest to you is: ");
            for (int i = 1; i <= 10; i++) {
                System.out.println(i + " " + b[i]);
            }
        }
        
        else if (inf.equals("distance")) {
            //using sort function based on the closest location
        	System.out.println("The order based on the closest location is: ");
            for (int i = 1; i <= 10; i++) {
                System.out.println(i + " " + stationArray[i]);
            }
        }
        
        else
            System.out.print("it's not in the oprtion, choose again");
        
    }
   }


			/*GasStationADT[] array = Load.LoadGasStations(); 
			Scanner original = new Scanner(System.in);
			System.out.print("Enter starting point: ");
		    String origin = original.nextLine();
		    UsingDistance.getArray(array, origin); 
			for (int i = 0; i < 157; i++)
			{
				System.out.println(array[i]);
			}*/