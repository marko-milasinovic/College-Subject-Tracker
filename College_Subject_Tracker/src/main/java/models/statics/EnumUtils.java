package models.statics;

import java.util.regex.Pattern;

public final class EnumUtils {
	
	/**
	 * A common method for all enums since they can't have another base class
	 *
	 * @param <T>    Enum type
	 * @param c      enum type. All enums must be all caps.
	 * @param string case insensitive
	 * @return corresponding enum, or null
	 */
	public static final <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
		if (c != null && !string.isBlank()) {
			try {
				return Enum.valueOf(c, cleanString(string));
			}
			catch (IllegalArgumentException ex) {
				System.out.println("Error while processing enum: " + ex.getLocalizedMessage());
			}
		}
		return null;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private methods
	//
	private static final Pattern CLEAN_ENUM = Pattern.compile("[^0-9A-Z_]", Pattern.CASE_INSENSITIVE);
	
	private static final String cleanString(String string) {
		int len = string.length();
		String test = CLEAN_ENUM.matcher(string).replaceFirst("");
		
		return len == test.length() ? test.toUpperCase() : "";
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private EnumUtils() {}
}
