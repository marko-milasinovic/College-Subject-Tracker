package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
	
	protected static final ComboBox createComboBox(Collection collection) {
		ComboBox comboBox = createComboBox();
		
		if (collection != null && !collection.isEmpty()) {
			comboBox.getItems().addAll(collection);
			comboBox.getSelectionModel().select(0);
		}
		
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
		textArea.setMinWidth(150);
		textArea.setPrefWidth(300);
		textArea.setEditable(editable);
		textArea.setFocusTraversable(editable);
		
		return textArea;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// ScrollPane
	//
	protected static final ScrollPane createScrollPane(Node node) {
		ScrollPane scrollPane = new ScrollPane(node);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		scrollPane.setMinWidth(100);
		scrollPane.setMinHeight(100);
		scrollPane.setPadding(new Insets(10, 10, 10, 10));
		
		return scrollPane;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// VBox
	//
	protected static final VBox createVBox() {
		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		
		return vBox;
	}
	
	protected static final VBox createVBox(int separation) {
		VBox vBox = new VBox(separation);
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
		Button button = new Button(name);
		button.setMinWidth(80);
		//button.getParent().layout();	//needs to be done as final part of button creation
		//button.getParent().applyCss();
		button.setWrapText(false);
		UtilsFX.styleElement(button);
		button.setMaxWidth(Control.USE_PREF_SIZE);
		
		return button;
	}
	
	protected static final Button createButton(String name, Image image) {
		Button button = createButton(name);
		
		if (image != null) {
			button.setGraphic(new ImageView(image));
		}
		return button;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// GridPane
	//
	protected static final GridPane makeGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(20);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setAlignment(Pos.CENTER);
		
		return gridPane;
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Complete VBox
	//
	protected static final void makeVBoxComplete(VBox complete, HBox top) {    //soon to be deprecated
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
	// Complete VBox
	//
	protected static final Node layoutSideBySide(String label, Node leftNode, Node rightNode) {
		Node node = layoutSideBySide(leftNode, rightNode);
		return layoutAboveBelow(new Label(label), node);
	}
	
	protected static final Node layoutSideBySide(Node leftNode, Node rightNode) {
		HBox hBox = createHBox();
		hBox.getChildren().addAll(leftNode, rightNode);
		
		return hBox;
	}
	
	protected static final Node layoutAboveBelow(Node aboveNode, Node belowNode) {
		VBox vBox = createVBox();
		vBox.getChildren().addAll(aboveNode, belowNode);
		
		return vBox;
	}
	
	protected static final Node layoutAboveBelow(String label, Node leftNode, Node rightNode) {
		Node node = layoutAboveBelow(leftNode, rightNode);
		return layoutAboveBelow(new Label(label), node);
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Private constructor so that the static class cant be created
	//
	private Creators() {}
}
