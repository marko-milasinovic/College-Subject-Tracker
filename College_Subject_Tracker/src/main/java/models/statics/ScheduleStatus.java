package models.statics;

public enum ScheduleStatus {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variable instances
	//
	PASSED_NOT_TODAY("Prošao ne danas", -10), PASSED_TODAY("Prošao danas", -1), IN_PROGRESS("Traje", 0), UPCOMING_TODAY("Sledi", +1), UPCOMING_NOT_TODAY("Sledi", +10);
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Invariables
	//
	private final String localisedName;
	private final int value;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main constructor
	//
	ScheduleStatus(String localisedName, int value) {
		this.localisedName = localisedName;
		this.value = value;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Public methods
	//
	public String getLocalisedName() {
		return localisedName;
	}
	
	public int getValue() {
		return value;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	public static final ScheduleStatus readFromString(String string) {
		ScheduleStatus oblast = EnumUtils.getEnumFromString(ScheduleStatus.class, string);
		
		return oblast == null ? ScheduleStatus.PASSED_NOT_TODAY : oblast;
	}
	
	@Override
	public final String toString() {
		return localisedName;
	}
}
