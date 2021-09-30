package models;

import core.Configs;
import core.Patterns;

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
	public EMail(String eMail)
	{
		this();
		
		if( !(eMail.isBlank() && eMail.length() < Configs.MAX_EMAIL_LENGTH && !isValidEMail(eMail) ) ){
			this.eMail = eMail;
		}
		else {
			this.eMail = "";
			System.out.println("Invalid EMail created!");
		}
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public String getEMail() {
		return eMail;
	}
	
	public void setEMail(String eMail) {
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
	// Overrides
	//
	@Override
	public final String toString() {
		return eMail;
	}
}