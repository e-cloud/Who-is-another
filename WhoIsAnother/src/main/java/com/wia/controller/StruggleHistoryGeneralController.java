/**
 * 
 */
package com.wia.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import com.wia.model.analysis.GeneralInfo;
import com.wia.model.analysis.Info;
import com.wia.model.data.Author;
import com.wia.util.ThreadLoaclDateFormatUtil;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryGeneralController extends AbstractFXController {

	@FXML
	private BarChart<String, Integer> generalBarChart;
	@FXML
	private AreaChart<String, Number> growthChart;
	@FXML
	private Label rankLabel;
	@FXML
	private Label submitLabel;
	@FXML
	private Label solveLabel;
	@FXML
	private Label submissionLabel;
	@FXML
	private Label acceptLabel;
	@FXML
	private Parent rootLayout;

	private GeneralInfo generalInfo;

	@FXML
	private void initialize() {
		generalBarChart.setTitle("各年度做题情况总览");
		// generalBarChart.boundsInLocalProperty();
		generalBarChart.setLegendSide(Side.RIGHT);
		generalBarChart.setHorizontalZeroLineVisible(true);
		generalBarChart.getXAxis().setLabel("年份");
		generalBarChart.getYAxis().setLabel("题目数量");

		growthChart.getXAxis().setLabel("做题日期");
		growthChart.getYAxis().setLabel("题目数量");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init() {

		Map<String, Object> map = (Map<String, Object>) myScreensContainer
				.getUserData();
		generalInfo = (GeneralInfo) map.get("generalinfo");
		Author author = (Author) map.get("user");

		rankLabel.setText(author.getRank() + "");
		submitLabel.setText(author.getSubmitted() + "");
		solveLabel.setText(author.getSolved() + "");
		submissionLabel.setText(author.getSubmissions() + "");
		acceptLabel.setText(author.getAccepted() + "");
		initBarChart();
		initAreaChart();
	}

	@SuppressWarnings({ "unchecked" })
	private void initBarChart() {
		// final NumberAxis xAxis = new NumberAxis(1, 30, 1);
		// final NumberAxis yAxis = new NumberAxis(-5, 27, 5);
		// xAxis.setForceZeroInRange(true);

		Map<Integer, Integer> solveMap = generalInfo
				.getProblemCountPerYear(Info.SOLVE);
		Map<Integer, Integer> submitMap = generalInfo
				.getProblemCountPerYear(Info.SUBMIT);

		final TreeSet<Integer> treeSet = new TreeSet<Integer>(solveMap.keySet());
		XYChart.Series<String, Integer> solveSeries = new XYChart.Series<String, Integer>();
		solveSeries.setName("已解决");
		for (Iterator<Integer> iterator = treeSet.iterator(); iterator
				.hasNext();) {
			Integer year = iterator.next();

			solveSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(year),
							solveMap.get(year)));
		}

		XYChart.Series<String, Integer> submitSeries = new XYChart.Series<String, Integer>();
		submitSeries.setName("已提交");
		for (Iterator<Integer> iterator = treeSet.iterator(); iterator
				.hasNext();) {
			Integer year = iterator.next();
			submitSeries.getData().add(
					new XYChart.Data<String, Integer>(String.valueOf(year),
							submitMap.get(year)));

		}

		generalBarChart.getData().addAll(submitSeries, solveSeries);

		for (Iterator<Data<String, Integer>> iterator = submitSeries.getData()
				.iterator(); iterator.hasNext();) {
			final XYChart.Data<String, Integer> data = iterator.next();
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
					new BarEventHandler(data));
		}

		for (Iterator<Data<String, Integer>> iterator = solveSeries.getData()
				.iterator(); iterator.hasNext();) {
			final XYChart.Data<String, Integer> data = iterator.next();
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
					new BarEventHandler(data));

		}

	}

	private void initAreaChart() {

		SeriesAddition addition = new SeriesAddition(growthChart, generalInfo);

		// add solve series
		addition.addSolveSeries();
		// add submit series
		addition.addSubmitSeries();
		// add accepted series
		addition.addAcceptedSeries();
		// add submission series
		addition.addSubmissionSeries();
	}

	private class SeriesAddition {

		private final GeneralInfo generalInfo;
		private final AreaChart<String, Number> targetChart;

		public SeriesAddition(AreaChart<String, Number> target,
				GeneralInfo generalInfo) {
			this.generalInfo = generalInfo;
			this.targetChart = target;
		}

		public void addSolveSeries() {
			List<Pair<Date, Integer>> solvelist = generalInfo
					.getPairList(GeneralInfo.SOLVE);
			int solvecount = 0;
			XYChart.Series<String, Number> solveseries = new XYChart.Series<>();
			solveseries.setName("已解决");
			for (Iterator<Pair<Date, Integer>> iterator = solvelist.iterator(); iterator
					.hasNext();) {
				Pair<Date, Integer> pair = iterator.next();
				solvecount += pair.getValue();
				solveseries.getData().add(
						new XYChart.Data<>(ThreadLoaclDateFormatUtil
								.formatSimpleDate(pair.getKey()), solvecount));
			}
			targetChart.getData().add(solveseries);
		}

		public void addSubmitSeries() {
			List<Pair<Date, Integer>> submitlist = generalInfo
					.getPairList(GeneralInfo.SUBMIT);
			int submitcount = 0;
			XYChart.Series<String, Number> submitseries = new XYChart.Series<>();
			submitseries.setName("已提交");
			for (Iterator<Pair<Date, Integer>> iterator = submitlist.iterator(); iterator
					.hasNext();) {
				Pair<Date, Integer> pair = iterator.next();
				submitcount += pair.getValue();
				submitseries.getData().add(
						new XYChart.Data<>(ThreadLoaclDateFormatUtil
								.formatSimpleDate(pair.getKey()), submitcount));
			}
			targetChart.getData().add(submitseries);
		}

		public void addAcceptedSeries() {
			List<Pair<Date, Integer>> acceptedlist = generalInfo
					.getPairList(GeneralInfo.ACCEPT);
			int acceptedcount = 0;
			XYChart.Series<String, Number> acceptedseries = new XYChart.Series<>();
			acceptedseries.setName("提交成功");
			for (Iterator<Pair<Date, Integer>> iterator = acceptedlist
					.iterator(); iterator.hasNext();) {
				Pair<Date, Integer> pair = iterator.next();
				acceptedcount += pair.getValue();
				acceptedseries
						.getData()
						.add(new XYChart.Data<>(ThreadLoaclDateFormatUtil
								.formatSimpleDate(pair.getKey()), acceptedcount));
			}
			targetChart.getData().add(acceptedseries);
		}

		public void addSubmissionSeries() {
			List<Pair<Date, Integer>> submissions = generalInfo
					.getPairList(GeneralInfo.SUBMISSION);
			int submissioncount = 0;
			XYChart.Series<String, Number> submisionseries = new XYChart.Series<>();
			submisionseries.setName("总提交");
			for (Iterator<Pair<Date, Integer>> iterator = submissions
					.iterator(); iterator.hasNext();) {
				Pair<Date, Integer> pair = iterator.next();
				submissioncount += pair.getValue();
				submisionseries.getData().add(
						new XYChart.Data<>(ThreadLoaclDateFormatUtil
								.formatSimpleDate(pair.getKey()),
								submissioncount));
			}
			targetChart.getData().add(submisionseries);
		}
	}

	private class BarEventHandler implements EventHandler<MouseEvent> {
		private final XYChart.Data<String, Integer> data;

		public BarEventHandler(XYChart.Data<String, Integer> data) {
			// TODO Auto-generated constructor stub
			this.data = data;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			Map<String, Object> map = (Map<String, Object>) myScreensContainer
					.getUserData();
			map.put(StruggleHistoryRootController.YEAR,
					Integer.valueOf(data.getXValue()));
			map.remove(StruggleHistoryRootController.MONTH);
			map.remove(StruggleHistoryRootController.DAY);
			myScreensContainer
					.setScreen(StruggleHistoryRootController.ID_SH_DETAIL);
		}
	}

}
