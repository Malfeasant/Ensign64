package us.malfeasant.ensign64;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
		
		stage.setScene(new Scene(pane));
		stage.show();
	}
	
	private void checkAndQuit(WindowEvent e) {
		Alert a = new Alert(AlertType.CONFIRMATION, "Save state before quitting?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		a.showAndWait().ifPresentOrElse((bt) -> {
			// if YES or NO:
			if (bt == ButtonType.YES) {
				System.out.println("Saving state...");	// TODO save state
			} else if (bt == ButtonType.NO) {
				// prepare to quit- TODO shut down worker
				System.out.println("Quitting without saving.");
			} else {
				// some other type (CANCEL should be only other type possible)
				System.out.println("Doing nothing.");
				e.consume();
			}
		}, () -> {
			// if empty (window closed) treat as cancel
			e.consume();
		});
	}
}
