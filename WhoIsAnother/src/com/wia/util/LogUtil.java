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
	 * 输出一般信息
	 * 
	 * @param message
	 */
	public static void i(Object message) {
		System.out.println("Info: " + message);
	}

	/**
	 * 输出错误信息
	 * 
	 * @param message
	 */
	public static void e(Object message) {
		System.out.println("Error: " + message);
	}

	/**
	 * 输出调试信息
	 * 
	 * @param message
	 */
	public static void d(Object message) {
		System.out.println("Debug: " + message);
	}

	/**
	 * 输出警告信息
	 * 
	 * @param message
	 */
	public static void w(Object message) {
		System.out.println("Warning: " + message);
	}
}
