/**
 * 
 */
package com.wia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;

import com.wia.CentralCoordinator;

/**
 * @author Saint Scott
 * 
 */
public class WelcomeController extends AbstractFXController {
	@FXML
	private Parent rootLayout;

	@FXML
	private void handleGo(ActionEvent event) {
		myScreensContainer.setScreen(CentralCoordinator.PROGRESSID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#getLayout()
	 */
	@Override
	public Parent getLayout() {
		// TODO Auto-generated method stub
		return rootLayout;
	}
}
