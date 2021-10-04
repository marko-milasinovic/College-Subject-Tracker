package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import scenes.statics.UtilsFX;

import java.util.Collection;

//a static class, only available within scenes package
class Creators {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// ComboBox
	//
	protected static final ComboBox createComboBox() {
		ComboBox comboBox = new ComboBox();
		comboBox.setEditable(false);
		comboBox.setMaxWidth(200);
		comboBox.setPrefWidth(150);
		
		return comboBox;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// TextField
	//
	protected static final TextField createTextField(boolean editable) {
		TextField textField = new TextField();
		textField.setAlignment(Pos.BASELINE_LEFT);
		textField.setPrefWidth(150);
		textField.setEditable(editable);
		textField.setFocusTraversable(editable);
		
		return textField;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// TextField
	//
	protected static final TextArea createTextArea(boolean editable) {
		TextArea textArea = new TextArea();
		textArea.setWrapText(true);
		textArea.setPrefWidth(150);
		textArea.setEditable(editable);
		textArea.setFocusTraversable(editable);
		
		return textArea;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// VBox
	//
	protected static final VBox createVBox() {
		VBox vBox = new VBox(5);
		vBox.setAlignment(Pos.CENTER);
		
		return vBox;
	}
	
	protected static final VBox createVBox(Collection<Node> nodes) {
		VBox vBox = new VBox(5);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(nodes);
		return vBox;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// HBox
	//
	protected static final HBox createHBox() {
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		return hBox;
	}
	
	protected static final HBox createHBox(Collection<Node> nodes) {
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(nodes);
		
		return hBox;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Label
	//
	protected static final Label createLabel() {
		Label lbl = new Label();
		lbl.setAlignment(Pos.BASELINE_LEFT);
		lbl.setMaxWidth(500);
		lbl.setMinWidth(10);
		
		return lbl;
	}
	
	protected static final Label createLabel(String text) {
		Label lbl = new Label(text);
		lbl.setAlignment(Pos.BASELINE_LEFT);
		lbl.setMaxWidth(500);
		lbl.setMinWidth(10);
		
		return lbl;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Button
	//
	protected static final Button createButton(String name) {
		Button btn = new Button(name);
		//btn.setGraphic(UtilsFX.getImageView("icons/search2.png"));
		UtilsFX.styleElement(btn);
		
		return btn;
	}
	
	protected static final Button createButton(String name, String imagePath) {
		Button btn = new Button(name);
		//btn.setGraphic(UtilsFX.getImageView(imagePath));
		UtilsFX.styleElement(btn);
		
		return btn;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Complete VBox
	//
	protected static final void makeVBoxComplete(VBox complete, HBox top) {
		complete.setAlignment(Pos.CENTER);
		complete.setPadding(new Insets(20, 20, 20, 5));
		complete.getChildren().addAll(top);
		
		//UtilsFX.changeBackground("", complete);
	}
	
	protected static final void makeVBoxComplete(VBox complete, HBox top, HBox middle) {
		makeVBoxComplete(complete, top);
		complete.getChildren().addAll(middle);
	}
	
	protected static final void makeVBoxComplete(VBox complete, HBox top, HBox middle, HBox bottom) {
		makeVBoxComplete(complete, top, middle);
		complete.getChildren().addAll(bottom);
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private Creators() {}
}
