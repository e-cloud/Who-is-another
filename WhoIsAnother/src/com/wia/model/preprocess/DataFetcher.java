/**
 * 
 */
package com.wia.model.preprocess;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.wia.model.data.Author;
import com.wia.model.data.SubmitLog;
import com.wia.model.local.FileFetcher;

/**
 * @author Saint Scott
 * 
 */
public class DataFetcher {

	private final static String UserStatus = "http://acm.hdu.edu.cn/userstatus.php?user=";
	private final static String RealTimeStatus = "http://acm.hdu.edu.cn/status.php?first=";
	private final static String UserStatusfile = "acm.hdu.edu.cn userstatus.php user=";
	private final static String RealTimeStatusfile = "acm.hdu.edu.cn status.php first=";

	@SuppressWarnings("unchecked")
	public static Author run2(String authorID) {
		DataParser parser = DataParserFactory.createGeneralAuthorInfoParser();
		Author author = null;
		try {
			// Fetcher fetcher = new PageFetcher();
			Fetcher fetcher = new FileFetcher();
			author = (Author) parser.parse(fetcher
					.fetch(getUserStatusUrl(authorID)));

			parser = DataParserFactory.createSubmitLogsParser();

			int lastRid = -1;
			while (true) {
				String fetchString = fetcher.fetch(getRealTimeStatusUrl(
						lastRid, authorID));
				List<SubmitLog> submitLogs = (List<SubmitLog>) parser
						.parse(fetchString);
				if (submitLogs == null) {
					break;
				}
				lastRid = submitLogs.get(submitLogs.size() - 1).getRid() - 1;
				author.add(submitLogs);
			}

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

	@SuppressWarnings("unchecked")
	public static Author run(String authorID) {
		DataParser parser = DataParserFactory.createGeneralAuthorInfoParser();
		Author author = null;
		try {
			// Fetcher fetcher = new PageFetcher();
			Fetcher fetcher = new FileFetcher();
			author = (Author) parser.parse(fetcher
					.fetch(getUserStatusFileUrl(authorID)));

			parser = DataParserFactory.createSubmitLogsParser();

			int lastRid = -1;
			while (true) {
				String fetchString = fetcher.fetch(getRealTimeStatusFileUrl(
						lastRid, authorID));
				List<SubmitLog> submitLogs = (List<SubmitLog>) parser
						.parse(fetchString);
				if (submitLogs == null) {
					break;
				}
				lastRid = submitLogs.get(submitLogs.size() - 1).getRid() - 1;
				author.add(submitLogs);
			}

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

	private static String getRealTimeStatusFileUrl(int rid, String authorID) {
		if (rid > 0) {
			return RealTimeStatusfile + rid + "&pid=&user=" + authorID
					+ "&lang=0&status=0";
		} else {
			return RealTimeStatusfile + "&pid=&user=" + authorID
					+ "&lang=0&status=0";
		}
	}
}
