package com.wbdp.wx.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateFormat {
	/**
	  * 日期减几年
	  */
	 public static String dateMinusYear(String str) throws Exception {
	 
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	  Date dt = sdf.parse(str);
	  Calendar rightNow = Calendar.getInstance();
	  rightNow.setTime(dt);
	  rightNow.add(Calendar.YEAR, -1);// 日期减1年
	  Date dt1 = rightNow.getTime();
	  String reStr = sdf.format(dt1);
	  return reStr;
	 }
	 
	 /**
	  * 日期加几年
	  */
	 public static String dateAddYear(String str) throws Exception {
	 
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	  Date dt = sdf.parse(str);
	  Calendar rightNow = Calendar.getInstance();
	  rightNow.setTime(dt);
	  rightNow.add(Calendar.YEAR, 1);// 日期加1年
	  Date dt1 = rightNow.getTime();
	  String reStr = sdf.format(dt1);
	  return reStr;
	 }
	 
	 /**
	  * 日期减几月
	  */
	 public static String dateMinusMonth(String str,Integer num) throws Exception {
	 
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = sdf.parse(str);//将字符串生成Date
	  Calendar rightNow = Calendar.getInstance();
	  rightNow.setTime(dt);//使用给定的 Date 设置此 Calendar 的时间。 
	  rightNow.add(Calendar.MONTH, -num);// 日期减1个月
	  Date dt1 = rightNow.getTime();//返回一个表示此 Calendar 时间值的 Date 对象。
	  String reStr = sdf.format(dt1);//将给定的 Date 格式化为日期/时间字符串，并将结果添加到给定的 StringBuffer。
	  return reStr;
	 }
	 
	 /**
	  * 日期加几月
	  */
	 public static String dateAddMonth(String str,Integer num) throws Exception {
	 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  Date dt = sdf.parse(str);
	  Calendar rightNow = Calendar.getInstance();
	  rightNow.setTime(dt);
	  rightNow.add(Calendar.MONTH, num);// 日期加3个月
	  // rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
	  Date dt1 = rightNow.getTime();
	  String reStr = sdf.format(dt1);
	  return reStr;
	 }
	 
	 
	 @Test
	 public void test() throws Exception{
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 System.out.println(dateAddMonth(sdf.format(new Date()),12));
	 }
}
