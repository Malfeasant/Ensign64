package us.malfeasant.ensign64;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ensign64 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button ntscBut = new Button("NTSC");
		ntscBut.setOnAction((e) -> {});	// TODO
		Button palBut = new Button("PAL");
		palBut.setOnAction((e) -> {});	// TODO
		VBox quickBox = new VBox(ntscBut, palBut);
		quickBox.setAlignment(Pos.CENTER);
		Tab quickTab = new Tab("Quick", quickBox);
		quickTab.closableProperty().set(false);
		
		// TODO some buttons to pick old/new vic, select ROMs, set clock and power options...
		Tab custTab  = new Tab("Custom");
		custTab.closableProperty().set(false);
		
		Worker worker = new Worker();
		VBox testBox = new VBox();
		for (Worker.Mode mode : Worker.Mode.values()) {
			Button b = new Button(mode.name());
			b.setOnAction((e) -> {
				worker.setMode(mode);
			});
			testBox.getChildren().add(b);
		}
		Button killBut = new Button("Kill");
		testBox.getChildren().add(killBut);
		killBut.setOnAction((e) -> worker.shutdown());
		Tab testTab = new Tab("Test", testBox);
		
		TabPane pane = new TabPane();
		pane.getTabs().addAll(quickTab, custTab, testTab);
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
	}
}
