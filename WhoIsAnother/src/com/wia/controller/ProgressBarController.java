/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import com.wia.CentralCoordinator;

/**
 * @author Saint Scott
 * 
 */
public class ProgressBarController extends AbstractFXController {

	@FXML
	public void handleClick(MouseEvent event) {
		myScreensContainer.switchToScreen(CentralCoordinator.ROOTSTAGEID);
	}

}
