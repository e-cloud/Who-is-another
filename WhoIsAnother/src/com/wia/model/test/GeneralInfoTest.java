/**
 * 
 */
package com.wia.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.Context;
import com.wia.model.analysis.GeneralInfo;
import com.wia.model.analysis.Info;
import com.wia.model.data.Author;
import com.wia.model.preprocess.DataPreprocessor;

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
		Context context = Context.getInstance();
		DataPreprocessor preprocessor = new DataPreprocessor();
		Author author = preprocessor.retrieveAuthorFromNet("wdp515105");
		context.setAuthor(author);
		generalInfo = GeneralInfo.getInstance();
		targetAuthor = context.getCurrentAuthor();
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

}
