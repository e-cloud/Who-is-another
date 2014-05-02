/**
 * 
 */
package com.wia.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import com.wia.Context;
import com.wia.model.data.Author;

/**
 * @author Saint Scott
 * 
 */
public class ComparatorController extends AbstractFXController {
	@FXML
	private GridPane rootLayout;
	@FXML
	private TextField compareField;
	@FXML
	private Button compareButton;

	private BarChart<Number, String> compareChart;

	@FXML
	private void initialize() {
		compareButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Context.getInstance().addContextObject("competitor",
						new Author(compareField.getText()));
				myScreensContainer.setScreen(RootStageController.COMPARATORID);
			}
		});

		final CategoryAxis yAxis = new CategoryAxis();

		final NumberAxis xAxis = new NumberAxis();

		compareChart = new BarChart<Number, String>(xAxis, yAxis);

		// setup chart

		compareChart.setTitle("你还没输入比较ID");

		yAxis.setLabel("Year");

		// yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(years)));

		xAxis.setLabel("Price");

		// add starting data

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(compareChart);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setFitToWidth(true);
		rootLayout.add(scrollPane, 0, 1);
	}

	@Override
	public void update() {

		Context context = Context.getInstance();
		if (context.containsKey("competitor")) {
			compareChart.setTitle("阿三噶电话嘎嘎德国");
			final String[] years = { "2007", "2008", "2009" };
			XYChart.Series<Number, String> series1 = new XYChart.Series<Number, String>();

			series1.setName("Data Series 1");

			XYChart.Series<Number, String> series2 = new XYChart.Series<Number, String>();

			series2.setName("Data Series 2");

			XYChart.Series<Number, String> series3 = new XYChart.Series<Number, String>();

			series3.setName("Data Series 3");

			compareChart.getData().clear();
			compareChart.getData().add(series1);

			compareChart.getData().add(series2);

			compareChart.getData().add(series3);
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public Parent getLayout() {
		// TODO Auto-generated method stub
		return rootLayout;
	}

}
