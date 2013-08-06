package us.malfeasant.ensign64;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Launcher extends Application {
	// TODO- instead of String, would be nice to store the machines themselves?
	private final ObservableList<String> machines = FXCollections.observableArrayList();
	private final ObjectProperty<String> selected = new SimpleObjectProperty<>(this, "Selected");
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Ensign64");
		BorderPane root = new BorderPane();
		
		Button newb = new Button("New");
		newb.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("New");	// TODO
				machines.add(String.valueOf(System.currentTimeMillis()));
			}
		});
		Button conf = new Button("Settings");
		conf.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println(selected.get() + " => Settings" );	// TODO
			}
		});
		Button run = new Button("Run");
		run.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println(selected.get() + " => Run");	// TODO
			}
		});
		// have to move this here so delete button's handler has access to it- ugly, but not as bad
		// as writing a list listener...  really, listview should handle it on its own, but
		// currently, when the selected item is removed, the listview shows nothing selected, while
		// the selectionmodel considers some element selected.
		final ListView<String> machineView = new ListView<>(machines);
		Button del = new Button("Delete");
		del.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println(selected.get() + " => Deleted");	// TODO
				machines.remove(selected.get());
				machineView.getSelectionModel().clearSelection();
			}
		});
		ToolBar tb = new ToolBar(newb, conf, run, del);
		
		root.setTop(tb);
		
		SplitPane pane = new SplitPane();
		selected.bind(machineView.getSelectionModel().selectedItemProperty());
		// disable conf & run buttons if no item is selected
		conf.disableProperty().bind(selected.isNull());
		run.disableProperty().bind(selected.isNull());
		pane.getItems().add(machineView);
		
		root.setCenter(pane);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
