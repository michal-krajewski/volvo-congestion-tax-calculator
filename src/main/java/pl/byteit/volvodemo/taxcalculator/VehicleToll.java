package pl.byteit.volvodemo.taxcalculator;

import pl.byteit.volvodemo.taxcalculator.common.Validations;

import java.time.LocalDateTime;

import static pl.byteit.volvodemo.taxcalculator.common.Validations.requireNotNull;

public record VehicleToll(Vehicle vehicle, LocalDateTime dateTime, int congestionTax) {

	public VehicleToll {
		requireNotNull(vehicle, "Vehicle cannot be null");
		requireNotNull(dateTime, "DateTime cannot be null");
	}

}
