/**
 * 
 */
package com.wia.model.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.util.Pair;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.model.data.Author;
import com.wia.model.preprocess.DataPreprocessor;

/**
 * @author Saint Scott
 * 
 */
public class ACERecommendTest {
	private static Logger logger = Logger.getLogger(ACERecommendTest.class
			.getName());
	private static final DataPreprocessor preprocessor = new DataPreprocessor();

	private static Author author;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		author = preprocessor.retrieveAuthorFromNet("jpg");
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
		ACERecommend recommend = new ACERecommend();
		List<Pair<Integer, Integer>> rcmdList = recommend.recommand(author, 10);
		List<Pair<Integer, Integer>> compareList = new ArrayList<>();
		// "1016=97, 1019=97, 1010=97, 1012=97, 1013=97, 1003=97, 1005=97, 1097=97, 1108=97, 1028=97"
		compareList.add(new Pair<Integer, Integer>(1016, 97));
		compareList.add(new Pair<Integer, Integer>(1019, 97));
		compareList.add(new Pair<Integer, Integer>(1010, 97));
		compareList.add(new Pair<Integer, Integer>(1012, 97));
		compareList.add(new Pair<Integer, Integer>(1013, 97));
		compareList.add(new Pair<Integer, Integer>(1003, 97));
		compareList.add(new Pair<Integer, Integer>(1005, 97));
		compareList.add(new Pair<Integer, Integer>(1097, 97));
		compareList.add(new Pair<Integer, Integer>(1108, 97));
		compareList.add(new Pair<Integer, Integer>(1028, 97));
		Assert.assertEquals(compareList, rcmdList);
	}

}
