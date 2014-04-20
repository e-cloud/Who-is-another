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
		System.out.println("Info: " + message);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param message
	 */
	public static void e(Object message) {
		System.out.println("Error: " + message);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param message
	 */
	public static void d(Object message) {
		System.out.println("Debug: " + message);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param message
	 */
	public static void w(Object message) {
		System.out.println("Warning: " + message);
	}
}
