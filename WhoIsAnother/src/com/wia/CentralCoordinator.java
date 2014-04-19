/**
 *
 */
package com.wia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.wia.controller.RootStageController;
import com.wia.view.layout.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class CentralCoordinator extends Application {

	public Stage primaryStage;
	public final static String WELCOMEID = "WELCOME";
	public final static String welcomeFile = "/com/wia/view/Welcome.fxml";
	public final static String PROGRESSID = "PROGRESS";
	public final static String progressFile = "/com/wia/view/ProgressBar.fxml";
	public final static String ROOTSTAGEID = "ROOTSTAGE";
	public final static String rootStageFile = "/com/wia/view/RootStage.fxml";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#init()
	 */
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Who is another");

		ScreensContainer mainContainer = new ScreensContainer();
		mainContainer.loadScreen(CentralCoordinator.WELCOMEID,
				CentralCoordinator.welcomeFile);
		mainContainer.loadScreen(CentralCoordinator.PROGRESSID,
				CentralCoordinator.progressFile);
		FXMLLoader loader = mainContainer.loadScreen(
				CentralCoordinator.ROOTSTAGEID,
				CentralCoordinator.rootStageFile);
		((RootStageController) loader.getController()).init();

		mainContainer.switchToScreen(CentralCoordinator.WELCOMEID);

		// Group root = new Group();
		// root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(mainContainer);
		primaryStage.setScene(scene);
		primaryStage.show();
		/*
		 * // remove the window's border //
		 * this.primaryStage.initStyle(StageStyle.UNDECORATED); rootLayout = new
		 * StackPane();
		 * 
		 * try { // Load the root layout from the fxml file FXMLLoader
		 * rootloader = createFXMLLoader("view/RootStage.fxml"); FXMLLoader
		 * welcomeloader = createFXMLLoader("view/Welcome.fxml"); FXMLLoader
		 * progressloader = createFXMLLoader("view/ProgressBar.fxml");
		 * 
		 * BorderPane rootStageLayout = (BorderPane) rootloader.load(); GridPane
		 * welcomeLayout = (GridPane) welcomeloader.load(); BorderPane
		 * progressLayout = (BorderPane) progressloader.load();
		 * 
		 * rootLayout.getChildren().add(ROOT, rootStageLayout);
		 * rootLayout.getChildren().add(PROGRESS, progressLayout);
		 * rootLayout.getChildren().add(WELCOME, welcomeLayout);
		 * 
		 * Scene scene = new Scene(rootLayout); primaryStage.setScene(scene);
		 * 
		 * primaryStage.show(); } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
