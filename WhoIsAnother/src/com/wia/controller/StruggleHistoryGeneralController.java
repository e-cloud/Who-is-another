/**
 * 
 */
package com.wia.controller;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import com.wia.Context;
import com.wia.util.LogUtil;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryGeneralController extends AbstractFXController {

	@FXML
	private LineChart lineChart;

	private Context context;

	@FXML
	private void initialize() {
		context = Context.getInstance();
		initChart();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initChart() {
		final NumberAxis xAxis = new NumberAxis(1, 30, 1);
		final NumberAxis yAxis = new NumberAxis(-5, 27, 5);
		xAxis.setForceZeroInRange(true);

		lineChart.setTitle("Temperature Monitoring (in Degrees C)");

		Map<Integer, Integer> map = context.;

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("March");
		series1.getData().add(new XYChart.Data(1, -2));
		series1.getData().add(new XYChart.Data(3, -4));
		series1.getData().add(new XYChart.Data(6, 0));
		series1.getData().add(new XYChart.Data(9, 5));
		series1.getData().add(new XYChart.Data(12, -4));
		series1.getData().add(new XYChart.Data(15, 6));
		series1.getData().add(new XYChart.Data(18, 8));
		series1.getData().add(new XYChart.Data(21, 14));
		series1.getData().add(new XYChart.Data(24, 4));
		series1.getData().add(new XYChart.Data(27, 6));
		series1.getData().add(new XYChart.Data(30, 6));

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("April");
		series2.getData().add(new XYChart.Data(1, 4));
		series2.getData().add(new XYChart.Data(3, 10));
		series2.getData().add(new XYChart.Data(6, 15));
		series2.getData().add(new XYChart.Data(9, 8));
		series2.getData().add(new XYChart.Data(12, 5));
		series2.getData().add(new XYChart.Data(15, 18));
		series2.getData().add(new XYChart.Data(18, 15));
		series2.getData().add(new XYChart.Data(21, 13));
		series2.getData().add(new XYChart.Data(24, 19));
		series2.getData().add(new XYChart.Data(27, 21));
		series2.getData().add(new XYChart.Data(30, 21));

		XYChart.Series series3 = new XYChart.Series();
		series3.setName("May");
		series3.getData().add(new XYChart.Data(1, 20));
		series3.getData().add(new XYChart.Data(3, 15));
		series3.getData().add(new XYChart.Data(6, 13));
		series3.getData().add(new XYChart.Data(9, 12));
		series3.getData().add(new XYChart.Data(12, 14));
		series3.getData().add(new XYChart.Data(15, 18));
		series3.getData().add(new XYChart.Data(18, 25));
		series3.getData().add(new XYChart.Data(21, 25));
		series3.getData().add(new XYChart.Data(24, 23));
		series3.getData().add(new XYChart.Data(27, 26));
		series3.getData().add(new XYChart.Data(30, 26));

		lineChart.setHorizontalZeroLineVisible(true);
		lineChart.getData().addAll(series1, series2, series3);
	}
}
