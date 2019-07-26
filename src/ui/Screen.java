package ui;

import javafx.scene.image.PixelWriter;

class Screen {
	private final PixelWriter pixels;
	
	Screen(PixelWriter p) {
		pixels = p;
	}
/*	WritableImage screenImage = new WritableImage(flavor.cycles * 8, flavor.lines);
	PixelWriter pixWriter = screenImage.getPixelWriter();
	ImageView view = new ImageView(screenImage);
	view.setViewport(new Rectangle2D(0, 0, flavor.cycles * 8, flavor.lines));	// TODO: crop image
	pane.setCenter(view);
*/	
}
