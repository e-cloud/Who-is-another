/**
 * 
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.BreadcrumbBar;

/**
 * @author Saint Scott
 *
 */
public class BreadcrumbBarTest extends Application {

	private Button yearLink;
	@FXML
	private Button monthLink;
	@FXML
	private Button daylabel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Background Processes");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 300, 50, Color.WHITE);

		yearLink = new Button("year");
		monthLink = new Button("month");
		daylabel = new Button("day");

		BreadcrumbBar bar = new BreadcrumbBar();
		bar.setPrefHeight(50);
		bar.addItem("year", yearLink);
		bar.addItem("month", monthLink);
		bar.addItem("day", daylabel);

		// bar.getStylesheets().clear();
		bar.getStylesheets().add(
				getClass().getResource("/css/BreadCrumbBar.css")
						.toExternalForm());
		bar.getStylesheets().add(
				getClass().getResource("/css/BreadCrumbItem.css")
						.toExternalForm());

		root.getChildren().add(bar);

		// bar.autosize();
		// bar.setMinHeight(120);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
