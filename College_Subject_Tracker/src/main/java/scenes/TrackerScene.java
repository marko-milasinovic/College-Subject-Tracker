package scenes;

import core.Utilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.WebLink;
import models.statics.DayOfWeek;
import models.Subject;
import repositories.image.IImageRepository;
import repositories.image.InMemoryImageRepository;
import repositories.subject.ISubjectRepository;
import scenes.statics.UtilsFX;
import viewers.SubjectViewer;

public final class TrackerScene extends BorderPane {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Variables
	//
	private ISubjectRepository subjectRepository;
	private IImageRepository imageRepository;
	private MenuBar mb_Main;
	private VBox vb_Complete;
	
	private Subject currentSubject;
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main Scene constructor
	//
	public TrackerScene(Stage thisWindow, ISubjectRepository subjectRepository, IImageRepository imageRepository)
	{
		thisWindow.setTitle("Subject tracker");
		this.subjectRepository = subjectRepository;
		this.imageRepository = imageRepository;
		
		this.currentSubject = subjectRepository.getSubjects().get(0);
		
		mb_Main = createMenuBar();
		this.setTop(mb_Main);
		
		vb_Complete = new VBox(20);
		initializeElements();
		
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
	private final void initializeElements()
	{
		// * * * * *
		//TOP-VBOX #1
		//
		Label lbl_CurrentTime = Creators.createLabel();
		lbl_CurrentTime.setText(Utilities.getCurrentDayOfWeek().toString() + "  " + Utilities.getCurrentTimeOfDay());
		
		Label lbl_ShortSchedule = Creators.createLabel();
		lbl_ShortSchedule.setText("lbl_ShortSchedule");
		
		TextField tf_SubjectName = Creators.createTextField(false);
		tf_SubjectName.setText(currentSubject.getSubjectLongName());
		
		Button btn_ShowDetailed = Creators.createButton("Prikaži detaljno");
		
		final HBox hb_Top_N1_Info = Creators.createHBox();
		hb_Top_N1_Info.getChildren().addAll(
				lbl_CurrentTime,
				new Label("Predmet: "),
				tf_SubjectName,
				lbl_ShortSchedule
		);
		
		final VBox vb_Top_N1 = Creators.createVBox();
		vb_Top_N1.getChildren().add(hb_Top_N1_Info);
		
		
		
		// * * * * *
		//TOP-VBOX #2
		//
		ImageView iv_launchDefaultBrowserWithLink = new ImageView();
		UtilsFX.createCustomThumbnail(iv_launchDefaultBrowserWithLink, imageRepository.getImage("BROWSER_CHROME"));
		
		ComboBox cmb_WebLinks = Creators.createComboBox();
		cmb_WebLinks.getItems().addAll(currentSubject.getWebLinks());
		cmb_WebLinks.getSelectionModel().select(0);
		
		final HBox hb_MoodleInfo = Creators.createHBox();
		hb_MoodleInfo.getChildren().addAll(
				iv_launchDefaultBrowserWithLink,
				cmb_WebLinks
		);
		
		final VBox vb_Top_N2 = Creators.createVBox();
		vb_Top_N2.getChildren().addAll(
				hb_MoodleInfo
		);
		
		
		// * * * * *
		//TOP-VBOX #3
		//
		TextArea ta_Description = new TextArea();
		ta_Description.setText(currentSubject.getSubjectDescription());
		
		final VBox vb_Top_N3 = Creators.createVBox();
		vb_Top_N3.getChildren().add(ta_Description);
		
		
		// * * * * *
		//TOP-HBOX #Complete
		//
		final HBox hb_Top_Complete = Creators.createHBox();
		hb_Top_Complete.getChildren().addAll(
				vb_Top_N1,
				new Separator(Orientation.VERTICAL),
				vb_Top_N2,
				vb_Top_N3
		);
		
		
		// * * * * *
		//MIDDLE-HBOX #Complete
		//
		TableView tv_Subjects = new TableView<Subject>();
		tv_Subjects.setPrefHeight(200);
		tv_Subjects.setMinHeight(100);
		
		final HBox hb_Middle_Complete = Creators.createHBox();
		
		hb_Middle_Complete.getChildren().addAll(
				tv_Subjects
		);
		
		
		// * * * * *
		//BOTTOM-HBOX #Complete
		//
		final HBox hb_Bottom_Complete = new HBox(100);
		hb_Bottom_Complete.setAlignment(Pos.CENTER);
		
		hb_Bottom_Complete.getChildren().addAll(
				btn_ShowDetailed
		);
		
		
		Creators.makeVBoxComplete(vb_Complete, hb_Top_Complete, hb_Middle_Complete, hb_Bottom_Complete);
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Logic for scene elements ?? should be combined
	//
	private final void implementLogic()
	{
		Subject currentSubject = SubjectViewer.getCurrentSubject(subjectRepository);
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Creation of menu bar
	//
	private MenuBar createMenuBar()
	{
		MenuBar mb_Main = new MenuBar();
		
		//
		// Passive items
		//
		MenuItem mi_AddPredmet = new MenuItem("Dodaj predmet");
		
		MenuItem mi_EditPredmet = new MenuItem("Izmeni postojeći predmet");

		Menu mu_Settings = new Menu("Podešavanja");

		mu_Settings.getItems().addAll(
				mi_AddPredmet,
				mi_EditPredmet
		);
		

		
		//
		// Active items
		//
		mi_AddPredmet.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				
			}});
		
		mi_EditPredmet.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				
			}});
		
		
		
		//
		// Add to mb_Main bar
		//
		mb_Main.getMenus().addAll(
				mu_Settings
		);
		
		for(DayOfWeek day : DayOfWeek.values())
		{
			if(day == DayOfWeek.UNDEFINED){ continue; }
			
			Menu dayToAdd = new Menu(day.getLocalisedName());
			dayToAdd.setOnAction(event -> {
			});
			mb_Main.getMenus().add(dayToAdd);
		}
		
		return mb_Main;
	}
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private methods
	//
	
	
	
}
