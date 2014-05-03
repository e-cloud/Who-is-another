/**
 * 
 */
package com.wia.controller;

import java.util.Iterator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import com.wia.Context;
import com.wia.model.analysis.ACERecommend;

/**
 * @author Saint Scott
 * 
 */
public class AceRecommendController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private BarChart<?, ?> rcmdChart;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void update() {
		List<Pair<Integer, Integer>> pairs = new ACERecommend().recommand(
				Context.getInstance().getCurrentAuthor(), 10);
		XYChart.Series series = new XYChart.Series();
		series.setName("提交人次");
		for (Iterator<Pair<Integer, Integer>> iterator = pairs.iterator(); iterator
				.hasNext();) {
			Pair<Integer, Integer> pair = iterator.next();
			series.getData().add(
					new XYChart.Data<String, Integer>(pair.getKey() + "", pair
							.getValue()));
		}

		rcmdChart.getData().clear();
		rcmdChart.getData().add(series);
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
