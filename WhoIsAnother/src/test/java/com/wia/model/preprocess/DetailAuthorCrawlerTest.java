/**
 * 
 */
package com.wia.model.preprocess;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Iterator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.model.data.Author;
import com.wia.model.data.Problem;

/**
 * @author Saint Scott
 *
 */
public class DetailAuthorCrawlerTest {

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
		Author user = new DetailAuthorCrawler("jpg").crawl();
		assertEquals(user.getSubmitted(), 40);
		Collection<Problem> problems = user.getProblemMap().values();
		int count = 0;
		for (Iterator<Problem> iterator = problems.iterator(); iterator
				.hasNext();) {
			Problem problem = iterator.next();
			count += problem.getSubmitMap().size();
		}
		assertEquals(user.getSubmissions(), count);
	}

}
