/**
 * 
 */
package com.wia.util;

/**
 * @author Saint Scott
 *
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLoaclDateFormatUtil {
	private static final String date_format_simple = "yyyy-MM-dd";
	private static final String date_format_detail = "yyyy-MM-dd HH:mm:ss";
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

	/**
	 * @return "yyyy-MM-dd" like formatter
	 */
	public static DateFormat getSimpleDateFormat() {
		DateFormat df = threadLocal.get();
		if (df == null) {
			df = new SimpleDateFormat(date_format_simple);
			threadLocal.set(df);
		}
		return df;
	}

	/**
	 * @return "yyyy-MM-dd HH:mm:ss" like formatter
	 */
	public static DateFormat getDetailDateFormat() {
		DateFormat df = threadLocal.get();
		if (df == null) {
			df = new SimpleDateFormat(date_format_detail);
			threadLocal.set(df);
		}
		return df;
	}

	/**
	 * @param date
	 * @return formatted "yyyy-MM-dd" like string
	 * @throws ParseException
	 */
	public static String formatSimpleDate(Date date) throws ParseException {
		return getSimpleDateFormat().format(date);
	}

	/**
	 * @param date
	 * @return formatted "yyyy-MM-dd HH:mm:ss" like string
	 * @throws ParseException
	 */
	public static String formatDetailDate(Date date) throws ParseException {
		return getDetailDateFormat().format(date);
	}

	/**
	 * @param dateStr
	 *            "yyyy-MM-dd" like
	 * @return parsed Date
	 * @throws ParseException
	 */
	public static Date parseSimple(String dateStr) throws ParseException {
		return getSimpleDateFormat().parse(dateStr);
	}

	/**
	 * @param dateStr
	 *            "yyyy-MM-dd HH:mm:ss" like
	 * @return parsed Date
	 * @throws ParseException
	 */
	public static Date parseDetail(String dateStr) throws ParseException {
		return getDetailDateFormat().parse(dateStr);
	}
}
