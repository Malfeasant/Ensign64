package ui;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;

import config.VicFlavor;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextInputDialog;
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
	
	private final Map<String, ConfigWrapper> confMap = new IdentityHashMap<>();
	private ListView<String> cfgListView;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO: read command line, some means of persistence for saved machines
		
		primaryStage.setTitle("Ensign64 Manager");
		
		BorderPane pane = new BorderPane();
		
		cfgListView = new ListView<>();
		pane.setLeft(cfgListView);
		ReadOnlyObjectProperty<String> listSelected = cfgListView.getSelectionModel().selectedItemProperty();
		BooleanBinding isEmpty = listSelected.isNull();
		
		Menu fileMenu = new Menu("File");
		ObservableList<MenuItem> menuList = fileMenu.getItems();
		MenuItem item = new MenuItem("New...");
		item.setOnAction(e -> {
			showConfig("");
		});
		menuList.add(item);
		item = new MenuItem("Settings...");
		item.disableProperty().bind(isEmpty);
		item.setOnAction(e -> {
			showConfig(listSelected.get());
		});
		menuList.add(item);
		item = new MenuItem("Start");	// TODO: connect listener- also, maybe this doesn't belong in File menu?
		item.disableProperty().bind(isEmpty);
		menuList.add(item);
		item = new MenuItem("Delete");
		item.disableProperty().bind(isEmpty);
		item.setOnAction(e -> {
			// TODO: nag dialog
			confMap.remove(listSelected.get());
			cfgListView.getItems().remove(listSelected.get());
		});
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
	
	// Look up the config in the map- if it exists, use that as the start values of each dialog, otherwise
	// pick sensible defaults
	// TODO: this needs a lot of work. It's functional, but very ugly.
	private void showConfig(String name) {
		ConfigWrapper wrapper = confMap.getOrDefault(name, new ConfigWrapper());
		// will never allow null or blank string so this should work
		// TODO: show a (series of) dialog(s) to configure a new machine, ensure it's
		// valid, return it, or null if something went wrong
		TextInputDialog nameInput = new TextInputDialog(name);
		Optional<String> newName = nameInput.showAndWait();
		newName.ifPresent(text -> {
			if (text.equals("")) return;	// Don't save a blank name no matter what
			if (!text.equals(name)) {	// If name has changed
				if (confMap.containsKey(text)) {	// name already used
					return;	// TODO: allow retry or at least alert why it failed...
				}
				// Everything checks out, update the map and the listview
				confMap.put(text, wrapper);
				cfgListView.getItems().remove(wrapper.getName());
				confMap.remove(wrapper.getName());	// does nothing if it's ""
				cfgListView.getItems().add(text);
				wrapper.setName(text);
			}
			// Todo: Test if the machine is running.  If it is, no more dialogs.
			ChoiceDialog<VicFlavor> flavorInput = new ChoiceDialog<>(wrapper.getConfig().getFlavor(), VicFlavor.values());
			Optional<VicFlavor> newFlavor = flavorInput.showAndWait();
			newFlavor.ifPresent(flavor -> {
				if (!flavor.equals(wrapper.getConfig().getFlavor())) {
					wrapper.setConfig(wrapper.getConfig().modify(flavor));
				}
			});
		});
	}
}
