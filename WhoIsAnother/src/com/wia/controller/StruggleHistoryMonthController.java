/**
 * 
 */
package com.wia.controller;

import com.wia.view.layout.ControlledScreen;
import com.wia.view.layout.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryMonthController implements ControlledScreen {
	ScreensContainer myScreensContainer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see screensframework.ControlledScreen#setScreenParent(screensframework.
	 * ScreensContainer)
	 */
	@Override
	public void setScreenParent(ScreensContainer screenParent) {
		// TODO Auto-generated method stub
		myScreensContainer = screenParent;
	}

}
