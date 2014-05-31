/**
 * 
 */
package com.wia.controller;

import com.wia.view.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public abstract class AbstractFXController {

	protected ScreensContainer myScreensContainer = null;

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
}
