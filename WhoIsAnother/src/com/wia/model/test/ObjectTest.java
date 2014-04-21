/**
 * 
 */
package com.wia.model.test;

import com.wia.model.data.Author;
import com.wia.model.preprocess.DataFetcher;
import com.wia.util.LogUtil;

/**
 * @author Saint Scott
 * 
 */
public class ObjectTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Author author = DataFetcher.run("myi_i");
		LogUtil.i(author);
	}

}
