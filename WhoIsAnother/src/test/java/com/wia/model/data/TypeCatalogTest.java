/**
 * 
 */
package com.wia.model.data;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.model.preprocess.DetailAuthorCrawler;

/**
 * @author Saint Scott
 * 
 */
public class TypeCatalogTest {

	private static Logger logger = Logger.getLogger(TypeCatalogTest.class
			.getName());

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
	 * Test method for {@link com.wia.model.data.TypeCatalog#getType(int)}.
	 */
	@Test
	public void testGetType() {
		assertEquals("基础题", TypeCatalog.getInstance().getType(1017));
	}

	/**
	 * Test method for
	 * {@link com.wia.model.data.TypeCatalog#count(java.util.Collection)}.
	 */
	@Test
	public void testCount() {
		logger.info(TypeCatalog.getInstance()
				.count(author.getProblemMap().keySet()).toString());
	}

	/**
	 * Test method for
	 * {@link com.wia.model.data.TypeCatalog#classify(java.util.Collection)}.
	 */
	@Test
	public void testClassify() {
		logger.info(TypeCatalog.getInstance()
				.classify(author.getProblemMap().keySet()).toString());
	}

}
