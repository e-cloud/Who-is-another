/**
 * 
 */
package com.wia.controller;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import com.wia.model.analysis.GeneralInfo;
import com.wia.model.data.Author;
import com.wia.view.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryRootController extends AbstractFXController {

	public final static String ID_SH_GENERAL = "strugglehistorygeneral";
	public final static String file_sh_general = "/fxml/StruggleHistoryGeneral.fxml";
	public final static String ID_SH_DETAIL = "strugglehistorydetail";
	public final static String file_sh_detail = "/fxml/StruggleHistoryDetail.fxml";

	public final static String GENERALINFO = "generalinfo";
	public final static String AUTHOR = "author";
	public final static String YEAR = "year";
	public final static String MONTH = "month";
	public final static String DAY = "day";

	@FXML
	private Parent rootLayout;

	private ScreensContainer historyContainer;

	@Override
	public void init() {
		historyContainer = new ScreensContainer();
		historyContainer.registerScreen(ID_SH_GENERAL, file_sh_general);
		historyContainer.registerScreen(ID_SH_DETAIL, file_sh_detail);

		Map<String, Object> userdata = new HashMap<>();
		userdata.put("user", myScreensContainer.getUserData());
		userdata.put("generalinfo",
				new GeneralInfo((Author) myScreensContainer.getUserData()));
		historyContainer.setUserData(userdata);

		((StackPane) rootLayout).getChildren().addAll(historyContainer);

		historyContainer.setScreen(ID_SH_GENERAL);

	}
}
