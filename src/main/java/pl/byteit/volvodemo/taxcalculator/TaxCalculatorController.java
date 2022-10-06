package pl.byteit.volvodemo.taxcalculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxCalculatorController {

	private final CongestionTaxCalculator congestionTaxCalculator;
	private final String currency;

	public TaxCalculatorController(
			CongestionTaxCalculator congestionTaxCalculator,
			@Value("${currency}") String currency
	) {
		this.congestionTaxCalculator = congestionTaxCalculator;
		this.currency = currency;
	}

	@PostMapping("/tax")
	CalculatedTaxDto calculateTax(@RequestBody GetTaxInput getTaxInput) {
		return new CalculatedTaxDto(currency, congestionTaxCalculator.getTax(getTaxInput));
	}

}
