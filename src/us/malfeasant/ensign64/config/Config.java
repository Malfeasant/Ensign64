package us.malfeasant.ensign64.config;

import java.io.File;

import us.malfeasant.fxdialog.Dialog;
import us.malfeasant.fxdialog.Dialog.MessageType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class Config {
	public static class ConfigCell extends ListCell<Config> {
		@Override
		protected void updateItem(Config item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null) textProperty().unbind();
			else textProperty().bind(item.name);
		}
	}
	private final StringProperty name;
	private Oscillator osc;
	private Powerline pow;
	private VicRev vicRev;
	
	public Config() {
		this("", Oscillator.NTSC, Powerline.NA, VicRev.NTSCr8);
	}
	private Config(String n, Oscillator o, Powerline p, VicRev vr) {
		name = new SimpleStringProperty(n);
		osc = o;
		pow = p;
		vicRev = vr;
	}
	
	public static Config readFrom(File f) {
		// TODO
		return null;
	}
	
	public boolean edit(Node owner) {
		String newName = Dialog.showInputDialog(owner, "Give your machine a name.", name.get());
		if (newName == null) return false;	// means dialog was cancelled
		if (newName.equals("")) {
			Dialog.showMessageDialog(owner, "Try again- your machine must have a name.",
					"Abort", MessageType.ERROR);
			return false;
		}
		// TODO more options, sanity checks
		// if any dialog is cancelled, return false
		
		name.set(newName);
		return true;
	}
}
