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
		BufferedImage image = new BufferedImage(512, 262, BufferedImage.TYPE_BYTE_BINARY);
		Viewer viewer = new Viewer(image);
//		JPanel panel = new JPanel();
//		panel.add(viewer);
//		frame.add(panel);
		frame.add(viewer);
		
		WritableRaster raster = image.getRaster();
		System.out.println("Raster size: " + raster.getWidth() + "x" + raster.getHeight());
		for (int y = 0; y < raster.getHeight(); y++) {
			raster.setSample(5, y, 0, 1);
			raster.setSample(raster.getWidth() - 6, y, 0, 1);
		}
		for (int x = 0; x < raster.getWidth(); x++) {
			raster.setSample(x, 5, 0, 1);
			raster.setSample(x, raster.getHeight() - 6, 0, 1);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
