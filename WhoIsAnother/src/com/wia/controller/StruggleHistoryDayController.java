/**
 * 
 */
package com.wia.controller;

import java.util.Collection;
import java.util.Iterator;

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

import com.wia.Context;
import com.wia.model.analysis.GeneralInfo;
import com.wia.model.data.Problem;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryDayController extends AbstractFXController {

	@FXML
	Button backButton;
	@FXML
	private Parent rootLayout;
	@FXML
	private Hyperlink generalLink;
	@FXML
	private Hyperlink yearLink;
	@FXML
	private Hyperlink monthLink;
	@FXML
	private Label dayLabel;
	@FXML
	private Label barlabel;
	@FXML
	private Label pielabel;
	@FXML
	private BarChart<?, ?> dayBarChart;
	@FXML
	private PieChart dayPieChart;

	private GeneralInfo generalInfo;
	private Collection<Problem> problems;

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
						.setScreen(StruggleHistoryRootController.STRUGGLEHISTORYMONTHID);
			}
		});

	}

	@Override
	public void update() {
		int year = Integer.valueOf((String) Context.getInstance()
				.getContextObject("year"));
		int month = Integer.valueOf((String) Context.getInstance()
				.getContextObject("month"));
		int day = Integer.valueOf((String) Context.getInstance()
				.getContextObject("day"));
		yearLink.setText(year + "年");
		monthLink.setText(month + "月");
		dayLabel.setText(day + "日");
		barlabel.setText(year + "年" + month + "月" + day + "日题目提交详细情况");
		pielabel.setText(year + "年" + month + "月" + day + "日做题情况总体分析");
		generalInfo = GeneralInfo.getInstance();
		initBarChart(year, month - 1, day - 1);
		initPieChart(year, month - 1, day - 1);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initBarChart(int year, int month, int day) {
		// yearBarChart.setTitle("Growth Curve");

		problems = generalInfo.getProblemList(year, month, day);

		XYChart.Series solveSeries = new XYChart.Series();
		solveSeries.setName("解决题目");

		XYChart.Series unSolveSeries = new XYChart.Series();
		unSolveSeries.setName("未解决题目");

		for (Iterator iterator = problems.iterator(); iterator.hasNext();) {
			Problem problem = (Problem) iterator.next();
			if (problem.isSolved()) {
				solveSeries.getData().add(
						new XYChart.Data(problem.getPid() + "", problem
								.getSubmitCount()));
			} else {
				unSolveSeries.getData().add(
						new XYChart.Data(problem.getPid() + "", problem
								.getSubmitCount()));
			}
		}

		dayBarChart.getData().clear();
		if (solveSeries.getData().size() > 0) {
			dayBarChart.getData().add(solveSeries);
		}
		if (unSolveSeries.getData().size() > 0) {
			dayBarChart.getData().add(unSolveSeries);
		}

		dayBarChart.setBarGap(1.0);
		dayBarChart.setAnimated(true);
		dayBarChart.setLegendSide(Side.RIGHT);
		dayBarChart.setHorizontalZeroLineVisible(true);

	}

	private void initPieChart(int year, int month, int day) {
		int solvecount = 0;
		for (Iterator<Problem> iterator = problems.iterator(); iterator
				.hasNext();) {
			Problem problem = iterator.next();
			if (problem.isSolved()) {
				solvecount++;
			}
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(
						new PieChart.Data("Solved-" + solvecount, solvecount),
						new PieChart.Data("UnSolved-"
								+ (problems.size() - solvecount), problems
								.size() - solvecount));

		dayPieChart.setData(pieChartData);
		dayPieChart.setAnimated(true);
		dayPieChart.setLegendSide(Side.RIGHT);
	}
}
