package pl.byteit.volvodemo.taxcalculator.vehicle;

import pl.byteit.volvodemo.taxcalculator.Vehicle;

import java.util.List;

public class InMemoryTaxFreeVehicleTypeDao implements TaxFreeVehicleTypeDao {

	private static final List<String> DB = List.of(
			"Emergency",
			"Bus",
			"Diplomat",
			"Motorcycle",
			"Military",
			"Foreign"
	);

	@Override
	public boolean existsByVehicleType(String vehicleType) {
		return DB.contains(vehicleType);
	}

}
