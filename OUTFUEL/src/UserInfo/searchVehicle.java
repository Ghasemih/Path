package UserInfo;

public class searchVehicle {
	public static VehicleADT search(VehicleADT[] array, int year, String model, String make) {
		for (int i = 0 ; i < array.length ; i++) {
			if (array[i].getModel() == model) {
				if (array[i].getYear() == year)
					return array[i];
			}
		}
		VehicleADT noVehicle = new VehicleADT(0, "empty", "empty", "empty", 0.0, 0.0, 0.0);
		return noVehicle;
	}
}
