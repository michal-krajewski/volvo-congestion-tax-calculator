package pl.byteit.volvodemo.taxcalculator.rate;

import java.time.LocalTime;

public class HourCongestionTaxRateService {

	private final HourCongestionTaxRateDao hourCongestionTaxRateDao;

	public HourCongestionTaxRateService(HourCongestionTaxRateDao hourCongestionTaxRateDao) {
		this.hourCongestionTaxRateDao = hourCongestionTaxRateDao;
	}

	public Integer getTax(LocalTime time) {
		return hourCongestionTaxRateDao.findAll().stream()//Cache should be added
				.filter(hourTaxRate -> hourTaxRate.shouldApply(time))
				.findFirst()
				.map(HourCongestionTaxRateEntity::getTax)
				.orElse(0);
	}

}
