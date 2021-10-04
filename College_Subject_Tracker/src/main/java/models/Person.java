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
	public final UUID getUuid() {
		return uuid;
	}
	
	public final String getFirstName() {
		return firstName;
	}
	
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public final String getLastName() {
		return lastName;
	}
	
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public final EMail getEMail() {
		return eMail;
	}
	
	public final void setEMail(EMail eMail) {
		this.eMail = eMail;
	}
	
	public final String getOccupation() {
		return occupation;
	}
	
	public final void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// File-Operators
	//
	public final String writeToText(Separators separator) {
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(uuid.toString() + separator.getCharacter());
		stringBuilder.append(firstName + separator.getCharacter());
		stringBuilder.append(lastName + separator.getCharacter());
		stringBuilder.append(eMail.writeToText(separator.next()) + separator.getCharacter());
		stringBuilder.append(occupation + separator.getCharacter());
		
		return stringBuilder.toString();
	}
	
	public final Person readFromText(String line, Separators separator) {
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return this;
		}
		String[] inputArray = line.split(separator.getCharacter());
		
		if (inputArray.length < 4) {
			System.out.print("Error - bad [Person] line-length-input. ");
			return this;
		}
		
		// ARG #0
		//this.uuid = UUID.fromString(inputArray[0]);
		this.uuid = UUID.randomUUID();
		
		// ARG #1
		this.firstName = inputArray[1];
		
		// ARG #2
		this.lastName = inputArray[2];
		
		// ARG #3
		this.eMail = new EMail().readFromText(inputArray[3], separator.next());
		
		// ARG #4
		this.occupation = inputArray[4];
		
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
