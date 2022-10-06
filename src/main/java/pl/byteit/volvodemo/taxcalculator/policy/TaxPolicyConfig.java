package pl.byteit.volvodemo.taxcalculator.policy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaxPolicyConfig {

	@Bean
	DateTaxPolicyDao jpaDateTaxPolicyDao(DateTaxPolicyRepository repository) {
		return () -> repository.findAll().stream().map(DateTaxPolicyEntity::toPolicy).toList();
	}

	@Bean
	TaxPolicyService taxPolicyService(DateTaxPolicyDao dateTaxPolicyDao) {
		return new TaxPolicyService(dateTaxPolicyDao);
	}

}
