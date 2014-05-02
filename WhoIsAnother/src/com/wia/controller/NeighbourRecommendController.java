/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

/**
 * @author Saint Scott
 * 
 */
public class NeighbourRecommendController extends AbstractFXController {
	@FXML
	private GridPane rootLayout;

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
