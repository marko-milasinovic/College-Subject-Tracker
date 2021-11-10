package models;

import core.Configs;
import core.Patterns;
import fileOperators.FileUtils;
import models.statics.Separators;

import java.util.regex.Matcher;

public final class WebLink {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private String description;
	private String webLink;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for a NEW WebLink
	//
	public WebLink() {
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for an EXISTING WebLink
	//
	public WebLink(String description, String webLink) {
		this();
		
		if(!setWebLink(webLink)){
			this.webLink = Configs.EMPTY_STRING;
			System.out.println("Invalid URL given!");
		}
		
		if(!setDescription(description)){
			this.description = Configs.EMPTY_STRING;
			System.out.println("Invalid Description given!");
		}
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public final String getWebLink() {
		return webLink;
	}
	
	public final boolean setWebLink(String webLink) {
		if ( !( !webLink.isBlank() && webLink.length() < Configs.MAX_LINK_LENGTH && isValidURL(webLink) )) {
			return false;
		}
		
		this.webLink = webLink;
		return true;
	}
	
	public final String getDescription() {
		return description;
	}
	
	public final boolean setDescription(String description) {
		if (description.isBlank()) {
			return false;
		}
		this.description = description;
		return true;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Validation methods
	//
	private static final boolean isValidURL(String webStr) {
		Matcher matcher = Patterns.WEB_URL.matcher(webStr);
		return matcher.find();
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// File-Operators
	//
	public final char[] writeToText(Separators separator) {
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return Configs.EMPTY_CHAR_ARRAY;
		}
		
		char[] buffer = new char[description.length() + webLink.length() + 1];
		
		int pos = 0;
		for (char c : description.toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		
		buffer[pos] = separator.getChar();
		++pos;
		
		for (char c : webLink.toCharArray()) {
			buffer[pos] = c;
			++pos;
		}
		
		return buffer;
	}
	
	
	public final WebLink readFromText(String line, Separators separator) {
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return this;
		}
		
		String[] inputArray = line.split(separator.getCharacter());
		
		if (inputArray.length < 2) {
			System.out.print("Error - bad [WebLink] line-length-input. ");
		}
		
		// ARG #0
		setDescription(inputArray[0]);
		
		// ARG #1
		setWebLink(inputArray[1]);
		
		return this;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	@Override
	public final String toString() {
		return description;
	}
}