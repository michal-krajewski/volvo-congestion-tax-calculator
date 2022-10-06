package pl.byteit.volvodemo.taxcalculator.policy;

import java.time.LocalDate;

public interface TaxPolicy {

	boolean shouldApply(LocalDate date);

}
