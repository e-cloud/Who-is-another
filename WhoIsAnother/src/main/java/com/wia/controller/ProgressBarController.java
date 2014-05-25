/**
 * 
 */
package com.wia.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;

import com.wia.CentralCoordinator;
import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.preprocess.DataPreprocessor;

/**
 * @author Saint Scott
 * 
 */
public class ProgressBarController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private ProgressBar progressBar;

	@Override
	public void update() {
		final Task<ObservableList<String>> task = new Task<ObservableList<String>>() {
			@Override
			protected ObservableList<String> call() throws InterruptedException {
				updateMessage("Finding Author . . .");

				Context context = Context.getInstance();
				DataPreprocessor preprocessor = new DataPreprocessor();
				Author author = preprocessor
						.retrieveAuthorFromNet((String) context
								.getContextObject("requestID"));
				context.setAuthor(author);

				if (!context.containsKey("ranklist")) {
					ObservableList<Author> authors = FXCollections
							.observableList(preprocessor
									.retrieveTopAuthors(100));
					context.addContextObject("ranklist", authors);
				}

				updateMessage("Finished.");
				return null;
			}

		};

		task.stateProperty().addListener(new ChangeListener<Worker.State>() {
			@Override
			public void changed(
					ObservableValue<? extends Worker.State> observableValue,
					Worker.State oldState, Worker.State newState) {
				if (newState == Worker.State.SUCCEEDED) {
					myScreensContainer.loadScreen(
							CentralCoordinator.ID_ROOTSTAGE,
							CentralCoordinator.file_rootstage);
					myScreensContainer
							.setScreen(CentralCoordinator.ID_ROOTSTAGE);
				}
			}
		});

		new Thread(task).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#getLayout()
	 */
	@Override
	public Parent getLayout() {
		// TODO Auto-generated method stub
		return rootLayout;
	}

}
