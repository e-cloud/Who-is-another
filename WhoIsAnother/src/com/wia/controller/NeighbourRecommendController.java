/**
 * 
 */
package com.wia.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import com.wia.model.analysis.Recommend;
import com.wia.model.data.TypeCatalog;

/**
 * @author Saint Scott
 * 
 */
public class NeighbourRecommendController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private GridPane gridPane;

	@Override
	public void update() {
		Recommend recommend = new Recommend();
		List<Integer> nblist = recommend.neighborRecommend();
		Map<String, List<Integer>> nbrcmdmap = TypeCatalog.getInstance()
				.classify(nblist);

		int rowIndex = 1;
		for (Iterator<String> iterator = nbrcmdmap.keySet().iterator(); iterator
				.hasNext();) {
			String type = iterator.next();
			VBox vBox = new VBox();
			vBox.getChildren().add(new Label(type));
			FlowPane flowPane = new FlowPane(20, 10);
			for (Iterator<Integer> iterator2 = nbrcmdmap.get(type).iterator(); iterator2
					.hasNext();) {
				Integer integer = iterator2.next();
				flowPane.getChildren().add(new Label(integer.toString()));
			}
			vBox.getChildren().add(flowPane);
			gridPane.add(vBox, 0, rowIndex++);
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
