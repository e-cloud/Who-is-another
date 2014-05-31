package com.wia.model.preprocess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.wia.model.data.Author;

public class AuthorPidListCrawler {

	private final List<Author> authors;

	public AuthorPidListCrawler(List<Author> authors) {
		this.authors = authors;
	}

	/**
	 * @return a list of sets of author's submitted problem's id
	 */
	public List<Set<Integer>> crawl() {
		// TODO Auto-generated method stub
		List<Set<Integer>> setList = new ArrayList<>();
		ExecutorService exec = Executors.newFixedThreadPool(100);
		List<Future<Set<Integer>>> result = new ArrayList<>();

		for (Iterator<Author> iterator = authors.iterator(); iterator.hasNext();) {
			Author author = iterator.next();
			result.add(exec.submit(new CrawlTask(author)));
		}
		for (Iterator<Future<Set<Integer>>> iterator = result.iterator(); iterator
				.hasNext();) {
			try {
				Future<Set<Integer>> future = iterator.next();

				Set<Integer> pidSet = future.get();
				setList.add(pidSet);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				exec.shutdown();
			}

		}
		return setList;
	}

	private class CrawlTask implements Callable<Set<Integer>> {

		private final Author author;

		/**
		 * 
		 */
		public CrawlTask(Author author) {
			// TODO Auto-generated constructor stub
			this.author = author;
		}

		@Override
		public Set<Integer> call() throws Exception {
			// TODO Auto-generated method stub
			return new SimpleAuthorCrawler(author).crawl();
		}
	}

}
