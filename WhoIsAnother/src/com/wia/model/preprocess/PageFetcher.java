/**
 * 
 */
package com.wia.model.preprocess;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.wia.util.IOUtil;

/**
 * @author Saint Scott
 * 
 */
public class PageFetcher {

	public static Object fetch(String url) throws ClientProtocolException,
			IOException {
		// TODO Auto-generated method stub
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		String content;
		try {
			HttpEntity entity = response.getEntity();
			content = IOUtil.parseInputStreamWithCharset(entity.getContent(),
					retrieveCharset(response));
		} finally {
			response.close();
		}
		return content;
	}

	private static String retrieveCharset(HttpResponse response) {
		HeaderIterator iter = response.headerIterator("content-type");
		Header header = iter.nextHeader();
		if (header.getValue().contains("charset")) {
			return header.getValue().split("charset=")[1];
		} else {
			return "gb2312";
		}
	}

}
