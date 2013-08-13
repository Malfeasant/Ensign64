package us.malfeasant.ensign64;

import us.malfeasant.ensign64.config.Config;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Launcher extends Application {
	private final ListView<Config> machineView = new ListView<>();
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Ensign64");
		final BorderPane root = new BorderPane();
		
		machineView.setEditable(true);
		machineView.setCellFactory(new Callback<ListView<Config>, ListCell<Config>>() {
			@Override
			public ListCell<Config> call(ListView<Config> arg0) {
				return new Config.ConfigCell();
			}
		});
		
		Button newb = new Button("New");
		newb.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Config newMachine = new Config();
				if (newMachine.edit(root)) {
					machineView.getItems().add(newMachine);
					machineView.getSelectionModel().select(newMachine);
				}
			}
		});
		Button conf = new Button("Settings");
		conf.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				machineView.getSelectionModel().getSelectedItem().edit(machineView);
			}
		});
		Button run = new Button("Run");
		run.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println(machineView.getSelectionModel().getSelectedItem()
						+ " => Run");	// TODO
			}
		});
		Button del = new Button("Delete");
		del.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println(machineView.getSelectionModel().getSelectedItem()
						+ " => Deleted");	// TODO (also maybe add a nag)
				machineView.getItems().remove(machineView.getSelectionModel().getSelectedItem());
				machineView.getSelectionModel().clearSelection();
			}
		});
		ToolBar tb = new ToolBar(newb, conf, run, del);
		
		root.setTop(tb);
		
		SplitPane pane = new SplitPane();
		ReadOnlyObjectProperty<Config> selected = machineView.getSelectionModel().selectedItemProperty();
		// disable conf run & del buttons if no item is selected
		conf.disableProperty().bind(selected.isNull());
		run.disableProperty().bind(selected.isNull());
		del.disableProperty().bind(selected.isNull());
		pane.getItems().add(machineView);
		
		root.setCenter(pane);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
