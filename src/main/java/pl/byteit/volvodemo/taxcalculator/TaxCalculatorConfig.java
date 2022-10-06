package pl.byteit.volvodemo.taxcalculator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.byteit.volvodemo.taxcalculator.policy.TaxPolicyService;
import pl.byteit.volvodemo.taxcalculator.rate.HourCongestionTaxRateService;
import pl.byteit.volvodemo.taxcalculator.rule.MaxDayChargeRule;
import pl.byteit.volvodemo.taxcalculator.rule.SingleChargeRule;
import pl.byteit.volvodemo.taxcalculator.vehicle.VehicleTaxExemptService;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

@Configuration
public class TaxCalculatorConfig {

	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper()
				.registerModules(new JavaTimeModule(), new Jdk8Module())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	@Bean
	CongestionTaxCalculator congestionTaxCalculator(
			VehicleTaxExemptService vehicleTaxExemptService,
			TaxPolicyService taxPolicyService,
			HourCongestionTaxRateService hourCongestionTaxRateService,
			@Value("${day.charge.max}") int singleDayMaxCharge,
			@Value("${single.charge.durationInMinutes}") int durationInMinutes
	) {
		Function<List<VehicleToll>, Integer> calculationRule =
				new SingleChargeRule(Duration.ofMinutes(durationInMinutes)).andThen(new MaxDayChargeRule(singleDayMaxCharge));
		return new CongestionTaxCalculator(
				vehicleTaxExemptService,
				taxPolicyService,
				hourCongestionTaxRateService,
				calculationRule
		);
	}

}
