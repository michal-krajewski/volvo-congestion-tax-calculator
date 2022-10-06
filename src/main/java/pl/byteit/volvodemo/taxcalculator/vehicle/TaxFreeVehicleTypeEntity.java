package pl.byteit.volvodemo.taxcalculator.vehicle;

import javax.persistence.*;

@Entity
@Table(name = "tax_free_vehicle_types")
class TaxFreeVehicleTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String vehicleType;


	@Deprecated //Hibernate only
	protected TaxFreeVehicleTypeEntity() {
	}

	TaxFreeVehicleTypeEntity(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	String getVehicleType() {
		return vehicleType;
	}

}
