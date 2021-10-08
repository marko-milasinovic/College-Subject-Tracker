package scenes;

import core.Configs;
import core.StyleCss;
import core.Utilities;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.WebLink;
import models.statics.DayOfWeek;
import models.Subject;
import models.statics.ScheduleStatus;
import repositories.image.IImageRepository;
import repositories.subject.ISubjectRepository;
import scenes.statics.UtilsFX;
import viewers.SubjectViewer;

import java.awt.*;
import java.net.URI;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public final class TrackerScene extends BorderPane {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private Stage primaryStage;
	private ISubjectRepository subjectRepository;
	private IImageRepository imageRepository;
	private ThreadPoolExecutor executor;
	
	private VBox vb_Complete;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Subject scene element information variables
	//
	private Subject currentSubject;
	private TextField tf_SubjectLongName;
	private TextArea ta_SubjectDescription;
	private ComboBox cmb_SubjectWebLinks;
	private Label lbl_ShortSchedule;
	
	private TableView<Subject> tv_Subjects;
	private TableColumn<Subject, String> tcShortName, tcLectures, tcExercises;
	private Button btn_ShowDetailed;
	private GridPane gp_SubjectInfo;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main Scene constructor
	//
	public TrackerScene(Stage stage, ISubjectRepository subjectRepository, IImageRepository imageRepository, ThreadPoolExecutor executor) {
		this.primaryStage = stage;
		this.subjectRepository = subjectRepository;
		this.imageRepository = imageRepository;
		this.executor = executor;
		
		this.primaryStage.setTitle("Subject tracker");
		
		this.setTop(createMenuBar());
		
		vb_Complete = Creators.createVBox();
		
		initializeElements();
		
		updateCurrentSubject();
		
		implementLogic();
		
		//addTooltips();
		
		this.setCenter(Creators.createScrollPane(vb_Complete));
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Element initialiser and scene component organisator
	//
	private final void initializeElements() {
		// * * * * * * * * * *
		//TOP-GridPane Chosen Subject Information
		//
		gp_SubjectInfo = Creators.makeGridPane();
		
		//first row
		gp_SubjectInfo.add(new Label("Predmet: "), 0, 0);
		
		tf_SubjectLongName = Creators.createTextField(false);
		gp_SubjectInfo.add(tf_SubjectLongName, 1, 0);
		
		//second row
		gp_SubjectInfo.add(new Label("Linkovi: "), 0, 1);
		
		cmb_SubjectWebLinks = Creators.createComboBox();
		gp_SubjectInfo.add(cmb_SubjectWebLinks, 1, 1);
		
		ImageView iv_LaunchDefaultBrowserWithLink = new ImageView();
		UtilsFX.createCustomThumbnail(iv_LaunchDefaultBrowserWithLink, imageRepository.getImage("BROWSER_CHROME"));
		Tooltip.install(iv_LaunchDefaultBrowserWithLink, UtilsFX.formatTooltip("Otvori web stranicu", iv_LaunchDefaultBrowserWithLink));
		gp_SubjectInfo.add(iv_LaunchDefaultBrowserWithLink, 2, 1);
		
		iv_LaunchDefaultBrowserWithLink.setOnMouseClicked(event1 -> {
			try {
				Desktop.getDesktop().browse(new URI("https://" + cmb_SubjectWebLinks.getSelectionModel().getSelectedItem().toString()));
			}
			catch (Exception e) {    //IOException, URISyntaxException
				System.out.println(e.getLocalizedMessage());
			}
		});
		
		//third row TODO
		
		
		// * * * * * * * * * *
		//TOP-Header HBOX #1
		//
		final HBox hb_Top_N1_Header = Creators.createHBox();
		
		Button btn_DayOfWeek = Creators.createButton("Danas");
		btn_DayOfWeek.setOnAction(event -> updateCurrentSubject());
		
		hb_Top_N1_Header.getChildren().addAll(btn_DayOfWeek, new Separator(Orientation.VERTICAL));
		
		for (DayOfWeek day : DayOfWeek.values()) {
			if (day == DayOfWeek.UNDEFINED) { continue; }
			
			btn_DayOfWeek = Creators.createButton(day.getLocalisedName());
			
			btn_DayOfWeek.setOnAction(event -> updateCurrentSubject(day, 1));
			
			hb_Top_N1_Header.getChildren().add(btn_DayOfWeek);
		}
		
		
		// * * * * *
		//TOP-VBOX #1
		//
		//lbl_CurrentTime = Creators.createLabel();
		//lbl_CurrentTime.setText(Utilities.getCurrentDayOfWeek().toString() + "  " + Utilities.getCurrentTimeOfDay());
		
		lbl_ShortSchedule = Creators.createLabel();
		lbl_ShortSchedule.setText("lbl_ShortSchedule");
		
		btn_ShowDetailed = Creators.createButton("Prikaži detaljno", imageRepository.getImage("SEARCH_2"));
		
		
		final VBox vb_Top_N1 = Creators.createVBox();
		vb_Top_N1.getChildren().addAll(gp_SubjectInfo);
		
		
		// * * * * *
		//TOP-VBOX #2
		//
		
		
		//final HBox hb_MoodleInfo = Creators.createHBox();
		//hb_MoodleInfo.getChildren().addAll(iv_LaunchDefaultBrowserWithLink);
		
		//final VBox vb_Top_N2 = Creators.createVBox();
		//vb_Top_N2.getChildren().addAll(hb_MoodleInfo);
		
		
		// * * * * *
		//TOP-VBOX #3
		//
		ta_SubjectDescription = Creators.createTextArea(false);
		
		final VBox vb_Top_N3 = Creators.createVBox();
		vb_Top_N3.getChildren().addAll(new Label("Beleške predmeta"), ta_SubjectDescription);
		
		
		// * * * * *
		//TOP-HBOX #Complete
		//
		final HBox hb_Top_Complete = Creators.createHBox();
		hb_Top_Complete.getChildren().addAll(vb_Top_N1, vb_Top_N3);
		
		
		// * * * * *
		//MIDDLE-HBOX #Complete
		//
		tv_Subjects = new TableView<>();
		tv_Subjects.setPrefHeight(200);
		tv_Subjects.setMinHeight(100);
		tv_Subjects.setPrefWidth(600);
		tv_Subjects.setMinWidth(300);
		
		final HBox hb_Middle_Complete = Creators.createHBox();
		
		hb_Middle_Complete.getChildren().addAll(tv_Subjects);
		
		
		// * * * * *
		//BOTTOM-HBOX #Complete
		//
		final HBox hb_Bottom_Complete = new HBox(100);
		hb_Bottom_Complete.setAlignment(Pos.CENTER);
		
		hb_Bottom_Complete.getChildren().addAll(btn_ShowDetailed// ,
				// btn_NewShop
				// btn_PrikaziSve
		);
		
		vb_Complete.getChildren().add(hb_Top_N1_Header);
		Creators.makeVBoxComplete(vb_Complete, hb_Top_Complete, hb_Middle_Complete, hb_Bottom_Complete);
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Logic for scene elements ?? should be combined
	//
	private final void implementLogic() {
		tcShortName = new TableColumn<Subject, String>("Predmet");
		tcShortName.setCellValueFactory(new PropertyValueFactory<Subject, String>("SubjectLongName"));
		tcShortName.setMinWidth(150);
		tcShortName.setMaxWidth(300);
		tcShortName.setStyle("-fx-alignment: CENTER;");
		
		tcLectures = new TableColumn<>("Predavanja");
		tcLectures.setCellValueFactory(new PropertyValueFactory<>("LectureScheduleString"));
		tcLectures.setMinWidth(150);
		tcLectures.setMaxWidth(200);
		
		tcExercises = new TableColumn<Subject, String>("Vežbe");
		tcExercises.setCellValueFactory(new PropertyValueFactory<>("ExerciseScheduleString"));
		tcExercises.setMinWidth(150);
		tcExercises.setMaxWidth(200);
		
		tv_Subjects.getColumns().add(tcShortName);
		tv_Subjects.getColumns().add(tcLectures);
		tv_Subjects.getColumns().add(tcExercises);
		
		btn_ShowDetailed.setOnAction(event1 -> {
			Subject selected = tv_Subjects.getSelectionModel().getSelectedItem();
			
			if (selected == null) {
				//Utilities.sendError("Neki predmet mora biti izabran.");
				return;
			}
			
			changeScene(new SubjectEditScene(primaryStage, subjectRepository, imageRepository, executor, selected.getUuid()));
			
			updateCurrentSubject();
		});
		
		tv_Subjects.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER) {
				Subject subject = tv_Subjects.getSelectionModel().getSelectedItem();
				if (subject != null) {
					btn_ShowDetailed.fire();
				}
				keyEvent.consume();
			}
		});
		
		
		//listener memory leak will be fixed in the future, TODO
		tv_Subjects.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				updateDisplayedSubjectInfo(newSelection);
			}
		});
		
		
		tv_Subjects.setOnMousePressed(event -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
				Subject subject = tv_Subjects.getSelectionModel().getSelectedItem();
				
				if (subject != null) {
					btn_ShowDetailed.fire();
				}
				event.consume();
			}
		});
		
		updateCurrentSubject();
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Creation of Main Menu
	//
	private MenuBar createMenuBar() {
		// * * * * * * * * * * *
		// Layout
		//
		MenuItem menuItem_AddSubject = new MenuItem("Dodaj predmet");
		menuItem_AddSubject.setGraphic(new ImageView(imageRepository.getImage("ADD")));
		
		MenuItem menuItem_EditSubject = new MenuItem("Izmeni predmet");
		menuItem_EditSubject.setGraphic(new ImageView(imageRepository.getImage("MANAGEMENT")));
		
		MenuItem menuItem_DeleteSubject = new MenuItem("Obriši predmet");
		menuItem_DeleteSubject.setGraphic(new ImageView(imageRepository.getImage("REMOVE")));
		
		Menu menu_Settings = new Menu("Podešavanja");
		menu_Settings.setGraphic(new ImageView(imageRepository.getImage("SETTINGS")));
		
		menu_Settings.getItems().addAll(menuItem_AddSubject, menuItem_EditSubject, menuItem_DeleteSubject);
		
		
		// * * * * * * * * * * *
		// Logic
		//
		menuItem_AddSubject.setOnAction(event -> {
			//changeScene(new SubjectEditScene(primaryStage, subjectRepository, imageRepository, executor, selected.getUuid()));
		});
		
		menuItem_EditSubject.setOnAction(event -> {
			//changeScene(new SubjectEditScene(primaryStage, subjectRepository, imageRepository, executor, selected.getUuid()));
		});
		
		
		menuItem_DeleteSubject.setOnAction(event -> {
			//changeScene(new SubjectEditScene(primaryStage, subjectRepository, imageRepository, executor, selected.getUuid()));
		});
		
		
		MenuBar mb_Main = new MenuBar();
		mb_Main.getMenus().addAll(menu_Settings);
		
		return mb_Main;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private methods
	//
	private final void updateCurrentSubject() {
		DayOfWeek dayOfWeek = Utilities.getCurrentDayOfWeek();
		int time = Utilities.getCurrentMinutesFromDayStart();
		updateCurrentSubject(dayOfWeek, time);
	}
	
	
	private final void updateCurrentSubject(DayOfWeek dayOfWeek, int time) {
		Thread thread = new Thread(() -> {
			List<Subject> subjectList = subjectRepository.getSubjects();
			
			if (!(subjectList != null && !subjectList.isEmpty())) {
				return;
			}
			
			Platform.runLater(() -> {
				updateTable(tv_Subjects, subjectList, dayOfWeek, time);
				
				currentSubject = SubjectViewer.getCurrentSubject(subjectList, dayOfWeek, time);
				if (currentSubject == null) {
					resetSubjectInfo();
					return;
				}
				
				updateDisplayedSubjectInfo(currentSubject);
			});
		});
		
		executor.execute(thread);
	}
	
	
	private final void updateDisplayedSubjectInfo(Subject subject) {
		if (subject == null) {
			resetSubjectInfo();
			return;
		}
		tf_SubjectLongName.setText(subject.getSubjectLongName());
		
		Collection<WebLink> webLinks = subject.getWebLinks();
		if (webLinks != null && !webLinks.isEmpty()) {
			cmb_SubjectWebLinks.getItems().clear();
			cmb_SubjectWebLinks.getItems().addAll(webLinks);
			cmb_SubjectWebLinks.getSelectionModel().select(0);
		}
		
		ta_SubjectDescription.setText(subject.getSubjectDescription());
		
		return;
	}
	
	
	private final void resetSubjectInfo() {
		tf_SubjectLongName.setText(Configs.SINGLE_SPACE);
		
		cmb_SubjectWebLinks.getItems().clear();
		
		ta_SubjectDescription.setText(Configs.SINGLE_SPACE);
	}
	
	
	private final void updateTable(TableView<Subject> tv_Subjects, List<Subject> subjectList, DayOfWeek dayOfWeek, int time) {
		//if any is true, return;
		if (!(tv_Subjects != null && subjectList != null && !subjectList.isEmpty())) {
			System.out.println("Failure to update table, bad arguments");
			return;
		}
		
		if (tv_Subjects.getItems().isEmpty()) {
			Collections.sort(subjectList);
			tv_Subjects.getItems().clear();
			
			for (Subject subject : subjectList) {
				tv_Subjects.getItems().add(subject);
			}
		}
		
		colourSubjectNameCollumn(tcShortName, dayOfWeek, time);
		colourLectureCollumn(tcLectures, dayOfWeek, time);
		colourExerciseCollumn(tcExercises, dayOfWeek, time);
	}
	
	private static final void colourExerciseCollumn(TableColumn<Subject, String> tableColumn, DayOfWeek dayOfWeek, int time) {
		if (tableColumn.getText().isEmpty()) {
			return;
		}
		
		tableColumn.setCellFactory(column -> new TableCell<>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty); // This is mandatory
				
				if (item == null || empty) {
					setText(null);
					setStyle("");
				} else {  // If the cell is not empty
					setText(item); // Put the String data in the cell
					Subject itemTest = getTableView().getItems().get(getIndex());
					
					ScheduleStatus testScheduleStatus = SubjectViewer.getSubjectScheduleStatus(itemTest.getExerciseSchedule(), dayOfWeek, time);
					//ScheduleStatus testScheduleStatus = SubjectViewer.getSubjectExerciseStatus(itemTest, dayOfWeek, time);
					
					if (testScheduleStatus.equals(ScheduleStatus.UPCOMING_TODAY)) {
						setStyle(StyleCss.COMMON_RARITY_COLOUR);
					} else if (testScheduleStatus.equals(ScheduleStatus.PASSED_TODAY)) {
						setStyle(StyleCss.RED_FINISHED_TYPE_COLOUR);
					} else if (testScheduleStatus.equals(ScheduleStatus.IN_PROGRESS)) {
						setStyle(StyleCss.GREEN_FINISHED_TYPE_COLOUR);
					} else {
						//never use setTextFill(Color.BLACK), it makes letters almost illegible
						setStyle(StyleCss.REGULAR_TEXT);
					}
				}
			}
		});
	}
	
	private static final void colourLectureCollumn(TableColumn<Subject, String> tableColumn, DayOfWeek dayOfWeek, int time) {
		if (tableColumn.getText().isEmpty()) {
			return;
		}
		
		tableColumn.setCellFactory(column -> new TableCell<>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty); // This is mandatory
				
				if (item == null || empty) {
					setText(null);
					setStyle("");
				} else {  // If the cell is not empty
					setText(item); // Put the String data in the cell
					Subject itemTest = getTableView().getItems().get(getIndex());
					
					ScheduleStatus testScheduleStatus = SubjectViewer.getSubjectScheduleStatus(itemTest.getLectureSchedule(), dayOfWeek, time);
					//ScheduleStatus testScheduleStatus = SubjectViewer.getSubjectLectureStatus(itemTest, dayOfWeek, time);
					
					if (testScheduleStatus.equals(ScheduleStatus.UPCOMING_TODAY)) {
						setStyle(StyleCss.COMMON_RARITY_COLOUR);
					} else if (testScheduleStatus.equals(ScheduleStatus.PASSED_TODAY)) {
						setStyle(StyleCss.RED_FINISHED_TYPE_COLOUR);
					} else if (testScheduleStatus.equals(ScheduleStatus.IN_PROGRESS)) {
						setStyle(StyleCss.GREEN_FINISHED_TYPE_COLOUR);
					} else {
						//never use setTextFill(Color.BLACK), it makes letters almost illegible
						setStyle(StyleCss.REGULAR_TEXT);
					}
				}
			}
		});
	}
	
	
	private static final void colourSubjectNameCollumn(TableColumn<Subject, String> tcArg, DayOfWeek dayOfWeek, int time) {
		if (tcArg.getText().isEmpty()) {
			return;
		}
		
		tcArg.setCellFactory(column -> new TableCell<Subject, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty); // This is mandatory
				
				if (item == null || empty) {
					setText(null);
					setStyle("");
				} else {  // If the cell is not empty
					setText(item); // Put the String data in the cell
					Subject itemTest = getTableView().getItems().get(getIndex());
					
					ScheduleStatus testScheduleStatus = SubjectViewer.getSubjectStatus(itemTest, dayOfWeek, time);
					
					if (testScheduleStatus.equals(ScheduleStatus.UPCOMING_TODAY)) {
						setStyle(StyleCss.COMMON_RARITY_COLOUR);
					} else if (testScheduleStatus.equals(ScheduleStatus.PASSED_TODAY)) {
						setStyle(StyleCss.RED_FINISHED_TYPE_COLOUR);
					} else if (testScheduleStatus.equals(ScheduleStatus.IN_PROGRESS)) {
						setStyle(StyleCss.GREEN_FINISHED_TYPE_COLOUR);
					} else {
						//never use setTextFill(Color.BLACK), it makes letters almost illegible
						setStyle(StyleCss.REGULAR_TEXT);
					}
					//switch (testScheduleStatus) {	//DOESNT WORK FOR SOME REASON
					//						case UPCOMING_TODAY:
					//							setStyle(StyleCss.COMMON_RARITY_COLOUR);
					//						case PASSED_TODAY:
					//							setStyle(StyleCss.COMMON_RARITY_COLOUR);
					//						case IN_PROGRESS:
					//							setStyle(StyleCss.GREEN_FINISHED_TYPE_COLOUR);
					//						default:
					//							setTextFill(Color.BLACK);
					//							setStyle("-fx-background-color: transparent");
					//					}
					
					
					
					/*
					 * { //Here I can see if the row of this cell is selected or not
					 * if(getTableView().getSelectionModel().getSelectedItems().contains(itemTest))
					 * setTextFill(Color.WHITE); else setTextFill(Color.BLACK); }
					 */
				}
			}
		});
	}
	
	
	private final void changeScene(BorderPane borderPane) {
		if (borderPane == null) {
			return;
		}
		
		//removeListeners(); //TODO
		
		primaryStage.getScene().setRoot(borderPane);
		primaryStage.show();
		
		return;
	}
}
