/**
 * 
 */
package com.wia.model.preprocess;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.model.data.Author;

/**
 * @author Saint Scott
 *
 */
public class SimpleAuthorCrawlerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Author author = new Author("jpg");
		SimpleAuthorCrawler crawler = new SimpleAuthorCrawler(author, true);
		Set<Integer> set = crawler.crawl();
		assertEquals(40, set.size());
	}

}
