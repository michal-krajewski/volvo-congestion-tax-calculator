package pl.byteit.volvodemo.taxcalculator.vehicle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TaxFreeVehicleTypeConfig {

	@Bean
	TaxFreeVehicleTypeDao jpaTaxFreeVehicleTypeDao(TaxFreeVehicleTypeRepository taxFreeVehicleTypeRepository) {
		return taxFreeVehicleTypeRepository::existsByVehicleType;
	}

	@Bean
	VehicleTaxExemptService vehicleTaxExemptService(TaxFreeVehicleTypeDao taxFreeVehicleTypeDao) {
		return new VehicleTaxExemptService(taxFreeVehicleTypeDao);
	}

}
