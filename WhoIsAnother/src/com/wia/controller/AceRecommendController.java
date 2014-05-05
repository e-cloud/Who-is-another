/**
 * 
 */
package com.wia.controller;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Region;
import javafx.util.Pair;

import com.wia.Context;
import com.wia.model.analysis.ACERecommend;

/**
 * @author Saint Scott
 * 
 */
public class AceRecommendController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private BarChart<?, ?> rcmdChart;

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
		System.out.println(2);
		final Task<List<Pair<Integer, Integer>>> task = new DownloadTask();
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent arg0) {
				// TODO Auto-generated method stub
				try {
					List<Pair<Integer, Integer>> pairs = task.get();
					initChart(pairs);
					myScreensContainer.getChildren().removeAll(greylayer,
							indicator);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// if(indicator.progressProperty().isBound()){
		// indicator.progressProperty().unbind();;
		// }
		//
		// indicator.progressProperty().bind(task.progressProperty());
		// indicator.visibleProperty().bind(task.runningProperty());

		myScreensContainer.getChildren().addAll(greylayer, indicator);
		new Thread(task).start();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initChart(List<Pair<Integer, Integer>> pairs) {
		XYChart.Series series = new XYChart.Series();
		series.setName("提交人次");
		for (Iterator<Pair<Integer, Integer>> iterator = pairs.iterator(); iterator
				.hasNext();) {
			Pair<Integer, Integer> pair = iterator.next();
			series.getData().add(
					new XYChart.Data<String, Integer>(pair.getKey() + "", pair
							.getValue()));
		}

		rcmdChart.getData().clear();
		rcmdChart.getData().add(series);
	}

	private class DownloadTask extends Task<List<Pair<Integer, Integer>>> {

		@Override
		public List<Pair<Integer, Integer>> call() throws Exception {
			// TODO Auto-generated method stub
			return new ACERecommend().recommand(Context.getInstance()
					.getCurrentAuthor(), 10);
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
