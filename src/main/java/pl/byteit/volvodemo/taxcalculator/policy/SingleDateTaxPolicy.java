package pl.byteit.volvodemo.taxcalculator.policy;

import pl.byteit.volvodemo.taxcalculator.policy.TaxPolicy;

import java.time.LocalDate;

class SingleDateTaxPolicy implements TaxPolicy {

	private final LocalDate date;

	SingleDateTaxPolicy(LocalDate date) {
		this.date = date;
	}

	@Override
	public boolean shouldApply(LocalDate date) {
		return this.date.equals(date);
	}

}
