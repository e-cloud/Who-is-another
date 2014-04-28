/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import com.wia.view.layout.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryRootController extends AbstractFXController {
	private ScreensContainer historyContainer;

	public final static String STRUGGLEHISTORGENERALID = "strugglehistorygeneral";
	public final static String struggleHistroyGeneralFile = "/com/wia/view/StruggleHistoryGeneral.fxml";
	public final static String STRUGGLEHISTORYEARID = "strugglehistoryyear";
	public final static String struggleHistroyYearFile = "/com/wia/view/StruggleHistoryYear.fxml";
	public final static String STRUGGLEHISTORYMONTHID = "strugglehistorymonth";
	public final static String struggleHistroyMonthFile = "/com/wia/view/StruggleHistoryMonth.fxml";
	public final static String STRUGGLEHISTORYDAYID = "strugglehistoryday";
	public final static String struggleHistroyDayFile = "/com/wia/view/StruggleHistoryDay.fxml";
	@FXML
	private Parent rootLayout;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#init()
	 */
	@Override
	public void init() {
		historyContainer = new ScreensContainer();
		historyContainer.loadScreen(STRUGGLEHISTORGENERALID,
				struggleHistroyGeneralFile);
		historyContainer.loadScreen(STRUGGLEHISTORYEARID,
				struggleHistroyYearFile);
		historyContainer.loadScreen(STRUGGLEHISTORYMONTHID,
				struggleHistroyMonthFile);
		historyContainer.loadScreen(STRUGGLEHISTORYDAYID,
				struggleHistroyDayFile);
		historyContainer.setScreen(STRUGGLEHISTORGENERALID);

		AnchorPane pane = (AnchorPane) myScreensContainer
				.getScreen(RootStageController.STRUGGLEHISTORYROOTID);
		pane.getChildren().addAll(historyContainer);
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
