/**
 * 
 */
package com.wia.model.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.wia.model.preprocess.DataParser;
import com.wia.model.preprocess.DataParserFactory;
import com.wia.model.preprocess.PageFetcher;

/**
 * @author Saint Scott
 * 
 */
public class ObjetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DataParser parser = DataParserFactory
					.createGeneralAuthorInfoFetcher();
			parser.parse((String) PageFetcher
					.fetch("http://acm.hdu.edu.cn/userstatus.php?user=syx_China"));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
