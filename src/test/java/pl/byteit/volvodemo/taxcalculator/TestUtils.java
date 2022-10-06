package pl.byteit.volvodemo.taxcalculator;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class TestUtils {

	public static LocalDateTime dateTimeFrom(String isoDateTimeFormatValue) {
		return LocalDateTime.parse(isoDateTimeFormatValue, ISO_DATE_TIME);
	}

}
