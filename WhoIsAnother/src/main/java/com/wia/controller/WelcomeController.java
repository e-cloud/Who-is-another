/**
 * 
 */
package com.wia.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import com.wia.CentralCoordinator;

/**
 * @author Saint Scott
 * 
 */
public class WelcomeController extends AbstractFXController {
	@FXML
	private Parent rootLayout;

	@FXML
	private Button goButton;

	@FXML
	private void initialize() {
		goButton.setOnAction(new EventHandler<ActionEvent>() {
			//
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				myScreensContainer.setScreen(CentralCoordinator.ID_ROOTSTAGE);
			}
		});
	}

	// public void addListener(Scene scene) {
	// // TODO Auto-generated method stub
	// goButton.setOnAction(new EventHandler<ActionEvent>() {
	//
	// @Override
	// public void handle(ActionEvent event) {
	// // TODO Auto-generated method stub
	// ScreensContainer container = new ScreensContainer();
	// FXMLLoader loader = new FXMLLoader(
	// WelcomeController.class.getResource(file_rootstage));
	// try {
	// Parent root = (BorderPane) loader.load();
	// scene.setRoot(root);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
}
