package core;

import java.awt.*;
import java.util.stream.Stream;

public final class ScreenResolution {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Invariables
	//
	private static final int SCALED_WIDTH_MIN = 500;
	private static final int SCALED_WIDTH_MAX = 10000;
	private static final int SCALED_HEIGHT_MIN = 300;
	private static final int SCALED_HEIGHT_MAX = 10000;
	
	public static final double SCALE_FACTOR = 0.7;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	public static int TRUE_WIDTH = 1920;
	public static int TRUE_HEIGHT = 1080;
	public static int SCALED_WIDTH = 600;
	public static int SCALED_HEIGHT = 400;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Methods
	//
	public static final boolean initialiseScreenResolution() {
		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			TRUE_WIDTH = screenSize.width;
			TRUE_HEIGHT = screenSize.height;
		}
		catch (Exception e) {
			System.out.println("Error while reading screen size");
			return false;
		}
		
		int scaled = (int) (TRUE_WIDTH * SCALE_FACTOR);
		
		if (scaled < SCALED_WIDTH_MIN) {
			scaled = SCALED_WIDTH_MIN;
		} else if (scaled > SCALED_WIDTH_MAX) {
			scaled = SCALED_WIDTH_MAX;
		}
		
		SCALED_WIDTH = scaled;
		
		scaled = (int) (TRUE_HEIGHT * SCALE_FACTOR);
		if (scaled < SCALED_HEIGHT_MIN) {
			scaled = SCALED_HEIGHT_MIN;
		} else if (scaled > SCALED_HEIGHT_MAX) {
			scaled = SCALED_HEIGHT_MAX;
		}
		
		SCALED_HEIGHT = scaled;
		return true;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private ScreenResolution() {}
}
