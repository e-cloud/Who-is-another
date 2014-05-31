/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * @author Saint Scott
 * 
 */
public class SettingController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private Button refreshButton;
	@FXML
	private ToggleButton yesButton;
	@FXML
	private ToggleButton noButton;
	@FXML
	private ToggleGroup cacheGroup;
	@FXML
	private Button typeManageButton;

	@FXML
	private void initialize() {

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
