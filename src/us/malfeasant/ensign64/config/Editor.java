package us.malfeasant.ensign64.config;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import us.malfeasant.fxdialog.Dialog;
import us.malfeasant.fxdialog.Dialog.MessageType;
import us.malfeasant.fxdialog.Dialog.OptionType;
import us.malfeasant.fxdialog.Dialog.Response;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Editor implements Externalizable {
	public static class ConfigCell extends ListCell<Editor> {
		@Override
		protected void updateItem(Editor item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null) textProperty().unbind();
			else textProperty().bind(item.name);
		}
	}
	private final StringProperty name;
	private Oscillator osc;
	private Powerline pow;
	private VicRev vicRev;
	
	public Editor() {
		this("", Oscillator.NTSC, Powerline.NA, VicRev.NTSCr8);
	}
	private Editor(String n, Oscillator o, Powerline p, VicRev vr) {
		name = new SimpleStringProperty(this, "Machine Name", n);
		osc = o;
		pow = p;
		vicRev = vr;
	}
	
/*	public static Editor readFrom(File f) {
		// TODO
		return null;
	}*/
	
	public boolean edit(Node owner) {
		final BooleanProperty valid = new SimpleBooleanProperty(false);
		// after all that work on the dialog class, i'm going to roll a new one...
		final Stage stage = new Stage(StageStyle.UTILITY);
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
		
		final TextField nameField = new TextField(name.get());
		final ChoiceBox<Oscillator> oscChoice = new ChoiceBox<>();
		oscChoice.getItems().addAll(Oscillator.values());
		oscChoice.getSelectionModel().select(osc);
		final ChoiceBox<Powerline> powChoice = new ChoiceBox<>();
		powChoice.getItems().addAll(Powerline.values());
		powChoice.getSelectionModel().select(pow);
		final ChoiceBox<VicRev> vicChoice = new ChoiceBox<>();
		vicChoice.getItems().addAll(VicRev.values());
		vicChoice.getSelectionModel().select(vicRev);
		grid.addColumn(1, nameField, oscChoice, powChoice, vicChoice);
		
		grid.setPadding(new Insets(10));
		grid.setHgap(10);
		grid.setVgap(10);
		root.setCenter(grid);
		
		HBox hb = new HBox(5);
		hb.setPadding(new Insets(0, 0, 10, 0));
		Button b = new Button("Ok");
		b.setDefaultButton(true);
		b.setMinSize(75, 30);
		b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if (checkAndSet(nameField, oscChoice.getValue(),
								powChoice.getValue(), vicChoice.getValue())) {
							valid.set(true);
							stage.close();
						}
					}
				});
		b.disableProperty().bind(nameField.textProperty().isEqualTo(""));
		hb.getChildren().add(b);
		b = new Button("Cancel");
		b.setCancelButton(true);
		b.setMinSize(75, 30);
		b.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						stage.close();	// discard changes
					}
				});
		hb.getChildren().add(b);
		hb.setAlignment(Pos.CENTER);
		root.setBottom(hb);
		
		stage.setScene(new Scene(root));
		stage.showAndWait();
		
		return valid.get();
	}
	private boolean checkAndSet(TextField tf, Oscillator o, Powerline p, VicRev v) {
		if (tf.getText().equals("")) {
			// this shouldn't happen since Ok is disabled when the text field is empty, but JIC...
			Dialog.showMessageDialog(tf, "Please enter a name.", "Problem", MessageType.ERROR);
			return false;
		}
		if ((o == Oscillator.NTSC && (p == Powerline.EU || v == VicRev.PAL)) ||
				(o == Oscillator.PAL && (p == Powerline.NA || v != VicRev.PAL))) {
			if (Dialog.showConfirmDialog(tf,
					"Your timing options are goofy- are you sure this is what you want?",
					"Gremlins may attack", OptionType.YES_NO) != Response.YES) return false;
		}
		
		name.set(tf.getText());
		osc = o;
		pow = p;
		vicRev = v;
		return true;
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name.get());
		out.writeObject(osc);
		out.writeObject(pow);
		out.writeObject(vicRev);
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name.set(in.readUTF());
		osc = (Oscillator) in.readObject();
		pow = (Powerline) in.readObject();
		vicRev = (VicRev) in.readObject();
	}
}
