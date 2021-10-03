package scenes;

import core.Configs;
import core.StyleCss;
import core.Utilities;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public final class TrackerScene extends BorderPane {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private ISubjectRepository subjectRepository;
	private IImageRepository imageRepository;
	private MenuBar mb_Main;
	private VBox vb_Complete;
	private ThreadPoolExecutor executor;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Subject scene element information variables
	//
	private Subject currentSubject;
	private TextField tf_SubjectName;
	private TextArea ta_Description;
	private ComboBox cmb_WebLinks;
	private Label lbl_CurrentTime;
	private Label lbl_ShortSchedule;
	
	private TableView<Subject> tv_Subjects;
	private TableColumn<Subject, String> tcShortName, tcLectures, tcExercises;
	private Button btn_ShowDetailed;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main Scene constructor
	//
	public TrackerScene(Stage thisWindow, ISubjectRepository subjectRepository, IImageRepository imageRepository, ThreadPoolExecutor executor) {
		thisWindow.setTitle("Subject tracker");
		this.subjectRepository = subjectRepository;
		this.imageRepository = imageRepository;
		
		this.currentSubject = subjectRepository.getSubjects().get(0);
		
		this.executor = executor;
		
		mb_Main = createMenuBar();
		this.setTop(mb_Main);
		
		vb_Complete = new VBox(20);
		initializeElements();
		
		updateCurrentSubject();
		
		implementLogic();
		
		//addTooltips();
		
		ScrollPane scrollPane = new ScrollPane(vb_Complete);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		
		this.setCenter(scrollPane);
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Element initialiser and scene component organisator
	//
	private final void initializeElements() {
		// * * * * *
		//TOP-VBOX #1
		//
		lbl_CurrentTime = Creators.createLabel();
		lbl_CurrentTime.setText(Utilities.getCurrentDayOfWeek().toString() + "  " + Utilities.getCurrentTimeOfDay());
		
		lbl_ShortSchedule = Creators.createLabel();
		lbl_ShortSchedule.setText("lbl_ShortSchedule");
		
		tf_SubjectName = Creators.createTextField(false);
		
		btn_ShowDetailed = Creators.createButton("Prikaži detaljno");
		
		final HBox hb_Top_N1_Info = Creators.createHBox();
		hb_Top_N1_Info.getChildren().addAll(lbl_CurrentTime, new Label("Predmet: "), tf_SubjectName, lbl_ShortSchedule);
		
		final VBox vb_Top_N1 = Creators.createVBox();
		vb_Top_N1.getChildren().add(hb_Top_N1_Info);
		
		
		// * * * * *
		//TOP-VBOX #2
		//
		ImageView iv_launchDefaultBrowserWithLink = new ImageView();
		UtilsFX.createCustomThumbnail(iv_launchDefaultBrowserWithLink, imageRepository.getImage("BROWSER_CHROME"));
		
		cmb_WebLinks = Creators.createComboBox();
		
		final HBox hb_MoodleInfo = Creators.createHBox();
		hb_MoodleInfo.getChildren().addAll(iv_launchDefaultBrowserWithLink, cmb_WebLinks);
		
		final VBox vb_Top_N2 = Creators.createVBox();
		vb_Top_N2.getChildren().addAll(hb_MoodleInfo);
		
		
		// * * * * *
		//TOP-VBOX #3
		//
		ta_Description = Creators.createTextArea(false);
		
		final VBox vb_Top_N3 = Creators.createVBox();
		vb_Top_N3.getChildren().add(ta_Description);
		
		
		// * * * * *
		//TOP-HBOX #Complete
		//
		final HBox hb_Top_Complete = Creators.createHBox();
		hb_Top_Complete.getChildren().addAll(vb_Top_N1, new Separator(Orientation.VERTICAL), vb_Top_N2, vb_Top_N3);
		
		
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
		
		tcLectures = new TableColumn<Subject, String>("Predavanja");
		tcLectures.setCellValueFactory(new PropertyValueFactory<Subject, String>("LectureSchedule"));
		tcLectures.setMinWidth(150);
		tcLectures.setMaxWidth(200);
		
		tcExercises = new TableColumn<Subject, String>("Vežbe");
		tcExercises.setCellValueFactory(new PropertyValueFactory<Subject, String>("ExerciseSchedule"));
		tcExercises.setMinWidth(150);
		tcExercises.setMaxWidth(200);
		
		tv_Subjects.getColumns().add(tcShortName);
		tv_Subjects.getColumns().add(tcLectures);
		tv_Subjects.getColumns().add(tcExercises);
		
		btn_ShowDetailed.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event1) {
				Subject selected = tv_Subjects.getSelectionModel().getSelectedItem();
				
				if (selected == null) {
					Utilities.sendError("Neki predmet mora biti izabran.");
					return;
				}
				
				//Main.window.setScene( new Scene(
				//						new SubjectEditor(selektovan, stage), Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT)
				//				);
				
				updateCurrentSubject();
			}
		});
		
		tv_Subjects.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					btn_ShowDetailed.fire();
					keyEvent.consume();
				}
			}
		});
		
		tv_Subjects.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					btn_ShowDetailed.fire();
					event.consume();
				}
			}
		});
		
		updateCurrentSubject();
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Creation of menu bar
	//
	private MenuBar createMenuBar() {
		MenuBar mb_Main = new MenuBar();
		
		//
		// Passive items
		//
		MenuItem mi_AddPredmet = new MenuItem("Dodaj predmet");
		//mi_AddPredmet.setGraphic(UtilsFX.getImageView("icons/add.png"));
		
		MenuItem mi_EditPredmet = new MenuItem("Izmeni postojeći predmet");
		//mi_EditPredmet.setGraphic(UtilsFX.getImageView("icons/management.png"));
		
		Menu mu_Settings = new Menu("Podešavanja");
		//mu_Settings.setGraphic(UtilsFX.getImageView("icons/settings.png"));
		mu_Settings.getItems().addAll(mi_AddPredmet, mi_EditPredmet);
		
		
		//
		// Active items
		//
		mi_AddPredmet.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Main.window.setScene( new Scene(
				//		new SubjectEditScene(selectedSubject), Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT)
				//	);
			}
		});
		
		mi_EditPredmet.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Main.window.setScene( new Scene(
				//		new SubjectEditScene(selectedSubject), Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT)
				//	);
			}
		});
		
		
		//
		// Add to mb_Main bar
		//
		mb_Main.getMenus().addAll(mu_Settings);
		
		for (DayOfWeek day : DayOfWeek.values()) {
			if (day == DayOfWeek.UNDEFINED) { continue; }
			
			Menu dayToAdd = new Menu(day.getLocalisedName());
			dayToAdd.setOnAction(event -> {
				updateCurrentSubject(day, 1);
			});
			mb_Main.getMenus().add(dayToAdd);
		}
		
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
		Thread thread = new Thread() {
			public final void run() {
				List<Subject> subjectList = subjectRepository.getSubjects();
				
				if (!(subjectList != null && !subjectList.isEmpty())) {
					return;
				}
				
				currentSubject = SubjectViewer.getCurrentSubject(subjectList, DayOfWeek.WEDNESDAY, 921);
				//currentSubject = SubjectViewer.getCurrentSubject(subjectList, dayOfWeek, time);
				if (currentSubject == null) {
					resetSubjectInfo();
					return;
				}
				
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						
						tf_SubjectName.setText(currentSubject.getSubjectLongName());
						
						Collection<WebLink> webLinks = currentSubject.getWebLinks();
						if (webLinks != null && !webLinks.isEmpty()) {
							cmb_WebLinks.getItems().clear();
							cmb_WebLinks.getItems().addAll(webLinks);
							cmb_WebLinks.getSelectionModel().select(0);
						}
						
						ta_Description.setText(currentSubject.getSubjectDescription());
						
						updateTable(tv_Subjects, subjectList, dayOfWeek, time);
					}
				});
			}
		};
		
		executor.execute(thread);
	}
	
	private final void resetSubjectInfo() {
		tf_SubjectName.setText(Configs.SINGLE_SPACE);
		
		cmb_WebLinks.getItems().clear();
		
		ta_Description.setText(Configs.SINGLE_SPACE);
	}
	
	;
	
	private final void updateTable(TableView<Subject> tv_Subjects, List<Subject> subjectList, DayOfWeek dayOfWeek, int time) {
		//if any is true, return;
		if (!(tv_Subjects != null && subjectList != null && !subjectList.isEmpty())) {
			System.out.println("Failure to update table, bad arguments");
			return;
		}
		
		Collections.sort(subjectList);
		tv_Subjects.getItems().clear();
		
		for (Subject subject : subjectList) {
			tv_Subjects.getItems().add(subject);
		}
		
		colourSubjectTable(tcShortName, dayOfWeek, time);
		//colourLectureCollumn(tcShortName, dayOfWeek, time);
		//colourExerciseCollumn(tcShortName, dayOfWeek, time);
	}
	
	
	private static final void colourSubjectTable(TableColumn<Subject, String> tcArg, DayOfWeek dayOfWeek, int time) {
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
}
