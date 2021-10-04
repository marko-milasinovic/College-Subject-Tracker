package core;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.statics.DayOfWeek;
import scenes.statics.UtilsFX;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utilities {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// JavaFX popup error screen
	//
	public static void sendError(String message1) {
		//message1 = Objects.requireNonNull(message1, "Must not be null");
		
		Alert alert = new Alert(Alert.AlertType.ERROR, message1);
		
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		
		alert.setTitle(" ");
		alert.setGraphic(getImageView("icons/error3.png"));
		alert.setHeaderText("Došlo je do greške!");
		
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(UtilsFX.getFileStream("icons/messageSent.png")));
		
		alert.show();
	}
	
	public static void sendConfirmation(String message1) {
		//message1 = Objects.requireNonNull(message1, "Must not be null");
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION, message1);
		
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		
		
		alert.setTitle(" ");
		alert.setGraphic(getImageView("icons/confirm.png"));
		alert.setHeaderText("Uspešno izvršeno!");
		
		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(UtilsFX.getFileStream("icons/messageSent.png")));
		
		alert.show();
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Time helpers
	//
	public static final String getCurrentTimeOfDay() {
		SimpleDateFormat formatter = new SimpleDateFormat(Configs.DATE_TIME_FORMAT);
		Date date = new Date();
		return formatter.format(date);
	}
	
	public static final DayOfWeek getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
			case 1:
				return DayOfWeek.SUNDAY;
			case 2:
				return DayOfWeek.MONDAY;
			case 3:
				return DayOfWeek.TUESDAY;
			case 4:
				return DayOfWeek.WEDNESDAY;
			case 5:
				return DayOfWeek.THURSDAY;
			case 6:
				return DayOfWeek.FRIDAY;
			case 7:
				return DayOfWeek.SATURDAY;
			default:
				return DayOfWeek.UNDEFINED;
		}
	}
	
	public static final DayOfWeek getCurrentDayOfWeek() {
		return getDayOfWeek(new Date());
	}
	
	public static final int getCurrentMinutesFromWeekStart() {
		return (getCurrentDayOfWeek().getPosition() * 1440) + LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
	}
	
	public static final int getCurrentMinutesFromDayStart() {
		return LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute();
	}
	
	public static final int getDaysFromWeekStart() {
		return (getCurrentDayOfWeek().getPosition() * 1440);
	}
	
	public static final int getNextDaysFromWeekStart() {
		return (getCurrentDayOfWeek().getPosition() * 1440 + 1440);
	}
	
	
	/**
	 * Converts String to upper-case and underscore basic ascii characters
	 *
	 * @param string is null safe and empty -safe
	 * @return clean string OR "-" if something went wrong
	 */
	private static String cleanString(String string) {
		if (!(string != null && !string.isEmpty())) {
			return "-";
		}
		
		return string.trim().toUpperCase().replaceAll("Č", "C").replaceAll("Ć", "C").replaceAll("Š", "S").replaceAll("Ž", "Z").replaceAll("Đ", "DJ").replaceAll(" ", "_").replaceAll("[^A-Z_]", "");
	}
	
	public static boolean isInt(String string1) {
		if (string1.length() == string1.replaceAll("[^0-9]", "").length()) {
			return true;
		}
		return false;
	}
	
	
	public static boolean isDouble(String string1) {
		if (string1.length() == string1.replaceAll("[^0-9.-]", "").length()) {
			return true;
		}
		return false;
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Validation methods
	//
	private static final Pattern VALID_TEXT_REGEX = Pattern.compile("^[A-Z0-9._%+-]", Pattern.CASE_INSENSITIVE);
	
	public static final boolean isValidString(String emailStr) {
		Matcher matcher = VALID_TEXT_REGEX.matcher(emailStr);
		return matcher.find();
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// The all powerfull GC
	//
	public static void gc() {
		Object obj = new Object();
		WeakReference<Object> ref = new WeakReference<Object>(obj);
		obj = null;
		while (ref.get() != null) {
			System.gc();
		}
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Image manipulation
	//
	private static ImageView getImageView(String fileName) {
		Image openIcon = UtilsFX.getImage(fileName, 100, 100);
		
		ImageView openView = new ImageView(openIcon);
		openView.setFitWidth(100);
		openView.setFitHeight(100);
		
		return openView;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Number methods
	//
	public static final Integer convertToInt(String line) {
		try {
			return Integer.valueOf(line);
		}
		catch (NumberFormatException exception) {
			System.out.println("Exception while parsing int" + exception.getLocalizedMessage());
			return 0;
		}
	}
	
	public static final String convertToHourFormat(int time) {
		int h = time / 60;
		int m = time % 60;
		
		
		char[] htime = new char[2];
		if (h < 10) {
			htime[0] = '0';
			htime[1] = (char) (h + '0');
		} else {
			htime[0] = (char) (h / 10 + '0');
			htime[1] = (char) (h % 10 + '0');
		}
		
		char[] mtime = new char[2];
		if (m < 10) {
			mtime[0] = '0';
			mtime[1] = (char) (m + '0');
		} else {
			mtime[0] = (char) (m / 10 + '0');
			mtime[1] = (char) (m % 10 + '0');
		}
		
		return String.valueOf(htime) + ":" + String.valueOf(mtime);
		//return htime + ":" + mtime;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private Utilities() {}
}