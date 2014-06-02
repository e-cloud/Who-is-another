/**
 * 
 */
package com.wia.model.preprocess;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;

import org.apache.http.client.ClientProtocolException;

import com.wia.model.data.SubmitLog;
import com.wia.util.UrlUtil;

/**
 * 此类返参线程主要完成指定url的抓取，同时如果有下一页，递归抓取（还没想好如何用并发队列抓取） 运行完毕返回 List< SubmitLog >
 * 
 * @author Saint Scott
 * 
 */
class SubmitlogCrawlTask implements Callable<List<SubmitLog>> {

	public final static String RealTimeStatusUrl = "http://acm.hdu.edu.cn/status.php?pid=";
	private final String authorID;
	private Queue<String> urlQueue = null;

	/**
		 * 
		 */
	public SubmitlogCrawlTask(String authorID, Queue<String> queue) {
		// TODO Auto-generated constructor stub
		this.authorID = authorID;
		urlQueue = queue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public List<SubmitLog> call() throws Exception {
		// TODO Auto-generated method stub
		PageFetcher fetcher = new PageFetcher();
		List<SubmitLog> submitLogs = new ArrayList<>();
		try {
			while (!urlQueue.isEmpty()) {

				String url = urlQueue.poll();
				if (url != null) {
					String data = fetcher.fetch(url);
					int first = SubmitLogsParser.parse(data, submitLogs);
					// author.add(submitLogs);
					synchronized (urlQueue) {
						if (first > 0) {
							String pushurl = getRealTimeStatusUrl(submitLogs
									.get(submitLogs.size() - 1).getPid(),
									authorID, first);
							urlQueue.add(pushurl);
						} else if (first == -1) {
							System.out.println("push again");
							urlQueue.add(url);
						}
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return submitLogs;
	}

	/**
	 * @param pid
	 * @param authorID
	 * @param first
	 * @return 指定参数对应的ur
	 */
	private String getRealTimeStatusUrl(int pid, String authorID, int first) {
		return RealTimeStatusUrl + pid + "&user=" + UrlUtil.encode(authorID)
				+ "&first=" + first;
	}
}
