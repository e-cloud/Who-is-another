/**
 * 
 */
package com.wia.model.fetcher;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Saint Scott
 * 
 */
public class GeneralAuthorInfoFetcher implements DataFetcher {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wia.model.fetcher.DataFetcher#fetch(java.lang.String)
	 */
	@Override
	public Object fetch(String url) {
		// TODO Auto-generated method stub
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(
				"http://acm.hdu.edu.cn/userstatus.php?user=shu_kiri");
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
