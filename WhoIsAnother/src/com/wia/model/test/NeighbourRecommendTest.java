/**
 * 
 */
package com.wia.model.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.wia.Context;
import com.wia.model.analysis.NeighbourRecommend;
import com.wia.model.data.Author;
import com.wia.model.preprocess.DataPreprocessor;

/**
 * @author acer
 *
 */
public class NeighbourRecommendTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Context context = Context.getInstance();
		DataPreprocessor preprocessor = new DataPreprocessor();
		Author author = preprocessor.retrieveAuthorFromNet("wdp515105");
		context.setAuthor(author);
	}

	/**
	 * Test method for {@link com.wia.model.analysis.NeighbourRecommend#neighborRecommend()}.
	 */
	@Test
	public void testNeighborRecommend() {
		NeighbourRecommend neighbourRecommend = new NeighbourRecommend();
		List<Integer> list = neighbourRecommend.neighborRecommend();
		assertEquals(list.size() , 21);
		
	}

}
