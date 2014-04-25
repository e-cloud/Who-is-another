/**
 * 
 */
package com.wia.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import com.wia.Context;
import com.wia.model.analysis.GeneralInfo;
import com.wia.model.analysis.Info;
import com.wia.model.data.Author;
import com.wia.util.LogUtil;

/**
 * @author Saint Scott
 * 
 */
public class StruggleHistoryGeneralController extends AbstractFXController {

	@SuppressWarnings("rawtypes")
	@FXML
	private BarChart generalBarChart;

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

	private GeneralInfo generalInfo;

	@FXML
	private void initialize() {
		generalInfo = GeneralInfo.getInstance();
		Author author = Context.getInstance().getCurrentAuthor();
		rankLabel.setText(author.getRank() + "");
		submitLabel.setText(author.getSubmitted() + "");
		solveLabel.setText(author.getSolved() + "");
		submissionLabel.setText(author.getSubmissions() + "");
		acceptLabel.setText(author.getAccepted() + "");
		initChart();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initChart() {
		// final NumberAxis xAxis = new NumberAxis(1, 30, 1);
		// final NumberAxis yAxis = new NumberAxis(-5, 27, 5);
		// xAxis.setForceZeroInRange(true);

		generalBarChart.setTitle("Growth Curve");

		Map<Integer, Integer> solveMap = generalInfo
				.getProblemCountPerYear(Info.SOLVE);
		Map<Integer, Integer> submitMap = generalInfo
				.getProblemCountPerYear(Info.SUBMIT);

		final TreeSet<Integer> treeSet = new TreeSet<Integer>(solveMap.keySet());
		XYChart.Series solveSeries = new XYChart.Series();
		solveSeries.setName("解决题目数");
		for (Iterator iterator = treeSet.iterator(); iterator.hasNext();) {
			Integer year = (Integer) iterator.next();

			solveSeries.getData().add(
					new XYChart.Data(String.valueOf(year), solveMap.get(year)));
		}

		XYChart.Series submitSeries = new XYChart.Series();
		submitSeries.setName("提交题目数");
		for (Iterator iterator = treeSet.iterator(); iterator.hasNext();) {
			Integer year = (Integer) iterator.next();

			submitSeries.getData()
					.add(new XYChart.Data(String.valueOf(year), submitMap
							.get(year)));

		}
		generalBarChart.getData().addAll(submitSeries, solveSeries);

		for (Iterator iterator = submitSeries.getData().iterator(); iterator
				.hasNext();) {
			XYChart.Data data = (XYChart.Data) iterator.next();
			Node node = data.getNode();
			LogUtil.d(node);
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							LogUtil.d(e.getSource());
						}
					});

		}

		for (Iterator iterator = solveSeries.getData().iterator(); iterator
				.hasNext();) {
			XYChart.Data data = (XYChart.Data) iterator.next();

			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
					new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							LogUtil.d(e.getSource());
						}
					});

		}

		generalBarChart.setBarGap(100);
		generalBarChart.setAnimated(true);
		generalBarChart.setLegendSide(Side.RIGHT);
		generalBarChart.setHorizontalZeroLineVisible(true);
		// generalBarChart.onMouseClickedProperty().set(new
		// EventHandler<Event>() {
		//
		// @Override
		// public void handle(Event event) {
		// // TODO Auto-generated method stub
		// LogUtil.d(event.getSource() + "property");
		// }
		// });

		generalBarChart.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub

				myScreensContainer
						.switchToScreen(StruggleHistoryRootController.STRUGGLEHISTORYEARID);
			}
		});
	}
}
