package models.statics;

public enum DayOfWeek
{
	MONDAY(	"Ponedeljak", 	1),
	TUESDAY("Utorak", 		2),
	WEDNESDAY(		"Sreda", 			3),
	THURSDAY(	"Četvrtak", 		4),
	FRIDAY(		"Petak", 			5),
	SATURDAY(		"Subota", 		6),
	SUNDAY(	"Nedelja", 		7),
	
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
	
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	public static final DayOfWeek readFromString(String testString)
	{
		DayOfWeek outputDayOfWeek = EnumUtils.getEnumFromString(DayOfWeek.class, testString);
		
		return outputDayOfWeek == null ? DayOfWeek.UNDEFINED : outputDayOfWeek;
	}
	
	
	@Override
	public final String toString() {
		return localisedName;
	}
}
