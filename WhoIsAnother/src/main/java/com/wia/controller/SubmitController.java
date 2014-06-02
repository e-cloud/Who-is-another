package com.wia.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.wia.model.Submit;

public class SubmitController extends AbstractFXController {
	@FXML
	private Button back;

	@FXML
	private Button submit;

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
		language.getItems().addAll("G++", "GCC", "C++", "C", "Pascal", "Java");
		submit.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Submit.login(username.getText(), password.getText());
				Submit.submintCode(language.getValue(), code.getText());

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String webViewUrl = "http://acm.hdu.edu.cn/status.php?first=&pid="
						+ Submit.curProblemid
						+ "&user="
						+ username.getText()
						+ "&lang=0&status=0";

				myScreensContainer.setUserData(webViewUrl);

				myScreensContainer
						.setScreen(RootStageController.ID_SHOWPROBLEM);
			}
		});

		back.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
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
