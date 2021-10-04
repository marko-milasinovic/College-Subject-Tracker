package core;

import java.io.File;
import java.util.Date;

public final class Configs {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Invariables
	//
	public static final int EXECUTOR_QUEUE_DEPTH = 3;
	
	public static final String SINGLE_SPACE = " ";
	public static final String EMPTY_STRING = "";
	
	public static final Integer MAX_LINK_LENGTH = 256;
	public static final Integer MAX_EMAIL_LENGTH = 256;
	
	public static final String FILE_OUTPUT_LOCATION = "fileOutput/Output.txt";
	
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	
	public static final String DATE_TIME_FORMAT = "HH:mm";
	
	public static final Date DEFAULT_DATE_TIME = new Date(100); //January 1, 1970.
	
	
	public static final int SCREEN_WIDTH = 1150;
	public static final int SCREEN_HEIGHT = 1020;
	
	public static final String SAVE_FILE_FOLDER_NAME = ".college_subject_tracker";
	public static final String SAVE_FILE_JAR_NAME = "college_subject_tracker.jar";
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	public static String USER_DIRECTORY_PATH = "";
	public static String FULL_SAVE_FILE_JAR_PATH = "";
	
	public static final Integer SCHEDULE_SINGLE_LESSON_DURATION = 45;
	public static final Integer SCHEDULE_SINGLE_LESSON_BREAK = 15;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Methods
	//
	public static final boolean initialiseConfigs() {
		FULL_SAVE_FILE_JAR_PATH = USER_DIRECTORY_PATH + File.separator + SAVE_FILE_FOLDER_NAME + File.separator + SAVE_FILE_JAR_NAME;
		return true;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private Configs() {}
}
