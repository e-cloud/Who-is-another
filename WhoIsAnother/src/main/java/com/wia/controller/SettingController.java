/**
 * 
 */
package com.wia.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.wia.CentralCoordinator;
import com.wia.Context;

/**
 * @author Saint Scott
 * 
 */
public class SettingController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private TextField switchTextField;
	@FXML
	private Button switchButton;
	@FXML
	private Button refreshButton;

	@FXML
	private void initialize() {
		switchButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Context context = Context.getInstance();
				context.addContextObject("requestID", switchTextField.getText());
				((CentralCoordinator) context.getCoordinator())
						.setScreen(CentralCoordinator.ID_PROGRESS);
			}
		});
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

	@Override
	public void update() {

	}
}
