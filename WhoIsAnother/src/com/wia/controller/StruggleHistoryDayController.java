/**
 * 
 */
package com.wia.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryDayController extends AbstractFXController {

	@FXML
	Button backButton;

	@FXML
	private void initialize() {
		backButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				myScreensContainer
						.switchToScreen(StruggleHistoryRootController.STRUGGLEHISTORYEARID);
			}
		});

	}
}
