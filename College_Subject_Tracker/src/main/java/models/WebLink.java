package models;

import core.Configs;
import core.Patterns;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public WebLink(String webLink
	) {
		this();
		
		if( !(webLink.isBlank() && webLink.length() < Configs.MAX_LINK_LENGTH && !isValidURL(webLink) ) ){
			this.webLink = webLink;
		}
		else {
			this.webLink = "";
			System.out.println("Invalid URL created!");
		}
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public String getWebLink() {
		return webLink;
	}
	
	public void setWebLink(String webLink) {
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
	// Overrides
	//
	@Override
	public final String toString() {
		return webLink;
	}
}