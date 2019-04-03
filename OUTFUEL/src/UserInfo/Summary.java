package UserInfo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides user with a summary of gas consumed, distance traveled 
 * and and money spent on gas
 * 
 * Initially, user will input the amount of gas they currently have in their car
 * 
 * After 10 closest gas stations are presented. We prompt user to enter the 
 * amount of gas they will be refilling their car with.
 * 
 * @author Andy
 *
 */
public class Summary {

	String start;
	GasStationADT station;
	
	public Summary(GasStationADT station, String start) {
		this.station = station;
		this.start = start; 
	}
	
	/**
	 * Returns the distance between start and the final destination
	 * 
	 * @param destination
	 * @return
	 * @throws IOException
	 */
	public double getDistance(String destination) throws IOException {
		return DistanceGoogle.distance(start, destination);
		
	}
	
	public double gasConsumed(String destination, VehicleADT v) throws IOException {
		//constants represent conversion rates for gallons to litres and mile to km
		return ((getDistance(destination) * 0.621371) / v.getAverageMPG())*3.78541;
	}
	
	/**
	 * Returns the costs of refueling car with amount Litres  of gas 
	 * 
	 * @param amount spent on g609 Argyle Street, Moss Valeas fuel, measured in Litres 
	 * @return
	 */
	public double getGasCosts(double amount) {
		
		//convert price from cents to dollar
		double price = ((station.getPrice())/100.0);
	
		//format to 2 decimal places
		DecimalFormat df = new DecimalFormat("###.##");
		
		double formatPrice = Double.parseDouble(df.format(amount*price));
		
		//fuel rate for gas station * amount refilled 
		return formatPrice; 
	}
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Summary sum = new Summary(Load.LoadGasStations()[0], "1618 Canterbury Road");
		System.out.println("Your total cost is: $" + sum.getGasCosts(19.8) + " AUD");
		System.out.println(sum.getDistance("158 Clyde Street"));
		System.out.println();
		
		
	}
}
