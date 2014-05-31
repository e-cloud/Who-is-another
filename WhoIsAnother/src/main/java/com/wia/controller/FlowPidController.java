/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 * @author Saint Scott
 *
 */
public class FlowPidController {

	@FXML
	private Label typename;
	@FXML
	private FlowPane flowPane;

	public FlowPane init(String text) {
		typename.setText(text);
		return flowPane;
	}

}
