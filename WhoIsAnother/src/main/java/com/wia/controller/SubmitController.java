package com.wia.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;

import com.wia.model.Submit;

public class SubmitController extends AbstractFXController {
	@FXML
	private Button backButton;

	@FXML
	private Button submitButton;

	@FXML
	private ComboBox<String> language;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private TextArea code;

	@FXML
	private void initialize() {
		Submit submit = new Submit();
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Task<Void> task = new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						// TODO Auto-generated method stub
						submit.login(username.getText(), password.getText());
						submit.submitCode(language.getValue(), code.getText());
						Thread.sleep(2000);
						return null;
					}

					@Override
					protected void succeeded() {
						// TODO Auto-generated method stub
						String webViewUrl = "http://acm.hdu.edu.cn/status.php?first=&pid="
								+ Submit.currentProblemID
								+ "&user="
								+ username.getText() + "&lang=0&status=0";

						myScreensContainer.setUserData(webViewUrl);

						myScreensContainer
								.setScreen(RootStageController.ID_SHOWPROBLEM);
					}

				};

				Dialogs.create().title("Progress Dialog")
						.masthead("Submitting the code!")
						.showWorkerProgress(task);

				new Thread(task).start();
			}
		});

		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myScreensContainer
						.setScreen(RootStageController.ID_SHOWPROBLEM);
			}
		});

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
