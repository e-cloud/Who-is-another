/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import screensframework.ControlledScreen;
import screensframework.ScreensController;

import com.wia.CentralCoordinator;

/**
 * @author Saint Scott
 * 
 */
public class RootStageController implements ControlledScreen {

	CentralCoordinator coordinator;
	ScreensController myController;
	ScreensController rightPanecController;

	public final static String STRUGGLEHISTORYGENERALID = "STRUGGLEHISTORYGENERAL";
	public final static String struggleHistroyGeneralFile = "/com/wia/view/StruggleHistoryGeneral.fxml";
	public final static String LIGHTSPOTID = "PROGRESS";
	public final static String lightSpotFile = "/com/wia/view/LightSpot.fxml";
	public final static String RANKLISTID = "ROOTSTAGE";
	public final static String ranklistFile = "/com/wia/view/Ranklist.fxml";

	@FXML
	private Label STRUGGLEHISTORYGENERAL;
	@FXML
	private Label PROGRESS;
	@FXML
	private Label ROOTSTAGE;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		rightPanecController = new ScreensController();
		rightPanecController.loadScreen(STRUGGLEHISTORYGENERALID,
				struggleHistroyGeneralFile);
		rightPanecController.loadScreen(LIGHTSPOTID, lightSpotFile);
		rightPanecController.loadScreen(RANKLISTID, ranklistFile);
		rightPanecController.setScreen(STRUGGLEHISTORYGENERALID);

		Group root = new Group();
		root.getChildren().addAll(rightPanecController);
		BorderPane borderLayout = (BorderPane) myController
				.getScreen(CentralCoordinator.ROOTSTAGEID);
		borderLayout.setLeft(root);
	}

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

	@FXML
	private void handleClick(MouseEvent event) {
		rightPanecController.setScreen(((Label) event.getSource()).getId());
	}
}
