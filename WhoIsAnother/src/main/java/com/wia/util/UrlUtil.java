/**
 * 
 */
package com.wia.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Saint Scott
 *
 */
public class UrlUtil {

	public static String encode(String targetString) {
		try {
			return URLEncoder.encode(targetString, "gb18030");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return targetString;
	}

}
