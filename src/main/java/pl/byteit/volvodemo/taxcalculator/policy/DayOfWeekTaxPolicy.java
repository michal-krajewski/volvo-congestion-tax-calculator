package pl.byteit.volvodemo.taxcalculator.policy;

import pl.byteit.volvodemo.taxcalculator.policy.TaxPolicy;

import java.time.DayOfWeek;
import java.time.LocalDate;

class DayOfWeekTaxPolicy implements TaxPolicy {

	private final DayOfWeek dayOfWeek;

	DayOfWeekTaxPolicy(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Override
	public boolean shouldApply(LocalDate date) {
		return date.getDayOfWeek() == dayOfWeek;
	}

}
