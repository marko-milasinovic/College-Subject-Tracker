package models;

import core.Configs;
import core.Patterns;
import models.statics.Separators;

import java.util.regex.Matcher;

public final class WebLink {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private String webLink;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for a NEW WebLink
	//
	public WebLink() {
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for an EXISTING WebLink
	//
	public WebLink(String webLink) {
		this();
		
		if (!(webLink.isBlank() && webLink.length() < Configs.MAX_LINK_LENGTH && !isValidURL(webLink))) {
			this.webLink = webLink;
		} else {
			this.webLink = "";
			System.out.println("Invalid URL created!");
		}
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public final String getWebLink() {
		return webLink;
	}
	
	public final void setWebLink(String webLink) {
		this.webLink = webLink;
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
	public final String writeToText(Separators separator) {
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return Configs.SINGLE_SPACE;
		}
		
		return webLink;
	}
	
	
	public final WebLink readFromText(String line, Separators separator) {
		if (separator == Separators.UNDEFINED) {
			System.out.println("Maximum depth reached.");
			return this;
		}
		
		// ARG #0
		if (isValidURL(line)) {
			this.webLink = line;
		} else {
			System.out.println(line);
			System.out.println("Invalid WebLink.");
			this.webLink = Configs.SINGLE_SPACE;
		}
		
		return this;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	@Override
	public final String toString() {
		if (webLink.contains("//")) return webLink.split("//")[1];
		return webLink;
	}
}