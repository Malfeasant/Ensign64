package us.malfeasant.ensign64;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Launcher {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> launch(args));
	}
	
	private static void launch(String[] args) {
		JFrame frame = new JFrame("Ensign 64");
		BufferedImage image = new BufferedImage(512, 262, BufferedImage.TYPE_BYTE_BINARY, new ColorWrangler().makeModel());
		Viewer viewer = new Viewer(image);
		frame.add(viewer);
		
		WritableRaster raster = image.getRaster();
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				raster.setSample(x, y, 0, (x / 8 + y / 8) & 0xf);
			}
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
