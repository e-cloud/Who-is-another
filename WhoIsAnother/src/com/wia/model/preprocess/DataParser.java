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
	 *            �������������Ҫ����������
	 * @return ���ؽ��������ɵĶ�����ʵ�������
	 * @throws ParseException
	 */
	public Object parse(String data) throws ParseException;
}
