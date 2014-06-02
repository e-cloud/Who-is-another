/**
 * 
 */
package com.wia.model.preprocess;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wia.model.data.Author;
import com.wia.util.UrlUtil;

/**
 * @author Saint Scott
 *
 */
public class SimpleAuthorCrawler {

	private final static String UserStatusUrl = "http://acm.hdu.edu.cn/userstatus.php?user=";

	private final Author author;
	private boolean isIncludeNeighbour = false;

	public SimpleAuthorCrawler(Author author, boolean isIncludeNeighbour) {
		this.author = author;
		this.isIncludeNeighbour = isIncludeNeighbour;
	}

	public SimpleAuthorCrawler(Author author) {
		this.author = author;
	}

	/**
	 * crawl the author info into the author object
	 * 
	 * @return a set of the author's submitted problem's id
	 */
	public Set<Integer> crawl() {
		// TODO Auto-generated method stub
		PageFetcher fetcher = new PageFetcher();
		Set<Integer> pidSet = null;
		try {
			String data = fetcher.fetch(getUserStatusUrl(author.getAuthorID()));
			pidSet = AuthorInfoParser.parse(data, author);

			if (pidSet == null) {
				return null;
			}

			if (isIncludeNeighbour) {
				List<Author> neighbourList = AuthorListParser.parse(data);
				removeDuplicateAuthor(neighbourList, author.getAuthorID());
				author.setNeighbourList(neighbourList);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pidSet;
	}

	/**
	 * 删除authors中对应authorID的author
	 * 
	 * @param authors
	 * @param authorID
	 */
	private void removeDuplicateAuthor(List<Author> authors, String authorID) {
		for (Iterator<Author> iterator = authors.iterator(); iterator.hasNext();) {
			Author author = iterator.next();
			if (author.getAuthorID().equals(authorID)) {
				authors.remove(author);
				return;
			}
		}
	}

	/**
	 * @param authorID
	 * @return 指定参数对应的url
	 */
	private String getUserStatusUrl(String authorID) {
		return UserStatusUrl + UrlUtil.encode(authorID);
	}
}
