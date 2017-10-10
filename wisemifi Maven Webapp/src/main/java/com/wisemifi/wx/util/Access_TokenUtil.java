package com.wisemifi.wx.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.constant.DeveloInfor;

/**
 * 获取access_token
 * @author 汪赛军
 * date:2017年7月31日下午4:06:24
 *
 */
public class Access_TokenUtil {
	private static Logger log = Logger.getLogger(Access_TokenUtil.class);
	/**
	 * 获取普通access_token
	 * @return
	 */
	public static String getAccess_Token(){
		String url = DeveloInfor.ACCESS_TOKEN_URL.replace("APPID", DeveloInfor.AppID).replace("APPSECRET", DeveloInfor.AppSecret);
		Jedis jedis = null;
		try {
			//1.创建客户端访问服务器的httpclient对象   打开浏览器  
	        HttpClient httpclient=new DefaultHttpClient(); 
	        //2.以请求的连接地址创建get请求对象     浏览器中输入网址  
	        HttpGet httpget=new HttpGet(url);  
	      //3.向服务器端发送请求 并且获取响应对象  浏览器中输入网址点击回车  
	        HttpResponse response=httpclient.execute(httpget);  
	      //4.获取响应对象中的响应码  
	        StatusLine statusLine=response.getStatusLine();//获取请求对象中的响应行对象  
	        int responseCode=statusLine.getStatusCode();//从状态行中获取状态码  
	        if(responseCode==200){  
	            //5.获取HttpEntity消息载体对象  可以接收和发送消息  
	            HttpEntity entity=response.getEntity();  
	            //EntityUtils中的toString()方法转换服务器的响应数据  
	            String str=EntityUtils.toString(entity, "utf-8");  
	            System.out.println("服务器的响应是:"+str);  
	            JSONObject obj = JSON.parseObject(str);
	            String access_token = obj.getString("access_token");
	            jedis = RedisDataStore.getconnetion();
	            //将获取的access_token存入redis中
	            if(access_token==null){
	            	log.info("token为空");
	            }else{
	            	jedis.set("access_token", access_token);
	            }
	            System.out.println("存入缓存的access_token："+jedis.get("access_token"));
	        }else{  
	            System.out.println("响应失败!");  
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			RedisDataStore.close(jedis);
		}
		return null;
	}
	
	/**
	 * 获取jsapi_ticket
	 * @return
	 */
	public static String getJsapi_ticket(){
		Jedis jedis = null;
		try {
			jedis = RedisDataStore.getconnetion();
			String url = DeveloInfor.JSAPI_TICKET_URL.replace("ACCESS_TOKEN",jedis.get("access_token"));
			//1.创建客户端访问服务器的httpclient对象   打开浏览器  
	        HttpClient httpclient=new DefaultHttpClient(); 
	        //2.以请求的连接地址创建get请求对象     浏览器中输入网址  
	        HttpGet httpget=new HttpGet(url);  
	      //3.向服务器端发送请求 并且获取响应对象  浏览器中输入网址点击回车  
	        HttpResponse response=httpclient.execute(httpget);  
	      //4.获取响应对象中的响应码  
	        StatusLine statusLine=response.getStatusLine();//获取请求对象中的响应行对象  
	        int responseCode=statusLine.getStatusCode();//从状态行中获取状态码  
	        if(responseCode==200){  
	            //5.获取HttpEntity消息载体对象  可以接收和发送消息  
	            HttpEntity entity=response.getEntity();  
	            //EntityUtils中的toString()方法转换服务器的响应数据  
	            String str=EntityUtils.toString(entity, "utf-8");  
	            System.out.println("服务器的响应是:"+str);  
	            JSONObject obj = JSON.parseObject(str);
	            String ticket = obj.getString("ticket");
	            
	            //将获取的access_token存入redis中
	            if(ticket==null){
	            	log.info("ticket为空");
	            }else{
	            	//将ticket存入redis中
	            	jedis.set("ticket", ticket);
	            }
	            log.info("存入缓存的ticket："+jedis.get("ticket"));
	        }else{  
	        	log.info("响应失败!");  
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			RedisDataStore.close(jedis);
		}
		 
		return null;
	}
}
