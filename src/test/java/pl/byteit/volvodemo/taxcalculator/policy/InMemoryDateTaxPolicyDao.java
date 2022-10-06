package pl.byteit.volvodemo.taxcalculator.policy;

import java.time.LocalDate;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class InMemoryDateTaxPolicyDao implements DateTaxPolicyDao {

	private static final List<TaxPolicy> DB = List.of(
			new DayOfWeekTaxPolicy(SATURDAY),
			new DayOfWeekTaxPolicy(SUNDAY),
			new DatesPeriodTaxPolicy(LocalDate.of(2013, 7, 1), LocalDate.of(2013, 7, 31)),
			new DatesPeriodTaxPolicy(LocalDate.of(2013, 3, 28), LocalDate.of(2013, 3, 29)),
			new DatesPeriodTaxPolicy(LocalDate.of(2013, 12, 24), LocalDate.of(2013, 12, 26)),
			new SingleDateTaxPolicy(LocalDate.of(2013, 1, 1   )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 4, 1   )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 4, 30  )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 5, 1   )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 5, 8   )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 5, 9   )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 6, 5   )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 6, 6   )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 6, 21  )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 11, 1 )),
			new SingleDateTaxPolicy(LocalDate.of(2013, 12, 31))
	);

	@Override
	public List<TaxPolicy> findAll() {
		return DB;
	}

}
