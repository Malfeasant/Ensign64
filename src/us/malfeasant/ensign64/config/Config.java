package us.malfeasant.ensign64.config;

import java.io.File;

import us.malfeasant.fxdialog.Dialog;
import us.malfeasant.fxdialog.Dialog.MessageType;

import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class Config {
	public static class ConfigCell extends ListCell<Config> {
		public ConfigCell() {
			setEditable(true);
		}
		@Override
		protected void updateItem(Config item, boolean empty) {
			super.updateItem(item, empty);
			setText(item == null ? "" : item.name);
		}
		@Override
		public void commitEdit(Config arg0) {
			// TODO Auto-generated method stub
//			super.commitEdit(arg0);
			System.out.println("Committed " + arg0);
		}
		@Override
		public void startEdit() {
			// TODO Auto-generated method stub
			super.startEdit();
			System.out.println("Starting edit on " + getItem());
//			getItem().edit(this);
		}
	}
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
	
	public boolean edit(Node owner) {
		String newName = Dialog.showInputDialog(owner, "Give your machine a name.", name);
		if (newName == null) return false;	// means dialog was cancelled
		if (newName.equals("")) {
			Dialog.showMessageDialog(owner, "Try again- your machine must have a name.",
					"Polly shouldn't be!", MessageType.ERROR);
			return false;
		}
		// TODO more options, sanity checks
		// if any dialog is cancelled, return false
		
		name = newName;
		return true;
	}
}
