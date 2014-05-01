/**
 * 
 */
package com.wia.controller;

import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

import com.wia.Context;
import com.wia.view.layout.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
class BarEventHandler implements EventHandler<MouseEvent> {
	private static Logger logger = Logger.getLogger(BarEventHandler.class
			.getName());
	private final XYChart.Data<?, ?> data;
	private final String screenID;
	private final ScreensContainer screensContainer;
	private final String key;

	public BarEventHandler(XYChart.Data<?, ?> data, String screenID,
			ScreensContainer container, String key) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.screenID = screenID;
		this.screensContainer = container;
		this.key = key;
	}

	@Override
	public void handle(MouseEvent event) {
		// TODO Auto-generated method stub
		logger.info(event.getSource().toString());
		Context.getInstance().addContextObject(key, data.getXValue());
		screensContainer.setScreen(screenID);
	}
}
