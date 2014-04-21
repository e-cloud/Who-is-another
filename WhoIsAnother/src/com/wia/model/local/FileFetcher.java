/**
 * 
 */
package com.wia.model.local;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.wia.model.preprocess.Fetcher;
import com.wia.util.IOUtil;

/**
 * @author Administrator
 * 
 */
public class FileFetcher implements Fetcher {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.preprocess.Fetcher#fetch(java.lang.String)
	 */
	@Override
	public String fetch(String url) {
		InputStream is;
		String content = null;
		try {
			is = new FileInputStream("./" + url);

			content = IOUtil.parseInputStreamWithCharset(is, "gb2312");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return content.replace("&nbsp;", " ");
	}
}