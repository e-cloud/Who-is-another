/**
 * 
 */
package com.wia.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import jfxtras.labs.scene.control.BreadcrumbBar;
import jfxtras.labs.scene.control.BreadcrumbItem;
import jfxtras.labs.util.BreadcrumbBarEventHandler;

import com.wia.model.analysis.GeneralInfo;
import com.wia.model.analysis.Info;
import com.wia.model.data.Problem;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryDetailController extends AbstractFXController {

	@FXML
	private Parent rootLayout;
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private PieChart pieChart;
	@FXML
	private HBox hBox;
	@FXML
	private GridPane gridPane;

	private Button generalLink;
	private Button yearLink;
	private Button monthLink;
	private Button dayLink;

	private BreadcrumbBar breadcrumbBar;

	private GeneralInfo generalInfo;

	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		// TODO Auto-generated method stub
		Map<String, Object> map = (Map<String, Object>) myScreensContainer
				.getUserData();
		generalInfo = (GeneralInfo) map
				.get(StruggleHistoryRootController.GENERALINFO);

		showYearCharts();
	}

	@FXML
	private void initialize() {

		barChart.setLegendSide(Side.RIGHT);
		barChart.setHorizontalZeroLineVisible(true);

		pieChart.setLegendSide(Side.RIGHT);

		setupBreadcrumb();
	}

	private void setupBreadcrumb() {
		generalLink = new Button("概览");
		yearLink = new Button("year");
		monthLink = new Button("month");
		dayLink = new Button("day");

		generalLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				myScreensContainer
						.setScreen(StruggleHistoryRootController.ID_SH_GENERAL);
			}
		});

		yearLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				showYearCharts();
			}
		});

		monthLink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				showMonthCharts();
			}
		});

		breadcrumbBar = new BreadcrumbBar();
		breadcrumbBar.setHomeItem(breadcrumbBar.addItem("概览", generalLink));

		breadcrumbBar.getStylesheets().clear();
		breadcrumbBar.getStylesheets().add(
				getClass().getResource("/css/BreadCrumbBar.css")
						.toExternalForm());
		breadcrumbBar.getStylesheets().add(
				getClass().getResource("/css/BreadCrumbItem.css")
						.toExternalForm());

		breadcrumbBar
				.setOnItemAction(new BreadcrumbBarEventHandler<BreadcrumbItem>() {

					@Override
					public void handle(MouseEvent event) {
						// TODO Auto-generated method stub
						Node node = ((BreadcrumbItem) event.getSource())
								.getContent();
						if (!node.isDisable()) {
							node.fireEvent(new ActionEvent(node, node));

						}
					}
				});
		hBox.getChildren().add(breadcrumbBar);
	}

	@SuppressWarnings("unchecked")
	private void showYearCharts() {
		Map<String, Object> map = (Map<String, Object>) myScreensContainer
				.getUserData();
		int year = (int) map.get(StruggleHistoryRootController.YEAR);

		breadcrumbBar.removeItem(1);

		breadcrumbBar.addItem(year + "", yearLink);
		yearLink.setDisable(true);

		map.remove(StruggleHistoryRootController.MONTH);
		map.remove(StruggleHistoryRootController.DAY);

		barChart.setTitle(year + "年各月做题情况分析图");
		pieChart.setTitle(year + "年总体做题情况比例图");

		barChart.getXAxis().setLabel("月份");
		barChart.getYAxis().setLabel("题目数量");

		showYearBarChart(year);
		showYearPieChart(year);
	}

	@SuppressWarnings("unchecked")
	private void showMonthCharts() {
		Map<String, Object> map = (Map<String, Object>) myScreensContainer
				.getUserData();
		int year = (int) map.get(StruggleHistoryRootController.YEAR);
		int month = (int) map.get(StruggleHistoryRootController.MONTH);

		breadcrumbBar.removeItem(2);
		breadcrumbBar.addItem(month + "", monthLink);
		yearLink.setDisable(false);
		monthLink.setDisable(true);

		map.remove(StruggleHistoryRootController.DAY);

		barChart.setTitle(year + "年" + month + "月各天做题情况分析图");
		pieChart.setTitle(year + "年" + month + "月总体做题情况比例图");

		barChart.getXAxis().setLabel("该月各天");
		barChart.getYAxis().setLabel("题目数量");

		showMonthBarChart(year, month - 1);
		showMonthPieChart(year, month - 1);
	}

	@SuppressWarnings("unchecked")
	private void showDayCharts() {
		Map<String, Object> map = (Map<String, Object>) myScreensContainer
				.getUserData();
		int year = (int) map.get(StruggleHistoryRootController.YEAR);
		int month = (int) map.get(StruggleHistoryRootController.MONTH);
		int day = (int) map.get(StruggleHistoryRootController.DAY);

		breadcrumbBar.addItem(day + "", dayLink);
		monthLink.setDisable(false);
		dayLink.setDisable(true);

		barChart.setTitle(year + "年" + month + "月" + day + "日做题情况分析图");
		pieChart.setTitle("当天总体做题情况比例图");

		barChart.getXAxis().setLabel("题号");
		barChart.getYAxis().setLabel("提交次数");

		showDayBarChart(year, month - 1, day);
		showDayPieChart(year, month - 1, day);
	}

	@SuppressWarnings("unchecked")
	private void showYearBarChart(int year) {
		// yearBarChart.setTitle("Growth Curve");

		Map<Integer, Integer> solveMap = generalInfo.getProblemCountPerMonth(
				year, Info.SOLVE);
		Map<Integer, Integer> submitMap = generalInfo.getProblemCountPerMonth(
				year, Info.SUBMIT);

		XYChart.Series<String, Integer> solveSeries = new XYChart.Series<String, Integer>();
		XYChart.Series<String, Integer> submitSeries = new XYChart.Series<String, Integer>();

		solveSeries.setName("已解决");
		submitSeries.setName("已提交");

		for (int i = 0; i < 12; i++) {
			Integer a = solveMap.get(i);
			Integer b = submitMap.get(i);
			if (a == null && b == null) {
				continue;
			}
			solveSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(i + 1),
							a == null ? 0 : a));
			submitSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(i + 1),
							b == null ? 0 : b));
		}

		barChart.getData().clear();
		barChart.getData().addAll(solveSeries, submitSeries);

		for (Iterator<Data<String, Integer>> iterator = submitSeries.getData()
				.iterator(); iterator.hasNext();) {
			final XYChart.Data<String, Integer> data = iterator.next();
			Node node = data.getNode();
			node.addEventHandler(MouseEvent.MOUSE_CLICKED,
					new YearBarEventHandler(data));
		}

		for (Iterator<Data<String, Integer>> iterator = solveSeries.getData()
				.iterator(); iterator.hasNext();) {
			final XYChart.Data<String, Integer> data = iterator.next();
			Node node = data.getNode();
			node.addEventHandler(MouseEvent.MOUSE_CLICKED,
					new YearBarEventHandler(data));
		}

	}

	private void showYearPieChart(int year) {
		// yearPieChart
		int submit = generalInfo.getProblemCount(year, Info.SUBMIT);
		int solved = generalInfo.getProblemCount(year, Info.SOLVE);
		pieChart.getData().clear();
		pieChart.getData().addAll(new PieChart.Data("已解决：" + solved, solved),
				new PieChart.Data("未解决：" + (submit - solved), submit - solved));

	}

	@SuppressWarnings("unchecked")
	private void showMonthBarChart(int year, int month) {

		Map<Integer, Integer> solveMap = generalInfo.getProblemCountPerDay(
				year, month, Info.SOLVE);
		Map<Integer, Integer> submitMap = generalInfo.getProblemCountPerDay(
				year, month, Info.SUBMIT);

		XYChart.Series<String, Integer> solveSeries = new XYChart.Series<String, Integer>();
		XYChart.Series<String, Integer> submitSeries = new XYChart.Series<String, Integer>();

		solveSeries.setName("已解决");
		submitSeries.setName("已提交");

		int monthsize = Calendar.getInstance().getActualMaximum(
				Calendar.DAY_OF_MONTH);
		for (int i = 0; i < monthsize; i++) {
			Integer a = solveMap.get(i);
			Integer b = submitMap.get(i);
			if (a == null && b == null) {
				continue;
			}
			solveSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(i),
							a == null ? 0 : a));
			submitSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(i),
							b == null ? 0 : b));
		}

		barChart.getData().clear();
		barChart.getData().addAll(solveSeries, submitSeries);

		for (Iterator<Data<String, Integer>> iterator = submitSeries.getData()
				.iterator(); iterator.hasNext();) {
			final XYChart.Data<String, Integer> data = iterator.next();
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
					new MonthBarEventHandler(data));
		}

		for (Iterator<Data<String, Integer>> iterator = solveSeries.getData()
				.iterator(); iterator.hasNext();) {
			final XYChart.Data<String, Integer> data = iterator.next();
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
					new MonthBarEventHandler(data));
		}
	}

	private void showMonthPieChart(int year, int month) {
		int submit = generalInfo.getProblemCountPerMonth(year, Info.SUBMIT)
				.get(month);
		int solved = generalInfo.getProblemCountPerMonth(year, Info.SOLVE).get(
				month);
		pieChart.getData().clear();
		pieChart.getData().addAll(new PieChart.Data("已解决：" + solved, solved),
				new PieChart.Data("未解决：" + (submit - solved), submit - solved));
	}

	@SuppressWarnings("unchecked")
	private void showDayBarChart(int year, int month, int day) {
		// yearBarChart.setTitle("Growth Curve");

		Collection<Problem> problems = generalInfo.getProblemList(year, month,
				day);

		XYChart.Series<String, Integer> solveSeries = new XYChart.Series<String, Integer>();
		XYChart.Series<String, Integer> unsolveSeries = new XYChart.Series<String, Integer>();

		solveSeries.setName("已解决题目");
		unsolveSeries.setName("未解决题目");

		for (Iterator<Problem> iterator = problems.iterator(); iterator
				.hasNext();) {
			Problem problem = iterator.next();

			XYChart.Data<String, Integer> data = new XYChart.Data<String, Integer>(
					problem.getPid() + "", problem.getSubmitCount());

			if (problem.isSolved()) {
				solveSeries.getData().add(data);
			} else {
				unsolveSeries.getData().add(data);
			}
		}

		barChart.getData().clear();
		barChart.getData().addAll(solveSeries, unsolveSeries);

	}

	private void showDayPieChart(int year, int month, int day) {

		Collection<Problem> problems = generalInfo.getProblemList(year, month,
				day);

		int solvecount = 0;
		for (Iterator<Problem> iterator = problems.iterator(); iterator
				.hasNext();) {
			Problem problem = iterator.next();
			if (problem.isSolved()) {
				solvecount++;
			}
		}

		pieChart.getData().clear();
		pieChart.getData().addAll(
				new PieChart.Data("已解决：" + solvecount, solvecount),
				new PieChart.Data("未解决：" + (problems.size() - solvecount),
						problems.size() - solvecount));
	}

	class YearBarEventHandler implements EventHandler<MouseEvent> {
		private final XYChart.Data<String, Integer> data;

		public YearBarEventHandler(XYChart.Data<String, Integer> data) {
			// TODO Auto-generated constructor stub
			this.data = data;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			Map<String, Object> map = (Map<String, Object>) myScreensContainer
					.getUserData();
			map.put(StruggleHistoryRootController.MONTH,
					Integer.valueOf(data.getXValue()));
			map.remove(StruggleHistoryRootController.DAY);
			showMonthCharts();
		}
	}

	class MonthBarEventHandler implements EventHandler<MouseEvent> {
		private final XYChart.Data<String, Integer> data;

		public MonthBarEventHandler(XYChart.Data<String, Integer> data) {
			// TODO Auto-generated constructor stub
			this.data = data;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			Map<String, Object> map = (Map<String, Object>) myScreensContainer
					.getUserData();
			map.put(StruggleHistoryRootController.DAY,
					Integer.valueOf(data.getXValue()));
			showDayCharts();
		}
	}
}
