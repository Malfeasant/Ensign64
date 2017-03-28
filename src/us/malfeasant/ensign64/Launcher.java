package us.malfeasant.ensign64;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Launcher {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> choose());
	}
	
	private static void choose() {
		final String NTSC = "NTSC";
		final String PAL = "PAL";
		final String CUSTOM = "Custom";
		String[] choices = { NTSC, PAL, CUSTOM };
		String selection = (String) JOptionPane.showInputDialog(null, "Choose your machine.",
				"Choose", JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
		if (selection == null) {
			System.out.println("Null, exiting.");
			return;
		}
		switch (selection) {
		case NTSC:
			// TODO set clock, VIC to NTSC (later rev), power to 60Hz
			break;
		case PAL:
			// TODO set clock, VIC to PAL, power to 50Hz
			break;
		case CUSTOM:
			// TODO - a new dialog with more options- possible to mismatch chip vs clock, powerline freq applied vs expected...
			break;
		}
		System.out.println(selection);
	}
	
}
