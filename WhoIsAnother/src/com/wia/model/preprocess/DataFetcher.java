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

/**
 * @author Saint Scott
 * 
 */
public class DataFetcher {

	private final static String UserStatus = "http://acm.hdu.edu.cn/userstatus.php?user=";
	private final static String RealTimeStatus = "http://acm.hdu.edu.cn/status.php?first=";

	@SuppressWarnings("unchecked")
	public static Author run(String authorID) {
		DataParser parser = DataParserFactory.createGeneralAuthorInfoFetcher();
		Author author = null;
		try {
			author = (Author) parser.parse(PageFetcher
					.fetch(getUserStatusUrl(authorID)));

			parser = DataParserFactory.createSubmitLogsFetcher();

			int lastRid = -1;
			while (true) {
				String fetchString = PageFetcher.fetch(getRealTimeStatusUrl(
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
			return RealTimeStatus + "0&pid=&user=" + authorID
					+ "&lang=0&status=0";
		}
	}
}
