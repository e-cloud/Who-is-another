/**
 * 
 */
package com.wia.model.preprocess;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.wia.model.data.Author;

/**
 * @author Saint Scott
 *
 */
public class AuthorPidListCrawlerTest {
	private static List<Author> topAuthors = null;
	private static List<Author> neighbours = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		topAuthors = new TopAuthorsCrawler(100).crawl();
		// neighbours = new
		// DetailAuthorCrawler("jpg").crawl().getNeighbourList();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testTopAuthors() {
		List<Set<Integer>> list = new AuthorPidListCrawler(topAuthors).crawl();
		assertEquals(100, list.size());
		int expectsize = 0;
		for (Author author : topAuthors) {
			expectsize += author.getSubmitted();
		}
		int actualsize = 0;
		for (Set<Integer> set : list) {
			actualsize += set.size();
		}
		assertEquals(expectsize, actualsize);
	}

	@Ignore
	@Test
	public void testNeighbour() {
		List<Set<Integer>> list = new AuthorPidListCrawler(neighbours).crawl();
		assertEquals(6, list.size());
		int expectsize = 0;
		for (Author author : neighbours) {
			expectsize += author.getSubmitted();
		}
		int actualsize = 0;
		for (Set<Integer> set : list) {
			actualsize += set.size();
		}
		assertEquals(expectsize, actualsize);
	}

}
