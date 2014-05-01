/**
 * 
 */
package com.wia.model.test;

import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.model.analysis.ACERecommend;
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
		List<Integer> rcmdList = recommend.recommand(author, 10);
		logger.info(rcmdList.toString());
	}

}
