package fileOperators;

import core.Utilities;
import models.Subject;
import models.statics.Separators;
import repositories.subject.ISubjectRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public final class FileUtils {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Invariables
	//
	private static final int MINIMUM_BUFFER_SIZE = 32768;
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Public methods
	//
	public static void loadFiles(ISubjectRepository subjectRepository)
	{
		SubjectOperators.loadElements(subjectRepository, Separators.FIRST);
	}
	
	
	public static void saveFiles()
	{
		//TODO
	}
	
	
	
	/**
	 * generic fileWriter that appends
	 */
	//textToSave.getBytes().length
	public static void writeFile( String toWrite, String filePath )
	{
		PrintWriter writer = null;
		
		try
		{
			BufferedWriter bWriter = new BufferedWriter(
					new FileWriter(filePath, true)	//BufferedWriter out = new BufferedWriter(new FileWriter(file), 32768);
			);
			
			writer = new PrintWriter( bWriter, true );
			
			writer.write( toWrite.toString() + "\n");	//sb.append(i).append(System.lineSeparator());
		}
		catch(Exception e) {
			Utilities.sendError("Greska u fajlu: " + e.getMessage());
			Logger.getLogger(FileUtils.class.getName()).log(null);
		}
		finally {
			if ( writer != null ) {
				writer.close();
			}
		}
	}
	
	
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// File operations
	//
	public static final boolean isValidFile(String filePathString)
	{
		//windows files cannot be named such
		if(filePathString.replaceAll("/\\:*?<>|\"", "").length() != filePathString.length()) {
			return false;
		}
		
		Path path = Paths.get(filePathString);
		
		if (Files.isRegularFile(path) && new File(filePathString).isFile()) {
			return true;
		}
		
		return false;
	}

	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private FileUtils() {}
}
