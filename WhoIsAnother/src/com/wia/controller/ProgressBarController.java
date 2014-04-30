/**
 * 
 */
package com.wia.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

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
	public void handleClick(MouseEvent event) {
		// myScreensContainer.setScreen(CentralCoordinator.ROOTSTAGEID);
	}

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
							CentralCoordinator.ROOTSTAGEID,
							CentralCoordinator.rootStageFile);
					myScreensContainer
							.setScreen(CentralCoordinator.ROOTSTAGEID);
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
