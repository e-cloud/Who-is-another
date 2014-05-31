/**
 * 
 */
package com.wia.model.analysis;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.model.data.Author;
import com.wia.model.preprocess.DetailAuthorCrawler;

/**
 * @author acer
 * 
 */
public class NeighbourRecommendTest {

	private static Author author;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		author = new DetailAuthorCrawler("wdp515105").crawl();
	}

	/**
	 * Test method for
	 * {@link com.wia.model.analysis.NeighbourRecommend#neighborRecommend()}.
	 */
	@Test
	public void testNeighborRecommend() {
		NeighbourRecommend neighbourRecommend = new NeighbourRecommend(author);
		List<Integer> list = neighbourRecommend.recommend();
		assertEquals(list.size(), 21);

	}

}
