package models;


import models.statics.Separators;
import models.statics.DayOfWeek;

public final class Schedule {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private DayOfWeek dayOfWeek;
	private Integer startTime;
	private Integer lessonCount;
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for a NEW WebLink
	//
	public Schedule() {
		dayOfWeek = DayOfWeek.UNDEFINED;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for an EXISTING WebLink
	//
	public Schedule(DayOfWeek dayOfWeek, Integer startTime, Integer lessonCount) {
		this();
		
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.lessonCount = lessonCount;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public Integer getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Integer time) {
		this.startTime = startTime;
	}
	
	public Integer getLessonCount() {
		return lessonCount;
	}
	
	public void setLessonCount(Integer lessonCount) {
		this.lessonCount = lessonCount;
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
		
		stringBuilder.append(dayOfWeek.name() + separator.getCharacter());
		stringBuilder.append(startTime + separator.getCharacter());
		stringBuilder.append(lessonCount);
		
		return stringBuilder.toString();
	}
	
	public final Schedule readFromText(String line, Separators separator){
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
		return dayOfWeek.toString();
	}
}
