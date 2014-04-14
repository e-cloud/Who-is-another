/**
 * 
 */
package com.wia.controller;

import screensframework.ControlledScreen;
import screensframework.ScreensController;

/**
 * @author Saint Scott
 * 
 */
public class SettingController implements ControlledScreen {
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

}
