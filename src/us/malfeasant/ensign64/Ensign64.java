package us.malfeasant.ensign64;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import us.malfeasant.ensign64.config.Configuration;

public class Ensign64 extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button ntscBut = new Button("NTSC");
		ntscBut.setOnAction((e) -> create(Configuration.getNTSC()));
		Button palBut = new Button("PAL");
		palBut.setOnAction((e) -> create(Configuration.getPAL()));
		VBox quickBox = new VBox(ntscBut, palBut);
		quickBox.setAlignment(Pos.CENTER);
		Tab quickTab = new Tab("Quick", quickBox);
		quickTab.closableProperty().set(false);
		
		// TODO some buttons to pick old/new vic, select ROMs, set clock and power options...
		Tab custTab  = new Tab("Custom");
		custTab.closableProperty().set(false);
		
		TabPane pane = new TabPane();
		pane.getTabs().addAll(quickTab, custTab);
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
	}
	
	private void create(Configuration c) {
		
	}
}
