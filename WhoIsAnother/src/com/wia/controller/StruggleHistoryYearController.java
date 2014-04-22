/**
 * 
 */
package com.wia.controller;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import com.wia.model.analysis.GeneralInfo;
import com.wia.util.LogUtil;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryYearController extends AbstractFXController {

	@FXML
	Button backButton;

	@SuppressWarnings("rawtypes")
	@FXML
	private BarChart yearBarChart;
	@FXML
	private PieChart yearPieChart;

	@FXML
	private Hyperlink generalLink;
	@FXML
	private Hyperlink yearLink;
	@FXML
	private Label barlabel;
	@FXML
	private Label pielabel;

	private GeneralInfo generalInfo;

	@FXML
	private void initialize() {
		backButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				myScreensContainer
						.switchToScreen(StruggleHistoryRootController.STRUGGLEHISTORGENERALID);
			}
		});
		yearLink.setText("2007年");
		barlabel.setText("提交题目数2007年各月分布");
		pielabel.setText("2007年做题情况总体分析");
		generalInfo = GeneralInfo.getInstance();
		initBarChart();
		initPieChart();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initBarChart() {
		// yearBarChart.setTitle("Growth Curve");

		Map<Integer, Integer> solveMap = generalInfo
				.getSolvedProblemCountPerMonth(2007);
		Map<Integer, Integer> submitMap = generalInfo
				.getSubmittedProblemCountPerMonth(2007);

		XYChart.Series solveSeries = new XYChart.Series();
		solveSeries.setName("解决题目数");

		XYChart.Series submitSeries = new XYChart.Series();
		submitSeries.setName("提交题目数");

		for (int i = 0; i < 12; i++) {
			Integer a = solveMap.get(i);
			Integer b = submitMap.get(i);
			solveSeries.getData().add(
					new XYChart.Data(String.valueOf(i + 1), a == null ? 0 : a));
			submitSeries.getData().add(
					new XYChart.Data(String.valueOf(i + 1), b == null ? 0 : b));
		}
		LogUtil.d(submitSeries.getData());
		LogUtil.d(solveSeries.getData());

		yearBarChart.getData().addAll(submitSeries, solveSeries);
		// yearBarChart.setBarGap(100);
		yearBarChart.setAnimated(true);
		yearBarChart.setLegendSide(Side.RIGHT);
		yearBarChart.setHorizontalZeroLineVisible(true);
		yearBarChart.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				myScreensContainer
						.switchToScreen(StruggleHistoryRootController.STRUGGLEHISTORYMONTHID);
			}
		});
	}

	private void initPieChart() {
		// yearPieChart
		int submit = generalInfo.getSubmittedProblemCount(2007);
		int solved = generalInfo.getSolvedProblemCount(2007);
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(new PieChart.Data("解决题目数", solved),
						new PieChart.Data("提交题目数", submit));
		yearPieChart.setData(pieChartData);
		yearBarChart.setAnimated(true);
		yearPieChart.setLegendSide(Side.RIGHT);
	}
}
