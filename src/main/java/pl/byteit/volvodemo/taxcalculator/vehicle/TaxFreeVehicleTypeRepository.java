package pl.byteit.volvodemo.taxcalculator.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxFreeVehicleTypeRepository extends JpaRepository<TaxFreeVehicleTypeEntity, Long> {

	boolean existsByVehicleType(String vehicleType);

}
