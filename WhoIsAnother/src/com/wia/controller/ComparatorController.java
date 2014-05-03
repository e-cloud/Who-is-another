/**
 * 
 */
package com.wia.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.data.TypeCatalog;
import com.wia.model.preprocess.DataPreprocessor;

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

	private ScrollPane scrollPane;

	@FXML
	private void initialize() {
		compareButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Context.getInstance().addContextObject("competitor",
						compareField.getText());
				myScreensContainer.setScreen(RootStageController.COMPARATORID);
			}
		});

		final CategoryAxis yAxis = new CategoryAxis();
		final NumberAxis xAxis = new NumberAxis();
		compareChart = new BarChart<Number, String>(xAxis, yAxis);
		compareChart.setTitle("你还没输入比较ID");

		scrollPane = new ScrollPane();
		scrollPane.setContent(compareChart);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setFitToWidth(true);
		rootLayout.add(scrollPane, 0, 1);
		compareField.setText("wdp515105");
	}

	@Override
	public void update() {
		Context context = Context.getInstance();
		if (context.containsKey("competitor")) {

			final CategoryAxis yAxis = new CategoryAxis();
			final NumberAxis xAxis = new NumberAxis();
			compareChart = new BarChart<Number, String>(xAxis, yAxis);
			scrollPane.setContent(compareChart);

			Author currentAuthor = context.getCurrentAuthor();
			ExecutorService exec = Executors.newFixedThreadPool(1);
			Future<Author> future = exec.submit(new RefreshChartTask());
			Author competitor = null;
			try {
				competitor = future.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			compareChart.setTitle("对比状况");
			XYChart.Series<Number, String> series1 = new XYChart.Series<Number, String>();
			XYChart.Series<Number, String> series2 = new XYChart.Series<Number, String>();

			series1.setName("你");
			series2.setName(competitor.getAuthorName());

			// series1.getData().add(
			// new Data<Number, String>(currentAuthor.getRank(), "Rank"));
			series1.getData().add(
					new Data<Number, String>(currentAuthor.getSubmitted(),
							"ProblemSubmitted"));
			series1.getData().add(
					new Data<Number, String>(currentAuthor.getSolved(),
							"ProblemSolved"));
			series1.getData().add(
					new Data<Number, String>(currentAuthor.getSubmissions(),
							"Submissions"));
			series1.getData().add(
					new Data<Number, String>(currentAuthor.getAccepted(),
							"Accepted"));

			series2.getData().add(
					new Data<Number, String>(competitor.getSubmitted(),
							"ProblemSubmitted"));
			series2.getData().add(
					new Data<Number, String>(competitor.getSolved(),
							"ProblemSolved"));
			series2.getData().add(
					new Data<Number, String>(competitor.getSubmissions(),
							"Submissions"));
			series2.getData().add(
					new Data<Number, String>(competitor.getAccepted(),
							"Accepted"));

			TypeCatalog catalog = TypeCatalog.getInstance();

			Map<String, Integer> solve = catalog.count(currentAuthor
					.getProblemMap().keySet());
			Map<String, Integer> submit = catalog.count(competitor
					.getProblemMap().keySet());

			for (Iterator<String> iterator = solve.keySet().iterator(); iterator
					.hasNext();) {
				String type = iterator.next();
				series1.getData().add(
						new Data<Number, String>(solve.get(type), type));
			}

			for (Iterator<String> iterator = submit.keySet().iterator(); iterator
					.hasNext();) {
				String type = iterator.next();
				series2.getData().add(
						new Data<Number, String>(submit.get(type), type));
			}

			for (Iterator<Data<Number, String>> iterator = series1.getData()
					.iterator(); iterator.hasNext();) {
				final Data<Number, String> data = iterator.next();
				data.nodeProperty().addListener(new LabelListener(data));
			}
			for (Iterator<Data<Number, String>> iterator = series2.getData()
					.iterator(); iterator.hasNext();) {
				final Data<Number, String> data = iterator.next();
				data.nodeProperty().addListener(new LabelListener(data));
			}

			compareChart.getData().clear();
			compareChart.getData().add(series1);
			compareChart.getData().add(series2);
			int size = solve.size() >= submit.size() ? solve.size() : submit
					.size();
			compareChart.setMinHeight((size + 4) * 80);
		}
	}

	private class LabelListener implements ChangeListener<Node> {
		private final Data<Number, String> data;

		public LabelListener(Data<Number, String> data) {
			this.data = data;
		}

		@Override
		public void changed(ObservableValue<? extends Node> ov, Node oldNode,
				final Node node) {
			if (node != null) {
				// setNodeStyle(data);
				displayLabelForData(data);
			}
		}
	}

	/**
	 * Change color of bar if value of i is <5 then red, if >5 then green if i>8
	 * then blue
	 */
	@SuppressWarnings("unused")
	private void setNodeStyle(XYChart.Data<Number, String> data) {
		Node node = data.getNode();
		if (data.getXValue().intValue() > 8) {
			node.setStyle("-fx-bar-fill: -fx-exceeded;");
		} else if (data.getXValue().intValue() > 5) {
			node.setStyle("-fx-bar-fill: -fx-achieved;");
		} else {
			node.setStyle("-fx-bar-fill: -fx-not-achieved;");
		}
	}

	/**
	 * places a text label with a bar's value above a bar node for a given
	 * XYChart.Data
	 */
	private void displayLabelForData(XYChart.Data<Number, String> data) {
		final Node node = data.getNode();
		final Text dataText = new Text(data.getXValue() + "");
		node.parentProperty().addListener(new ChangeListener<Parent>() {
			@Override
			public void changed(ObservableValue<? extends Parent> ov,
					Parent oldParent, Parent parent) {
				Group parentGroup = (Group) parent;
				parentGroup.getChildren().add(dataText);
			}
		});

		node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov,
					Bounds oldBounds, Bounds bounds) {
				dataText.setLayoutX(Math.round(bounds.getMinX()
						+ bounds.getWidth() + dataText.prefWidth(-1) / 4));
				dataText.setLayoutY(Math.round(bounds.getMinY()
						+ dataText.prefHeight(-1)));
			}
		});
	}

	public class RefreshChartTask implements Callable<Author> {

		@Override
		public Author call() throws Exception {
			// TODO Auto-generated method stub
			return new DataPreprocessor()
					.retrieveAuthorFromNet((String) Context.getInstance()
							.getContextObject("competitor"));
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
