package models;

import models.statics.Separators;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Subject implements Comparable<Subject>
{
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
	public Subject(
			UUID uuid,
			String subjectLongName,
			String subjectShortName,
			String subjectSubjectDescription,
			Person professor,
			Person assistent,
			List<WebLink> webLinks,
			int maxEspb,
			int semesterId,
			Schedule lectureSchedule,
			Schedule exerciseSchedule
	) {
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
	public UUID getUuid() {
		return uuid;
	}
	
	public String getSubjectLongName() {
		return SubjectLongName;
	}
	
	public void setSubjectLongName(String subjectLongName) {
		SubjectLongName = subjectLongName;
	}
	
	public String getSubjectShortName() {
		return SubjectShortName;
	}
	
	public void setSubjectShortName(String subjectShortName) {
		SubjectShortName = subjectShortName;
	}
	
	public String getSubjectDescription() {
		return subjectDescription;
	}
	
	public void setSubjectDescription(String subjectDescription) {
		subjectDescription = subjectDescription;
	}
	
	public Person getProfessor() {
		return professor;
	}
	
	public void setProfessor(Person professor) {
		this.professor = professor;
	}
	
	public Person getAssistent() {
		return assistent;
	}
	
	public void setAssistent(Person assistent) {
		this.assistent = assistent;
	}
	
	public List<WebLink> getWebLinks() {
		return webLinks;
	}
	
	public void setWebLinks(List<WebLink> webLinks) {
		this.webLinks = webLinks;
	}
	
	public int getMaxEspb() {
		return maxEspb;
	}
	
	public void setMaxEspb(int maxEspb) {
		this.maxEspb = maxEspb;
	}
	
	public int getSemesterId() {
		return semesterId;
	}
	
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	
	public Schedule getLectureSchedule() {
		return lectureSchedule;
	}
	
	public void setLectureSchedule(Schedule lectureSchedule) {
		this.lectureSchedule = lectureSchedule;
	}
	
	public Schedule getExerciseSchedule() {
		return exerciseSchedule;
	}
	
	public void setExerciseSchedule(Schedule exerciseSchedule) {
		exerciseSchedule = exerciseSchedule;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Advanced public methods
	//
	
	
	
	
	
	
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// File-Operators
	//
	public final String writeToText(Separators separator){
		
		if(separator == Separators.UNDEFINED){
			System.out.println("Maximum depth reached.");
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		//OpisPredmeta ARG #0
		stringBuilder.append(uuid.toString() + separator.getCharacter());
		
		//OpisPredmeta ARG #1
		stringBuilder.append(SubjectLongName + separator.getCharacter());
		
		//OpisPredmeta ARG #2
		stringBuilder.append(SubjectShortName + separator.getCharacter());
		
		//OpisPredmeta ARG #3
		stringBuilder.append(subjectDescription + separator.getCharacter());
		
		//OpisPredmeta ARG #4
		stringBuilder.append(professor + separator.getCharacter());
		
		//OpisPredmeta ARG #5
		stringBuilder.append(assistent + separator.getCharacter());
		
		//OpisPredmeta ARG #XX
		//stringBuilder.append(labAssistent + separator.getCharacter());
		
		//OpisPredmeta ARG #6
		for(WebLink webLink : webLinks){
			stringBuilder.append(webLink + separator.getNextSeparator().getCharacter());
		}
		
		//OpisPredmeta ARG #7
		stringBuilder.append(maxEspb + separator.getCharacter());
		
		//OpisPredmeta ARG #8
		stringBuilder.append(semesterId + separator.getCharacter());
		
		//OpisPredmeta ARG #9
		stringBuilder.append(lectureSchedule.writeToText(separator.getNextSeparator()) + separator.getCharacter());
		
		//OpisPredmeta ARG #10
		stringBuilder.append(exerciseSchedule.writeToText(separator.getNextSeparator()) + separator.getCharacter());
		
		return stringBuilder.toString();
	}
	
	public final Subject readFromText(String line, Separators separator){
		
		if(separator == Separators.UNDEFINED){
			System.out.println("Maximum depth reached.");
			return this;
		}
		
		String[] inputArray = line.split( separator.getCharacter() );
		
		if(inputArray.length < 11) {
			System.out.print("Error - bad [PREDMET] line-length-input. ");
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
		this.professor = new Person().readFromText(inputArray[4], separator.getNextSeparator());
		
		// ARG #5
		this.assistent = new Person().readFromText(inputArray[5], separator.getNextSeparator());
		
		//OpisPredmeta ARG #XX
		//stringBuilder.append(labAssistent + separator.getCharacter());
		
		// ARG #6
		String[] webLinkArray = inputArray[6].split( separator.getNextSeparator().getCharacter() );
		for(String string : webLinkArray){
			this.webLinks.add(new WebLink(string));
		}
		
		// ARG #7
		int testValue = Integer.valueOf(inputArray[7]);
		this.maxEspb = testValue > 0 ? testValue : 0;
		
		// ARG #8
		testValue = Integer.valueOf(inputArray[8]);
		this.semesterId = testValue > 0 ? testValue : 0;
		
		// ARG #9
		this.lectureSchedule = new Schedule().readFromText(inputArray[9], separator.getNextSeparator());
		
		// ARG #10
		this.exerciseSchedule = new Schedule().readFromText(inputArray[10], separator.getNextSeparator());
		
		return this;
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	@Override
	public final String toString() {
		return SubjectLongName;
	}
	
	@Override
	public final int compareTo(Subject o) {
		return Integer.valueOf(semesterId).compareTo(o.getSemesterId());
	}
}