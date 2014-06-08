/**
 * 
 */
package com.wia.controller;

import java.util.Map;

import javafx.concurrent.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.preprocess.DetailAuthorCrawler;

/**
 * @author Saint Scott
 *
 */
public class ImportAuthorTask extends Task<Author> {
	private final static Logger logger = LoggerFactory
			.getLogger(ImportAuthorTask.class);
	private final String authorID;

	public ImportAuthorTask(String authorID) {
		this.authorID = authorID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Author call() throws Exception {
		// TODO Auto-generated method stub
		logger.info("call a task to crawl {}'s info", authorID);
		Context context = Context.getInstance();
		if (context.containsKey("authors")) {
			Map<String, Author> map = (Map<String, Author>) context
					.getContextObject("authors");
			if (map.containsKey(authorID)) {
				return map.get(authorID);
			}
		}
		return new DetailAuthorCrawler(authorID).crawl();
	}
}
