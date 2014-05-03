/**
 * 
 */
package com.wia.controller;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import com.wia.Context;
import com.wia.model.analysis.GeneralInfo;
import com.wia.model.analysis.Info;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryMonthController extends AbstractFXController {

	private static Logger logger = Logger
			.getLogger(StruggleHistoryMonthController.class.getName());
	@FXML
	private Parent rootLayout;
	@FXML
	Button backButton;

	@FXML
	private BarChart<?, ?> monthBarChart;
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

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public Parent getLayout() {
		// TODO Auto-generated method stub
		return rootLayout;
	}

	@FXML
	private void initialize() {
		backButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				myScreensContainer
						.setScreen(StruggleHistoryRootController.STRUGGLEHISTORYEARID);
			}
		});

	}

	@Override
	public void update() {
		int year = Integer.valueOf((String) Context.getInstance()
				.getContextObject("year"));
		int month = Integer.valueOf((String) Context.getInstance()
				.getContextObject("month"));
		yearLink.setText(year + "年");
		monthLink.setText(month + "月");
		barlabel.setText("提交题目数" + year + "年" + month + "月各天分布");
		pielabel.setText(year + "年" + month + "月做题情况总体分析");
		generalInfo = GeneralInfo.getInstance();
		initBarChart(year, month - 1);
		initPieChart(year, month - 1);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initBarChart(int year, int month) {

		Map<Integer, Integer> solveMap = generalInfo.getProblemCountPerDay(
				year, month, Info.SOLVE);
		Map<Integer, Integer> submitMap = generalInfo.getProblemCountPerDay(
				year, month, Info.SUBMIT);

		XYChart.Series solveSeries = new XYChart.Series();
		solveSeries.setName("解决题目数");

		XYChart.Series submitSeries = new XYChart.Series();
		submitSeries.setName("提交题目数");

		Calendar calendar = Calendar.getInstance();
		int monthsize = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < monthsize; i++) {
			Integer a = solveMap.get(i);
			Integer b = submitMap.get(i);
			if (a == null & b == null) {
				continue;
			}
			solveSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(i + 1),
							a == null ? 0 : a));
			submitSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(i + 1),
							b == null ? 0 : b));
		}

		monthBarChart.getData().clear();
		monthBarChart.getData().addAll(submitSeries, solveSeries);

		for (Iterator iterator = submitSeries.getData().iterator(); iterator
				.hasNext();) {
			final XYChart.Data data = (XYChart.Data) iterator.next();
			data.getNode().addEventHandler(
					MouseEvent.MOUSE_CLICKED,
					new BarEventHandler(data,
							StruggleHistoryRootController.STRUGGLEHISTORYDAYID,
							myScreensContainer, "day"));
		}

		for (Iterator iterator = solveSeries.getData().iterator(); iterator
				.hasNext();) {
			final XYChart.Data data = (XYChart.Data) iterator.next();
			data.getNode().addEventHandler(
					MouseEvent.MOUSE_CLICKED,
					new BarEventHandler(data,
							StruggleHistoryRootController.STRUGGLEHISTORYDAYID,
							myScreensContainer, "day"));
		}

		monthBarChart.setBarGap(5);
		monthBarChart.setAnimated(true);
		monthBarChart.setLegendSide(Side.RIGHT);
		monthBarChart.setHorizontalZeroLineVisible(true);
	}

	private void initPieChart(int year, int month) {
		int submit = generalInfo.getProblemCountPerMonth(year, Info.SUBMIT)
				.get(month);
		int solved = generalInfo.getProblemCountPerMonth(year, Info.SOLVE).get(
				month);
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(new PieChart.Data("Solved:" + solved,
						solved), new PieChart.Data("UnSolved:"
						+ (submit - solved), submit - solved));
		monthPieChart.setData(pieChartData);
		monthBarChart.setAnimated(true);
		monthPieChart.setLegendSide(Side.RIGHT);
	}

}
