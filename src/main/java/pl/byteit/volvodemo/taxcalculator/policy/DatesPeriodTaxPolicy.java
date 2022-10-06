package pl.byteit.volvodemo.taxcalculator.policy;

import pl.byteit.volvodemo.taxcalculator.policy.TaxPolicy;

import java.time.LocalDate;

class DatesPeriodTaxPolicy implements TaxPolicy {

	private final LocalDate from;
	private final LocalDate to;

	DatesPeriodTaxPolicy(LocalDate from, LocalDate to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public boolean shouldApply(LocalDate date) {
		return !date.isBefore(from) && !date.isAfter(to);
	}

}
