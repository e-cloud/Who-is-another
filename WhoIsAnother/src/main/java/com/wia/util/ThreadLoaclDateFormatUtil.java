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

	private static final ThreadLocal<DateFormat> simpleDateFormat = new ThreadLocal<DateFormat>() {
		@Override
		public DateFormat get() {
			return super.get();
		}

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(date_format_simple);
		}

		@Override
		public void remove() {
			super.remove();
		}

		@Override
		public void set(DateFormat value) {
			super.set(value);
		}
	};

	private static final ThreadLocal<DateFormat> detailDateFormat = new ThreadLocal<DateFormat>() {
		@Override
		public DateFormat get() {
			return super.get();
		}

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(date_format_detail);
		}

		@Override
		public void remove() {
			super.remove();
		}

		@Override
		public void set(DateFormat value) {
			super.set(value);
		}
	};

	/**
	 * @return "yyyy-MM-dd" like formatter
	 */
	public static DateFormat getSimpleDateFormat() {
		DateFormat df = simpleDateFormat.get();
		if (df == null) {
			df = new SimpleDateFormat(date_format_simple);
			simpleDateFormat.set(df);
		}
		return df;
	}

	/**
	 * @return "yyyy-MM-dd HH:mm:ss" like formatter
	 */
	public static DateFormat getDetailDateFormat() {
		DateFormat df = detailDateFormat.get();
		if (df == null) {
			df = new SimpleDateFormat(date_format_detail);
			detailDateFormat.set(df);
		}
		return df;
	}

	/**
	 * @param date
	 * @return formatted "yyyy-MM-dd" like string
	 */
	public static String formatSimpleDate(Date date) {
		if (date == null) {
			return null;
		}
		return getSimpleDateFormat().format(date);
	}

	/**
	 * @param date
	 * @return formatted "yyyy-MM-dd HH:mm:ss" like string
	 */
	public static String formatDetailDate(Date date) {
		if (date == null) {
			return null;
		}
		return getDetailDateFormat().format(date);
	}

	/**
	 * @param dateStr
	 *            "yyyy-MM-dd" like
	 * @return parsed Date
	 * @throws ParseException
	 */
	public static Date parseSimple(String dateStr) {
		DateFormat dateFormat = getSimpleDateFormat();
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @param dateStr
	 *            "yyyy-MM-dd HH:mm:ss" like
	 * @return parsed Date
	 * @throws ParseException
	 */
	public static Date parseDetail(String dateStr) {
		DateFormat dateFormat = getDetailDateFormat();
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
