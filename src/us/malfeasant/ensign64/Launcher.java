package us.malfeasant.ensign64;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import us.malfeasant.ensign64.video.VIC;

public class Launcher {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> launch(args));
	}
	
	private static void launch(String[] args) {
		JFrame frame = new JFrame("Ensign 64");
		VIC vic = new VIC(VIC.Revision.NTSCr8);
		Viewer viewer = new Viewer(vic.getRaster());
		frame.add(viewer);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
