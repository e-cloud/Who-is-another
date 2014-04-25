/**
 * 
 */
package com.wia.controller;

import java.util.Calendar;
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
import com.wia.model.analysis.Info;
import com.wia.util.LogUtil;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryMonthController extends AbstractFXController {

	@FXML
	Button backButton;

	@SuppressWarnings("rawtypes")
	@FXML
	private BarChart monthBarChart;
	@FXML
	private PieChart monthPieChart;

	@FXML
	private Hyperlink generalLink;
	@FXML
	private Hyperlink yearLink;
	@FXML
	private Hyperlink monthLink;
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
						.switchToScreen(StruggleHistoryRootController.STRUGGLEHISTORYEARID);
			}
		});
		yearLink.setText("2007年");
		monthLink.setText("3月");
		barlabel.setText("提交题目数2007年3月各天分布");
		pielabel.setText("2007年3月做题情况总体分析");
		generalInfo = GeneralInfo.getInstance();
		initBarChart();
		initPieChart();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initBarChart() {
		// yearBarChart.setTitle("Growth Curve");

		Map<Integer, Integer> solveMap = generalInfo.getProblemCountPerDay(
				2007, 2, Info.SOLVE);
		Map<Integer, Integer> submitMap = generalInfo.getProblemCountPerDay(
				2007, 2, Info.SUBMIT);

		XYChart.Series solveSeries = new XYChart.Series();
		solveSeries.setName("解决题目数");

		XYChart.Series submitSeries = new XYChart.Series();
		submitSeries.setName("提交题目数");

		Calendar calendar = Calendar.getInstance();
		int monthsize = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < monthsize; i++) {
			Integer a = solveMap.get(i);
			Integer b = submitMap.get(i);
			solveSeries.getData().add(
					new XYChart.Data(String.valueOf(i + 1), a == null ? 0 : a));
			submitSeries.getData().add(
					new XYChart.Data(String.valueOf(i + 1), b == null ? 0 : b));
		}
		LogUtil.d(submitSeries.getData());
		LogUtil.d(solveSeries.getData());

		monthBarChart.getData().addAll(submitSeries, solveSeries);
		monthBarChart.setBarGap(1.0);
		monthBarChart.setAnimated(true);
		monthBarChart.setLegendSide(Side.RIGHT);
		monthBarChart.setHorizontalZeroLineVisible(true);
		monthBarChart.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				myScreensContainer
						.switchToScreen(StruggleHistoryRootController.STRUGGLEHISTORYDAYID);
			}
		});
	}

	private void initPieChart() {
		// yearPieChart
		int submit = generalInfo.getProblemCountPerMonth(2007, Info.SUBMIT)
				.get(2);
		int solved = generalInfo.getProblemCountPerMonth(2007, Info.SOLVE).get(
				2);
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(new PieChart.Data("解决题目数", solved),
						new PieChart.Data("提交题目数", submit));
		monthPieChart.setData(pieChartData);
		monthBarChart.setAnimated(true);
		monthPieChart.setLegendSide(Side.RIGHT);
	}
}
