/**
 * 
 */
package com.wia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import screensframework.ControlledScreen;
import screensframework.ScreensController;

import com.wia.CentralCoordinator;

/**
 * @author Saint Scott
 * 
 */
public class WelcomeController implements ControlledScreen {

	ScreensController myController;

	/*
	 * (non-Javadoc)
	 * 
	 * @see screensframework.ControlledScreen#setScreenParent(screensframework.
	 * ScreensController)
	 */
	@Override
	public void setScreenParent(ScreensController screenParent) {
		// TODO Auto-generated method stub
		myController = screenParent;
	}

	@FXML
	private void handleGo(ActionEvent event) {
		myController.setScreen(CentralCoordinator.PROGRESSID);
	}
}
