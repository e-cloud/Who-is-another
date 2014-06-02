/**
 * 
 */
package com.wia.model.analysis;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.model.data.Author;
import com.wia.model.preprocess.DetailAuthorCrawler;

/**
 * @author Saint Scott
 * 
 */
public class ACERecommendTest {

	private static Author author;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		author = new DetailAuthorCrawler("jpg").crawl();
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
	 * {@link com.wia.model.analysis.ACERecommend#recommand(com.wia.model.data.Author, int)}
	 * .
	 */
	@Test
	public void testRecommand() {
		ACERecommend recommend = new ACERecommend(author);
		List<Pair<Integer, Integer>> rcmdList = recommend.recommend(10);
		List<Pair<Integer, Integer>> compareList = new ArrayList<>();
		// <[1003=98, 1005=98, 1010=98, 1012=98, 1013=98, 1016=98, 1019=98,
		// 1028=98, 1042=98, 1058=98]>
		compareList.add(new Pair<Integer, Integer>(1003, 98));
		compareList.add(new Pair<Integer, Integer>(1005, 98));
		compareList.add(new Pair<Integer, Integer>(1010, 98));
		compareList.add(new Pair<Integer, Integer>(1012, 98));
		compareList.add(new Pair<Integer, Integer>(1013, 98));
		compareList.add(new Pair<Integer, Integer>(1016, 98));
		compareList.add(new Pair<Integer, Integer>(1019, 98));
		compareList.add(new Pair<Integer, Integer>(1028, 98));
		compareList.add(new Pair<Integer, Integer>(1042, 98));
		compareList.add(new Pair<Integer, Integer>(1058, 98));
		Assert.assertEquals(compareList, rcmdList);
	}

}
