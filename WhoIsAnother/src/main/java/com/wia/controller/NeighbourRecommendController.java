/**
 * 
 */
package com.wia.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.controlsfx.dialog.Dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wia.model.analysis.NeighbourRecommend;
import com.wia.model.data.Author;
import com.wia.model.data.TypeCatalog;

/**
 * @author Saint Scott
 * 
 */
public class NeighbourRecommendController extends AbstractFXController {
	private final static Logger logger = LoggerFactory
			.getLogger(NeighbourRecommendController.class);
	@FXML
	private Parent rootLayout;
	@FXML
	private GridPane gridPane;

	@Override
	public void init() {
		Author author = (Author) myScreensContainer.getUserData();

		logger.info("show {}'s neighbour recommend");
		Task<List<Integer>> task = new AnalysizeTask(author);

		Dialogs.create()
				.title("Progress Dialog")
				.masthead(
						"Downloading Author " + author.getAuthorID()
								+ "'s neighbours' information!")
				.showWorkerProgress(task);

		new Thread(task).start();
	}

	private void initChart(List<Integer> nblist) {
		Map<String, List<Integer>> nbrcmdmap = TypeCatalog.getInstance()
				.classify(nblist);

		int rowIndex = 0;

		for (Iterator<String> iterator = nbrcmdmap.keySet().iterator(); iterator
				.hasNext();) {
			String type = iterator.next();
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(
						"/fxml/FlowPid.fxml"));
				VBox vBox = loader.load();
				FlowPane flowPane = ((FlowPidController) loader.getController())
						.init(type);

				for (Iterator<Integer> iterator2 = nbrcmdmap.get(type)
						.iterator(); iterator2.hasNext();) {
					Integer integer = iterator2.next();
					flowPane.getChildren().add(new Label(integer.toString()));
				}
				gridPane.add(vBox, 0, rowIndex++);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class AnalysizeTask extends Task<List<Integer>> {

		private final Author author;

		public AnalysizeTask(Author author) {
			this.author = author;
		}

		@Override
		public List<Integer> call() throws Exception {
			// TODO Auto-generated method stub
			NeighbourRecommend neighbourRecommend = new NeighbourRecommend(
					author);
			return neighbourRecommend.recommend();
		}

		@Override
		protected void succeeded() {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			try {
				List<Integer> nblist = this.get();
				initChart(nblist);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
