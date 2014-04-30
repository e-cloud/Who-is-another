/**
 *
 */
package com.wia;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.wia.view.layout.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class CentralCoordinator extends Application {

	@SuppressWarnings("unused")
	private Context context;

	public Stage primaryStage;
	public final static String baseUrl = "/com/wia/view/";
	public final static String WELCOMEID = "WELCOME";
	public final static String welcomeFile = baseUrl + "Welcome.fxml";
	public final static String PROGRESSID = "PROGRESS";
	public final static String progressFile = baseUrl + "ProgressBar.fxml";
	public final static String ROOTSTAGEID = "ROOTSTAGE";
	public final static String rootStageFile = baseUrl + "RootStage.fxml";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#init()
	 */
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
		context = Context.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Who is another");

		ScreensContainer mainContainer = new ScreensContainer();
		mainContainer.loadScreen(CentralCoordinator.WELCOMEID,
				CentralCoordinator.welcomeFile);
		mainContainer.loadScreen(CentralCoordinator.PROGRESSID,
				CentralCoordinator.progressFile);

		mainContainer.setScreen(CentralCoordinator.WELCOMEID);

		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
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
