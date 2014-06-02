/**
 *
 */
package com.wia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.wia.view.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public class MainDirector extends Application {

	ScreensContainer mainContainer;
	public Stage primaryStage;
	public final static String ID_WELCOME = "WELCOME";
	public static String file_welcome = "/fxml/Welcome.fxml";
	public final static String ID_ROOTSTAGE = "ROOTSTAGE";
	public static String file_rootstage = "/fxml/RootStage.fxml";

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
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Who is another");

		mainContainer = new ScreensContainer();
		mainContainer.registerScreen(MainDirector.ID_WELCOME,
				MainDirector.file_welcome);
		mainContainer.registerScreen(MainDirector.ID_ROOTSTAGE,
				MainDirector.file_rootstage);

		mainContainer.setScreen(MainDirector.ID_WELCOME);

		// Group root = new Group();
		// root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(mainContainer);
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

	public void setScreen(String screenID) {
		mainContainer.setScreen(screenID);
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
