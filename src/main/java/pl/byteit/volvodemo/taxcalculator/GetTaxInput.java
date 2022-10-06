package pl.byteit.volvodemo.taxcalculator;

import java.time.LocalDateTime;
import java.util.List;

import static pl.byteit.volvodemo.taxcalculator.common.Validations.requireNotNull;
import static pl.byteit.volvodemo.taxcalculator.common.Validations.requireNotNullOrEmpty;

public record GetTaxInput(Vehicle vehicle, List<LocalDateTime> passageTimes) {

	public GetTaxInput {
		requireNotNull(vehicle, "Vehicle cannot be null");
		requireNotNullOrEmpty(passageTimes, "Passage times cannot be null nor empty");
	}

}
