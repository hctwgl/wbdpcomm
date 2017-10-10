package com.wbdp.bee.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilMD5 {

	
	/*
	 * 进行SHA256加密
	 */
	public static String toMD5(String strText){
	  //定义返回结果集
		String strResult=null;
	  //判断输入的字符串
		if (strText != null && strText.length() > 0)  
	    {  
	      try  
	      {  
	        // SHA 加密开始  
	        // 创建加密对象 并傳入加密類型  
	        MessageDigest messageDigest = MessageDigest.getInstance("MD5");  //SHA-256
	        // 传入要加密的字符串  
	        messageDigest.update(strText.getBytes());  
	        // 得到 byte 类型结果  
	        byte byteBuffer[] = messageDigest.digest();  
	        // 將 byte 转换为string  
	        StringBuffer strHexString = new StringBuffer();  
	        // 遍历 byte buffer  
	        for (int i = 0; i < byteBuffer.length; i++)  
	        {  
	          String hex = Integer.toHexString(0xff & byteBuffer[i]);  
	          if (hex.length() == 1)  
	          {  
	            strHexString.append('0');  
	          }  
	          strHexString.append(hex);  
	        }  
	        // 得到返回結果  
	        strResult = strHexString.toString();  
	      }  
	      catch (NoSuchAlgorithmException e){  
	        e.printStackTrace();  
	        }  
	     }
		return strResult;
	}	
		

}
