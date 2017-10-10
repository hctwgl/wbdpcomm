package com.wbdp.wx.utils.string;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class RandomStrUtils {
	/**
	 * 生成随机字符串
	 * @return
	 */
	public static String RandomStr() {
        String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String b = "";
        char[] rands = new char[10]; 
        for (int i = 0; i < rands.length; i++) 
        { 
            int rand = (int) (Math.random() * a.length()); 
            rands[i] = a.charAt(rand); 
        } 
        for(int i=0;i<rands.length;i++){
            b += rands[i];
        }
		return b;
    }
	/**
	 * 生成微信支付接口的订单号
	 * @return
	 */
	public static String getSpbill_create_ip(){
		String format = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date());
		String randomStr = RandomStr();
		StringBuffer buffer = null;
		if(!format.equals("")&&!randomStr.equals("")){
				buffer = new StringBuffer();
				buffer.append(format);
				buffer.append(randomStr);
		}
		return buffer.toString();
	}
	/**
	 * 商户订单号
	 * 组成：mch_id+yyyymmdd+10位一天内不能重复的数字。 
	 * @param mch_id 
	 * @return
	 */
	public static String getRebPacktMch_billno(String mch_id){
		
		String format = new SimpleDateFormat("yyyyMMdd") .format(new Date());
		String randomStr = RandomStr();

		StringBuffer buffer = null;
		if(!format.equals("")&&!randomStr.equals("")){
				buffer = new StringBuffer();
				buffer.append(mch_id);
				buffer.append(format);
				buffer.append(randomStr);
		}

		return buffer.toString();
	}
	
	
	@Test
	public void test(){
//		String spbill_create_ip = getSpbill_create_ip();
//		System.out.println(spbill_create_ip);
//		int length = spbill_create_ip.length();
//		System.out.println(length);
//		String rebPacktMch_billno = getRebPacktMch_billno();
//		System.out.println("rebPacktMch_billno:"+rebPacktMch_billno);
//		System.out.println("rebPacktMch_billno:length"+rebPacktMch_billno.length());
	}
	
}