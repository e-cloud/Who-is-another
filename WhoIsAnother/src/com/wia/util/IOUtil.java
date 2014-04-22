/**
 * 
 */
package com.wia.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.util.CharArrayBuffer;

/**
 * @author Saint Scott
 * 
 */
public class IOUtil {

	/**
	 * 将指定编码集的输入流解析为字符串
	 * 
	 * @param is
	 *            inputstream to be parsed
	 * @param charset
	 * @return parsed string
	 * @throws IOException
	 */
	public static String parseInputStreamWithCharset(InputStream is,
			String charset) throws IOException {
		Reader reader = new InputStreamReader(is, charset);
		CharArrayBuffer buffer = new CharArrayBuffer(is.available());
		try {
			char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
		} finally {
			reader.close();
		}
		return buffer.toString();
	}

	/**
	 * 将utf-8编码集的输入流解析为字符串
	 * 
	 * @param is
	 *            inputstream to be parsed
	 * @param charset
	 * @return parsed string
	 * @throws IOException
	 */
	public static String parseInputStreamWithCharset(InputStream is)
			throws IOException {
		return parseInputStreamWithCharset(is, "utf-8");
	}
}
