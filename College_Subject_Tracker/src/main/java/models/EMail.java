package models;

import core.Configs;
import core.Patterns;
import models.statics.Separators;

import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

public final class EMail {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private String eMail;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for a NEW EMail
	//
	public EMail() {
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for an EXISTING WebLink
	//
	public EMail(String eMail) {
		this();
		
		if (!(eMail.isBlank() && eMail.length() < Configs.MAX_EMAIL_LENGTH && !isValidEMail(eMail))) {
			this.eMail = eMail;
		} else {
			this.eMail = "";
			System.out.println("Invalid EMail created!");
		}
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public final String getEMail() {
		return eMail;
	}
	
	public final void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Validation methods
	//
	private static final boolean isValidEMail(String emailStr) {
		Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(emailStr);
		return matcher.find();
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// File-Operators
	//
	public final String writeToText(Separators separator) {
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return Configs.SINGLE_SPACE;
		}
		
		return eMail.toLowerCase(Locale.ROOT);
	}
	
	
	public final EMail readFromText(String line, Separators separator) {
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return this;
		}
		
		// ARG #0
		if (isValidEMail(line)) {
			this.eMail = line;
		} else {
			System.out.println("Invalid Email.");
			this.eMail = Configs.SINGLE_SPACE;
		}
		
		return this;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	@Override
	public final String toString() {
		return eMail;
	}
}
