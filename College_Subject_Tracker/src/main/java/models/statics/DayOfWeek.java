package models.statics;

public enum DayOfWeek {
	MONDAY("Ponedeljak", 1), TUESDAY("Utorak", 2), WEDNESDAY("Sreda", 3), THURSDAY("Četvrtak", 4), FRIDAY("Petak", 5), SATURDAY("Subota", 6), SUNDAY("Nedelja", 7),
	
	UNDEFINED("-", 0);
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Invariables
	//
	private final String localisedName;
	private final int position;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main constructor
	//
	DayOfWeek(String localisedName, int position) {
		this.localisedName = localisedName;
		this.position = position;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public String getLocalisedName() {
		return localisedName;
	}
	
	public int getPosition() {
		return position;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Advanced public methods
	//
	
	/**
	 * Integer convertion of Day position in Week
	 *
	 * @return dayOfWeek.getPosition() * 10000
	 */
	public final Integer getDayOfWeekConversion() {
		return this.getPosition() * 10000;
	}
	
	public final DayOfWeek next() {
		int testPosition = this.position + 1;
		
		if (!(testPosition < 7 && testPosition > 0)) {
			return MONDAY;
		}
		
		return getDayOfWeekFromPosition(this.position + 1);
	}
	
	
	public static final DayOfWeek getDayOfWeekFromPosition(int argPosition) {
		switch (argPosition) {
			case 7:
				return DayOfWeek.SUNDAY;
			case 6:
				return DayOfWeek.SATURDAY;
			case 5:
				return DayOfWeek.FRIDAY;
			case 4:
				return DayOfWeek.THURSDAY;
			case 3:
				return DayOfWeek.WEDNESDAY;
			case 2:
				return DayOfWeek.TUESDAY;
			case 1:
				return DayOfWeek.MONDAY;
			default:
				return DayOfWeek.UNDEFINED;
		}
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	public static final DayOfWeek readFromString(String testString) {
		DayOfWeek outputDayOfWeek = EnumUtils.getEnumFromString(DayOfWeek.class, testString);
		
		return outputDayOfWeek == null ? DayOfWeek.UNDEFINED : outputDayOfWeek;
	}
	
	
	@Override
	public final String toString() {
		return localisedName;
	}
}
