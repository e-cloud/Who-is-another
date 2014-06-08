/**
 * 
 */
package com.wia.controller;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import org.controlsfx.dialog.Dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wia.model.analysis.ACERecommend;
import com.wia.model.data.Author;

/**
 * @author Saint Scott
 * 
 */
public class AceRecommendController extends AbstractFXController {
	private final static Logger logger = LoggerFactory
			.getLogger(AceRecommendController.class);
	@FXML
	private Parent rootLayout;
	@FXML
	private BarChart<String, Integer> rcmdChart;

	@Override
	public void init() {
		Author author = (Author) myScreensContainer.getUserData();

		Task<List<Pair<Integer, Integer>>> task = new AnalysizeTask(author);

		Dialogs.create().title("Progress Dialog")
				.masthead("Downloading Top 100 Authors' information!")
				.showWorkerProgress(task);

		new Thread(task).start();

	}

	private void initChart(List<Pair<Integer, Integer>> pairs) {
		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
		series.setName("提交人次");
		for (Iterator<Pair<Integer, Integer>> iterator = pairs.iterator(); iterator
				.hasNext();) {
			Pair<Integer, Integer> pair = iterator.next();
			series.getData().add(
					new XYChart.Data<String, Integer>(pair.getKey() + "", pair
							.getValue()));
		}

		rcmdChart.getData().add(series);
		logger.info("show acerecommend chart");
	}

	private class AnalysizeTask extends Task<List<Pair<Integer, Integer>>> {

		private final Author author;

		public AnalysizeTask(Author author) {
			this.author = author;
		}

		@Override
		public List<Pair<Integer, Integer>> call() throws Exception {
			// TODO Auto-generated method stub
			ACERecommend aceRecommend = new ACERecommend(author);
			return aceRecommend.recommend(10);
		}

		@Override
		protected void succeeded() {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			try {
				List<Pair<Integer, Integer>> acelist = this.get();
				initChart(acelist);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
