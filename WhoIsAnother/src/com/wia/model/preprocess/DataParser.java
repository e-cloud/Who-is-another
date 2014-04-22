/**
 * 
 */
package com.wia.model.preprocess;

import java.text.ParseException;

/**
 * @author Saint Scott
 * 
 */
public interface DataParser {

	/**
	 * @param data
	 *            输入参数——需要解析的数据
	 * @return 返回解析后生成的对象，由实现类决定
	 * @throws ParseException
	 */
	public Object parse(String data) throws ParseException;
}
