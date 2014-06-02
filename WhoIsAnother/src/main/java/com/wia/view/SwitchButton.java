/**
 * 
 */
package com.wia.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

/**
 * @author Saint Scott
 *
 */

public class SwitchButton extends Label {
	private final SimpleBooleanProperty switchON = new SimpleBooleanProperty(
			true);

	public SwitchButton() {
		Button switchBtn = new Button();
		// this.setPrefWidth(60);
		switchBtn.setPrefWidth(30);
		switchBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchON.set(!switchON.get());
			}
		});

		setGraphic(switchBtn);

		switchON.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> value,
					Boolean off, Boolean on) {
				if (on) {
					setText("ON");
					setStyle("-fx-background-color: green;-fx-text-fill:white;");
					setContentDisplay(ContentDisplay.RIGHT);
				} else {
					setText("OFF");
					setStyle("-fx-background-color: grey;-fx-text-fill:black;");
					setContentDisplay(ContentDisplay.LEFT);
				}
			}
		});

		switchON.set(false);
	}

	public SimpleBooleanProperty switchOnProperty() {
		return switchON;
	}
}