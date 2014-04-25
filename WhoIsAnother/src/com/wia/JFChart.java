/**
 * 
 */
package com.wia;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Saint Scott
 * 
 */
public class JFChart extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("java-buddy.blogspot.com");
		Group root = new Group();

		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		xAxis.setLabel("Month");
		yAxis.setLabel("Value");

		final AreaChart<String, Number> areaChart = new AreaChart<>(xAxis,
				yAxis);

		areaChart.setTitle("AreaChart");

		// Series 1
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("XYChart.Series 1");

		series1.getData().add(new XYChart.Data("January", 100));
		series1.getData().add(new XYChart.Data("February", 200));
		series1.getData().add(new XYChart.Data("March", 50));
		series1.getData().add(new XYChart.Data("April", 75));
		series1.getData().add(new XYChart.Data("May", 110));
		series1.getData().add(new XYChart.Data("June", 300));
		series1.getData().add(new XYChart.Data("July", 111));
		series1.getData().add(new XYChart.Data("August", 30));
		series1.getData().add(new XYChart.Data("September", 75));
		series1.getData().add(new XYChart.Data("October", 55));
		series1.getData().add(new XYChart.Data("November", 225));
		series1.getData().add(new XYChart.Data("December", 99));

		// Series 2
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("XYChart.Series 2");

		series2.getData().add(new XYChart.Data("January", 150));
		series2.getData().add(new XYChart.Data("February", 100));
		series2.getData().add(new XYChart.Data("March", 60));
		series2.getData().add(new XYChart.Data("April", 40));
		series2.getData().add(new XYChart.Data("May", 30));
		series2.getData().add(new XYChart.Data("June", 100));
		series2.getData().add(new XYChart.Data("July", 100));
		series2.getData().add(new XYChart.Data("August", 10));
		series2.getData().add(new XYChart.Data("September", 175));
		series2.getData().add(new XYChart.Data("October", 155));
		series2.getData().add(new XYChart.Data("November", 125));
		series2.getData().add(new XYChart.Data("December", 150));

		areaChart.getData().addAll(series1, series2);

		setOnMouseEventsOnSeries(series1.getNode(), areaChart,
				"Series 1 clicked");
		setOnMouseEventsOnSeries(series2.getNode(), areaChart,
				"Series 2 clicked");

		root.getChildren().addAll(areaChart);
		primaryStage.setScene(new Scene(root, 500, 400));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void setOnMouseEventsOnSeries(Node node, final AreaChart chart,
			final String label) {

		node.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				chart.setTitle(label);
			}
		});

	}

}
