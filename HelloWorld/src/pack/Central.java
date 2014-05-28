package pack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Central extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;

	// private Button button;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("InOut");
		// Set the application icon

		// remove the window's border
		// this.primaryStage.initStyle(StageStyle.UNDECORATED);

		try {
			// Load the root layout from the fxml file
			FXMLLoader loader = new FXMLLoader(
					Control.class.getResource("/view/HelloWorldView.fxml"));
			rootLayout = (AnchorPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app
			// RootLayoutController controller = loader.getController();
			// controller.setMainApp(this);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// showPersonOverview();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
