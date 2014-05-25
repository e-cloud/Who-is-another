/**
 * 
 */
package com.wia.model.local;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Saint Scott
 * 
 */
public class TypeCatalogGeneratorTest {
	private static Logger logger = Logger
			.getLogger(TypeCatalogGeneratorTest.class.getName());
	private static TypeCatalogGenerator generator = new TypeCatalogGenerator();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	 * {@link com.wia.model.local.TypeCatalogGenerator#GenerateCatalogMap()}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGenerateCatalogMap() throws IOException {
		Map<Integer, String> catalogMap = generator
				.generateCatalogMap("/DefaultCatalog.json");
		Assert.assertEquals(489, catalogMap.size());
	}

}
