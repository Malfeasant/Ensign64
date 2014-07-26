package us.malfeasant.ensign64;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JComponent;

public class Viewer extends JComponent {
	private static final long serialVersionUID = 1L;
	
	private final WritableRaster raster;
	private final ColorWrangler palette;
	private double scaleX;
	private double scaleY;
	private BufferedImage image;
	
	public Viewer(WritableRaster pixels) {
		raster = pixels;
		palette = new ColorWrangler();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				scaleX = getWidth() / (double) raster.getWidth();
				scaleY = getHeight() / (double) raster.getHeight();
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
		if (image == null) {
			image = new BufferedImage(palette.makeModel(), raster, false, null);
		}
		g2.drawRenderedImage(image, AffineTransform.getScaleInstance(scaleX, scaleY));
	}
}
