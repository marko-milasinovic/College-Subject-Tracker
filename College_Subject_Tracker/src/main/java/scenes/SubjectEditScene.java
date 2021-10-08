package scenes;

import core.Configs;
import core.StyleCss;
import core.Utilities;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Person;
import models.Schedule;
import models.Subject;
import models.WebLink;
import models.statics.DayOfWeek;
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

public final class SubjectEditScene extends BorderPane {
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
	private Subject displayedSubject;
	private TextField tf_SubjectLongName;
	private TextField tf_SubjectShortName;
	private TextArea ta_SubjectDescription;
	private ComboBox<WebLink> cmb_WebLinks;
	private TextField tf_WebLink;
	
	private Button btn_SaveChanges;
	private Button btn_CancelChanges;
	private Button btn_DeleteSubject;
	
	private ComboBox<String> cmb_MaxEspb;
	private ComboBox<String> cmb_SemesterId;
	
	//person info
	//private Person professor;
	//private Person assistent;
	
	//schedule info
	private ComboBox<DayOfWeek> cmb_LScheduleDayOfWeek;
	private TextField tf_LScheduleStartTime;
	private ComboBox<String> cmb_LScheduleLessonCount;
	private ComboBox<DayOfWeek> cmb_EScheduleDayOfWeek;
	private TextField tf_EScheduleStartTime;
	private ComboBox<String> cmb_EScheduleLessonCount;
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main Scene constructor
	//
	public SubjectEditScene(Stage stage, ISubjectRepository subjectRepository, IImageRepository imageRepository, ThreadPoolExecutor executor, UUID subjectUUID) {
		this.primaryStage = stage;
		this.subjectRepository = subjectRepository;
		this.imageRepository = imageRepository;
		this.executor = executor;
		
		this.primaryStage.setTitle("Subject editor");
		
		this.displayedSubject = subjectRepository.getSubject(subjectUUID);
		
		this.setTop(createMenuBar());
		
		vb_Complete = Creators.createVBox();
		
		initializeElements();
		
		displaySubjectInfo();
		
		implementLogic();
		
		//addTooltips();
		
		this.setCenter(Creators.createScrollPane(vb_Complete));
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Element initialiser and scene component organisator
	//
	private final void initializeElements() {
		// * * * * * * * * * * *
		// Element creation
		//
		tf_SubjectLongName = Creators.createTextField(true);
		tf_SubjectShortName = Creators.createTextField(true);
		ta_SubjectDescription = Creators.createTextArea(true);
		cmb_MaxEspb = Creators.createComboBox(Configs.SEMESTER_MAX_ESPB_CHOICES);
		cmb_SemesterId = Creators.createComboBox(Configs.SEMESTER_ID_CHOICES);
		
		cmb_WebLinks = Creators.createComboBox();
		tf_WebLink = Creators.createTextField(true);
		
		//person info
		//private Person professor;
		//private Person assistent;
		
		//schedule info
		cmb_LScheduleDayOfWeek = Creators.createComboBox(DayOfWeek.VALID_VALUES);
		tf_LScheduleStartTime = Creators.createTextField(true);
		cmb_LScheduleLessonCount = Creators.createComboBox(Configs.LESSON_COUNT_CHOICES);
		
		cmb_EScheduleDayOfWeek = Creators.createComboBox(DayOfWeek.VALID_VALUES);
		tf_EScheduleStartTime = Creators.createTextField(true);
		cmb_EScheduleLessonCount = Creators.createComboBox(Configs.LESSON_COUNT_CHOICES);
		
		btn_SaveChanges = Creators.createButton("Sačuvaj izmene");
		btn_CancelChanges = Creators.createButton("Poništi izmene");
		btn_DeleteSubject = Creators.createButton("Obriši predmet");
		
		
		// * * * * * * * * * * *
		// Layout creation
		//
		GridPane gp_BasicSubjectInfo = Creators.makeGridPane();
		//first row
		gp_BasicSubjectInfo.add(new Label("Pun naziv predmeta: "), 0, 0);
		gp_BasicSubjectInfo.add(tf_SubjectLongName, 1, 0);
		
		//second row
		gp_BasicSubjectInfo.add(new Label("Kratak naziv predmeta: "), 0, 1);
		gp_BasicSubjectInfo.add(tf_SubjectShortName, 1, 1);
		
		//third row
		gp_BasicSubjectInfo.add(new Label("Opis predmeta: "), 0, 2);
		gp_BasicSubjectInfo.add(ta_SubjectDescription, 1, 2);
		
		//fourth row
		gp_BasicSubjectInfo.add(new Label("Maksimum ESPB: "), 0, 3);
		gp_BasicSubjectInfo.add(cmb_MaxEspb, 1, 3);
		
		//fifth row
		gp_BasicSubjectInfo.add(new Label("Redni broj semestra: "), 0, 4);
		gp_BasicSubjectInfo.add(cmb_SemesterId, 1, 4);
		
		
		//GridPane gp_WebLinks = Creators.makeGridPane();
		//first row
		gp_BasicSubjectInfo.add(new Label("Izbor linkova: "), 0, 5);
		gp_BasicSubjectInfo.add(cmb_WebLinks, 1, 5);
		
		//second row
		gp_BasicSubjectInfo.add(new Label("Izmena linka: "), 0, 6);
		gp_BasicSubjectInfo.add(tf_WebLink, 1, 6);
		
		//Node n_leftSide = Creators.layoutAboveBelow(gp_BasicSubjectInfo);
		Node n_leftSide = gp_BasicSubjectInfo;
		
		GridPane gp_LSchedule = Creators.makeGridPane();
		//first row
		gp_LSchedule.add(new Label("Dan nedelje: "), 0, 0);
		gp_LSchedule.add(cmb_LScheduleDayOfWeek, 1, 0);
		
		//second row
		gp_LSchedule.add(new Label("Vreme početka: "), 0, 1);
		gp_LSchedule.add(tf_LScheduleStartTime, 1, 1);
		
		//third row
		gp_LSchedule.add(new Label("Broj časova: "), 0, 2);
		gp_LSchedule.add(cmb_LScheduleLessonCount, 1, 2);
		Node n_LSchedule = Creators.layoutAboveBelow(new Label("Raspored predavanja"), gp_LSchedule);
		
		n_LSchedule.setStyle("-fx-background-color: lightgray; -fx-vgap: 1; -fx-hgap: 1; -fx-padding: 1;");
		
		
		GridPane gp_ESchedule = Creators.makeGridPane();
		//first row
		gp_ESchedule.add(new Label("Dan nedelje: "), 0, 0);
		gp_ESchedule.add(cmb_EScheduleDayOfWeek, 1, 0);
		
		//second row
		gp_ESchedule.add(new Label("Vreme početka: "), 0, 1);
		gp_ESchedule.add(tf_EScheduleStartTime, 1, 1);
		
		//third row
		gp_ESchedule.add(new Label("Broj časova: "), 0, 2);
		gp_ESchedule.add(cmb_EScheduleLessonCount, 1, 2);
		
		Node n_ESchedule = Creators.layoutAboveBelow(new Label("Raspored vežbi"), gp_ESchedule);
		
		n_ESchedule.setStyle("-fx-background-color: lightgray; -fx-vgap: 1; -fx-hgap: 1; -fx-padding: 1;");
		
		Node n_rightSide = Creators.layoutAboveBelow(n_LSchedule, n_ESchedule);
		
		Node content = Creators.layoutSideBySide(n_leftSide, n_rightSide);
		
		HBox hb_Footer = Creators.createHBox();
		hb_Footer.getChildren().addAll(btn_SaveChanges, btn_CancelChanges, new Separator(Orientation.VERTICAL), btn_DeleteSubject);
		
		vb_Complete.getChildren().addAll(content, hb_Footer);
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Logic for scene elements ?? should be combined
	//
	private final void implementLogic() {
		
		btn_SaveChanges.setOnAction(event1 -> {
			saveSubject();
			fileOperators.FileUtils.saveFiles(subjectRepository);
			
			changeScene(new TrackerScene(primaryStage, subjectRepository, imageRepository, executor));
		});
		
		btn_CancelChanges.setOnAction(event1 -> {
			changeScene(new TrackerScene(primaryStage, subjectRepository, imageRepository, executor));
		});
		
		btn_DeleteSubject.setOnAction(event1 -> {
			subjectRepository.deleteSubject(displayedSubject.getUuid());
			changeScene(new TrackerScene(primaryStage, subjectRepository, imageRepository, executor));
		});
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Creation of Main Menu
	//
	private MenuBar createMenuBar() {
		// * * * * * * * * * * *
		// Layout
		//
		MenuItem menuItem_Return = new MenuItem("Prethodni ekran");
		menuItem_Return.setGraphic(new ImageView(imageRepository.getImage("MANAGEMENT")));
		
		Menu menu_Navigation = new Menu("Navigacija");
		menu_Navigation.setGraphic(new ImageView(imageRepository.getImage("SETTINGS")));
		
		menu_Navigation.getItems().addAll(menuItem_Return);
		
		
		// * * * * * * * * * * *
		// Logic
		//
		menuItem_Return.setOnAction(event -> {
			changeScene(new TrackerScene(primaryStage, subjectRepository, imageRepository, executor));
		});
		
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menu_Navigation);
		
		return menuBar;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private methods
	//
	private final void displaySubjectInfo() {
		String subjectLongName = displayedSubject.getSubjectLongName();
		if (subjectLongName != null && !subjectLongName.isEmpty()) {
			tf_SubjectLongName.setText(subjectLongName);
		} else {
			tf_SubjectLongName.setText(Configs.EMPTY_STRING);
		}
		
		String subjectShortName = displayedSubject.getSubjectLongName();
		if (subjectShortName != null && !subjectShortName.isEmpty()) {
			tf_SubjectShortName.setText(subjectShortName);
		} else {
			tf_SubjectLongName.setText(Configs.EMPTY_STRING);
		}
		
		String subjectDescription = displayedSubject.getSubjectDescription();
		if (subjectDescription != null && !subjectDescription.isEmpty()) {
			ta_SubjectDescription.setText(subjectDescription);
		} else {
			ta_SubjectDescription.setText(Configs.EMPTY_STRING);
		}
		
		for (String item : cmb_MaxEspb.getItems()) {
			if (Integer.valueOf(item) == displayedSubject.getMaxEspb()) {
				cmb_MaxEspb.getSelectionModel().select(item);
			}
		}
		
		for (String item : cmb_SemesterId.getItems()) {
			if (Integer.valueOf(item) == displayedSubject.getSemesterId()) {
				cmb_SemesterId.getSelectionModel().select(item);
			}
		}
		
		Collection<WebLink> webLinks = displayedSubject.getWebLinks();
		if (webLinks != null && !webLinks.isEmpty()) {
			if (!cmb_WebLinks.getItems().isEmpty()) {
				cmb_WebLinks.getItems().clear();
			}
			cmb_WebLinks.getItems().addAll(webLinks);
			cmb_WebLinks.getSelectionModel().select(0);
			
			tf_WebLink.setText(cmb_WebLinks.getSelectionModel().getSelectedItem().toString());
		}
		
		//person info
		//private Person professor;
		//private Person assistent;
		
		//schedule info
		Schedule schedule = displayedSubject.getLectureSchedule();
		if (schedule != null) {
			DayOfWeek dayOfWeek = schedule.getDayOfWeek();
			if (dayOfWeek != DayOfWeek.UNDEFINED) {
				cmb_LScheduleDayOfWeek.getSelectionModel().select(dayOfWeek);
			}
			tf_LScheduleStartTime.setText(schedule.getStartTime().toString());
			cmb_LScheduleLessonCount.getSelectionModel().select(schedule.getLessonCount());
		}
		
		schedule = displayedSubject.getExerciseSchedule();
		if (schedule != null) {
			DayOfWeek dayOfWeek = schedule.getDayOfWeek();
			if (dayOfWeek != DayOfWeek.UNDEFINED) {
				cmb_EScheduleDayOfWeek.getSelectionModel().select(dayOfWeek);
			}
			cmb_EScheduleDayOfWeek.getSelectionModel().select(schedule.getDayOfWeek());
			tf_EScheduleStartTime.setText(schedule.getStartTime().toString());
			cmb_EScheduleLessonCount.getSelectionModel().select(schedule.getLessonCount());
		}
	}
	
	
	private final void saveSubject() {
		String test = tf_SubjectLongName.getText();
		if (test != null && test.isEmpty()) {
			displayedSubject.setSubjectLongName(test);
		}
		
		test = tf_SubjectShortName.getText();
		if (test != null && test.isEmpty()) {
			displayedSubject.setSubjectShortName(test);
		}
		
		test = ta_SubjectDescription.getText();
		if (test != null && test.isEmpty()) {
			displayedSubject.setSubjectDescription(test);
		}
		
		displayedSubject.setMaxEspb(Integer.valueOf(cmb_MaxEspb.getSelectionModel().getSelectedItem()));
		displayedSubject.setSemesterId(Integer.valueOf(cmb_SemesterId.getSelectionModel().getSelectedItem()));
		
		List<WebLink> webLinks = cmb_WebLinks.getItems();
		if (webLinks != null && webLinks.isEmpty()) {
			//displayedSubject.setWebLinks(webLinks);	//TODO
		}
		
		// has to be binded to cmb >> tf_WebLink = Creators.createTextField(true);
		
		//person info
		//private Person professor;
		//private Person assistent;
		
		//lectures
		displayedSubject.getLectureSchedule().setDayOfWeek(cmb_LScheduleDayOfWeek.getSelectionModel().getSelectedItem());
		
		test = tf_LScheduleStartTime.getText();
		if (test != null && test.isEmpty()) {
			displayedSubject.getLectureSchedule().setStartTime(Utilities.convertToInt(test));
		}
		
		displayedSubject.getLectureSchedule().setLessonCount(Integer.valueOf(cmb_LScheduleLessonCount.getSelectionModel().getSelectedItem()));
		
		//exercises
		displayedSubject.getExerciseSchedule().setDayOfWeek(cmb_EScheduleDayOfWeek.getSelectionModel().getSelectedItem());
		
		test = tf_EScheduleStartTime.getText();
		if (test != null && test.isEmpty()) {
			displayedSubject.getExerciseSchedule().setStartTime(Utilities.convertToInt(test));
		}
		displayedSubject.getExerciseSchedule().setLessonCount(Integer.valueOf(cmb_EScheduleLessonCount.getSelectionModel().getSelectedItem()));
		
		subjectRepository.editSubject(displayedSubject);
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
