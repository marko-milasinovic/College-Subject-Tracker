package models;

import models.statics.Separators;

import java.util.UUID;

public final class Person {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private UUID uuid;
	
	private String firstName;
	private String lastName;
	private EMail eMail;
	
	private String occupation; //professor / assistent etc.
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for a NEW person
	//
	public Person() {}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for EXISTING subjects
	//
	public Person(UUID uuid, String firstName, String lastName, EMail eMail, String occupation) {
		this();
		
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.occupation = occupation;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public UUID getUuid() {
		return uuid;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public EMail geteMail() {
		return eMail;
	}
	
	public void seteMail(EMail eMail) {
		this.eMail = eMail;
	}
	
	public String getOccupation() {
		return occupation;
	}
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// File-Operators
	//
	public final String writeToText(Separators separator){
		if(separator == Separators.UNDEFINED){
			System.out.println("Maximum depth reached.");
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(uuid.toString() + separator.getCharacter());
		stringBuilder.append(firstName + separator.getCharacter());
		stringBuilder.append(lastName + separator.getCharacter());
		stringBuilder.append(eMail + separator.getCharacter());
		stringBuilder.append(occupation + separator.getCharacter());
		
		return stringBuilder.toString();
	}
	
	public final Person readFromText(String line, Separators separator){
		if(separator == Separators.UNDEFINED){
			System.out.println("Maximum depth reached.");
			return this;
		}
		
		//code
		
		
		return this;
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	@Override
	public final String toString() {
		return firstName + " " + lastName;
	}
}
