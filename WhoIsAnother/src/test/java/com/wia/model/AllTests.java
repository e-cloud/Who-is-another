/**
 * 
 */
package com.wia.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.wia.model.analysis.ACERecommendTest;
import com.wia.model.analysis.GeneralInfoTest;
import com.wia.model.analysis.LightSpotTest;
import com.wia.model.analysis.NeighbourRecommendTest;
import com.wia.model.data.TypeCatalogTest;
import com.wia.model.local.TypeCatalogGeneratorTest;
import com.wia.model.preprocess.AuthorPidListCrawlerTest;
import com.wia.model.preprocess.DetailAuthorCrawlerTest;
import com.wia.model.preprocess.ParserTest;
import com.wia.model.preprocess.SimpleAuthorCrawlerTest;
import com.wia.model.preprocess.TopAuthorsCrawlerTest;

/**
 * @author Saint Scott
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ACERecommendTest.class, GeneralInfoTest.class,
		NeighbourRecommendTest.class, LightSpotTest.class,
		TypeCatalogGeneratorTest.class, TypeCatalogTest.class,
		AuthorPidListCrawlerTest.class, DetailAuthorCrawlerTest.class,
		ParserTest.class, SimpleAuthorCrawlerTest.class,
		TopAuthorsCrawlerTest.class })
public class AllTests {

}
