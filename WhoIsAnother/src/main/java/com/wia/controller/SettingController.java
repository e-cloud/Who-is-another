/**
 * 
 */
package com.wia.controller;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Saint Scott
 * 
 */
public class SettingController extends AbstractFXController {
	private final static Logger logger = LoggerFactory
			.getLogger(SettingController.class);
	@FXML
	private Parent rootLayout;
	@FXML
	private Button refreshButton;
	@FXML
	private RadioButton radioOn;
	@FXML
	private RadioButton radioOFF;
	@FXML
	private ToggleGroup cacheGroup;
	@FXML
	private Button typeManageButton;

	@FXML
	private void initialize() {
		radioOn.setUserData(true);
		radioOFF.setUserData(false);
		cacheGroup.selectToggle(radioOn);
		cacheGroup.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {

					@Override
					public void changed(
							ObservableValue<? extends Toggle> observable,
							Toggle oldValue, Toggle newValue) {
						// TODO set the value observable.getvalue.getuserdata()

					}
				});
	}

	@FXML
	private void handleTypeManage(ActionEvent event) {
		logger.info("click on setting's type manage button");
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"/fxml/TypeManage.fxml"));
			Parent page = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("分类管理（不可编辑）");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(rootLayout.getScene().getWindow());

			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			dialogStage.showAndWait();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
