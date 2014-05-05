/**
 * 
 */
package com.wia.model.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Saint Scott
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ACERecommendTest.class, DataPreprocessorTest.class,
		GeneralInfoTest.class, ParserTest.class, NeighbourRecommendTest.class,
		LightSpotTest.class, TypeCatalogGeneratorTest.class,
		TypeCatalogTest.class })
public class AllTests {

}
