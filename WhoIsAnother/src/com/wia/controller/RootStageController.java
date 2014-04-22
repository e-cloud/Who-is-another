/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import com.wia.CentralCoordinator;
import com.wia.view.layout.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class RootStageController extends AbstractFXController {

	// private CentralCoordinator coordinator;
	private ScreensContainer rightPaneContainer;

	public final static String STRUGGLEHISTORYROOTID = "strugglehistoryroot";
	public final static String struggleHistroyRootFile = "/com/wia/view/StruggleHistoryRoot.fxml";
	public final static String LIGHTSPOTID = "lightspot";
	public final static String lightSpotFile = "/com/wia/view/LightSpot.fxml";
	public final static String RANKLISTID = "ranklist";
	public final static String ranklistFile = "/com/wia/view/Ranklist.fxml";
	public final static String COMPARATORID = "comparator";
	public final static String comparatorFile = "/com/wia/view/Comparator.fxml";
	public final static String NEIGHBOURRECOMMENDID = "neighbourrecommend";
	public final static String neighbourrecommendFile = "/com/wia/view/NeighbourRecommend.fxml";
	public final static String ACERECOMMENDID = "acerecommend";
	public final static String acerecommendFile = "/com/wia/view/AceRecommend.fxml";
	public final static String PROFILEID = "profile";
	public final static String profileFile = "/com/wia/view/Profile.fxml";
	public final static String SETTINGID = "setting";
	public final static String settingFile = "/com/wia/view/Setting.fxml";
	public final static String HELPID = "help";
	public final static String helpFile = "/com/wia/view/Help.fxml";

	@FXML
	private Label strugglehistorygeneral;
	@FXML
	private Label lightspot;
	@FXML
	private Label ranklist;
	@FXML
	private Label comparator;
	@FXML
	private Label neighbourrecommend;
	@FXML
	private Label acerecommend;
	@FXML
	private Label profile;
	@FXML
	private Label setting;
	@FXML
	private Label help;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#init()
	 */
	@Override
	public void init() {
		rightPaneContainer = new ScreensContainer();
		rightPaneContainer.loadScreen(STRUGGLEHISTORYROOTID,
				struggleHistroyRootFile);
		rightPaneContainer.loadScreen(LIGHTSPOTID, lightSpotFile);
		rightPaneContainer.loadScreen(RANKLISTID, ranklistFile);
		rightPaneContainer.loadScreen(COMPARATORID, comparatorFile);
		rightPaneContainer.loadScreen(NEIGHBOURRECOMMENDID,
				neighbourrecommendFile);
		rightPaneContainer.loadScreen(ACERECOMMENDID, acerecommendFile);
		rightPaneContainer.loadScreen(PROFILEID, profileFile);
		rightPaneContainer.loadScreen(SETTINGID, settingFile);
		rightPaneContainer.loadScreen(HELPID, helpFile);
		rightPaneContainer.switchToScreen(STRUGGLEHISTORYROOTID);

		Group root = new Group();
		root.getChildren().addAll(rightPaneContainer);
		BorderPane borderLayout = (BorderPane) myScreensContainer
				.getScreen(CentralCoordinator.ROOTSTAGEID);
		borderLayout.setCenter(root);
	}

	@FXML
	private void handleClick(MouseEvent event) {
		System.out.println(((Label) event.getSource()).getId());
		rightPaneContainer.switchToScreen(((Label) event.getSource()).getId());
	}
}
