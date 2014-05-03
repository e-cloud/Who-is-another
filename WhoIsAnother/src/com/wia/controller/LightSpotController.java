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

import com.wia.Context;
import com.wia.model.analysis.SpotLight;

/**
 * @author Saint Scott
 * 
 */
public class LightSpotController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private LineChart<?, ?> intervalChart;
	@FXML
	private BarChart<?, ?> top10Chart;

	@FXML
	private Label daysLabel;
	@FXML
	private Label firstACLabel;

	@FXML
	private void initialize() {
		top10Chart.setTitle("Advanced Bar Chart");
		intervalChart.setTitle("Advanced Bar Chart");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void update() {
		// TODO Auto-generated method stub
		Context context = Context.getInstance();
		if (!context.containsKey("lightspot")) {
			context.addContextObject("lightspot", new SpotLight());
		}
		SpotLight spotLight = (SpotLight) context.getContextObject("lightspot");
		// 初始化时段表
		XYChart.Series solveSeries = new XYChart.Series();
		solveSeries.setName("解决题目数");

		XYChart.Series submitSeries = new XYChart.Series();
		submitSeries.setName("提交题目数");

		int[] solveInts = spotLight.getAcceptedTimeInterval();
		int[] submitInts = spotLight.getSubmitTimeInterval();
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
		XYChart.Series solveSeries2 = new XYChart.Series();
		solveSeries2.setName("解决题目数");
		List<Pair<String, Integer>> solveTop10 = spotLight.problemSolvedTop10();
		for (Iterator iterator = solveTop10.iterator(); iterator.hasNext();) {
			Pair<String, Integer> pair = (Pair<String, Integer>) iterator
					.next();
			solveSeries2.getData().add(
					new XYChart.Data<String, Integer>(pair.getKey(), pair
							.getValue()));
		}
		top10Chart.getData().clear();
		top10Chart.getData().add(solveSeries2);

		// 初始化各个标签
		daysLabel.setText(String.valueOf(spotLight.howManyDays()));
		firstACLabel.setText(String.valueOf(spotLight.ACInFirstSubmit()));
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
