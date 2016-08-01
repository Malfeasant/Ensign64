package us.malfeasant.ensign64;

import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Viewer {
	
	public static void create(Queue<DisplayUnit> queue) {
		SwingUtilities.invokeLater(() -> new Viewer(queue));
	}
	
	private final Queue<DisplayUnit> displayQueue;
	
	public Viewer(Queue<DisplayUnit> displayQueue) {
		this.displayQueue = displayQueue;
		JFrame frame = new JFrame("Ensign 64");
	}
}
