/**
 * 
 */
package com.wia.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import org.controlsfx.dialog.Dialogs;

import com.wia.Context;
import com.wia.model.analysis.GeneralInfo;
import com.wia.model.data.Author;
import com.wia.model.data.TypeCatalog;

/**
 * @author Saint Scott
 * 
 */
public class ComparatorController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private Button addCompetitor;
	@FXML
	private VBox vBox;

	private BarChart<String, Number> compareChart;

	private AreaChart<Number, Number> growthChart;

	private final Set<String> shownAuthors = new HashSet<>();

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		addCompetitor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Task<Author> task = new ImportAuthorTask(comboBox.getValue());
				Dialogs.create()
						.owner(rootLayout)
						.title("Progress Dialog")
						.masthead(
								"Downloading Author " + comboBox.getValue()
										+ "'s information!")
						.showWorkerProgress(task);
				task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

					@Override
					public void handle(WorkerStateEvent event) {
						// TODO Auto-generated method stub
						Context context = Context.getInstance();
						if (!context.containsKey("authors")) {
							context.addContextObject("authors",
									new HashMap<String, Author>());
						}
						Author author = (Author) event.getSource().getValue();
						if (author != null) {
							((Map<String, Author>) context
									.getContextObject("authors")).put(
									author.getAuthorID(), author);
							addCompetitor(author);
						} else {
							Platform.runLater(() -> {
								Dialogs.create().owner(rootLayout)
										.title("Warning Dialog")
										.masthead("Look, a Warning Dialog")
										.message("Sorry, No Such User!")
										.showWarning();
							});

						}
					}
				});
				new Thread(task).start();
			}
		});
		comboBox.setOnShowing(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Context context = Context.getInstance();
				if (context.containsKey("authors")) {
					comboBox.getItems().clear();
					comboBox.getItems().addAll(
							((Map<String, Author>) context
									.getContextObject("authors")).keySet());
				}
			}
		});
		comboBox.setPromptText("Author ID");
		comboBox.setTooltip(new Tooltip("请选择一个已有用户ID或键入新的用户ID进行添加"));
		initChart();
	}

	private void initChart() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("题目类型");
		yAxis.setLabel("做题数量");
		compareChart = new BarChart<String, Number>(xAxis, yAxis);
		compareChart.setTitle("分类比较");

		// scrollPane = new ScrollPane();
		// scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		// scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		// scrollPane.setMinHeight(350);
		// scrollPane.setContent(compareChart);

		NumberAxis cAxis = new NumberAxis();
		cAxis.setLabel("实际做题天数");
		NumberAxis nAxis = new NumberAxis();
		cAxis.setLabel("做题数量");
		growthChart = new AreaChart<Number, Number>(cAxis, nAxis);
		growthChart.setTitle("成长曲线比较");
		//
	}

	private void addCompetitor(Author author) {
		if (shownAuthors.contains(author.getAuthorID())) {
			return;
		}
		shownAuthors.add(author.getAuthorID());
		addAuthorToTypeChart(author);
		addAuthorToGrowthCurveChart(author);
	}

	private void addAuthorToTypeChart(Author author) {
		TypeCatalog catalog = TypeCatalog.getInstance();

		Map<String, Integer> solve = catalog.count(author.getProblemMap()
				.keySet());

		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName(author.getAuthorID());

		for (Iterator<String> iterator = solve.keySet().iterator(); iterator
				.hasNext();) {
			String type = iterator.next();
			series.getData().add(
					new Data<String, Number>(type, solve.get(type)));
		}

		for (Iterator<Data<String, Number>> iterator = series.getData()
				.iterator(); iterator.hasNext();) {
			final Data<String, Number> data = iterator.next();
			data.nodeProperty().addListener(new LabelListener(data));
		}
		compareChart.getData().add(series);
		if (!vBox.getChildren().contains(compareChart)) {
			vBox.getChildren().add(compareChart);
		}
	}

	private void addAuthorToGrowthCurveChart(Author author) {
		SeriesAddition addition = new SeriesAddition(growthChart,
				new GeneralInfo(author));
		addition.addSolveSeries();
		if (!vBox.getChildren().contains(growthChart)) {
			vBox.getChildren().add(growthChart);
		}
	}

	private class LabelListener implements ChangeListener<Node> {
		private final Data<String, Number> data;

		public LabelListener(Data<String, Number> data) {
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
	 * places a text label with a bar's value above a bar node for a given
	 * XYChart.Data
	 */
	private void displayLabelForData(XYChart.Data<String, Number> data) {
		final Node node = data.getNode();
		final Text dataText = new Text(data.getYValue() + "");
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
						+ bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2));
				dataText.setLayoutY(Math.round(bounds.getMinY()
						- dataText.prefHeight(-1) * 0.5));
			}
		});
	}

	private class SeriesAddition {

		private final GeneralInfo generalInfo;
		private final AreaChart<Number, Number> targetChart;

		public SeriesAddition(AreaChart<Number, Number> target,
				GeneralInfo generalInfo) {
			this.generalInfo = generalInfo;
			this.targetChart = target;
		}

		public void addSolveSeries() {
			List<Pair<Date, Integer>> solvelist = generalInfo
					.getPairList(GeneralInfo.SOLVE);
			int solvecount = 0;
			int index = 1;
			XYChart.Series<Number, Number> solveseries = new XYChart.Series<>();
			solveseries.setName("已解决题目数累计曲线");
			for (Iterator<Pair<Date, Integer>> iterator = solvelist.iterator(); iterator
					.hasNext();) {
				Pair<Date, Integer> pair = iterator.next();
				solvecount += pair.getValue();
				solveseries.getData().add(
						new XYChart.Data<>(index++, solvecount));
			}
			targetChart.getData().add(solveseries);
		}

		public void addSubmitSeries() {
			List<Pair<Date, Integer>> submitlist = generalInfo
					.getPairList(GeneralInfo.SUBMIT);
			int submitcount = 0;
			int index = 1;
			XYChart.Series<Number, Number> submitseries = new XYChart.Series<>();
			submitseries.setName("已提交题目数累计曲线");
			for (Iterator<Pair<Date, Integer>> iterator = submitlist.iterator(); iterator
					.hasNext();) {
				Pair<Date, Integer> pair = iterator.next();
				submitcount += pair.getValue();
				submitseries.getData().add(
						new XYChart.Data<>(index++, submitcount));
			}
			targetChart.getData().add(submitseries);
		}

		public void addAcceptedSeries() {
			List<Pair<Date, Integer>> acceptedlist = generalInfo
					.getPairList(GeneralInfo.ACCEPT);
			int acceptedcount = 0;
			int index = 1;
			XYChart.Series<Number, Number> acceptedseries = new XYChart.Series<>();
			acceptedseries.setName("提交成功次数累计曲线");
			for (Iterator<Pair<Date, Integer>> iterator = acceptedlist
					.iterator(); iterator.hasNext();) {
				Pair<Date, Integer> pair = iterator.next();
				acceptedcount += pair.getValue();
				acceptedseries.getData().add(
						new XYChart.Data<>(index++, acceptedcount));
			}
			targetChart.getData().add(acceptedseries);
		}

		public void addSubmissionSeries() {
			List<Pair<Date, Integer>> submissions = generalInfo
					.getPairList(GeneralInfo.SUBMISSION);
			int submissioncount = 0;
			int index = 1;
			XYChart.Series<Number, Number> submisionseries = new XYChart.Series<>();
			submisionseries.setName("提交次数累计曲线");
			for (Iterator<Pair<Date, Integer>> iterator = submissions
					.iterator(); iterator.hasNext();) {
				Pair<Date, Integer> pair = iterator.next();
				submissioncount += pair.getValue();
				submisionseries.getData().add(
						new XYChart.Data<>(index++, submissioncount));
			}
			targetChart.getData().add(submisionseries);
		}
	}

}
