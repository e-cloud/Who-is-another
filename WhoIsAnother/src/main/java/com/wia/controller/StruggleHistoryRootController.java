/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import com.wia.view.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryRootController extends AbstractFXController {
	private ScreensContainer historyContainer;

	public final static String ID_SH_GENERAL = "strugglehistorygeneral";
	public final static String file_sh_general = "/fxml/StruggleHistoryGeneral.fxml";
	public final static String ID_SH_YEAR = "strugglehistoryyear";
	public final static String file_sh_year = "/fxml/StruggleHistoryYear.fxml";
	public final static String ID_SH_MONTH = "strugglehistorymonth";
	public final static String file_sh_month = "/fxml/StruggleHistoryMonth.fxml";
	public final static String ID_SH_DAY = "strugglehistoryday";
	public final static String file_sh_day = "/fxml/StruggleHistoryDay.fxml";
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
		historyContainer.loadScreen(ID_SH_GENERAL, file_sh_general);
		historyContainer.loadScreen(ID_SH_YEAR, file_sh_year);
		historyContainer.loadScreen(ID_SH_MONTH, file_sh_month);
		historyContainer.loadScreen(ID_SH_DAY, file_sh_day);
		historyContainer.setScreen(ID_SH_GENERAL);

		AnchorPane pane = (AnchorPane) myScreensContainer
				.getScreen(RootStageController.ID_SH_ROOT);
		pane.getChildren().addAll(historyContainer);
	}

	@Override
	public void update() {

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
