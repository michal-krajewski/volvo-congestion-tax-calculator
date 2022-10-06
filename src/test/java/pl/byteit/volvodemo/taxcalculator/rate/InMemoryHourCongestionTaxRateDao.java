package pl.byteit.volvodemo.taxcalculator.rate;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class InMemoryHourCongestionTaxRateDao implements HourCongestionTaxRateDao {

	private static final List<HourCongestionTaxRateEntity> DB = List.of(
			new HourCongestionTaxRateEntity(LocalTime.of(6, 0), LocalTime.of(6, 30), 8),
			new HourCongestionTaxRateEntity(LocalTime.of(6, 30), LocalTime.of(7, 0), 13),
			new HourCongestionTaxRateEntity(LocalTime.of(7, 0), LocalTime.of(8, 0), 18),
			new HourCongestionTaxRateEntity(LocalTime.of(8, 0), LocalTime.of(8, 30), 13),
			new HourCongestionTaxRateEntity(LocalTime.of(8, 30), LocalTime.of(15, 0), 8),
			new HourCongestionTaxRateEntity(LocalTime.of(15, 0), LocalTime.of(15, 30), 13),
			new HourCongestionTaxRateEntity(LocalTime.of(15, 30), LocalTime.of(17, 0), 18),
			new HourCongestionTaxRateEntity(LocalTime.of(17, 0), LocalTime.of(18, 0), 13),
			new HourCongestionTaxRateEntity(LocalTime.of(18, 0), LocalTime.of(18, 30), 8)
	);

	@Override
	public List<HourCongestionTaxRateEntity> findAll() {
		return DB;
	}

}
