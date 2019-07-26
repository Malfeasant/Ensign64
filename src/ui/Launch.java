package ui;

import config.VicFlavor;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Launch extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO: Better way of specifying defaults- for now, hard code:
		VicFlavor flavor = VicFlavor.NEWNTSC;
		
		primaryStage.setTitle("Ensign64");
		
		BorderPane pane = new BorderPane();
		
		Menu fileMenu = new Menu("File");
		MenuBar menuBar = new MenuBar(fileMenu);
		
		pane.setTop(menuBar);
		
		WritableImage screenImage = new WritableImage(flavor.cycles * 8, flavor.lines);
		PixelWriter pixWriter = screenImage.getPixelWriter();
		ImageView view = new ImageView(screenImage);
		view.setViewport(new Rectangle2D(0, 0, flavor.cycles * 8, flavor.lines));	// TODO: crop image
		pane.setCenter(view);
		
		primaryStage.setScene(new Scene(pane, 600, 450));
		primaryStage.show();
	}
}
