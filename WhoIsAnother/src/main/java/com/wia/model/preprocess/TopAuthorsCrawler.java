/**
 * 
 */
package com.wia.model.preprocess;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.wia.model.data.Author;

/**
 * @author Saint Scott
 *
 */
public class TopAuthorsCrawler {

	private final static String RanklistUrl = "http://acm.hdu.edu.cn/ranklist.php?from=";

	private final int threadCount;

	public TopAuthorsCrawler(int top) {
		// TODO Auto-generated constructor stub
		this.threadCount = top / 25;
	}

	/**
	 * @return a list of authors with very simple info
	 */
	public List<Author> crawl() {
		// TODO Auto-generated method stub
		// "http://acm.hdu.edu.cn/ranklist.php?from=26"
		List<Author> authorList = new LinkedList<Author>();
		ExecutorService exec = Executors.newFixedThreadPool(threadCount);
		List<Future<String>> result = new LinkedList<>();

		for (int i = 0; i < threadCount; i++) {
			String url = RanklistUrl + (1 + 25 * i);
			result.add(exec.submit(new CrawlTask(url)));
		}

		for (Iterator<Future<String>> iterator = result.iterator(); iterator
				.hasNext();) {
			try {
				Future<String> future = iterator.next();
				List<Author> subList = AuthorListParser.parse(future.get());
				authorList.addAll(subList);

			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				exec.shutdown();
			}
		}

		return authorList;
	}

	private class CrawlTask implements Callable<String> {

		private final String url;

		/**
		 * 
		 */
		public CrawlTask(String url) {
			// TODO Auto-generated constructor stub
			this.url = url;
		}

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			PageFetcher fetcher = new PageFetcher();
			return fetcher.fetch(url);
		}
	}
}
