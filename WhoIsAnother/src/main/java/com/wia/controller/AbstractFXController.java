/**
 * 
 */
package com.wia.controller;

import javafx.scene.Parent;

import com.wia.view.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public abstract class AbstractFXController {

	protected ScreensContainer myScreensContainer;

	/**
	 * 
	 */
	public abstract void init();

	/**
	 * @param screensContainer
	 */
	public void setScreenContainer(ScreensContainer screensContainer) {
		myScreensContainer = screensContainer;
	};

	/**
	 * @return
	 */
	public abstract Parent getLayout();

	public void update() {

	}

}
