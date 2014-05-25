/**
 * 
 */
package com.wia.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import com.wia.Context;
import com.wia.model.data.Author;

/**
 * @author Saint Scott
 * 
 */
public class RanklistController extends AbstractFXController {
	@FXML
	private Parent rootLayout;
	@FXML
	private TableView<Author> ranklistTable;
	@FXML
	private TableColumn<Author, String> rankColumn;
	@FXML
	private TableColumn<Author, String> authorColumn;
	@FXML
	private TableColumn<Author, String> mottoColumn;
	@FXML
	private TableColumn<Author, String> solvedColumn;
	@FXML
	private TableColumn<Author, String> submittedColumn;
	@FXML
	private TableColumn<Author, String> acRatioColumn;
	@FXML
	private TextField compareField;
	@FXML
	private Button compareButton;

	@FXML
	private void initialize() {
		rankColumn
				.setCellValueFactory(new PropertyValueFactory<Author, String>(
						"Rank"));
		authorColumn
				.setCellValueFactory(new PropertyValueFactory<Author, String>(
						"AuthorName"));
		mottoColumn
				.setCellValueFactory(new PropertyValueFactory<Author, String>(
						"Motto"));
		solvedColumn
				.setCellValueFactory(new PropertyValueFactory<Author, String>(
						"Solved"));
		submittedColumn
				.setCellValueFactory(new PropertyValueFactory<Author, String>(
						"Submissions"));
		acRatioColumn
				.setCellValueFactory(new PropertyValueFactory<Author, String>(
						"ACRatio"));

		// Auto resize columns
		ranklistTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		ranklistTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Author>() {
					@Override
					public void changed(
							ObservableValue<? extends Author> observable,
							Author oldValue, Author newValue) {
						Context.getInstance().addContextObject("competitor",
								newValue.getAuthorID());
						myScreensContainer
								.setScreen(RootStageController.ID_COMPARATOR);
					}
				});

		compareButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Context.getInstance().addContextObject("competitor",
						compareField.getText());
				myScreensContainer.setScreen(RootStageController.ID_COMPARATOR);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update() {
		ranklistTable.setItems((ObservableList<Author>) Context.getInstance()
				.getContextObject("ranklist"));
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
