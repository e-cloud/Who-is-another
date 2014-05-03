/**
 * 
 */
package com.wia.model.test;

import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.data.TypeCatalog;
import com.wia.model.preprocess.DataPreprocessor;

/**
 * @author Saint Scott
 * 
 */
public class TypeCatalogTest {

	private static Logger logger = Logger.getLogger(SpotLightTest.class
			.getName());

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Context context = Context.getInstance();
		DataPreprocessor preprocessor = new DataPreprocessor();
		Author author = preprocessor.retrieveAuthorFromNet("jpg");
		context.setAuthor(author);
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
		logger.info(TypeCatalog.getInstance().getType(1017));
	}

	/**
	 * Test method for
	 * {@link com.wia.model.data.TypeCatalog#count(java.util.Collection)}.
	 */
	@Test
	public void testCount() {
		Author author = Context.getInstance().getCurrentAuthor();
		logger.info(TypeCatalog.getInstance()
				.count(author.getProblemMap().keySet()).toString());
	}

	/**
	 * Test method for
	 * {@link com.wia.model.data.TypeCatalog#classify(java.util.Collection)}.
	 */
	@Test
	public void testClassify() {
		Author author = Context.getInstance().getCurrentAuthor();
		logger.info(TypeCatalog.getInstance()
				.classify(author.getProblemMap().keySet()).toString());
	}

}
