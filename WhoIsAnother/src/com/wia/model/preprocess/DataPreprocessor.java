/**
 * 
 */
package com.wia.model.preprocess;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.data.SubmitLog;
import com.wia.model.local.FileFetcher;

/**
 * @author Saint Scott
 * 
 */
public class DataPreprocessor {

	private final static String UserStatus = "http://acm.hdu.edu.cn/userstatus.php?user=";
	private final static String RealTimeStatus = "http://acm.hdu.edu.cn/status.php?first=";
	private final static String UserStatusfile = "acm.hdu.edu.cn userstatus.php user=";
	private final static String RealTimeStatusfile = "acm.hdu.edu.cn status.php ";

	public static Author run(String authorID, int dataRetrieveAddress) {

		switch (dataRetrieveAddress) {
		case Context.LOCAL:
			return retrieveFromLocal(authorID);
		case Context.NETWORK:
			return retrieveFromNetwork(authorID);
		default:
			throw new NullPointerException("we don't have that kind of address");
		}
	}

	public static Author rertrieveSimpleAuthor() {

		return null;
	}

	public static Author retrieveFromNetwork(String authorID) {
		Author author = new Author(authorID);
		try {
			Fetcher fetcher = new PageFetcher();

			String data = fetcher.fetch(getUserStatusUrl(authorID));

			Set<Integer> set = AuthorInfoParser.parse(data, author);

			Map<String, Author> neighbourMap = AuthorListParser.parse(data,
					author.getAuthorID());
			author.setNeighbourMap(neighbourMap);

			List<SubmitLog> submitLogs = new ArrayList<>();
			for (Iterator<Integer> iterator = set.iterator(); iterator
					.hasNext();) {
				int pid = iterator.next();
				data = fetcher.fetch("http://acm.hdu.edu.cn/status.php?user="
						+ authorID + "&pid=" + pid);
				while (true) {
					int first = SubmitLogsParser.parse(data, submitLogs);
					if (first > 0) {
						data = fetcher
								.fetch("http://acm.hdu.edu.cn/status.php?first="
										+ first
										+ "&user="
										+ authorID
										+ "&pid="
										+ pid);
					} else {
						break;
					}
				}

			}
			author.add(submitLogs);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return author;
	}

	public static Author retrieveFromLocal(String authorID) {
		Author author = new Author(authorID);
		try {
			Fetcher fetcher = new FileFetcher();

			String data = fetcher
					.fetch(getUserStatusFileUrl(authorID) + ".htm");

			Set<Integer> set = AuthorInfoParser.parse(data, author);

			Map<String, Author> neighbourMap = AuthorListParser.parse(data,
					author.getAuthorID());
			author.setNeighbourMap(neighbourMap);

			List<SubmitLog> submitLogs = new ArrayList<>();
			for (Iterator<Integer> iterator = set.iterator(); iterator
					.hasNext();) {
				int pid = iterator.next();
				data = fetcher.fetch(getRealTimeStatusFileUrl(0, authorID, pid)
						+ ".htm");
				while (true) {
					int first = SubmitLogsParser.parse(data, submitLogs);
					if (first > 0) {
						data = fetcher.fetch(getRealTimeStatusFileUrl(first,
								authorID, pid) + ".htm");
					} else {
						break;
					}
				}

			}
			author.add(submitLogs);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return author;
	}

	private static String getUserStatusUrl(String authorID) {
		return UserStatus + authorID;
	}

	private static String getRealTimeStatusUrl(int rid, String authorID) {
		if (rid > 0) {
			return RealTimeStatus + rid + "&pid=&user=" + authorID
					+ "&lang=0&status=0";
		} else {
			return RealTimeStatus + "&pid=&user=" + authorID
					+ "&lang=0&status=0";
		}
	}

	private static String getUserStatusFileUrl(String authorID) {
		return UserStatusfile + authorID;
	}

	private static String getRealTimeStatusFileUrl(int rid, String authorID,
			int pid) {
		if (rid > 0) {
			return RealTimeStatusfile + "first=" + rid + "&user=" + authorID
					+ "&pid=" + pid;
		} else {
			return RealTimeStatusfile + "user=" + authorID + "&pid=" + pid;
		}
	}
}
