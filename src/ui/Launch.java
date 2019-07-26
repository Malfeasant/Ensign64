package ui;

import java.util.Optional;

import config.Configuration;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Program launcher: Inspired by VirtualBox, a list of configured machines that can be started/stopped/reset etc
 * @author Malfeasant
 */
public class Launch extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO: read command line, some means of persistence for saved machines
		
		primaryStage.setTitle("Ensign64 Manager");
		
		BorderPane pane = new BorderPane();
		
		ListView<Configuration> cfgList = new ListView<>();
		pane.setLeft(cfgList);
		ReadOnlyObjectProperty<Configuration> listSelected = cfgList.getSelectionModel().selectedItemProperty();
		BooleanBinding isEmpty = listSelected.isNull();
		
		Menu fileMenu = new Menu("File");
		ObservableList<MenuItem> menuList = fileMenu.getItems();
		MenuItem item = new MenuItem("New...");
		item.setOnAction(e -> {
			showConfig().ifPresent(consumer -> cfgList.getItems().add(consumer));
		});
		menuList.add(item);
		item = new MenuItem("Settings...");	// TODO: connect listener
		item.disableProperty().bind(isEmpty);
		menuList.add(item);
		item = new MenuItem("Start");	// TODO: connect listener
		item.disableProperty().bind(isEmpty);
		menuList.add(item);
		item = new MenuItem("Discard");
		item.setOnAction(e -> {
			cfgList.getItems().remove(listSelected.get());
		});
		item.disableProperty().bind(isEmpty);
		menuList.add(item);
		menuList.add(new SeparatorMenuItem());
		item = new MenuItem("Exit");
		item.setOnAction(e -> primaryStage.close());
		menuList.add(item);
		MenuBar menuBar = new MenuBar(fileMenu);
		pane.setTop(menuBar);
		
		// TODO: Depending on options, may not even display the launcher, if a machine is specified in cmd line f.e....
		primaryStage.setScene(new Scene(pane, 600, 450));
		primaryStage.show();
	}
	
	private Optional<Configuration> showConfig() {
		// TODO: show a dialog to configure a new machine, ensure it's valid, return it, or null if something went wrong
		// for now:
		return Optional.of(new Configuration());
	}
}
