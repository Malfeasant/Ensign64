package us.malfeasant.ensign64;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * GUI bits- manages display and controls of a complete machine- opens new window.
 *
 * @author Malfeasant
 */
public class Console {
	
	public Console() {
		Stage stage = new Stage();
		stage.setOnCloseRequest((e) -> checkAndQuit(e));
		BorderPane pane = new BorderPane();	// display (canvas?) will go in center
		
		Menu fileMenu = new Menu("File");
		MenuItem newItem = new MenuItem("New...");	// Bring back launcher window to create entirely new machine
		newItem.setOnAction((e) -> {
			Ensign64.getStage().show();
		});
		MenuItem readItem = new MenuItem("Read State...");
		readItem.setOnAction((e) -> {
			if (alertSave()) {
				// TODO dialog to read an image
			}
		});
		MenuItem saveItem = new MenuItem("Save State...");
		saveItem.setOnAction((e) -> {
			// TODO dialog to save an image
		});
		MenuItem resetItem = new MenuItem("Reset");
		resetItem.setOnAction((e) -> {
			if (alertSave()) {
				// TODO reset the machine
			}
		});
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setOnAction((e) -> {
			if (alertSave()) {
				stage.close();
				reallyQuit();
			}
		});
		fileMenu.getItems().addAll(newItem, readItem, saveItem, new SeparatorMenuItem(), resetItem, new SeparatorMenuItem(), exitItem);
		
		Menu speedMenu = new Menu("Speed");
		ToggleGroup speedGroup = new ToggleGroup();
		for (Worker.Mode m : Worker.Mode.values()) {
			RadioMenuItem item = new RadioMenuItem(m.name());
			item.setToggleGroup(speedGroup);
			speedMenu.getItems().add(item);
		}
		MenuBar bar = new MenuBar(fileMenu, speedMenu);
		pane.setTop(bar);
		
		stage.setScene(new Scene(pane));
		stage.show();
	}
	
	// returns true if ok to quit, false if not.  
	private boolean alertSave() {
		Alert a = new Alert(AlertType.CONFIRMATION, "Save state?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		
		Optional<ButtonType> result = a.showAndWait();
		// if tree seems kludgy... but Optional.ifPresent and others are not conducive to altering anything outside
		// their callbacks, which is needed to avoid duplicating code in the case of window close or exit menuitem.
		// can't even return a boolean from a common function...
		if (result.isPresent()) {
			// YES or NO:
			if (result.get() == ButtonType.YES) {
				System.out.println("Saving state...");	// TODO save state
				return true;
			}
			if (result.get() == ButtonType.NO) {
				System.out.println("Not saving.");
				return true;
			}
		}
		// some other type (CANCEL should be only possibility) or null (dialog closed without clicking button)
		System.out.println("Doing nothing.");
		return false;
	}
	private void checkAndQuit(WindowEvent e) {
		if (alertSave()) {
			reallyQuit();
		} else {
			e.consume();	// swallow the event, stop window from closing
		}
	}
	private void reallyQuit() {
		// TODO kill worker
	}
}
