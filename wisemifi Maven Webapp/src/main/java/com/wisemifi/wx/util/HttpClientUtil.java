package com.wisemifi.wx.util;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.constant.DeveloInfor;

/**
 * http请求工具类
 * @author 汪赛军
 * date:2017年8月9日下午2:12:58
 *
 */
public class HttpClientUtil {
	private static Logger log = Logger.getLogger(HttpClientUtil.class);
	/**
	 * http请求get方法
	 */
	public static String httpGet(String url){
		String str = "";
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
	             str=EntityUtils.toString(entity, "utf-8");  
	           log.info("服务器的响应是:"+str);  
	        }else{  
	        	log.info("响应失败!");  
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		 return str;
	}
	/**
	 * http请求post方法
	 * @param url
	 */
	public static String httpPost(String url,String json){
		//String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		//post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        String str = "";
        try {
        	//Jedis jedis = RedisDataStore.getconnetion();
        	//String strUrl = url.replace("ACCESS_TOKEN", jedis.get("myaccess_token"));
        	 HttpPost method = new HttpPost(url);
            if (null != json) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(json, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
              
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity(),"UTF-8");
                    /*if (true) {
                        return null;
                    }*/
                    /**把json字符串转换成json对象**/
                  //System.out.println("服务器返回的数据："+str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
	}
}	
