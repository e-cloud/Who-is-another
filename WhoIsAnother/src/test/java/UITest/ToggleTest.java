/**
 * 
 */
package UITest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Saint Scott
 *
 */
public class ToggleTest extends Application {

	private void init(Stage primaryStage) {
		Group root = new Group();
		primaryStage.setScene(new Scene(root));

		String pillButtonCss = getClass().getResource("/css/PillButton.css")
				.toExternalForm();

		// create 3 toggle buttons and a toogle group for them
		ToggleButton tb1 = new ToggleButton("Left Button");
		tb1.setId("pill-left");
		ToggleButton tb2 = new ToggleButton("Center Button");
		tb2.setId("pill-center");
		ToggleButton tb3 = new ToggleButton("Right Button");
		tb3.setId("pill-right");

		final ToggleGroup group = new ToggleGroup();
		tb1.setToggleGroup(group);
		tb2.setToggleGroup(group);
		tb3.setToggleGroup(group);
		// select the first button to start with
		group.selectToggle(tb1);

		// ////////////////////////////////////////

		tb1.setUserData(Color.LIGHTGREEN);
		tb2.setUserData(Color.LIGHTBLUE);
		tb3.setUserData(Color.SALMON);

		final Rectangle rect = new Rectangle(300, 300);

		group.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					@Override
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle toggle, Toggle new_toggle) {
						if (new_toggle == null) {
							rect.setFill(Color.WHITE);
						} else {
							rect.setFill((Color) group.getSelectedToggle()
									.getUserData());
						}
					}
				});

		// /////////////////////////////////////////

		rect.setArcHeight(10);
		rect.setArcWidth(10);

		HBox hBox = new HBox();
		hBox.getChildren().addAll(tb1, tb2, tb3);
		hBox.setPadding(new Insets(20, 20, 260, 20));
		hBox.getStylesheets().add(pillButtonCss);

		VBox vbox = new VBox();

		vbox.getChildren().add(hBox);
		vbox.getChildren().add(rect);

		root.getChildren().add(vbox);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}