/**
 * 
 */
package com.wia.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import com.wia.CentralCoordinator;
import com.wia.Context;

/**
 * @author Saint Scott
 * 
 */
public class WelcomeController extends AbstractFXController {
	@FXML
	private Parent rootLayout;

	@FXML
	private TextField textField;

	@FXML
	private void handleGo(ActionEvent event) {
		Context.getInstance()
				.addContextObject("requestID", textField.getText());
		myScreensContainer.setScreen(CentralCoordinator.ID_PROGRESS);
	}

	@FXML
	private void initailize() {
		textField.setOnKeyTyped(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					handleGo(null);
				}
			}
		});
		textField.setText("jpg");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public Parent getLayout() {
		// TODO Auto-generated method stub
		return rootLayout;
	}
}
