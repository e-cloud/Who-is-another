/**
 * 
 */
package com.wia.model.preprocess;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.wia.util.LogUtil;

/**
 * @author Saint Scott
 * 
 */
public class GeneralAuthorInfoParser implements DataParser {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.fetcher.DataParser#parse(java.lang.String)
	 */
	@Override
	public Object parse(String data) {
		// TODO Auto-generated method stub
		Document doc = Jsoup.parse(data);
		Element tr4 = doc.getElementsByTag("tr").get(3);
		LogUtil.d(tr4.html());
		return null;
	}

}
