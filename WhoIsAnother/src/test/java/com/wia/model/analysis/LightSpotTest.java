/**
 * 
 */
package com.wia.model.analysis;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.wia.model.data.Author;
import com.wia.model.preprocess.DetailAuthorCrawler;

/**
 * @author Saint Scott
 * 
 */
public class LightSpotTest {

	private static LightSpot lightSpot;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Author author = new DetailAuthorCrawler("wdp515105").crawl();
		lightSpot = new LightSpot(author);
		// lightSpot.init();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.LightSpot#getAcceptedTimeInterval()}.
	 */
	// @Ignore
	@Test
	public void testGetAcceptedTimeInterval() {
		int[] acceptedTimeInterval = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, };
		int[] compare = lightSpot.getAcceptedTimeInterval();
		for (int i = 0; i < 24; i++)
			assertEquals(acceptedTimeInterval[i], compare[i]);
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.LightSpot#getSubmitTimeInterval()}.
	 */
	// @Ignore
	@Test
	public void testGetSubmitTimeInterval() {
		int[] submitTimeInterval = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 2, 3, 0, 2,
				2, 0, 0, 0, 0, 4, 4, 4, 15, 5 };
		int[] compare = lightSpot.getSubmitTimeInterval();
		for (int i = 0; i < 24; i++)
			assertEquals(submitTimeInterval[i], compare[i]);
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.LightSpot#ACInFirstSubmit()}.
	 */
	@Ignore
	@Test
	public void testACInFirstSubmit() {
		assertEquals(lightSpot.ACInFirstSubmit(), 2);
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.LightSpot#problemSolvedTop10()}.
	 */
	@Ignore
	@Test
	public void testProblemSolvedTop10() {
		List<Pair<String, Integer>> date = lightSpot.problemSolvedTop10();
		List<Pair<String, Integer>> compare = new ArrayList<>();
		compare.add(new Pair<String, Integer>("2007-03-31", 3));
		compare.add(new Pair<String, Integer>("2007-03-16", 1));
		compare.add(new Pair<String, Integer>("2007-04-01", 1));
		compare.add(new Pair<String, Integer>("2007-01-17", 0));
		compare.add(new Pair<String, Integer>("2007-01-14", 0));
		compare.add(new Pair<String, Integer>("2007-03-17", 0));
		compare.add(new Pair<String, Integer>("2007-03-18", 0));

		for (int i = 0; i < date.size(); i++) {
			date.get(i).equals(compare.get(i));
		}
	}

}
