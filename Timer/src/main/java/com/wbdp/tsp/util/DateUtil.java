package com.wbdp.tsp.util;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.text.DateFormat;


/**
 * 时间处理类
 * @author 汪赛军
 *
 */
public class DateUtil {
	
	private static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	
	
	
	/**
	 * 将时间转换为时间戳
	 */
	public static Long dateStrlong(String str){
		try {
			Long date = format.parse(str).getTime();
			return date;
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将时间戳转换为时间
	 * @param args
	 */
	public static String longStrdate(Long l){
	    try {
	    	Long time=new Long(l);  
		    String d = format.format(time);  
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
	}
	/**
	 * 将时间转换成协议格式
	 * @param str
	 * @return
	 */
	public static String dateTostr(String str){
		
		return null;
	}
	
	/**
	 * 将格林威治时间转换为北京时间
	 * @param str
	 * @return
	 */
	public static String formatTime(String str){
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ); 
		Date date;
		String dateStr = "";
		try {
			//将时间字符串转换成时间对象
			date = sdf.parse(str);
			Calendar cal = Calendar.getInstance();  
		       cal.setTimeInMillis(date.getTime());  
		       cal.add(Calendar.HOUR, +8); 
		        dateStr = sdf.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	       return dateStr;
	}
	
	/**
	 * 获取当前时间前一天日期
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" ); 
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			date = calendar.getTime();
			//format.parse(format.format(date));
			Date d;
			d = format.parse(format.format(date));
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
