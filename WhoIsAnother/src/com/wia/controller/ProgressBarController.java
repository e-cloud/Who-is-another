/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import screensframework.ControlledScreen;
import screensframework.ScreensController;

import com.wia.CentralCoordinator;

/**
 * @author Saint Scott
 * 
 */
public class ProgressBarController implements ControlledScreen {

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
	public void handleClick(MouseEvent event) {
		myController.setScreen(CentralCoordinator.ROOTSTAGEID);
	}

}
