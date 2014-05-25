/**
 * 
 */
package com.wia.controller;

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
public class StruggleHistoryYearController extends AbstractFXController {

	private static Logger logger = Logger
			.getLogger(StruggleHistoryYearController.class.getName());
	@FXML
	private Parent rootLayout;

	@FXML
	private BarChart<?, ?> yearBarChart;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@FXML
	private void initialize() {
		generalLink.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				myScreensContainer
						.setScreen(StruggleHistoryRootController.ID_SH_GENERAL);
			}
		});
		// yearBarChart.setAnimated(true);
		yearBarChart.setLegendSide(Side.RIGHT);
		yearBarChart.setHorizontalZeroLineVisible(true);
	}

	@Override
	public void update() {
		generalInfo = GeneralInfo.getInstance();
		int year = Integer.valueOf((String) Context.getInstance()
				.getContextObject("year"));
		yearLink.setText(year + "年");
		barlabel.setText("提交题目数" + year + "年各月分布");
		pielabel.setText(year + "年做题情况总体分析");

		initBarChart(year);
		initPieChart(year);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initBarChart(int year) {
		// yearBarChart.setTitle("Growth Curve");

		Map<Integer, Integer> solveMap = generalInfo.getProblemCountPerMonth(
				year, Info.SOLVE);
		Map<Integer, Integer> submitMap = generalInfo.getProblemCountPerMonth(
				year, Info.SUBMIT);

		XYChart.Series solveSeries = new XYChart.Series<>();
		solveSeries.setName("解决题目数");

		XYChart.Series submitSeries = new XYChart.Series<>();
		submitSeries.setName("提交题目数");

		for (int i = 0; i < 12; i++) {
			Integer a = solveMap.get(i);
			Integer b = submitMap.get(i);
			if (a == null & b == null) {
				continue;
			}
			solveSeries.getData().add(
					new XYChart.Data(String.valueOf(i + 1), a == null ? 0 : a));
			submitSeries.getData().add(
					new XYChart.Data(String.valueOf(i + 1), b == null ? 0 : b));
		}

		yearBarChart.getData().clear();
		yearBarChart.getData().addAll(submitSeries, solveSeries);

		for (Iterator<?> iterator = submitSeries.getData().iterator(); iterator
				.hasNext();) {
			final XYChart.Data data = (XYChart.Data) iterator.next();
			data.getNode().addEventHandler(
					MouseEvent.MOUSE_CLICKED,
					new BarEventHandler(data,
							StruggleHistoryRootController.ID_SH_MONTH,
							myScreensContainer, "month"));
		}

		for (Iterator<?> iterator = solveSeries.getData().iterator(); iterator
				.hasNext();) {
			final XYChart.Data data = (XYChart.Data) iterator.next();
			data.getNode().addEventHandler(
					MouseEvent.MOUSE_CLICKED,
					new BarEventHandler(data,
							StruggleHistoryRootController.ID_SH_MONTH,
							myScreensContainer, "month"));
		}

	}

	private void initPieChart(int year) {
		// yearPieChart
		int submit = generalInfo.getProblemCount(year, Info.SUBMIT);
		int solved = generalInfo.getProblemCount(year, Info.SOLVE);
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(new PieChart.Data("Solved:" + solved,
						solved), new PieChart.Data("UnSolved:"
						+ (submit - solved), submit - solved));
		yearPieChart.setData(pieChartData);
		yearBarChart.setAnimated(true);
		yearPieChart.setLegendSide(Side.RIGHT);
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