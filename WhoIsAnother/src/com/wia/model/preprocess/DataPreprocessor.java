/**
 * 
 */
package com.wia.model.preprocess;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.wia.model.data.Author;
import com.wia.model.data.SubmitLog;

/**
 * @author Saint Scott
 * 
 */
public class DataPreprocessor {

	private final static String UserStatusUrl = "http://acm.hdu.edu.cn/userstatus.php?user=";
	private final static String RealTimeStatusUrl = "http://acm.hdu.edu.cn/status.php?pid=";
	private final static String RanklistUrl = "http://acm.hdu.edu.cn/ranklist.php?from=";

	/**
	 * @param author
	 * @param isIncludeNeighbour
	 * @return
	 */
	public Set<Integer> rertrieveSimpleAuthor(Author author,
			boolean isIncludeNeighbour) {

		Fetcher fetcher = new PageFetcher();
		Set<Integer> pidSet = null;
		try {
			String data = fetcher.fetch(getUserStatusUrl(author.getAuthorID()));
			pidSet = AuthorInfoParser.parse(data, author);

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
	 * @param authorID
	 * @return
	 */
	public Author retrieveAuthorFromNet(String authorID) {

		Author author = new Author(authorID);

		Set<Integer> pidSet = rertrieveSimpleAuthor(author, true);
		Queue<String> queue = generateURLQueue(pidSet, authorID);

		int threadcount = pidSet.size() >= 200 ? 200 : pidSet.size();

		ExecutorService exec = Executors.newFixedThreadPool(threadcount);
		List<Future<List<SubmitLog>>> result = new LinkedList<>();

		while (!queue.isEmpty()) {
			result.add(exec.submit(new SubmitlogCrawlTask(authorID, queue
					.remove())));
		}

		for (Iterator<Future<List<SubmitLog>>> iterator = result.iterator(); iterator
				.hasNext();) {
			try {

				Future<List<SubmitLog>> future = iterator.next();
				List<SubmitLog> subList = future.get();
				author.add(subList);

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

		return author;
	}

	/**
	 * 生成url队列
	 * 
	 * @param pidSet
	 * @param authorID
	 * @return
	 */
	public Queue<String> generateURLQueue(Set<Integer> pidSet, String authorID) {
		Queue<String> queue = new ConcurrentLinkedQueue<>();
		for (Iterator<Integer> iterator = pidSet.iterator(); iterator.hasNext();) {
			Integer pid = iterator.next();
			queue.add(getRealTimeStatusUrl(pid, authorID));
		}
		return queue;
	}

	/**
	 * @param top
	 *            指定前多少名
	 * @return List&lt;Author&gt;
	 */
	public List<Author> retrieveTopAuthors(int top) {
		return retrieveTopAuthors(top, top / 25);
	}

	/**
	 * @param top
	 *            指定前多少名
	 * @param threadcount
	 *            指定线程数量
	 * @return List&lt;Author&gt;
	 */
	public List<Author> retrieveTopAuthors(int top, int threadcount) {

		// "http://acm.hdu.edu.cn/ranklist.php?from=26"
		List<Author> authorList = new LinkedList<Author>();
		ExecutorService exec = Executors.newFixedThreadPool(threadcount);
		List<Future<String>> result = new LinkedList<>();
		int count = top / 25;
		for (int i = 0; i < count; i++) {
			String url = RanklistUrl + (1 + 25 * i);
			result.add(exec.submit(new CrawlTask(url)));
		}
		try {
			for (Iterator<Future<String>> iterator = result.iterator(); iterator
					.hasNext();) {
				Future<String> future = iterator.next();
				List<Author> subList = AuthorListParser.parse(future.get());
				authorList.addAll(subList);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			exec.shutdown();
		}

		return authorList;
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
		return UserStatusUrl + authorID;
	}

	/**
	 * @param pid
	 * @param authorID
	 * @return 指定参数对应的url
	 */
	private String getRealTimeStatusUrl(int pid, String authorID) {
		return RealTimeStatusUrl + pid + "&user=" + authorID;
	}

	/**
	 * @param pid
	 * @param authorID
	 * @param first
	 * @return 指定参数对应的url
	 */
	private String getRealTimeStatusUrl(int pid, String authorID, int first) {
		return RealTimeStatusUrl + pid + "&user=" + authorID + "&first="
				+ first;
	}

	/**
	 * 此类返参线程主要完成指定url的抓取，同时如果有下一页，递归抓取（还没想好如何用并发队列抓取） 运行完毕返回 List< SubmitLog >
	 * 
	 * @author Saint Scott
	 * 
	 */
	private class SubmitlogCrawlTask implements Callable<List<SubmitLog>> {
		private String url;
		private final String authorID;

		/**
		 * 
		 */
		public SubmitlogCrawlTask(String authorID, String url) {
			// TODO Auto-generated constructor stub
			this.url = url;
			this.authorID = authorID;
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
			List<SubmitLog> submitLogs = new LinkedList<>();
			String data = fetcher.fetch(url);
			int first = SubmitLogsParser.parse(data, submitLogs);
			if (first > 0) {
				url = getRealTimeStatusUrl(submitLogs.get(0).getPid(),
						authorID, first);
				submitLogs.addAll(call());
			}

			return submitLogs;
		}

	}

	/**
	 * 此类返参线程 纯粹返回 抓取指定url的网页的字符串
	 * 
	 * @author Saint Scott
	 * 
	 */
	private class CrawlTask implements Callable<String> {

		private final String url;

		/**
		 * 
		 */
		public CrawlTask(String url) {
			// TODO Auto-generated constructor stub
			this.url = url;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			PageFetcher fetcher = new PageFetcher();
			return fetcher.fetch(url);
		}
	}
}
