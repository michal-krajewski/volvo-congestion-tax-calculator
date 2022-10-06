package pl.byteit.volvodemo.taxcalculator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.byteit.volvodemo.taxcalculator.TestUtils.dateTimeFrom;

//This test implementation relies on the provided database. It should be prepared separately
public class CongestionTaxCalculatorIntegrationTest extends IntegrationTestBase {

	@Test
	void shouldCalculateTax() {
		GetTaxInput input = new GetTaxInput(
				new Vehicle("Car"),
				List.of(
						dateTimeFrom("2013-02-07T12:00:00"), //Single previous day passage
						dateTimeFrom("2013-02-08T12:00:00"),
						dateTimeFrom("2013-02-08T13:10:00"),
						dateTimeFrom("2013-02-08T14:20:00"),
						dateTimeFrom("2013-02-08T15:30:00"),
						dateTimeFrom("2013-02-08T16:40:00"),
						dateTimeFrom("2013-02-08T17:50:00"),
						dateTimeFrom("2013-02-09T17:50:00"), //Saturday
						dateTimeFrom("2013-07-09T17:50:00"), //Free month
						dateTimeFrom("2013-12-31T17:50:00") //Free day
				)
		);

		CalculatedTaxDto calculatedTax = post("/tax", input, CalculatedTaxDto.class);

		assertThat(calculatedTax)
				.extracting(CalculatedTaxDto::currency, CalculatedTaxDto::value)
				.containsExactly("SEK", 68);
	}

}
