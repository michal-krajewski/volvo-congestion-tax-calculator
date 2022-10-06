package pl.byteit.volvodemo.taxcalculator.rule;

import pl.byteit.volvodemo.taxcalculator.VehicleToll;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class MaxDayChargeRule implements Function<List<VehicleToll>, Integer> {

	private final Integer maxDayCharge;

	public MaxDayChargeRule(Integer maxDayCharge) {
		this.maxDayCharge = maxDayCharge;
	}

	@Override
	public Integer apply(List<VehicleToll> vehicleTolls) {
		return vehicleTolls.stream()
				.collect(groupingBy(vehicleToll -> vehicleToll.dateTime().toLocalDate(), summingInt(VehicleToll::congestionTax)))
				.values()
				.stream()
				.mapToInt(value -> Integer.min(maxDayCharge, value))
				.sum();
	}

}
