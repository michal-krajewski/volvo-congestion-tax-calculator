package pl.byteit.volvodemo.taxcalculator.policy;

import java.time.LocalDate;

public class TaxPolicyService {

	private final DateTaxPolicyDao dateRangeDao;

	public TaxPolicyService(DateTaxPolicyDao dateRangeDao) {
		this.dateRangeDao = dateRangeDao;
	}

	public boolean isTaxFree(LocalDate date) {
		return dateRangeDao.findAll().stream() //Cache needed
				.anyMatch(dateRange -> dateRange.shouldApply(date));
	}

}
