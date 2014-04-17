/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import com.wia.CentralCoordinator;
import com.wia.layout.ControlledScreen;
import com.wia.layout.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class ProgressBarController implements ControlledScreen {

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
	public void handleClick(MouseEvent event) {
		myScreensContainer.switchToScreen(CentralCoordinator.ROOTSTAGEID);
	}

}
