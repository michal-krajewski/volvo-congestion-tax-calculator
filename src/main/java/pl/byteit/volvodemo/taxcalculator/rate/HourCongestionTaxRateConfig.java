package pl.byteit.volvodemo.taxcalculator.rate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HourCongestionTaxRateConfig {

	@Bean
	HourCongestionTaxRateDao jpaHourCongestionTaxRateDao(HourCongestionTaxRateRepository repository) {
		return repository::findAll;
	}

	@Bean
	HourCongestionTaxRateService hourCongestionTaxRateService(HourCongestionTaxRateDao hourCongestionTaxRateDao) {
		return new HourCongestionTaxRateService(hourCongestionTaxRateDao);
	}

}
