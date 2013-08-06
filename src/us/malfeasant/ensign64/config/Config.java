package us.malfeasant.ensign64.config;

import java.io.File;
import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Config implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Oscillator osc;
	private Powerline pow;
	private VicRev vicRev;
	
	public Config() {
		this("", Oscillator.NTSC, Powerline.NA, VicRev.NTSCr8);
	}
	private Config(String n, Oscillator o, Powerline p, VicRev vr) {
		name = n;
		osc = o;
		pow = p;
		vicRev = vr;
	}
	
	public static Config readFrom(File f) {
		// TODO
		return null;
	}
	
	public void showEditor(Stage owner, Runnable onComplete) {
		Stage stage = new Stage();
		stage.setTitle(name.equals("") ? "New machine" : name + " - Settings");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(owner);
		stage.initStyle(StageStyle.UTILITY);
		
		ChoiceBox<Oscillator> oscChoice = new ChoiceBox<>();
		oscChoice.getItems().addAll(Oscillator.values());
		ChoiceBox<Powerline> powChoice = new ChoiceBox<>();
		powChoice.getItems().addAll(Powerline.values());
		ChoiceBox<VicRev> vicChoice = new ChoiceBox<>();
		vicChoice.getItems().addAll(VicRev.values());
		
		Button ok = new Button("Accept");
		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		Button cancel = new Button("Cancel");
		
		stage.show();
	}
}
