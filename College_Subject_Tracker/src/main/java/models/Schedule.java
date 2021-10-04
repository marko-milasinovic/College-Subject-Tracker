package models;


import core.Configs;
import core.Utilities;
import models.statics.ScheduleStatus;
import models.statics.Separators;
import models.statics.DayOfWeek;

import java.io.ObjectInputFilter;
import java.util.Locale;

public final class Schedule implements Comparable<Schedule>{
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private DayOfWeek dayOfWeek;
	
	//calculated as minutes from day start (00:00 up to 23:59) with range of 1-1440
	private Integer startTime;
	
	//Positive integer up to configurable 100
	private Integer lessonCount;
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for a NEW blank Schedule
	//
	public Schedule() {
		dayOfWeek = DayOfWeek.UNDEFINED;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Constructor for an EXISTING Schedule
	//
	public Schedule(DayOfWeek dayOfWeek, Integer startTime, Integer lessonCount) {
		
		if(this.dayOfWeek == null){
			dayOfWeek = DayOfWeek.UNDEFINED;
		}else {
			this.dayOfWeek = dayOfWeek;
		}
		
		if( !(startTime < 1440 && startTime > 0)){
			this.startTime = 0;
		}else{
			this.startTime = startTime;
		}
		
		if( !(lessonCount > 0 && lessonCount < 100)){
			this.lessonCount = 1;
		}else {
			this.lessonCount = lessonCount;
		}
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Basic public methods
	//
	public final DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	
	public final void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public final Integer getStartTime() {
		return startTime;
	}
	
	public final void setStartTime(Integer time) {
		this.startTime = startTime;
	}
	
	public final Integer getLessonCount() {
		return lessonCount;
	}
	
	public final void setLessonCount(Integer lessonCount) {
		this.lessonCount = lessonCount;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Advanced public methods
	//
	public final int getEndTime(){
		return startTime + (Configs.SCHEDULE_SINGLE_LESSON_DURATION+Configs.SCHEDULE_SINGLE_LESSON_BREAK) * lessonCount;
	}
	//private final Integer getTimeSinceWeekStart(){
	//		return dayOfWeek.getDayOfWeekConversion() + startTime;
	//	}
	//public final boolean isToday(DayOfWeek dayOfWeek){
	//		return this.dayOfWeek == dayOfWeek;
	//	}
	//
	//	public final boolean isUpcoming(int time){
	//		return this.startTime > time;
	//	}
	
	public final ScheduleStatus getScheduleStatus(DayOfWeek dayOfWeek, int timeSinceDayStart){
		int minDayTime = dayOfWeek.getDayOfWeekConversion();
		int testDayTime = this.getDayOfWeek().getDayOfWeekConversion();
		
		//testing for schedules that are not today
		if(testDayTime < minDayTime){
			return ScheduleStatus.PASSED_NOT_TODAY;
		}else if(testDayTime > dayOfWeek.getDayOfWeekConversion() + DayOfWeek.MONDAY.getDayOfWeekConversion()){
			return ScheduleStatus.UPCOMING_NOT_TODAY;
		}
		
		//Schedule is today
		if(timeSinceDayStart > startTime + (Configs.SCHEDULE_SINGLE_LESSON_DURATION+Configs.SCHEDULE_SINGLE_LESSON_BREAK) * lessonCount){
			return ScheduleStatus.PASSED_TODAY;
		}
		
		if(timeSinceDayStart < startTime){
			return ScheduleStatus.UPCOMING_TODAY;
		}
		
		return ScheduleStatus.IN_PROGRESS;
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
		
		String[] inputArray = line.split( separator.getCharacter() );
		
		if(inputArray.length < 3) {
			System.out.print("Error - bad [Schedule] line-length-input. ");
		}
		
		this.dayOfWeek = DayOfWeek.readFromString(inputArray[0].toUpperCase(Locale.ROOT));
		this.startTime = Utilities.convertToInt(inputArray[1]);
		this.lessonCount = Utilities.convertToInt(inputArray[2]);
		
		return this;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Overrides
	//
	@Override
	public final String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(
				dayOfWeek.toString() + ": "
						+ Utilities.convertToHourFormat(startTime) + " - "
						+ Utilities.convertToHourFormat(startTime+(Configs.SCHEDULE_SINGLE_LESSON_DURATION+Configs.SCHEDULE_SINGLE_LESSON_BREAK)*lessonCount)
		);
		
		return  stringBuilder.toString();
	}
	
	
	/**
	 *
	 * @param schedule
	 * @return compares based on day of week and start time of lesson
	 */
	@Override
	public int compareTo(Schedule schedule) {
		return Integer.valueOf(this.dayOfWeek.getDayOfWeekConversion() + this.startTime).compareTo(
				schedule.getDayOfWeek().getDayOfWeekConversion() + schedule.getStartTime()
		);
	}
}
