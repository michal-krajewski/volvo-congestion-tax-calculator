package pl.byteit.volvodemo.taxcalculator;

import static pl.byteit.volvodemo.taxcalculator.common.Validations.requireNotBlank;

public record Vehicle(String vehicleType) {

	public Vehicle {
		requireNotBlank(vehicleType, "Vehicle type cannot be blank");
	}

}
