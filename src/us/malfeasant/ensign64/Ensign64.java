package us.malfeasant.ensign64;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Ensign64 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		
		MenuItem loadItem = new MenuItem("Load...");	// read in some state- ram contents, config...
		loadItem.setOnAction(e -> {});	// TODO
		MenuItem saveItem = new MenuItem("Save...");	// save some state- ram contents, config...
		saveItem.setOnAction(e -> {});	// TODO
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setOnAction(e -> {
			if (okQuit()) primaryStage.close();
		});
		Menu fileMenu = new Menu("File", null, loadItem, saveItem, exitItem);
		
		MenuItem resetItem = new MenuItem("Reset");
		resetItem.setOnAction(e -> {});	// TODO
		
		ToggleGroup group = new ToggleGroup();
		RadioMenuItem stepItem = new RadioMenuItem("Step");
		RadioMenuItem slowItem = new RadioMenuItem("Slow");	// how slow?  1/10th speed?  1/1000th would still be 1000 clocks per sec...
		RadioMenuItem realItem = new RadioMenuItem("Real");	// as close to realtime as possible- depends on pal/ntsc
		RadioMenuItem fastItem = new RadioMenuItem("Fast");	// unlimited
		group.getToggles().addAll(stepItem, slowItem, realItem, fastItem);
		group.selectedToggleProperty().addListener((obs, then, now) -> {});	// TODO
		Platform.runLater(() -> group.selectToggle(realItem));	// TODO: make the default configurable?
		// using runLater() so the event gets delivered after start() has completed
		Menu speedMenu = new Menu("Speed", null, stepItem, slowItem, realItem, fastItem);
		
		Menu machineMenu = new Menu("Machine", null, resetItem, speedMenu);
		
		MenuBar mbar = new MenuBar(fileMenu, machineMenu);
		pane.setTop(mbar);
		
		primaryStage.setOnCloseRequest(e -> {
			if (!okQuit()) e.consume();	// okQuit returns false if dialog canceled, so continue running
		});
		
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
	}
	
	private boolean okQuit() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Save state?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		ButtonType clicked = alert.showAndWait().orElse(ButtonType.CANCEL);
		if (clicked == ButtonType.YES) {	
			// TODO: save dialog
			return true;
		}
		return (clicked == ButtonType.NO);	// if user clicked no, ok to quit- otherwise keep running
	}
}
