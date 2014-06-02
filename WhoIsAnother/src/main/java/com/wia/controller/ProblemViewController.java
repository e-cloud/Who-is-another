package com.wia.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import com.wia.model.Submit;

public class ProblemViewController extends AbstractFXController {
	@FXML
	private Button ok_1;

	@FXML
	private Button do_it;

	@FXML
	private WebView show_problem;

	@FXML
	private TextField problem_id;

	@FXML
	private Label problemid;

	@FXML
	private void initialize() {

		ok_1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String webViewUrl = null;

				webViewUrl = Submit.getProblemUrl(problem_id.getText());
				myScreensContainer.setUserData(webViewUrl);

				Submit.curProblemid = problem_id.getText();
				WebEngine webEngine = show_problem.getEngine();
				webEngine.load(webViewUrl);
			}
		});

		do_it.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myScreensContainer.setScreen(RootStageController.ID_SUBMITCODE);
			}
		});

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		if (myScreensContainer.getUserData() != null) {
			WebEngine webEngine = show_problem.getEngine();
			webEngine.load((String) myScreensContainer.getUserData());
		}
	}

}
