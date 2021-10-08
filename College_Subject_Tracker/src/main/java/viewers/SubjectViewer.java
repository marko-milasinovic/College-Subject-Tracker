package viewers;

import models.Schedule;
import models.Subject;
import models.statics.DayOfWeek;
import models.statics.ScheduleStatus;

import java.util.*;

public abstract class SubjectViewer {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Methods
	//
	public static final ScheduleStatus getSubjectScheduleStatus(Schedule schedule, DayOfWeek dayOfWeek, int time) {
		if(schedule == null){
			return ScheduleStatus.PASSED_NOT_TODAY;
		}
		
		int minDayTime = dayOfWeek.getDayOfWeekConversion();
		int maxDayTime = dayOfWeek.getDayOfWeekConversion() + DayOfWeek.MONDAY.getDayOfWeekConversion() - 1;
		
		int testLecTime = schedule.getDayOfWeek().getDayOfWeekConversion();
		
		if (testLecTime < minDayTime || testLecTime > maxDayTime) {
			return ScheduleStatus.PASSED_NOT_TODAY;
		}
		
		if (time < schedule.getStartTime()) {
			return ScheduleStatus.UPCOMING_TODAY;
		} else if (time > schedule.getEndTime()) {
			return ScheduleStatus.PASSED_TODAY;
		}
		return ScheduleStatus.IN_PROGRESS;
	}
	
	
	public static final ScheduleStatus getSubjectStatus(Subject subject, DayOfWeek dayOfWeek, int time) {
		ScheduleStatus lectureStatus = getSubjectScheduleStatus(subject.getLectureSchedule(), dayOfWeek, time);
		ScheduleStatus exerciseStatus = getSubjectScheduleStatus(subject.getExerciseSchedule(), dayOfWeek, time);
		
		//if any is in progress return in progress
		if(lectureStatus == ScheduleStatus.IN_PROGRESS || exerciseStatus == ScheduleStatus.IN_PROGRESS){
			return ScheduleStatus.IN_PROGRESS;
		}
		//if any is upcoming return upcoming
		else if(lectureStatus == ScheduleStatus.UPCOMING_TODAY || exerciseStatus == ScheduleStatus.UPCOMING_TODAY){
			return ScheduleStatus.UPCOMING_TODAY;
		}
		//if any passed today return passed today (by this point none can be upcoming today)
		else if(lectureStatus == ScheduleStatus.PASSED_TODAY || exerciseStatus == ScheduleStatus.PASSED_TODAY){
			return ScheduleStatus.PASSED_TODAY;
		}
		
		return ScheduleStatus.PASSED_NOT_TODAY;
	}
	
	
	public static final Subject getCurrentSubject(List<Subject> subjectList, DayOfWeek dayOfWeek, Integer time) {
		Subject currentSubject = getInProgressSubject(subjectList, dayOfWeek, time);
		
		return currentSubject != null ? currentSubject : getUpcomingTodaySubject(subjectList, dayOfWeek, time);
	}
	
	public static final Subject getUpcomingTodaySubject(List<Subject> subjectList, DayOfWeek dayOfWeek, Integer time) {
		
		if (!(subjectList != null && !subjectList.isEmpty() && dayOfWeek != DayOfWeek.UNDEFINED)) {
			return null;
		}
		
		Subject currentSubject = null;
		int currentSubjectStartTime = 1440;    //24hours * 60minutes
		
		Schedule testSchedule = null;
		ScheduleStatus testScheduleStatus;
		
		for (Subject testSubject : subjectList) {    //for now assumes all subjects have both lectures and excercises
			//looping lectures
			testSchedule = testSubject.getLectureSchedule();
			if (testSchedule != null) {
				testScheduleStatus = testSchedule.getScheduleStatus(dayOfWeek, time);
				
				if (testScheduleStatus == ScheduleStatus.UPCOMING_TODAY) {
					//check if is earlier than current schedule
					if (testSchedule.getStartTime() < currentSubjectStartTime) {
						currentSubject = testSubject;
						currentSubjectStartTime = testSchedule.getStartTime();
					}
				}
			}
			
			//looping excercises
			testSchedule = testSubject.getExerciseSchedule();
			if (testSchedule != null) {
				testScheduleStatus = testSchedule.getScheduleStatus(dayOfWeek, time);
				
				if (testScheduleStatus == ScheduleStatus.UPCOMING_TODAY) {
					//check if is earlier than current schedule
					if (testSchedule.getStartTime() < currentSubjectStartTime) {
						currentSubject = testSubject;
						currentSubjectStartTime = testSchedule.getStartTime();
					}
				}
			}
		}
		
		return currentSubject;
	}
	
	public static final Subject getInProgressSubject(List<Subject> subjectList, DayOfWeek dayOfWeek, Integer time) {
		
		if (!(subjectList != null && !subjectList.isEmpty() && dayOfWeek != DayOfWeek.UNDEFINED)) {
			return null;
		}
		
		Schedule testSchedule;
		ScheduleStatus testScheduleStatus;
		
		for (Subject testSubject : subjectList) {
			//looping lectures
			testSchedule = testSubject.getLectureSchedule();
			if (testSchedule != null) {
				testScheduleStatus = testSchedule.getScheduleStatus(dayOfWeek, time);
				
				if (testScheduleStatus == ScheduleStatus.IN_PROGRESS) {
					return testSubject;
				}
			}
			
			//looping excercises
			testSchedule = testSubject.getExerciseSchedule();
			if (testSchedule != null) {
				testScheduleStatus = testSchedule.getScheduleStatus(dayOfWeek, time);
				
				if (testScheduleStatus == ScheduleStatus.IN_PROGRESS) {
					return testSubject;
				}
			}
		}
		
		return null;
	}
	
}
