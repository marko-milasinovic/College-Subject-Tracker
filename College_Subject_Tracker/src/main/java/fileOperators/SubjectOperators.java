package fileOperators;

import core.Configs;
import models.Subject;
import models.statics.Separators;
import repositories.subject.ISubjectRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

final class SubjectOperators {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// INVARIABLES
	//
	private static final String SAVE_FILE_NAME = "src/main/resources/subjects.txt";
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// READ - methods
	//
	static final void readSubjects(ISubjectRepository subjectRepository, Separators separator) {
		if (!FileUtils.isValidFile(SAVE_FILE_NAME)) {
			System.out.println("Invalid file path chosen.");
			return;
		}
		
		if (subjectRepository == null) {
			System.out.println("Unreachable subject repository.");
			return;
		}
		
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		
		try {
			//create new file reader
			File file = new File(SAVE_FILE_NAME);
			if (!file.isFile()) {
				return;
			}
			
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8); //previously "UTF-8"
			reader = new BufferedReader(inputStreamReader);
			
			String line;
			
			while (reader.ready()) {    //while ( (line = reader.readLine()) != null )
				line = reader.readLine();
				
				Subject subject = new Subject().readFromText(line, separator);
				
				subjectRepository.addSubject(subject);
			}
		}
		catch (Exception e) {
			System.out.println("Error while reading file: " + e.getMessage());
		}
		finally {
			try {
				fileInputStream.close();
				inputStreamReader.close();
				reader.close();
			}
			catch (IOException e) {
				System.out.println("Error while reading file: " + e.getMessage());
			}
		}
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// WRITE - methods
	//
	static final void writeSubjects(ISubjectRepository subjectRepository, Separators separator) {
		if (!FileUtils.isValidFile(SAVE_FILE_NAME)) {
			System.out.println("Invalid file path chosen.");
			return;
		}
		
		List<Subject> subjects = subjectRepository.all();
		if (subjects == null) {
			System.out.println("Empty subject repository.");
			return;
		}
		
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		Writer writer = null;
		
		try {
			//create new file writer
			File file = new File(SAVE_FILE_NAME);
			if (!file.isFile()) {
				return;
			}
			
			fileOutputStream = new FileOutputStream(file);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8); //previously "UTF-8"
			writer = new BufferedWriter(outputStreamWriter);
			
			//empty previous file
			writer.write(Configs.EMPTY_STRING);
			
			//flush all outputs
			fileOutputStream.flush();
			outputStreamWriter.flush();
			writer.flush();
			
			
			char[] buffer = new char[FileUtils.WRITE_BUFFER_SIZE];
			int size = 0;
			
			for (Subject subject : subjects) {
				size = subject.writeToText(Separators.FIRST, buffer);
				
				for (int i = 0; i < size; i++) {
					writer.write(buffer[i]);
				}
				writer.write("\n");
			}
			
			fileOutputStream.flush();
			outputStreamWriter.flush();
			writer.flush();
		}
		catch (Exception e) {
			System.out.println("Error while writing file: " + e.getMessage());
		}
		finally {
			try {
				fileOutputStream.close();
				outputStreamWriter.close();
				writer.close();
			}
			catch (IOException e) {
				System.out.println("Error while writing file: " + e.getMessage());
			}
		}
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private SubjectOperators() {}
}