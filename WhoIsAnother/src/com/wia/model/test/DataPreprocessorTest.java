/**
 * 
 */
package com.wia.model.test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.wia.model.data.Author;
import com.wia.model.data.Problem;
import com.wia.model.preprocess.DataPreprocessor;

/**
 * @author Saint Scott
 * 
 */
public class DataPreprocessorTest {

	private static Logger logger = Logger.getLogger(DataPreprocessorTest.class
			.getName());
	private static final DataPreprocessor preprocessor = new DataPreprocessor();

	private static List<Author> authors;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		authors = preprocessor.retrieveTopAuthors(100);
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
	 * {@link com.wia.model.preprocess.DataPreprocessor#rertrieveSimpleAuthor(com.wia.model.data.Author, boolean)}
	 * .
	 */
	@Ignore
	@Test
	public void testRertrieveSimpleAuthor() {
		Author author = new Author("wdp515105");
		Set<Integer> pidset = preprocessor.rertrieveSimpleAuthor(author, false);
		assertEquals(pidset.size(), 9);
	}

	/**
	 * Test method for
	 * {@link com.wia.model.preprocess.DataPreprocessor#rertrieveSimpleAuthorList(java.util.List, boolean)}
	 * .
	 */
	// @Ignore
	@Test
	public void testRertrieveSimpleAuthorList() {
		logger.info(authors.size() + "");
		List<Set<Integer>> setList = preprocessor
				.rertrieveSimpleAuthorList(authors);
		int size = 0;
		for (Iterator<Set<Integer>> iterator = setList.iterator(); iterator
				.hasNext();) {
			Set<Integer> set = iterator.next();
			size += set.size();
		}
		logger.info(size + "");
		// logger.info(setList.toString());
	}

	/**
	 * Test method for
	 * {@link com.wia.model.preprocess.DataPreprocessor#retrieveAuthorFromNet(java.lang.String)}
	 * .
	 */
	@Ignore
	@Test
	public void testRetrieveAuthorFromNet() {
		// candidate "2011330300713" "haifei" "wdp515105"
		Author user = preprocessor.retrieveAuthorFromNet("haifei");
		assertEquals(user.getSubmitted(), 234);
		Collection<Problem> problems = user.getProblemMap().values();
		int count = 0;
		for (Iterator<Problem> iterator = problems.iterator(); iterator
				.hasNext();) {
			Problem problem = iterator.next();
			count += problem.getSubmitMap().size();
		}
		assertEquals(user.getSubmissions(), count);
		logger.info(user.toString());
	}

	/**
	 * Test method for
	 * {@link com.wia.model.preprocess.DataPreprocessor#retrieveTopAuthors(int, int)}
	 * .
	 */
	@Ignore
	@Test
	public void testRetrieveTopAuthors() {
		int top = 100;
		List<Author> authors = preprocessor.retrieveTopAuthors(top, 5);
		assertEquals(authors.size(), top / 4);
	}

}
