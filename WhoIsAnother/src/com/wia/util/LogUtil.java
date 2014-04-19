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
	public static void i(String message) {
		System.out.println("Info: " + message);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param message
	 */
	public static void e(String message) {
		System.out.println("Error: " + message);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param message
	 */
	public static void d(String message) {
		System.out.println("Debug: " + message);
	}

	/**
	 * ���������Ϣ
	 * 
	 * @param message
	 */
	public static void w(String message) {
		System.out.println("Warning: " + message);
	}
}
