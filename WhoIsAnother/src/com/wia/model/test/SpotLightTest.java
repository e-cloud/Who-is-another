/**
 * 
 */
package com.wia.model.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.util.Pair;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.wia.Context;
import com.wia.model.analysis.SpotLight;
import com.wia.model.data.Author;
import com.wia.model.preprocess.DataPreprocessor;

/**
 * @author Saint Scott
 * 
 */
public class SpotLightTest {

	private static Logger logger = Logger.getLogger(SpotLightTest.class
			.getName());

	private static SpotLight spotLight;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Context context = Context.getInstance();
		DataPreprocessor preprocessor = new DataPreprocessor();
		Author author = preprocessor.retrieveAuthorFromNet("wdp515105");
		context.setAuthor(author);
		spotLight = new SpotLight();
		spotLight.init();
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
	 * {@link com.wia.model.analysis.SpotLight#getAcceptedTimeInterval()}.
	 */
	// @Ignore
	@Test
	public void testGetAcceptedTimeInterval() {
		int[] acceptedTimeInterval = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 0, };
		int[] compare = spotLight.getAcceptedTimeInterval();
		for (int i = 0; i < 23; i++)
			assertEquals(acceptedTimeInterval[i], compare[i]);
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.SpotLight#getSubmitTimeInterval()}.
	 */
	// @Ignore
	@Test
	public void testGetSubmitTimeInterval() {
		int[] submitTimeInterval = { 0, 0, 0, 0, 0, 0, 0, 0, 7, 2, 3, 0, 2, 2,
				0, 0, 0, 0, 4, 4, 4, 15, 5, 0, };
		int[] compare = spotLight.getSubmitTimeInterval();
		for (int i = 0; i < 23; i++)
			assertEquals(submitTimeInterval[i], compare[i]);
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.SpotLight#ACInFirstSubmit()}.
	 */
	@Ignore
	@Test
	public void testACInFirstSubmit() {
		assertEquals(spotLight.ACInFirstSubmit(), 2);
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.SpotLight#problemSolvedTop10()}.
	 */
	@Ignore
	@Test
	public void testProblemSolvedTop10() {
		List<Pair<String, Integer>> date = spotLight.problemSolvedTop10();
		List<Pair<String, Integer>> compare = new ArrayList<>();
		compare.add(new Pair<String, Integer>("2007-03-31", 3));
		compare.add(new Pair<String, Integer>("2007-03-16", 1));
		compare.add(new Pair<String, Integer>("2007-04-01", 1));
		compare.add(new Pair<String, Integer>("2007-01-17", 0));
		compare.add(new Pair<String, Integer>("2007-01-14", 0));
		compare.add(new Pair<String, Integer>("2007-03-17", 0));
		compare.add(new Pair<String, Integer>("2007-03-18", 0));
	}

	/**
	 * Test method for {@link com.wia.model.analysis.SpotLight#howManyDays()}.
	 */
	@Ignore
	@Test
	public void testHowManyDays() {
		assertEquals(spotLight.howManyDays(), 2661);
	}

}
