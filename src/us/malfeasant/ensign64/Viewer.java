package us.malfeasant.ensign64;

import java.util.Queue;

import javax.swing.JPanel;

public class Viewer extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final Queue<DisplayUnit> displayQueue;
	
	public Viewer(Queue<DisplayUnit> displayQueue) {
		this.displayQueue = displayQueue;
	}
}
