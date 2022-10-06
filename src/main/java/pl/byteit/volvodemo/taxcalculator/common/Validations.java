package pl.byteit.volvodemo.taxcalculator.common;

import java.util.Collection;

public class Validations {

	public static <T> T requireNotNull(T value, String message) {
		if (value == null)
			throw new IllegalArgumentException(message);
		return value;
	}

	public static String requireNotBlank(String value, String message) {
		if (value == null || value.isBlank())
			throw new IllegalArgumentException(message);
		return value;
	}

	public static Collection<?> requireNotNullOrEmpty(Collection<?> value, String message) {
		if (value == null || value.isEmpty())
			throw new IllegalArgumentException(message);
		return value;
	}

}
