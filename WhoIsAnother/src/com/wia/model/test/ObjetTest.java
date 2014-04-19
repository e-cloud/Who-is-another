/**
 * 
 */
package com.wia.model.test;

import java.text.ParseException;

import com.wia.util.ThreadLoaclDateFormatUtil;

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
			System.out.println(ThreadLoaclDateFormatUtil
					.parseDetail("2011-05-21 21:52:56"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
