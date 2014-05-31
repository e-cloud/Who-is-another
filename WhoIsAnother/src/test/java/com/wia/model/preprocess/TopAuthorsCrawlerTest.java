/**
 * 
 */
package com.wia.model.preprocess;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.model.data.Author;

/**
 * @author Saint Scott
 *
 */
public class TopAuthorsCrawlerTest {

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
	public void test() throws InterruptedException, ExecutionException {
		TopAuthorsCrawler task = new TopAuthorsCrawler(100);
		List<Author> authors = task.crawl();
		assertEquals(100, authors.size());
	}
}
