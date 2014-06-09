package com.wia.controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import org.controlsfx.dialog.Dialogs;

import com.wia.Context;
import com.wia.model.Submit;
import com.wia.model.data.Author;

public class SubmitController extends AbstractFXController {
	@FXML
	private Button backButton;

	@FXML
	private Button submitButton;

	@FXML
	private ComboBox<String> language;

	@FXML
	private ComboBox<String> userName;

	@FXML
	private PasswordField password;
	@FXML
	private TextField pid;

	@FXML
	private TextArea code;

	@FXML
	private void initialize() {
		pid.setText(Submit.currentProblemID);
		Submit submit = new Submit();
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (pid.getText() == "" || pid.getText() == null) {
					Dialogs.create().owner(myScreensContainer)
							.title("Warning Dialog")
							.masthead("Look, a Warning Dialog")
							.message("pid should be not null!").showWarning();
					return;
				}
				if (code.getText().length() < 50) {
					Dialogs.create()
							.owner(myScreensContainer)
							.title("Warning Dialog")
							.masthead("Look, a Warning Dialog")
							.message(
									"Code length is improper! "
											+ "Make sure your code length is longer than 50 "
											+ "and not exceed 65536 Bytes.")
							.showWarning();
					return;
				}

				Task<Boolean> task = new Task<Boolean>() {

					@Override
					protected Boolean call() throws Exception {
						// TODO Auto-generated method stub
						boolean loginSucceed = submit.login(
								userName.getValue(), password.getText());
						if (!loginSucceed) {
							return false;
						}
						submit.submitCode(language.getValue(), code.getText());
						Thread.sleep(2000);
						return true;
					}

					@Override
					protected void succeeded() {
						// TODO Auto-generated method stub
						try {
							if (!this.get()) {
								Platform.runLater(() -> {
									Dialogs.create().title("Warning Dialog")
											.masthead("Look, a Warning Dialog")
											.message("Sorry, No Such User!")
											.showWarning();
								});
								return;
							}
						} catch (InterruptedException | ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String webViewUrl = "http://acm.hdu.edu.cn/status.php?first=&pid="
								+ Submit.currentProblemID
								+ "&user="
								+ userName.getValue() + "&lang=0&status=0";

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

		userName.setOnShowing(new EventHandler<Event>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Context context = Context.getInstance();
				if (context.containsKey("authors")) {
					userName.getItems().clear();
					userName.getItems().addAll(
							((Map<String, Author>) context
									.getContextObject("authors")).keySet());
				}
			}
		});
		userName.setPromptText("Author ID");
		userName.setTooltip(new Tooltip("请选择一个已有用户ID或键入新的用户ID进行添加"));
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
