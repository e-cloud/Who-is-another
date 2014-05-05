/**
 * 
 */
package com.wia.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import com.wia.model.analysis.Recommend;
import com.wia.model.data.TypeCatalog;

/**
 * @author Saint Scott
 * 
 */
public class NeighbourRecommendController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private GridPane gridPane;

	private ProgressIndicator indicator;
	private Region greylayer;

	@FXML
	private void initialize() {
		indicator = new ProgressIndicator();
		indicator.setMaxSize(200, 200);
		greylayer = new Region();
		greylayer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
	}

	@Override
	public void update() {
		System.out.println(1);
		final Task<List<Integer>> task = new DownloadTask();
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent arg0) {
				// TODO Auto-generated method stub
				try {
					List<Integer> nblist = task.get();
					initChart(nblist);
					myScreensContainer.getChildren().removeAll(greylayer,
							indicator);
					rootLayout.getParent().getParent().getParent()
							.setDisable(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		rootLayout.getParent().getParent().getParent().setDisable(true);
		// if(indicator.progressProperty().isBound()){
		// indicator.progressProperty().unbind();;
		// }
		// indicator.progressProperty().bind(task.progressProperty());
		// indicator.visibleProperty().bind(task.runningProperty());

		myScreensContainer.getChildren().addAll(greylayer, indicator);
		new Thread(task).start();
	}

	private void initChart(List<Integer> nblist) {
		Map<String, List<Integer>> nbrcmdmap = TypeCatalog.getInstance()
				.classify(nblist);

		int rowIndex = 1;
		for (Iterator<String> iterator = nbrcmdmap.keySet().iterator(); iterator
				.hasNext();) {
			String type = iterator.next();
			VBox vBox = new VBox();
			vBox.getChildren().add(new Label(type));
			FlowPane flowPane = new FlowPane(20, 10);
			for (Iterator<Integer> iterator2 = nbrcmdmap.get(type).iterator(); iterator2
					.hasNext();) {
				Integer integer = iterator2.next();
				flowPane.getChildren().add(new Label(integer.toString()));
			}
			vBox.getChildren().add(flowPane);
			gridPane.add(vBox, 0, rowIndex++);
		}
	}

	private class DownloadTask extends Task<List<Integer>> {

		@Override
		public List<Integer> call() throws Exception {
			// TODO Auto-generated method stub
			Recommend recommend = new Recommend();
			List<Integer> nblist = recommend.neighborRecommend();
			return nblist;
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public Parent getLayout() {
		// TODO Auto-generated method stub
		return rootLayout;
	}

}
