package us.malfeasant.ensign64;

import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import us.malfeasant.ensign64.timing.Crystal;
import us.malfeasant.ensign64.timing.Powerline;

public class Launcher {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> launch());
	}
	
	private static void launch() {
		use(choose());
	}
	private static MachineDescriptor choose() {
		final String NTSC = "NTSC";
		final String PAL = "PAL";
		final String CUSTOM = "Custom";
		String[] choices = { NTSC, PAL, CUSTOM };
		String selection = (String) JOptionPane.showInputDialog(null, "Choose your machine.",
				"Choose", JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
		if (selection == null) {
			// This happens if dialog is cancelled or closed...
			// TODO something more meaningful
			System.out.println("Null, exiting.");
			return null;
		}
		MachineDescriptor md = null;
		switch (selection) {
		case NTSC:
			// TODO set clock, VIC to NTSC (later rev), power to 60Hz
			md = new MachineDescriptor(Crystal.NTSC, Powerline.NA);
			break;
		case PAL:
			// TODO set clock, VIC to PAL, power to 50Hz
			md = new MachineDescriptor(Crystal.PAL, Powerline.EU);
			break;
		case CUSTOM:
			// TODO - a new dialog with more options- possible to mismatch chip vs clock,
			// powerline freq applied vs expected... this will evolve with MachineDescriptor.
			break;
		}
		//System.out.println(md);
		return md;
	}
	
	private static void use(MachineDescriptor md) {
		JFrame frame = new JFrame("Ensign64");
		frame.setContentPane(new Viewer(new LinkedBlockingQueue<DisplayUnit>())); // just a test
		frame.pack();
		frame.setVisible(true);
	}
}
