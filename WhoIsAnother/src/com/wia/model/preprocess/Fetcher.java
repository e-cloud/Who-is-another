/**
 * 
 */
package com.wia.model.preprocess;

import java.io.IOException;

/**
 * @author Administrator
 * 
 */
public interface Fetcher {

	public String fetch(String url) throws IOException;
}
