/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import com.wia.CentralCoordinator;
import com.wia.view.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class RootStageController extends AbstractFXController {

	// private CentralCoordinator coordinator;
	private ScreensContainer rightPaneContainer;

	public final static String ID_SH_ROOT = "strugglehistoryroot";
	public final static String file_sh_root = "/fxml/StruggleHistoryRoot.fxml";
	public final static String ID_LIGHTSPOT = "lightspot";
	public final static String file_lightspot = "/fxml/LightSpot.fxml";
	public final static String ID_RANKLIST = "ranklist";
	public final static String file_ranklist = "/fxml/Ranklist.fxml";
	public final static String ID_COMPARATOR = "comparator";
	public final static String file_comparator = "/fxml/Comparator.fxml";
	public final static String ID_NBRCMD = "neighbourrecommend";
	public final static String file_nbrcmd = "/fxml/NeighbourRecommend.fxml";
	public final static String ID_ACERCMD = "acerecommend";
	public final static String file_acercmd = "/fxml/AceRecommend.fxml";
	public final static String ID_PROFILE = "profile";
	public final static String file_profile = "/fxml/Profile.fxml";
	public final static String ID_SETTING = "setting";
	public final static String file_setting = "/fxml/Setting.fxml";
	public final static String ID_HELP = "help";
	public final static String file_help = "/fxml/Help.fxml";

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

	@FXML
	private Parent rootLayout;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	@Override
	public Parent getLayout() {
		return rootLayout;
	}

	@Override
	public void update() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#init()
	 */
	@Override
	public void init() {
		rightPaneContainer = new ScreensContainer();
		rightPaneContainer.loadScreen(ID_SH_ROOT, file_sh_root);
		rightPaneContainer.loadScreen(ID_LIGHTSPOT, file_lightspot);
		rightPaneContainer.loadScreen(ID_RANKLIST, file_ranklist);
		rightPaneContainer.loadScreen(ID_COMPARATOR, file_comparator);
		rightPaneContainer.loadScreen(ID_NBRCMD, file_nbrcmd);
		rightPaneContainer.loadScreen(ID_ACERCMD, file_acercmd);
		rightPaneContainer.loadScreen(ID_PROFILE, file_profile);
		rightPaneContainer.loadScreen(ID_SETTING, file_setting);
		rightPaneContainer.loadScreen(ID_HELP, file_help);
		rightPaneContainer.setScreen(ID_SH_ROOT);

		BorderPane layout = (BorderPane) myScreensContainer
				.getScreen(CentralCoordinator.ID_ROOTSTAGE);
		layout.setCenter(rightPaneContainer);
	}

	@FXML
	private void handleClick(MouseEvent event) {
		rightPaneContainer.setScreen(((Label) event.getSource()).getId());
	}
}
