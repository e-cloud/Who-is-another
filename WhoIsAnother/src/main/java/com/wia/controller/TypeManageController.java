package com.wia.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.controlsfx.dialog.Dialogs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.wia.Context;
import com.wia.model.data.TypeCatalog;

public class TypeManageController {

	@FXML
	private Parent rootLayout;
	@FXML
	private Button importButton;
	@FXML
	private Button exportButton;
	@FXML
	private ToggleButton editButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button quitButton;
	@FXML
	private Button restoreButton;
	@FXML
	private TextArea catalog;

	@FXML
	private void initialize() {

		catalog.setWrapText(true);
		catalog.setEditable(false);

		editButton.selectedProperty().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						// TODO Auto-generated method stub
						catalog.setEditable(observable.getValue());
						Stage parentStage = (Stage) rootLayout.getScene()
								.getWindow();
						if (!catalog.isEditable()) {
							parentStage.setTitle("分类管理（不可编辑）");
						} else {
							parentStage.setTitle("分类管理（可编辑）");
						}
					}
				});

		setupTextArea();
	}

	@FXML
	private void handleImport(ActionEvent event) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().add(
				new ExtensionFilter("JSON", "*.json"));
		fileChooser.setInitialDirectory(new File("./"));
		final File file = fileChooser.showOpenDialog(rootLayout.getScene()
				.getWindow());
		try {
			if (file != null && file.exists()) {
				FileUtils.copyFileToDirectory(file, new File("catalog"));
			}
			// TODO change configuration and refresh
			Context.getInstance().setConfigProperty("UserCatalog",
					"catalog/" + file.getName());
			setupTextArea();
			TypeCatalog.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void handleExport(ActionEvent event) {
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		final File selectedDirectory = directoryChooser.showDialog(rootLayout
				.getScene().getWindow());

		if (selectedDirectory != null) {
			selectedDirectory.getAbsolutePath();
			Context context = Context.getInstance();
			String catalogAddress = (String) context
					.getConfigProperty("UserCatalog");
			try {
				if (catalogAddress == null) {
					File exportFile = new File("UserCatalog.json");

					FileUtils
							.copyInputStreamToFile(
									getClass()
											.getResourceAsStream(
													(String) context
															.getConfigProperty("DefaultCatalog")),
									exportFile);
					FileUtils
							.copyFileToDirectory(exportFile, selectedDirectory);

				} else {
					File exportFile = new File(catalogAddress);
					FileUtils
							.copyFileToDirectory(exportFile, selectedDirectory);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param event
	 */
	@FXML
	private void handleSave(ActionEvent event) {
		String data = catalog.getText();

		ObjectMapper mapper = new ObjectMapper();
		ObjectMapper mapper2 = new ObjectMapper();
		try {
			JsonNode test = mapper.readTree(data); // 要测试的数据

			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(
							"/type-schema.json")));

			JsonNode sc = mapper2.readTree(fileReader);

			final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

			final JsonSchema schema = factory.getJsonSchema(sc); // 把规则转为schema

			ProcessingReport report; // 测试信息报告

			report = schema.validate(test);
			if (report.isSuccess()) { // 通过4位数测试
				catalog.setUserData(data);
				FileUtils.writeStringToFile(
						new File("catalog/UserCatalog.json"), data, "utf-8");
				editButton.selectedProperty().set(false);

				// TODO change the context configuration
				Context.getInstance().setConfigProperty("UserCatalog",
						"catalog/UserCatalog.json");
				TypeCatalog.reset();
			} else { // 没有通过测试
				Dialogs.create()
						.title("Error Dialog")
						.masthead("There is an error occured")
						.message(
								"Your modified text doesn't pass the validation.Please input four-digit!")
						.showError();
			}

		} catch (IOException e) { // 非数字和中文输入的逗号,抛错
			// TODO Auto-generated catch block
			Dialogs.create()
					.title("Error Dialog")
					.masthead("There is an error occured")
					.message(
							"Your modified text doesn't pass the validation.The type isn't right!")
					.showError();
			e.printStackTrace();
		}

		catch (ProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void handleQuitChange(ActionEvent event) {
		catalog.setText((String) catalog.getUserData());
	}

	@FXML
	private void handleRestoreDefault(ActionEvent event) {
		Context.getInstance().setConfigProperty("UserCatalog", null);
		setupTextArea();
	}

	private void setupTextArea() {
		Context context = Context.getInstance();
		String catalogAddress = (String) context
				.getConfigProperty("UserCatalog");
		try {
			String data = null;
			if (catalogAddress == null) {
				data = IOUtils.toString(
						getClass().getResource(
								(String) context
										.getConfigProperty("DefaultCatalog")),
						"utf-8");

			} else {
				data = FileUtils.readFileToString(
						new File((String) context
								.getConfigProperty("UserCatalog")), "utf-8");

			}
			catalog.setUserData(data);
			catalog.setText(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
