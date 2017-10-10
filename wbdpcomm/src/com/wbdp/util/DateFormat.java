package com.wbdp.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateFormat {

	/**
	 * locale local language
	 */
	public static Locale locale = null;

	public static String chineseDateFormat() {

		String[] weeks = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四",
				"星期五", "星期六" };

		Date date = new Date();
		int month = date.getMonth() + 1;
		int day = date.getDate();
		int year = date.getYear() + 1900;
		int week = date.getDay();
		String ret = String.valueOf(month) + "月" + String.valueOf(day) + "日, "
				+ year + "年  " + weeks[week];

		return ret;
	}

	public static String englishDateFormat() {
		String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May",
				"June", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };

		String[] weeks = new String[] { "Sunday", "Monday", "Tueday",
				"Wednesday", "Thursday", "Friday", "Saturday" };

		Date date = new Date();
		int month = date.getMonth();
		int day = date.getDate();
		int year = date.getYear() + 1900;
		int week = date.getDay();
		String ret = months[month] + " " + String.valueOf(day) + ", " + year
				+ "  " + weeks[week];

		return ret;
	}

	public static String getDate() {
		String ret = "";
		// locale = new ActionSupport().getLocale();
		if (locale.toString().equals("zh_CN")) {
			ret = chineseDateFormat();
		} else {
			ret = englishDateFormat();
		}

		return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(englishDateFormat());
		// System.out.println(chineseDateFormat());

		// Date date = new Date();
		// System.out.println(formatDate(date));
		// System.out.println(format("0310050609", 3, 4));
		// System.out.println(convertToDate("", ""));

		// System.out.println(new Date("2011/12/05"));
	}

	/**
	 * format for the data of Date(yyyy-MM-dd HH:mm:ss).
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, int type) {
		// get current time
		String dataFormat = "yyyy-MM-dd HH:mm:ss";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dataFormat);

		// handle time
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		// set all data to make the email
		String strCurrentDate = sdf.format(calendar.getTime());

		return strCurrentDate;
	}

	/**
	 * format for the data of Date(yyMMddHHmmss).
	 * 
	 * @param date
	 * @return
	 */
	public static String converseDate(Date date) {
		// get current time
		String dataFormat = "ssmmHHddMMyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dataFormat);

		// handle time
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		// set all data to make the email
		String strCurrentDate = sdf.format(calendar.getTime());

		return strCurrentDate;
	}

	public static String converseDateFormat1(Date date) {
		// get current time
		String dataFormat = "ddMMyyHHmmss";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dataFormat);

		// handle time
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		// set all data to make the email
		String strCurrentDate = sdf.format(calendar.getTime());

		return strCurrentDate;
	}

	/**
	 * format for the data of Date (yyyy-MM-dd HH:mm:ss.SSS).
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		// get current time
		String dataFormat = "yyyy-MM-dd HH:mm:ss.SSS";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dataFormat);

		// handle time
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		// set all data to make the email
		String strCurrentDate = sdf.format(calendar.getTime());

		return strCurrentDate;
	}

	/**
	 * format for the data of Date (yyyy-MM-dd HH:mm:ss.SSS).
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		// get current time
		String dataFormat = format;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dataFormat);

		// handle time
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		// set all data to make the email
		String strCurrentDate = sdf.format(calendar.getTime());

		return strCurrentDate;
	}

	/**
	 * convert date format "yyyy-MM-dd HH:mm:ss" to "ssmmHHddMMyy"
	 * 
	 * @param strDate
	 * @return
	 */
	public static String format(String strDate) {
		String ret = "";
		String dataFormat = "yyyy-MM-dd HH:mm:ss";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dataFormat);
		Date date = new Date();

		try {
			date = sdf.parse(strDate);
			ret = DateFormat.converseDate(date);
		} catch (ParseException pe) {
			dataFormat = "yyyy-MM-dd";
			sdf = new java.text.SimpleDateFormat(dataFormat);
			try {
				date = sdf.parse(strDate);
			} catch (ParseException e) {
				// 不处理异常

			}
			ret = DateFormat.converseDate(date);
		}

		return ret;
	}

	/**
	 * convert date format "ssmmHHddMMyy" to "WWddMMyy"
	 * 
	 * @param strDate
	 * @return
	 */
	public static String formatWeek(String strDate) {
		String ret = "";
		String dataFormat = "ssmmHHddMMyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dataFormat);
		Date date = new Date();

		try {
			date = sdf.parse(strDate);
			dataFormat = "WWddMMyy";
			sdf = new java.text.SimpleDateFormat(dataFormat);
			ret = sdf.format(date);
		} catch (ParseException pe) {
			dataFormat = "WWddMMyy";
			sdf = new java.text.SimpleDateFormat(dataFormat);
			ret = sdf.format(date);
		}

		return ret;
	}

	/**
	 * convert date format "ssmmHHddMMyy" to "ssmmHHddWMyy"
	 * 
	 * @param strDate
	 * @return
	 */
	public static String formatMW(String strDate) {
		String ret = "";
		String dataFormat = "ssmmHHddMMyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dataFormat);
		Date date = new Date();

		try {
			date = sdf.parse(strDate);
			int month = date.getMonth() + 1;
			int day = date.getDay();

			if (day == 0) {
				day = 7;
			}

			day = day << 1;
			day += month / 10;

			month = month % 10;

			String d = String.valueOf("0123456789ABCDEF".charAt(day));
			String m = String.valueOf("0123456789ABCDEF".charAt(month));

			ret = strDate.substring(0, 8) + d + m + strDate.substring(10, 12);

		} catch (ParseException pe) {
			ret = strDate;
		}

		return ret;
	}

	/**
	 * convert date format "1" to "2".
	 * 
	 * @param strDate
	 * @param from
	 * @param to
	 * @return
	 */
	public static String format(String strDate, int from, int to) {
		String ret = "";

		String fromFormat = getDateFormat(from);
		String toFormat = getDateFormat(to);

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				fromFormat);
		Date date = new Date();

		try {
			date = sdf.parse(strDate);

			int y = date.getYear();

			if (y < 100 && y >= 0) {
				date.setYear(y + 100);
			} else if (y < 0) {
				date = new Date();
			}

			sdf = new java.text.SimpleDateFormat(toFormat);
			ret = sdf.format(date);
		} catch (ParseException pe) {
			toFormat = "yyyy-MM-dd HH:mm:ss";
			sdf = new java.text.SimpleDateFormat(toFormat);
			ret = sdf.format(date);
		}

		return ret;
	}

	public static String getDateFormat(int type) {
		String dateFormat = "yyyy-MM-dd HH:mm:ss";

		switch (type) {
		case 1:
			dateFormat = "yyyy-MM-dd HH:mm:ss";
			break;

		case 2:
			dateFormat = "yyyyMMddHHmmss";
			break;

		case 3:
			dateFormat = "mmHHddMMyy";
			break;

		case 4:
			dateFormat = "yyyy-MM-dd HH:mm";
			break;

		case 5:
			dateFormat = "yyMMddHHmmss";
			break;

		case 6:
			dateFormat = "ssmmHHddMMyy";
			break;

		case 7:
			dateFormat = "dd-MM-yyyy HH:mm:ss";
			break;

		case 8:
			dateFormat = "dd-MM-yyyy HH:mm";
			break;
		case 9:
			dateFormat = "ddMM";
			break;
		case 10:
			dateFormat = "dd-MM";
			break;
		case 11:
			dateFormat = "mmHH";
			break;
		case 12:
			dateFormat = "HH:mm";
			break;
		case 13:
			dateFormat = "HHddMM";
			break;
		case 14:
			dateFormat = "dd-MM HH";
			break;
		case 15:
			dateFormat = "ddMMyyyyHHmmss";
			break;
		default:
			break;
		}

		return dateFormat;
	}

	public static Date convertToDate(String date) {
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		Date ret = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dateFormat);

		try {
			ret = sdf.parse(date);
		} catch (ParseException pe) {
			//
		}

		return ret;
	}

	public static Date convertToDate(String date, String dateFormat) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dateFormat);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
