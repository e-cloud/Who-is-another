package UITest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.wia.view.SwitchButton;

public class Main extends Application {

	@Override
	public void start(Stage stage) {

		SwitchButton button = new SwitchButton();
		StackPane root = new StackPane(button);
		Scene scene = new Scene(root, 180, 180);
		scene.setFill(Color.BLACK);
		stage.setScene(scene);
		stage.setTitle("Scrollbar");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
