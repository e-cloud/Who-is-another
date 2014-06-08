/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wia.model.data.Author;
import com.wia.util.ThreadLoaclDateFormatUtil;

/**
 * @author Saint Scott
 * 
 */
public class ProfileController extends AbstractFXController {
	private final static Logger logger = LoggerFactory
			.getLogger(ProfileController.class);
	@FXML
	private Parent rootLayout;

	@FXML
	private Label authorIDLabel;
	@FXML
	private Label nicknameLabel;
	@FXML
	private Label fromLabel;
	@FXML
	private Label nationalityLabel;
	@FXML
	private Label registDateLabel;
	@FXML
	private Label mottolLabel;
	@FXML
	private Label emaillLabel;

	@FXML
	private void initialize() {

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		Author author = (Author) myScreensContainer.getUserData();
		logger.info("show {}'s info", author.getAuthorID());
		authorIDLabel.setText(author.getAuthorID());
		nicknameLabel.setText(author.getAuthorName());
		fromLabel.setText(author.getFrom());
		nationalityLabel.setText(author.getNationality());
		registDateLabel.setText(ThreadLoaclDateFormatUtil
				.formatSimpleDate(author.getRegistrationTime()));
		mottolLabel.setText(author.getMotto());
		emaillLabel.setText(author.getEmail());
	}
}
