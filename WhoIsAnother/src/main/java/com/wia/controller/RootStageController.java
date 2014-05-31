/**
 * 
 */
package com.wia.controller;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import org.controlsfx.dialog.Dialogs;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.view.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class RootStageController extends AbstractFXController {

	public final static String ID_SH_ROOT = "strugglehistoryroot";
	public final static String file_sh_root = "/fxml/StruggleHistoryRoot.fxml";
	public final static String ID_LIGHTSPOT = "lightspot";
	public final static String file_lightspot = "/fxml/LightSpot.fxml";
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
	private BorderPane rootLayout;

	@FXML
	private Button struggleHistory;
	@FXML
	private Button lightspot;
	@FXML
	private Button neighbourRecommend;
	@FXML
	private Button aceRecommend;
	@FXML
	private Button profile;
	@FXML
	private Button comparator;

	@FXML
	private Button addUser;
	@FXML
	private Button setting;
	@FXML
	private Button help;

	@FXML
	private TabPane tabPane;

	private ScreensContainer subcontainer;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		help.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				showRootDocumentScreen(ID_HELP);
			}
		});
		comparator.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				showRootDocumentScreen(ID_COMPARATOR);
			}
		});
		setting.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				showRootDocumentScreen(ID_SETTING);
			}
		});
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
	}

	@Override
	public void init() {
		setupRootDocument();
	}

	private void setupRootDocument() {
		subcontainer = new ScreensContainer();
		subcontainer.registerScreen(ID_COMPARATOR, file_comparator);
		subcontainer.registerScreen(ID_HELP, file_help);
		subcontainer.registerScreen(ID_SETTING, file_setting);
	}

	private ScreensContainer createNewDocument() {
		ScreensContainer container = new ScreensContainer();
		container.registerScreen(ID_SH_ROOT, file_sh_root);
		container.registerScreen(ID_LIGHTSPOT, file_lightspot);
		container.registerScreen(ID_NBRCMD, file_nbrcmd);
		container.registerScreen(ID_ACERCMD, file_acercmd);
		container.registerScreen(ID_PROFILE, file_profile);

		return container;
	}

	private void showRootDocumentScreen(String id) {
		if (!rootLayout.getCenter().equals(subcontainer)) {
			rootLayout.setCenter(subcontainer);
		}
		subcontainer.setScreen(id);
	}

	private void showDocumentScreen(String id) {
		if (!rootLayout.getCenter().equals(tabPane)) {
			rootLayout.setCenter(tabPane);
		}

		Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
		if (selectedTab != null) {
			ScreensContainer container = (ScreensContainer) selectedTab
					.getContent();
			container.setScreen(id);
		}
	}

	@FXML
	private void showHistory(ActionEvent event) {
		showDocumentScreen(ID_SH_ROOT);
	}

	@FXML
	private void showLightSpot(ActionEvent event) {
		showDocumentScreen(ID_LIGHTSPOT);
	}

	@FXML
	private void showNbRcmd(ActionEvent event) {
		showDocumentScreen(ID_NBRCMD);
	}

	@FXML
	private void showAceRcmd(ActionEvent event) {
		showDocumentScreen(ID_ACERCMD);
	}

	@FXML
	private void showProfile(ActionEvent event) {
		showDocumentScreen(ID_PROFILE);
	}

	@FXML
	private void handleAddUser(ActionEvent event) {
		// TODO Auto-generated method stub
		String authorID = Dialogs.create().owner(rootLayout)
				.title("Text Input Dialog")
				.masthead("We need you to provide the AuthorID!")
				.message("Please enter the AuthorID:")
				.showTextInput("wdp515105");

		Task<Author> task = new ImportAuthorTask(authorID);

		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(WorkerStateEvent event) {
				// TODO Auto-generated method stub
				Context context = Context.getInstance();
				if (!context.containsKey("authors")) {
					context.addContextObject("authors",
							new HashMap<String, Author>());
				}
				Author author = (Author) event.getSource().getValue();

				if (author != null) {
					// TODO add
					((Map<String, Author>) context.getContextObject("authors"))
							.put(authorID, author);
					Tab tab = new Tab(authorID);
					tab.setClosable(true);
					tab.setUserData(author);

					ScreensContainer container = createNewDocument();
					container.setUserData(author);
					tab.setContent(container);
					tabPane.getTabs().add(tab);
					tabPane.getSelectionModel().select(tab);

					showDocumentScreen(ID_SH_ROOT);
				} else {
					Platform.runLater(() -> {
						Dialogs.create().owner(rootLayout)
								.title("Warning Dialog")
								.masthead("Look, a Warning Dialog")
								.message("Sorry, No Such User!").showWarning();
					});

				}
			}
		});

		Dialogs.create().owner(rootLayout).title("Progress Dialog")
				.masthead("Downloading Author " + authorID + "'s information!")
				.showWorkerProgress(task);

		new Thread(task).start();
	}

}
