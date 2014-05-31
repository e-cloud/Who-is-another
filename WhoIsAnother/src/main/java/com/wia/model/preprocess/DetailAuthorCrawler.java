/**
 * 
 */
package com.wia.model.preprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
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
public class DetailAuthorCrawler {

	private final String authorID;

	public DetailAuthorCrawler(String id) {
		authorID = id;
	}

	/**
	 * @return an author with the constructed targeting id
	 */
	public Author crawl() {
		// TODO Auto-generated method stub
		Author author = new Author(authorID);

		SimpleAuthorCrawler simpleTask = new SimpleAuthorCrawler(author, true);

		Set<Integer> pidSet = simpleTask.crawl();

		if (pidSet == null) {
			return null;
		} else if (pidSet.size() == 0) {
			return author;
		}

		Queue<String> queue = generateURLQueue(pidSet, authorID);

		int threadcount = pidSet.size() >= 100 ? 100 : pidSet.size();

		ExecutorService exec = Executors.newFixedThreadPool(threadcount);
		List<Future<List<SubmitLog>>> result = new ArrayList<>();

		Collection<SubmitlogCrawlTask> tasks = new ArrayList<>();

		while (threadcount-- != 0) {
			tasks.add(new SubmitlogCrawlTask(authorID, queue));
		}
		try {
			result.addAll(exec.invokeAll(tasks));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * @param pid
	 * @param authorID
	 * @return 指定参数对应的url
	 */
	private String getRealTimeStatusUrl(int pid, String authorID) {
		return SubmitlogCrawlTask.RealTimeStatusUrl + pid + "&user=" + authorID;
	}
}
