package pl.byteit.volvodemo.taxcalculator.rate;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "hour_congestion_tax_rates")
class HourCongestionTaxRateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalTime start;

	@Column(nullable = false)
	private LocalTime end;

	@Column(nullable = false)
	private Integer tax;

	@Deprecated //Hibernate only
	protected HourCongestionTaxRateEntity() {
	}

	HourCongestionTaxRateEntity(LocalTime start, LocalTime end, Integer tax) {
		this.start = start;
		this.end = end;
		this.tax = tax;
	}

	Integer getTax() {
		return tax;
	}

	boolean shouldApply(LocalTime localTime) {
		return isAfterOrEqualStartTime(localTime) && localTime.isBefore(end);
	}

	private boolean isAfterOrEqualStartTime(LocalTime time) {
		return time.compareTo(start) >= 0;
	}
}
