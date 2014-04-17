/**
 * 
 */
package com.wia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.wia.CentralCoordinator;
import com.wia.layout.ControlledScreen;
import com.wia.layout.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class WelcomeController implements ControlledScreen {

	ScreensContainer myScreensContainer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see screensframework.ControlledScreen#setScreenParent(screensframework.
	 * ScreensContainer)
	 */
	@Override
	public void setScreenParent(ScreensContainer screenParent) {
		// TODO Auto-generated method stub
		myScreensContainer = screenParent;
	}

	@FXML
	private void handleGo(ActionEvent event) {
		myScreensContainer.switchToScreen(CentralCoordinator.PROGRESSID);
	}
}
