package com.wbdp.cy;

import java.util.Arrays;

import org.apache.log4j.Logger;


/**
 * 车云网通信协议数据通用处理类，用于去包头包尾，异或校验，转义等功能
 * @author 汪赛军
 *
 */
public class CyDataUtil {
	private static Logger logger = Logger.getLogger(CyDataUtil.class);
	/**
	 * 去包头包尾
	 * @param str
	 * @return
	 */
	public static String delStr(String str){
		String sstr = str.substring(2);
		//String sstr2 = sstr.replaceAll("29", "");
		//System.out.println(sstr2);
		return sstr;
	}
	
	
	/**
	 * 对字符串进行解转义
	 * @param str
	 * @return
	 */
	public static String escapeStr(String str){
		//转义'('与')'符号
		String s = str.replaceAll("3D15", "28").replaceAll("3D14", "29").replaceAll("3D00", "3D");
		//转义)符号
		/*int x = Integer.parseInt("29",16);
		int y = Integer.parseInt("3d",16);
		String t = Integer.toHexString(x^y);*/
		//String ss = s.replaceAll("3d14", "29");
		//转义‘3d’符号
		
		return s;
	}
	/**
	 * 对字符进行转义
	 * @param str
	 * @return
	 */
	public static String strEscape(String str){
		String s = str.replaceAll("28", "3D15").replaceAll("29", "3D14").replaceAll("3D", "3D00");
		return s;
	}
	/**
	 * 将接收的数据进行异或运算,当返回的数值为校验码的十进制表示
	 * @param str 需要进行异或运算的字符串数组
	 * @return
	 */
	public static int xorStr(String[] str){
		//其他数据与0进行异或运算后等于本身，所以这里将0作为初始值进行异或运算
		int s = 0;
		int length = str.length-1;
		for(int i = 0;i<length;i++){
				s^=Integer.parseInt(str[i],16);
		}
		return s;
	}
	
	/**
	 * 将字符串每隔两个字符分割
	 * @param str 需要分割的字符串
	 * @return
	 */
	public static String[] splitStr(String str){
		int m=str.length()/2;
		if(m*2<str.length()){
		m++;
		}
		String[] strs=new String[m];
		int j=0;
		for(int i=0;i<str.length();i++){
		if(i%2==0){//每隔两个
		strs[j]=""+str.charAt(i);
		}else{
		strs[j]=strs[j]+""+str.charAt(i);//将字符加上两个空格
		j++;
		}           
		}
		//这里的replaceAll的作用是去掉字符串两端的"[]"符号
		return Arrays.toString(strs).replaceAll("[\\{\\}\\[\\]]", "").split(", ");
		}
	
}
