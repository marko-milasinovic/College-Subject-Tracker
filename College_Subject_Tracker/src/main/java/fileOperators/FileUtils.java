package fileOperators;

import core.Utilities;
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
	protected static final int WRITE_BUFFER_SIZE = 1024;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Public methods
	//
	public static final void loadFiles(ISubjectRepository subjectRepository) {
		SubjectOperators.readSubjects(subjectRepository, Separators.FIRST);
	}
	
	
	public static final void saveFiles(ISubjectRepository subjectRepository) {
		SubjectOperators.writeSubjects(subjectRepository, Separators.FIRST);
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// File operations
	//
	public static final boolean isValidFile(String filePathString) {
		//windows files cannot be named such
		if (filePathString.replaceAll("/\\:*?<>|\"", "").length() != filePathString.length()) {
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
