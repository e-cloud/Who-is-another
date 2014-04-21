/**
 * 
 */
package com.wia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.wia.CentralCoordinator;

/**
 * @author Saint Scott
 * 
 */
public class WelcomeController extends AbstractFXController {

	@FXML
	private void handleGo(ActionEvent event) {
		myScreensContainer.switchToScreen(CentralCoordinator.PROGRESSID);
	}
}
