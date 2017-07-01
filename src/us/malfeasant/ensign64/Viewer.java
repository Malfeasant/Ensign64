package us.malfeasant.ensign64;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Queue;

import javax.swing.JPanel;

public class Viewer extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final Queue<DisplayUnit> displayQueue;
	
	public Viewer(Queue<DisplayUnit> displayQueue) {
		this.displayQueue = displayQueue;
	}
	long repaints;
	long time = System.currentTimeMillis();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	// to honor the Opaque property
		
		++repaints;
		long t = System.currentTimeMillis();
		long elapsed = t - time;
		//time = t;
		Graphics2D g2 = (Graphics2D) g;
		//g2.drawString(Long.toString(elapsed), 0, 25);
		g2.drawString(Double.toString(repaints * 1000.0 / elapsed) + " fps", 0, 25);
		
		repaint();	// ensure continuous updates
		// Bah- doesn't work as expected- it really is continuous, 30k fps...
	}
}
