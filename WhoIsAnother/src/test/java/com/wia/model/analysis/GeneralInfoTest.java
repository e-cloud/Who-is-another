/**
 * 
 */
package com.wia.model.analysis;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.model.data.Author;
import com.wia.model.preprocess.DetailAuthorCrawler;

/**
 * @author Saint Scott
 * 
 */
public class GeneralInfoTest {

	private static GeneralInfo generalInfo = null;
	private static Author targetAuthor = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		targetAuthor = new DetailAuthorCrawler("wdp515105").crawl();
		generalInfo = new GeneralInfo(targetAuthor);
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
	 * Test method for {@link com.wia.model.analysis.Info#get(int)}.
	 */
	@Test
	public void testGet() {
		assertEquals(generalInfo.get(Info.SUBMIT), targetAuthor.getSubmitted());
		assertEquals(generalInfo.get(Info.SOLVE), targetAuthor.getSolved());
		assertEquals(generalInfo.get(Info.SUBMISSION),
				targetAuthor.getSubmissions());
		assertEquals(generalInfo.get(Info.ACCEPT), targetAuthor.getAccepted());
	}

	@Test
	public void testYear() {
		Map<Integer, Integer> solveMap = generalInfo.getProblemCountPerMonth(
				2007, Info.SOLVE);
		Map<Integer, Integer> submitMap = generalInfo.getProblemCountPerMonth(
				2007, Info.SUBMIT);

		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		for (int i = 0; i < 12; i++) {
			Integer a = solveMap.get(i);
			Integer b = submitMap.get(i);
			if (a == null & b == null) {
				continue;
			}
			map1.put(i, a == null ? 0 : a);
			map2.put(i, b == null ? 0 : b);
		}
	}

}
