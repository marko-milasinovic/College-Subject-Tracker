package fileOperators;

import core.Utilities;
import models.Subject;
import models.statics.Separators;
import repositories.subject.ISubjectRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

final class SubjectOperators {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// INVARIABLES
	//
	private static final String EMPTY_STRING = "";
	private static final String SAVE_FILE_NAME = "src/main/resources/subjects.txt";
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// READ - methods
	//
	static final boolean loadElements(ISubjectRepository subjectRepository, Separators separator)
	{
		if( !FileUtils.isValidFile(SAVE_FILE_NAME) ) {
			System.out.println("Invalid file path chosen.");
			return false;
		}
		
		Path path = Paths.get(SAVE_FILE_NAME).toAbsolutePath();
		String filePath = path.toString();
		
		try
		{
			//File file = new File(filePath);
			//FileInputStream fileInputStream = new FileInputStream(file);
			//InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			//BufferedReader reader = new BufferedReader(inputStreamReader);
			
			FileReader fileReader = new FileReader(filePath);
			BufferedReader reader = new BufferedReader(fileReader);
			
			while(reader.ready()){	//while ( (line = reader.readLine()) != null )
				
				Subject subject = new Subject().readFromText(reader.readLine(), separator);
				
				subjectRepository.addSubject(subject);
			}
			
			reader.close();
			
			return true;
		}
		catch(Exception e) {
			System.out.println("Greska u fajlu: " + e.getMessage() + e.getLocalizedMessage());
			Utilities.sendError("Greska u fajlu: " + e.getMessage() + e.getLocalizedMessage());
			Logger.getLogger(SubjectOperators.class.getName()).log(null);
			return false;
		}
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// WRITE - methods
	//
	static final void writeElementsXXX(Collection<Subject> elements, String fullFilePath, Separators s)
	{
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		Writer writer = null;
		
		try
		{
			/*
			BufferedWriter bWriter = new BufferedWriter(
					new FileWriter(filePath)
				);
			
			writer = new PrintWriter( bWriter, true );
			*/
			//Path textFile = Paths.get("foo.txt");
			//Files.write(textFile, lines, StandardCharsets.UTF_8);
			
			//List<String> read = Files.readAllLines(textFile, StandardCharsets.UTF_8);
			
			//OutputStreamWriter writer2 = new OutputStreamWriter(new FileOutputStream(PROPERTIES_FILE), StandardCharsets.UTF_8))
			
			
			
			//create new file writer
			fileOutputStream = new FileOutputStream(fullFilePath);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8); //previously "UTF-8"
			writer = new BufferedWriter(outputStreamWriter);
			
			//empty previous file
			writer.write(EMPTY_STRING);
			fileOutputStream.flush();
			outputStreamWriter.flush();
			writer.flush();
			
			
			
			byte[] buffer = new byte[1024];
			ByteArrayInputStream input = new ByteArrayInputStream(buffer, 0, buffer.length);
			for (int length; (length = input.read(buffer)) != -1; ) {
					//result.write(buffer, 0, length);
			}
			
			//ByteArrayOutputStream result = new ByteArrayOutputStream();
			//for (int length; (length = inputStream.read(buffer)) != -1; ) {
			//	result.write(buffer, 0, length);
			//}
			// StandardCharsets.UTF_8.name() > JDK 7
			//return result.toString("UTF-8");
			
			char[] writeChars = new char[256];
			
			
			
			for(Subject subject : elements){
				//writer.append( subject.writeToText(writeChars));
				writer.append(System.lineSeparator());
			}
			
			
			fileOutputStream.flush();
			outputStreamWriter.flush();
			writer.flush();
		}
		catch(Exception e) {
			//Utils.sendError("Greska u fajlu: " + e.getMessage());
			Logger.getLogger(SubjectOperators.class.getName()).log(null);
		}
		finally {
			try {
				fileOutputStream.close();
				outputStreamWriter.close();
				writer.close();
			} catch (IOException e) {
				Logger.getLogger(SubjectOperators.class.getName()).log(null);
			}
		}
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private SubjectOperators() {}
}
