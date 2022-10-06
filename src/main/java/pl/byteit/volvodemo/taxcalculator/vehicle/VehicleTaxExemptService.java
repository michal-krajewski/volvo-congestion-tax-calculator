package pl.byteit.volvodemo.taxcalculator.vehicle;

import pl.byteit.volvodemo.taxcalculator.Vehicle;

import java.util.List;

public class VehicleTaxExemptService {

	private final TaxFreeVehicleTypeDao taxFreeVehicleTypeDao;

	public VehicleTaxExemptService(TaxFreeVehicleTypeDao taxFreeVehicleTypeDao) {
		this.taxFreeVehicleTypeDao = taxFreeVehicleTypeDao;
	}

	public boolean isTaxFree(Vehicle vehicle) {
		return taxFreeVehicleTypeDao.existsByVehicleType(vehicle.vehicleType());
	}
}
