package com.wbdp.bee.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class UtilHttpSend {

	private static Logger logger = Logger.getLogger(UtilHttpSend.class);
	
	
	/**
	 * 采用post请求调用接口
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doPost(String url,String json){
		  HttpClient httpclient = new DefaultHttpClient();  
	        HttpPost httppost = new HttpPost(url);  
	        String strResult = "";          
	        try {      
	        	//设置请求格式与编码
	        	httppost.addHeader("Content-Type","application/json;charset=UTF-8");
                //设置发送请求的内容信息格式与编码
	            StringEntity s = new StringEntity(json.toString(),"utf-8");  
	            s.setContentEncoding("UTF-8");    
	            s.setContentType("application/json");
	            httppost.setEntity(s); 
	  System.out.println(json); 
                //发送请求
	            HttpResponse response = httpclient.execute(httppost);  
	            
	            //如果成功，HTTP服务器响应200.否则返回请求HTTP错误码
	               if (response.getStatusLine().getStatusCode() == 200) {  
	                    /*读返回数据*/  
	                    strResult = EntityUtils.toString(response.getEntity());  
	                   logger.info("doPost请求发送成功"); 
	                } else {  
	                    String err = response.getStatusLine().getStatusCode()+"";  
	                    strResult += "发送失败:"+err;  
	                    logger.error("doPost请求发送失败,错误code:"+err);
	                }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }    
	        return strResult;  
	}
	
	
	
	
	
	/**
	 * 采用get请求调用接口
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doGet(String url,HashMap<String, String> params){  
	        String strResult = "";          
	        try {   
	        	//拼接进来的参数(url?key=value)
	        	 if (params != null) {  
	        		 Iterator<String> it = params.keySet().iterator();  
	        		  StringBuffer sb = null;  
	        		  while (it.hasNext()) {  
	        		   String key = it.next();  
	        		   String value = params.get(key);  
	        		   if (sb == null) {  
	        		    sb = new StringBuffer();  
	        		    sb.append("?");  
	        		   } else {  
	        		    sb.append("&");  
	        		   }  
	        		   sb.append(key);  
	        		   sb.append("=");  
	        		   sb.append(value);  
	        		  }  
	        		  url += sb.toString();  
	        		 }  
	        	 
	        	//实例化httpClient客户端 
	   		  HttpClient httpclient = new DefaultHttpClient(); 
	   		    //实例化HttpGet方法，并且添加请求的url
	        	 HttpGet httpget = new HttpGet(url);   
                //设置发送请求的头部信息
                httpget.addHeader("Authorization", "APPCODE fc946a00848444188f87ff821a428cc1");
                //发送请求，并接受返回数据
	            HttpResponse response = httpclient.execute(httpget);  
	            
	                if (response.getStatusLine().getStatusCode() == 200) {  
	                    /*读返回数据*/  
	                    strResult = EntityUtils.toString(response.getEntity());  
	                   logger.info("doGet请求发送成功"); 
	                } else {  
	                    String err = response.getStatusLine().getStatusCode()+"";  
	                    strResult += "发送失败:"+err;  
	                    logger.error("doGet请求发送失败");
	                }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }    
	        return strResult;  
	}






	
}
