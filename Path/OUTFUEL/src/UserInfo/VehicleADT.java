package UserInfo;

public class VehicleADT {
	
	private int year;
	private String brand;
	private String model;
	private String GasType;
	private double FuelTankCapac; //In Litres
	private double currentGas; //In litres
	private double averageMPG;

	public VehicleADT(int year, String brand, String model, String GasType, double capacity, double currentGas, double aveMPG){
		
		this.year = year;
		this.brand = brand;
		this.model = model;
		this.GasType = GasType;
		this.FuelTankCapac = capacity;
		this.currentGas = currentGas;
		this.averageMPG = aveMPG;
		
	}
	
	public void set_gas(int gas) {
		currentGas = gas;
	}
	
	public int getYear(){
		return year;
	}
	
	public String getBrand(){
		return brand;
	}
	
	public String getModel(){
		return model;
	}
	
	public String getGasType(){
		return GasType;
	}
	
	public double getFuelTankCapac(){
		return FuelTankCapac;
	}
	
	public double getCurrentGas(){
		return currentGas;
	}
	
	public double getAverageMPG(){
		return averageMPG;
	}
	
	public double milesInTank(double fuelInTank){
		return this.averageMPG * fuelInTank;
	}
	
	public double gallonsOfGasNeeded(double distance){
		return distance / this.averageMPG ; 
	}
	
	public String toString()
	{
		String str = Integer.toString(year) + " " + brand + " " + model + " " + GasType + " " + Double.toString(FuelTankCapac) + " " + Double.toString(currentGas) + " " + Double.toString(averageMPG); 
		return str; 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
