/**
 * 
 */
package com.wia.controller;

import com.wia.view.layout.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public abstract class AbstractFXController {

	protected ScreensContainer myScreensContainer;

	/**
	 * 
	 */
	public void init() {
		System.out.println("AbstractFXController init");
	}

	/**
	 * @param screensContainer
	 */
	public void setScreenContainer(ScreensContainer screensContainer) {
		myScreensContainer = screensContainer;
	};
}
