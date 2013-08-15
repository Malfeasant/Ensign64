package us.malfeasant.ensign64;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import us.malfeasant.ensign64.config.Editor;
import us.malfeasant.fxdialog.Dialog;
import us.malfeasant.fxdialog.Dialog.MessageType;
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
	private final ListView<Editor> machineView = new ListView<>();
	private Path homePath;
	
	private void setPath() {
		homePath = Paths.get(System.getProperty("user.home") + "/Ensign64");
//		System.out.println(homePath);
		if (Files.exists(homePath)) {
			if (Files.isDirectory(homePath)) {
				readMachines();
			} else {
				// file exists with same name as the directory we're trying to make...
			}
		} else {	// hasn't been created yet
			try {
				Files.createDirectory(homePath);
			} catch (IOException e) {
				homePath = null;
				Dialog.showMessageDialog(machineView,
						"Could not create directory- machines will not be saved.",
						"Problem", MessageType.ERROR);
			}
		}
	}
	private void readMachines() {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(homePath, "*.ser")) {
			for (Path file : stream) {
				ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file));
				try {
					machineView.getItems().add((Editor) in.readObject());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Ensign64");
		final BorderPane root = new BorderPane();
		
		machineView.setEditable(true);
		machineView.setCellFactory(new Callback<ListView<Editor>, ListCell<Editor>>() {
			@Override
			public ListCell<Editor> call(ListView<Editor> arg0) {
				return new Editor.ConfigCell();
			}
		});
		
		Button newb = new Button("New");
		newb.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Editor newMachine = new Editor();
				if (newMachine.edit(root)) {
					machineView.getItems().add(newMachine);
					machineView.getSelectionModel().select(newMachine);
					write(newMachine);
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
		ReadOnlyObjectProperty<Editor> selected = machineView.getSelectionModel().selectedItemProperty();
		// disable conf run & del buttons if no item is selected
		conf.disableProperty().bind(selected.isNull());
		run.disableProperty().bind(selected.isNull());
		del.disableProperty().bind(selected.isNull());
		pane.getItems().add(machineView);
		
		root.setCenter(pane);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		setPath();
	}
	
	private void write(Editor cfg) {
		if (homePath == null) return;
		Path file = homePath.resolve(cfg.getName());
		try {
			ObjectOutputStream out =
					new ObjectOutputStream(Files.newOutputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
