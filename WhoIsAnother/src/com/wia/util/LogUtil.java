/**
 * 
 */
package com.wia.util;

/**
 * @author Saint Scott
 * 
 */
public class LogUtil {

	/**
	 * ���һ����Ϣ
	 * 
	 * @param message
	 */
	public static void i(Object message) {
		System.out.println("INFO: \n" + message);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param message
	 */
	public static void e(Object message) {
		System.out.println("ERROR: \n" + message);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param message
	 */
	public static void d(Object message) {
		System.out.println("DEBUG: \n" + message);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param message
	 */
	public static void w(Object message) {
		System.out.println("WARNING: \n" + message);
	}
}
