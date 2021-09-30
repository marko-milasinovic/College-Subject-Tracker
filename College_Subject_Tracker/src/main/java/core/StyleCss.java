package core;

//https://blog.ngopal.com.np/2012/07/11/customize-scrollbar-via-css/
public class StyleCss
{
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	// konstante
	//
	public static final String RED_FINISHED_TYPE_COLOUR 	= "-fx-background-color: IndianRed;"
	+ "-fx-text-fill: #ffffe6;";
	
	public static final String GREEN_FINISHED_TYPE_COLOUR 	= "-fx-background-color: DarkSeaGreen;"
	+ "-fx-text-fill: #4d2600;";
	
	
	public static final String COMMON_RARITY_COLOUR 	= "-fx-background-color: antiquewhite;"
	+ "-fx-text-fill: #4d2600;";
	
	public static final String UNCOMMON_RARITY_COLOUR 	= "-fx-background-color: aquamarine;"
	+ "-fx-text-fill: #4d2600;";
	
	public static final String RARE_RARITY_COLOUR 		= "-fx-background-color: cornflowerblue;"
	+ "-fx-text-fill: #ffffe6;";
	
	public static final String EPIC_RARITY_COLOUR 		= "-fx-background-color: darkgoldenrod;"
	+ "-fx-text-fill: #ffffe6;";
		
	
	public static final String LABEL_RARITY_MOD = 
			"-fx-font-weight: bold;-fx-font-size: 12;" 
			+ "-fx-border-radius: 7 7 7 7;-fx-background-radius: 7 7 7 7;";
	
	//public static final Color MENUCOLOUR = Color.rgb(209, 159, 33, .99);
	
	//public static final String MENUCOLOUR_S1 = "-fx-background-color: #ffebcc";
	
	//public static final String MENUCOLOUR_S2 = "-fx-border-color: #ff884d";
	
	public static final String COMBOBOX_STYLE = 
			"-fx-padding: 0 5 5 5;" 
			+ "-fx-background-insets: 0,0 0 2 0, 0 0 3 0, 0 0 4 0;"
			+ "-fx-background-radius: 4;"
			+ "-fx-background-color: \n" + 
				" linear-gradient(from 0% 93% to 0% 100%, #e76e32 0%, #e35e1c 100%),\n" + 
				" #d56644,\n" + 
				" #e6a17f,\n" + 
				" radial-gradient(center 50% 50%, radius 100%, #e6a17f, #de866e);"
			+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );";
			//+ "-fx-font-size: 1.1em;";
	
	
	public static final String MAIN_MENU_STYLE = 
			"-fx-padding: 3 7 3 7;" 
			+ "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;"
			+ "-fx-background-color: \n" + 
			" linear-gradient(from 0% 93% to 0% 100%, #f2e5d9 0%, #f8f2ec 100%),\n" +
			" radial-gradient(center 50% 50%, radius 100%, #e5cbb3, 	#ebd8c6);"
			+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"
			+ "-fx-font-size: 1.1em;"
			+ "-fx-border-color: #ecbbac;"
			+ "-fx-border-width: 0 0 1 0;"
			+ "-fx-border-insets: 0 0 6 0;";
			
			
	public static final String IDLE_BUTTON_STYLE = 
			"-fx-padding: 7 14 14 14;" 
			+ "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;"
			+ "-fx-background-radius: 8;"
			+ "-fx-background-color: \n" + 
				" linear-gradient(from 0% 93% to 0% 100%, #e76e32 0%, #e35e1c 100%),\n" + 
				" #d56644,\n" + 
				" #e6a17f,\n" + 
				" radial-gradient(center 50% 50%, radius 100%, #e6a17f, #de866e);"
			+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"
			//+ "-fx-font-weight: bold;"
			+ "-fx-font-size: 1.1em;";

	public static final String ENTERED_BUTTON_STYLE = 
			"-fx-padding: 7 14 14 14;" 
			+ "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;"
			+ "-fx-background-radius: 8;"
			+ "-fx-background-color: \n" + 
				" linear-gradient(from 0% 93% to 0% 100%, #ec8e5f 0%, #e97e49 100%),\n" + 
				" #de886e,\n" + 
				" #eec1aa,\n" + 
				" radial-gradient(center 50% 50%, radius 100%, #eec1aa, #e7a897);"
			+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );"
			+ "-fx-font-style: italic;"
			+ "-fx-font-size: 1.1em;";
	
	public static final String PRESSED_BUTTON_STYLE =  	//"-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
			"-fx-padding: 7 14 14 14;"
			+ "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;"
			+ "-fx-background-radius: 8;"
			+ "-fx-background-color:" 
	        + "linear-gradient(from 0% 93% to 0% 100%, #ec8e5f 0%, #e97e49 100%),"
	        + "#de886e,"
	        + "#eec1aa,"
	        + "radial-gradient(center 50% 50%, radius 100%, #eec1aa, #e7a897);"
	        + "-fx-font-style: italic;";
	
	
		//+ "-fx-border-color: #3e190e;"
		//+ "-fx-border-width: 1 1 1 1;"
		//+ "-fx-border-radius: 6;";
		//+ "-fx-font-weight: bold;"
	
		//-fx-border-width: 5;
	  	//-fx-background-insets: 12;
	  	//-fx-border-insets: 10;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private StyleCss() {}
}
