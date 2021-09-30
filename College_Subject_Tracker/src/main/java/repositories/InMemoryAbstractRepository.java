package repositories;

public abstract class InMemoryAbstractRepository {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Static variables
	//
	private static String userFolderLocation;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main constructor
	//
	public InMemoryAbstractRepository()
	{
		try {
			//search for /user folder
			
		}
		catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Methods
	//
}
