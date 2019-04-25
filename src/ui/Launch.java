package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launch extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello world!");
		Button button = new Button();
		button.setText("Say 'Hello world'");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello world.");
			}
		});
		
		StackPane pane = new StackPane();
		pane.getChildren().add(button);
		primaryStage.setScene(new Scene(pane, 300, 250));
		primaryStage.show();
	}
}
