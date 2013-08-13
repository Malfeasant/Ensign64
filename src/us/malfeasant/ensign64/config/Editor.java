package us.malfeasant.ensign64.config;

import java.io.File;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Editor {
	public static class ConfigCell extends ListCell<Editor> {
		@Override
		protected void updateItem(Editor item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null) textProperty().unbind();
			else textProperty().bind(item.name);
		}
	}
	private final StringProperty name;
	private final ObjectProperty<Oscillator> osc;
	private final ObjectProperty<Powerline> pow;
	private final ObjectProperty<VicRev> vicRev;
	
	public Editor() {
		this("", Oscillator.NTSC, Powerline.NA, VicRev.NTSCr8);
	}
	private Editor(String n, Oscillator o, Powerline p, VicRev vr) {
		name = new SimpleStringProperty(this, "Machine Name", n);
		osc = new SimpleObjectProperty<Oscillator>(this, "Clock Period", o);
		pow = new SimpleObjectProperty<>(this, "Powerline Period", p);
		vicRev = new SimpleObjectProperty<>(this, "VIC Revision", vr);
	}
	
	public static Editor readFrom(File f) {
		// TODO
		return null;
	}
	
	public boolean edit(Node owner) {
		// after all that work on the dialog class, i'm going to roll a new one...
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(owner.getScene().getWindow());
		stage.setResizable(false);
		stage.setTitle(name.get() == null || name.get().equals("") ?
				"New Machine" : name.get() + " Settings");
		
		BorderPane root = new BorderPane();
		GridPane grid = new GridPane();
		
		grid.addColumn(0, new Label[] {
				new Label("Name"),
				new Label("Pixel clock"),
				new Label("Powerline"),
				new Label("VIC chip"),
		});
		root.setCenter(grid);
		
		ButtonBuilder<?> bb = ButtonBuilder.create().minWidth(30);
		HBox hb = new HBox(5);
		hb.getChildren().addAll(
				bb.text("Ok").defaultButton(true).onAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						
					}
				}).build(),
				bb.text("Cancel").cancelButton(true).onAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						
					}
				}).build()
		);
		root.setBottom(hb);
		
		stage.setScene(new Scene(root));
		stage.showAndWait();
		
//		editName(owner);
		// TODO more options, sanity checks
		// if any dialog is cancelled, return false
		return true;
	}
/*	public void editName(Node owner) {
		String newName = Dialog.showInputDialog(owner, "Give your machine a name.", name.get());
		if (newName == null) return;	// means dialog was cancelled
		if (newName.equals("")) {
			Dialog.showMessageDialog(owner, "Try again- your machine must have a name.",
					"Abort", MessageType.ERROR);
			return;
		}
		name.set(newName);
	}*/
}
