/**
 * 
 */
package com.wia.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.util.ThreadLoaclDateFormatUtil;

/**
 * @author Saint Scott
 * 
 */
public class ProfileController extends AbstractFXController {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		Author author = Context.getInstance().getCurrentAuthor();
		authorIDLabel.setText(author.getAuthorID());
		nicknameLabel.setText(author.getAuthorName());
		fromLabel.setText(author.getFrom());
		nationalityLabel.setText(author.getNationality());
		registDateLabel.setText(ThreadLoaclDateFormatUtil
				.formatSimpleDate(author.getRegistrationTime()));
		mottolLabel.setText(author.getMotto());
		emaillLabel.setText(author.getEmail());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.controller.AbstractFXController#getLayout()
	 */
	@Override
	public Parent getLayout() {
		// TODO Auto-generated method stub
		return rootLayout;
	}

}
