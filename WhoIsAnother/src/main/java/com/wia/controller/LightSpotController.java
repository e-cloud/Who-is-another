/**
 * 
 */
package com.wia.controller;

import java.util.Iterator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.util.Pair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wia.model.analysis.LightSpot;
import com.wia.model.data.Author;

/**
 * @author Saint Scott
 * 
 */
public class LightSpotController extends AbstractFXController {
	private final static Logger logger = LoggerFactory
			.getLogger(LightSpotController.class);
	@FXML
	private Parent rootLayout;
	@FXML
	private LineChart<String, Integer> intervalChart;
	@FXML
	private BarChart<String, Integer> top10Chart;

	@FXML
	private Label daysLabel;
	@FXML
	private Label firstACLabel;

	@FXML
	private void initialize() {
		top10Chart.setTitle("Advanced Bar Chart");
		intervalChart.setTitle("Advanced Bar Chart");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		Author author = (Author) myScreensContainer.getUserData();
		logger.info("show {}'s lightspot", author.getAuthorID());
		// TODO Auto-generated method stub
		LightSpot lightSpot = new LightSpot(author);
		// 初始化时段表
		XYChart.Series<String, Integer> solveSeries = new XYChart.Series<String, Integer>();
		solveSeries.setName("解决题目数");

		XYChart.Series<String, Integer> submitSeries = new XYChart.Series<String, Integer>();
		submitSeries.setName("提交题目数");

		int[] solveInts = lightSpot.getAcceptedTimeInterval();
		int[] submitInts = lightSpot.getSubmitTimeInterval();
		for (int i = 0; i < solveInts.length; i++) {
			solveSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(i + 1),
							solveInts[i]));
			submitSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(i + 1),
							submitInts[i]));
		}
		intervalChart.getData().clear();
		intervalChart.getData().addAll(submitSeries, solveSeries);

		// 初始化解决题目数前十
		XYChart.Series<String, Integer> solveSeries2 = new XYChart.Series<String, Integer>();
		solveSeries2.setName("解决题目数");
		List<Pair<String, Integer>> solveTop10 = lightSpot.problemSolvedTop10();
		for (Iterator<Pair<String, Integer>> iterator = solveTop10.iterator(); iterator
				.hasNext();) {
			Pair<String, Integer> pair = iterator.next();
			solveSeries2.getData().add(
					new XYChart.Data<String, Integer>(pair.getKey(), pair
							.getValue()));
		}
		top10Chart.getData().clear();
		top10Chart.getData().add(solveSeries2);

		// 初始化各个标签
		daysLabel.setText(String.valueOf(lightSpot.howManyDays()));
		firstACLabel.setText(String.valueOf(lightSpot.ACInFirstSubmit()));
	}

}
