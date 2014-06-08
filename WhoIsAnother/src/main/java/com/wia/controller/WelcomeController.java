/**
 * 
 */
package com.wia.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wia.MainDirector;

/**
 * @author Saint Scott
 * 
 */
public class WelcomeController extends AbstractFXController {
	private final static Logger logger = LoggerFactory
			.getLogger(WelcomeController.class);
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
				logger.info("click on welcome's go button");
				// TODO Auto-generated method stub
				myScreensContainer.setScreen(MainDirector.ID_ROOTSTAGE);
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
