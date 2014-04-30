/**
 * 
 */
package com.wia.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

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
		Author author = preprocessor.retrieveAuthorFromNet((String) context
				.getContextObject("requestID"));
		context.setAuthor(author);
		spotLight = new SpotLight();
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
		assertEquals(spotLight.ACInFirstSubmit(), 2);
		logger.info(spotLight.ACInFirstSubmit() + "");
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.SpotLight#getSubmitTimeInterval()}.
	 */
	@Ignore
	@Test
	public void testGetSubmitTimeInterval() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.SpotLight#ACInFirstSubmit()}.
	 */
	@Ignore
	@Test
	public void testACInFirstSubmit() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.SpotLight#problemSolvedTop10()}.
	 */
	@Ignore
	@Test
	public void testProblemSolvedTop10() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wia.model.analysis.SpotLight#howManyDays()}.
	 */
	@Ignore
	@Test
	public void testHowManyDays() {
		fail("Not yet implemented");
	}

}
