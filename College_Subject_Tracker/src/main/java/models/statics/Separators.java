package models.statics;

public enum Separators
{
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variable instances
	//
	FIRST(1, ";"),
	SECOND(2, "_" ),
	THIRD(3, "~"),
	FOURTH(4, "\\"),
	FIFTH(5, "="),
	SIXTH(6, "@"),
	SEVENTH(7, "&"),
	
	UNDEFINED(-1, "-");
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private final int id;
	private final String character;
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main constructor
	//
	Separators(
			int id,
			String character
	) {
		this.character = character;
		this.id = id;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Public methods
	//
	public final String getCharacter() {
		return character;
	}
	
	public final int getId() {
		return id;
	}
	
	public final Separators getNextSeparator() {
		return getFromId(id+1);
	}
	
	public static final Separators getFromId(int num)
	{
		switch(num)
		{
			case 1:
				return FIRST;
			case 2:
				return SECOND;
			case 3:
				return THIRD;
			case 4:
				return FOURTH;
			case 5:
				return FIFTH;
			case 6:
				return SIXTH;
			case 7:
				return SEVENTH;
			default:
				System.out.println("Error - maximum depth reached, save file corrupted");
				return UNDEFINED;
		}
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	public static final Separators readFromString(String string)
	{
		Separators oblast = EnumUtils.getEnumFromString(Separators.class, string);
		
		return oblast == null ? Separators.UNDEFINED : oblast;
	}
	
	@Override
	public final String toString() {
		return character;
	}
}
