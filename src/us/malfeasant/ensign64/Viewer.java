package us.malfeasant.ensign64;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Viewer extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private final BufferedImage image;
	private double scaleX;
	private double scaleY;
	
	public Viewer(BufferedImage pixels) {
		image = pixels;
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				scaleX = getWidth() / (double) image.getWidth();
				scaleY = getHeight() / (double) image.getHeight();
				if (scaleX * 1.2 > scaleY) {
					scaleX = scaleY / 1.2;
				} else {
					scaleY = scaleX * 1.2;
				}
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.drawRenderedImage(image, AffineTransform.getScaleInstance(scaleX, scaleY));
	}
}
