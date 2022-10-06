package pl.byteit.volvodemo.taxcalculator;

import pl.byteit.volvodemo.taxcalculator.policy.TaxPolicyService;
import pl.byteit.volvodemo.taxcalculator.rule.MaxDayChargeRule;
import pl.byteit.volvodemo.taxcalculator.rule.SingleChargeRule;
import pl.byteit.volvodemo.taxcalculator.rate.HourCongestionTaxRateService;
import pl.byteit.volvodemo.taxcalculator.vehicle.VehicleTaxExemptService;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

public class CongestionTaxCalculator {

	private final VehicleTaxExemptService vehicleTaxExemptService;
	private final TaxPolicyService taxPolicyService;
	private final HourCongestionTaxRateService hourCongestionTaxRateService;
	private final Function<List<VehicleToll>, Integer> calculationRule;

	public CongestionTaxCalculator(
			VehicleTaxExemptService vehicleTaxExemptService,
			TaxPolicyService taxPolicyService,
			HourCongestionTaxRateService hourCongestionTaxRateService,
			Function<List<VehicleToll>, Integer> calculationRule) {
		this.vehicleTaxExemptService = vehicleTaxExemptService;
		this.taxPolicyService = taxPolicyService;
		this.hourCongestionTaxRateService = hourCongestionTaxRateService;
		this.calculationRule = calculationRule;
	}

	public int getTax(GetTaxInput input) {
		if (vehicleTaxExemptService.isTaxFree(input.vehicle()))
			return 0;

		List<VehicleToll> vehicleTolls = input.passageTimes().stream()
				.map(date -> getTollFee(input.vehicle(), date))
				.filter(Objects::nonNull)
				.toList();
		return calculationRule.apply(vehicleTolls);
	}

	private VehicleToll getTollFee(Vehicle vehicle, LocalDateTime date) {
		if (taxPolicyService.isTaxFree(date.toLocalDate()))
			return null;
		return new VehicleToll(vehicle, date, hourCongestionTaxRateService.getTax(date.toLocalTime()));
	}

}
