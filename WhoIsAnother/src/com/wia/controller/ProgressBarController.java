/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import com.wia.CentralCoordinator;

/**
 * @author Saint Scott
 * 
 */
public class ProgressBarController extends AbstractFXController {
	@FXML
	private Parent rootLayout;

	@FXML
	public void handleClick(MouseEvent event) {
		myScreensContainer.setScreen(CentralCoordinator.ROOTSTAGEID);
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
