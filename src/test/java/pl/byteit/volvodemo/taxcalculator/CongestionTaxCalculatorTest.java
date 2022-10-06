package pl.byteit.volvodemo.taxcalculator;

import org.junit.jupiter.api.Test;
import pl.byteit.volvodemo.taxcalculator.policy.InMemoryDateTaxPolicyDao;
import pl.byteit.volvodemo.taxcalculator.policy.TaxPolicyService;
import pl.byteit.volvodemo.taxcalculator.rate.HourCongestionTaxRateService;
import pl.byteit.volvodemo.taxcalculator.rate.InMemoryHourCongestionTaxRateDao;
import pl.byteit.volvodemo.taxcalculator.vehicle.InMemoryTaxFreeVehicleTypeDao;
import pl.byteit.volvodemo.taxcalculator.vehicle.VehicleTaxExemptService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.byteit.volvodemo.taxcalculator.TestUtils.dateTimeFrom;

class CongestionTaxCalculatorTest {

	private static final Vehicle CAR = new Vehicle("Car");

	private final VehicleTaxExemptService vehicleTaxExemptService = new VehicleTaxExemptService(new InMemoryTaxFreeVehicleTypeDao());
	private final TaxPolicyService taxPolicyService = new TaxPolicyService(new InMemoryDateTaxPolicyDao());
	private final HourCongestionTaxRateService taxRateService = new HourCongestionTaxRateService(new InMemoryHourCongestionTaxRateDao());

	private final CongestionTaxCalculator taxCalculator = new TaxCalculatorConfig().congestionTaxCalculator(
			vehicleTaxExemptService,
			taxPolicyService,
			taxRateService,
			60,
			60
	);

	@Test
	void shouldCalculateTaxForSinglePassage() {
		LocalDateTime passageDateTime = dateTimeFrom("2013-02-07T06:23:27");

		int tax = taxCalculator.getTax(new GetTaxInput(CAR, List.of(passageDateTime)));

		assertThat(tax).isEqualTo(8);
	}

	@Test
	void shouldSelectBiggerTaxForPassagesWithinHour() {
		List<LocalDateTime> passages = List.of(
				dateTimeFrom("2013-02-08T15:29:00"),
				dateTimeFrom("2013-02-08T15:47:00"),
				dateTimeFrom("2013-02-08T16:01:00")
		);

		int tax = taxCalculator.getTax(new GetTaxInput(CAR, passages));

		assertThat(tax).isEqualTo(18);
	}

	@Test
	void shouldCalculateTaxForTwoPassagesWhenPassagesAreSeparatedWith40Minutes() {
		List<LocalDateTime> passages = List.of(
				dateTimeFrom("2013-02-08T15:30:00"),
				dateTimeFrom("2013-02-08T16:10:00"),
				dateTimeFrom("2013-02-08T16:40:00")
		);

		int tax = taxCalculator.getTax(new GetTaxInput(CAR, passages));

		assertThat(tax).isEqualTo(36);
	}

	@Test
	void shouldLimitTotalTaxForSingleDay() {
		List<LocalDateTime> passages = List.of(
				dateTimeFrom("2013-02-08T12:00:00"),
				dateTimeFrom("2013-02-08T13:10:00"),
				dateTimeFrom("2013-02-08T14:20:00"),
				dateTimeFrom("2013-02-08T15:30:00"),
				dateTimeFrom("2013-02-08T16:40:00"),
				dateTimeFrom("2013-02-08T17:50:00")
		);

		int tax = taxCalculator.getTax(new GetTaxInput(CAR, passages));

		assertThat(tax).isEqualTo(60);
	}

	@Test
	void shouldNotLimitTotalTaxAmountForSingleDayWhenPassesOccurredInMultipleDays() {
		List<LocalDateTime> passages = List.of(
				dateTimeFrom("2013-02-07T12:00:00"),
				dateTimeFrom("2013-02-07T13:10:00"),
				dateTimeFrom("2013-02-07T14:20:00"),
				dateTimeFrom("2013-02-08T15:30:00"),
				dateTimeFrom("2013-02-08T16:40:00"),
				dateTimeFrom("2013-02-08T17:50:00")
		);

		int tax = taxCalculator.getTax(new GetTaxInput(CAR, passages));

		assertThat(tax).isGreaterThan(60);
	}

	@Test
	void shouldNotAddTaxForExemptVehicles() {
		Vehicle tollExemptVehicle = new Vehicle("Emergency");
		List<LocalDateTime> passages = List.of(
				dateTimeFrom("2013-02-08T15:29:00"),
				dateTimeFrom("2013-02-08T16:01:00")
		);

		int tax = taxCalculator.getTax(new GetTaxInput(tollExemptVehicle, passages));

		assertThat(tax).isZero();
	}

}
