/**
 * 
 */
package com.wia.model.preprocess;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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

import org.apache.http.client.ClientProtocolException;

import com.wia.model.data.Author;
import com.wia.model.data.SubmitLog;
import com.wia.model.local.FileFetcher;

/**
 * @author Saint Scott
 * 
 */
public class DataPreprocessor {

	private final static String UserStatusUrl = "http://acm.hdu.edu.cn/userstatus.php?user=";
	private final static String RealTimeStatusUrl = "http://acm.hdu.edu.cn/status.php?pid=";
	private final static String UserStatusfile = "acm.hdu.edu.cn userstatus.php user=";
	private final static String RealTimeStatusfile = "acm.hdu.edu.cn status.php ";
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
				author.setNeighbourList(removeDuplicateAuthor(neighbourList,
						author.getAuthorID()));
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

		ExecutorService exec = Executors.newFixedThreadPool(30);
		List<Future<List<SubmitLog>>> result = new LinkedList<>();

		Author author = new Author(authorID);

		Set<Integer> pidSet = rertrieveSimpleAuthor(author, true);
		Queue<String> queue = generateURLQueue(pidSet, authorID);

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

	public Author retrieveFromLocal(final String authorID) {
		Author author = new Author(authorID);
		try {
			Fetcher fetcher = new FileFetcher();
			String data = fetcher
					.fetch(getUserStatusFileUrl(authorID) + ".htm");

			Set<Integer> set = AuthorInfoParser.parse(data, author);

			List<Author> neighbourList = AuthorListParser.parse(data);
			author.setNeighbourList(removeDuplicateAuthor(neighbourList,
					authorID));

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

	public Queue<String> generateURLQueue(Set<Integer> pidSet, String authorID) {
		Queue<String> queue = new ConcurrentLinkedQueue<>();
		for (Iterator<Integer> iterator = pidSet.iterator(); iterator.hasNext();) {
			Integer pid = iterator.next();
			queue.add(getRealTimeStatusUrl(pid, authorID));
		}
		return queue;
	}

	public List<Author> retrieveTopAuthors(int top) {
		return retrieveTopAuthors(top, 20);
	}

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

	private List<Author> removeDuplicateAuthor(List<Author> authors,
			String authorID) {
		for (Iterator<Author> iterator = authors.iterator(); iterator.hasNext();) {
			Author author = iterator.next();
			if (author.getAuthorID().equals(authorID)) {
				authors.remove(author);
				return authors;
			}
		}
		return authors;
	}

	private String getUserStatusUrl(String authorID) {
		return UserStatusUrl + authorID;
	}

	private String getRealTimeStatusUrl(int pid, String authorID) {
		return RealTimeStatusUrl + pid + "&user=" + authorID;
	}

	private String getRealTimeStatusUrl(int pid, String authorID, int first) {
		return RealTimeStatusUrl + pid + "&user=" + authorID + "&first="
				+ first;
	}

	private String getUserStatusFileUrl(String authorID) {
		return UserStatusfile + authorID;
	}

	private String getRealTimeStatusFileUrl(int rid, String authorID, int pid) {
		if (rid > 0) {
			return RealTimeStatusfile + "first=" + rid + "&user=" + authorID
					+ "&pid=" + pid;
		} else {
			return RealTimeStatusfile + "user=" + authorID + "&pid=" + pid;
		}
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
