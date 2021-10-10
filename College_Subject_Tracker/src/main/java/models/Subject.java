package models;

import core.Configs;
import models.statics.Separators;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java?rq=1
public final class Subject implements Comparable<Subject> {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private UUID uuid;
	
	private String SubjectLongName;
	private String SubjectShortName;
	private String subjectDescription;
	
	private Person professor;
	private Person assistent;
	//private Person labAssistent;
	
	private List<WebLink> webLinks;
	
	private int maxEspb;
	private int semesterId;
	
	private Schedule lectureSchedule;
	private Schedule exerciseSchedule;
	//private Schedule LaboratorySchedule;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for NEW subjects
	//
	public Subject() {
		webLinks = new ArrayList<>();
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for EXISTING subjects
	//
	public Subject(UUID uuid, String subjectLongName, String subjectShortName, String subjectSubjectDescription, Person professor, Person assistent, List<WebLink> webLinks, int maxEspb, int semesterId, Schedule lectureSchedule, Schedule exerciseSchedule) {
		this();
		
		this.uuid = uuid;
		this.SubjectLongName = subjectLongName;
		this.SubjectShortName = subjectShortName;
		this.subjectDescription = subjectSubjectDescription;
		this.professor = professor;
		this.assistent = assistent;
		this.webLinks = webLinks;
		this.maxEspb = maxEspb;
		this.semesterId = semesterId;
		this.lectureSchedule = lectureSchedule;
		this.exerciseSchedule = exerciseSchedule;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public final UUID getUuid() {
		return uuid;
	}
	
	public final String getSubjectLongName() {
		return SubjectLongName;
	}
	
	public final void setSubjectLongName(String subjectLongName) {
		SubjectLongName = subjectLongName;
	}
	
	public final String getSubjectShortName() {
		return SubjectShortName;
	}
	
	public final void setSubjectShortName(String subjectShortName) {
		SubjectShortName = subjectShortName;
	}
	
	public final String getSubjectDescription() {
		return subjectDescription;
	}
	
	public final void setSubjectDescription(String subjectDescription) {
		subjectDescription = subjectDescription;
	}
	
	public final Person getProfessor() {
		return professor;
	}
	
	public final void setProfessor(Person professor) {
		this.professor = professor;
	}
	
	public final Person getAssistent() {
		return assistent;
	}
	
	public final void setAssistent(Person assistent) {
		this.assistent = assistent;
	}
	
	public final List<WebLink> getWebLinks() {
		return webLinks;
	}
	
	public final void setWebLinks(List<WebLink> webLinks) {
		this.webLinks = webLinks;
	}
	
	public final int getMaxEspb() {
		return maxEspb;
	}
	
	public final void setMaxEspb(int maxEspb) {
		this.maxEspb = maxEspb;
	}
	
	public final int getSemesterId() {
		return semesterId;
	}
	
	public final void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	
	public final Schedule getLectureSchedule() {
		return lectureSchedule;
	}
	
	public final void setLectureSchedule(Schedule lectureSchedule) {
		this.lectureSchedule = lectureSchedule;
	}
	
	public final Schedule getExerciseSchedule() {
		return exerciseSchedule;
	}
	
	public final void setExerciseSchedule(Schedule exerciseSchedule) {
		exerciseSchedule = exerciseSchedule;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// JavaFX table methods
	//
	public final String getLectureScheduleString() {
		return lectureSchedule.toString();
	}
	
	public final String getExerciseScheduleString() {
		return exerciseSchedule.toString();
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// File-Operators
	//
	public final int writeToText(Separators separator, char[] buffer) {
		
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return 0;
			//return Configs.EMPTY_CHAR_ARRAY;
		}
		
		int pos = 0;
		//OpisPredmeta ARG #0
		for (char c : uuid.toString().toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		//OpisPredmeta ARG #1
		for (char c : SubjectLongName.toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		//OpisPredmeta ARG #2
		for (char c : SubjectShortName.toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		//OpisPredmeta ARG #3
		for (char c : subjectDescription.toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		//OpisPredmeta ARG #4
		for (char c : professor.writeToText(separator.next()).toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		//OpisPredmeta ARG #5
		for (char c : assistent.writeToText(separator.next()).toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		
		//OpisPredmeta ARG #XX
		//stringBuilder.append(labAssistent.writeToText(separator.getNextSeparator()) + separator.getCharacter());
		
		//OpisPredmeta ARG #6
		for (WebLink webLink : webLinks) {
			for (char c : webLink.writeToText(separator.next().next()).toCharArray()) {
				buffer[pos] = c;
				++pos;
			}
			buffer[pos] = separator.next().getChar();
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		
		//OpisPredmeta ARG #7
		for (char c : String.valueOf(maxEspb).toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		
		//OpisPredmeta ARG #8
		for (char c : String.valueOf(semesterId).toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		
		//OpisPredmeta ARG #9
		for (char c : lectureSchedule.writeToText(separator.next()).toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		++pos;
		
		//OpisPredmeta ARG #10
		for (char c : exerciseSchedule.writeToText(separator.next()).toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		buffer[pos] = separator.getChar();
		//do not increment pos the last time
		
		return pos;
	}
	
	public final Subject readFromText(String line, Separators separator) {
		
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return this;
		}
		
		String[] inputArray = line.split(separator.getCharacter());
		
		if (inputArray.length < 11) {
			System.out.print("Error - bad [Subject] line-length-input. ");
		}
		
		
		// ARG #0
		this.uuid = UUID.fromString(inputArray[0]);
		
		// ARG #1
		this.SubjectLongName = inputArray[1];
		
		// ARG #2
		this.SubjectShortName = inputArray[2];
		
		// ARG #3
		this.subjectDescription = inputArray[3];
		
		// ARG #4
		this.professor = new Person().readFromText(inputArray[4], separator.next());
		
		// ARG #5
		this.assistent = new Person().readFromText(inputArray[5], separator.next());
		
		//OpisPredmeta ARG #XX
		//stringBuilder.append(labAssistent + separator.getCharacter());
		
		if (webLinks != null) {
			webLinks.clear();
		}
		// ARG #6
		String[] webLinkArray = inputArray[6].split(separator.next().getCharacter());
		for (String string : webLinkArray) {
			this.webLinks.add(new WebLink().readFromText(string, separator.next()));
		}
		
		// ARG #7
		int testValue = Integer.valueOf(inputArray[7]);
		this.maxEspb = testValue > 0 ? testValue : 0;
		
		// ARG #8
		testValue = Integer.valueOf(inputArray[8]);
		this.semesterId = testValue > 0 ? testValue : 0;
		
		// ARG #9
		this.lectureSchedule = new Schedule().readFromText(inputArray[9], separator.next());
		
		// ARG #10
		this.exerciseSchedule = new Schedule().readFromText(inputArray[10], separator.next());
		
		return this;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	@Override
	public final String toString() {
		return SubjectLongName;
	}
	
	/**
	 * @param subject
	 * @returns int comparison based on Semester ID value
	 */
	@Override
	public final int compareTo(Subject subject) {
		return Integer.valueOf(this.semesterId).compareTo(subject.getSemesterId());
	}
}
