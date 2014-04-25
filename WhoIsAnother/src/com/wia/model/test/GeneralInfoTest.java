/**
 * 
 */
package com.wia.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.wia.Context;
import com.wia.model.analysis.GeneralInfo;
import com.wia.model.analysis.Info;
import com.wia.model.data.Author;

/**
 * @author Saint Scott
 * 
 */
public class GeneralInfoTest {

	private static GeneralInfo generalInfo = null;
	private static Author author = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		generalInfo = GeneralInfo.getInstance();
		author = Context.getInstance().getCurrentAuthor();
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
	 * {@link com.wia.model.analysis.GeneralInfo#getPairList(int)}.
	 */
	@Ignore
	@Test
	public void testGetPairList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.GeneralInfo#getProblemList(int, int, int)}.
	 */
	@Ignore
	@Test
	public void testGetProblemList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.GeneralInfo#getProblemCount(int, int)}.
	 */
	@Ignore
	@Test
	public void testGetProblemCount() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.GeneralInfo#getProblemCountPerYear(int)}.
	 */
	@Ignore
	@Test
	public void testGetProblemCountPerYear() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.GeneralInfo#getProblemCountPerMonth(int, int)}
	 * .
	 */
	@Ignore
	@Test
	public void testGetProblemCountPerMonth() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.GeneralInfo#getProblemCountPerDay(int, int, int)}
	 * .
	 */
	@Ignore
	@Test
	public void testGetProblemCountPerDay() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wia.model.analysis.Info#get(int)}.
	 */
	@Test
	public void testGet() {
		assertEquals(generalInfo.get(Info.SUBMIT), author.getSubmitted());
		assertEquals(generalInfo.get(Info.SOLVE), author.getSolved());
		assertEquals(generalInfo.get(Info.SUBMISSION), author.getSubmissions());
		assertEquals(generalInfo.get(Info.ACCEPT), author.getAccepted());
	}

}
