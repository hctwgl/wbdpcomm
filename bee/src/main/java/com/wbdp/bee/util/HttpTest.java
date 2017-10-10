package com.wbdp.bee.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;



public class HttpTest {
	/**
	 * 使用get调用http接口，附带body参数
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception 
	 */
	public static String pushGet(String url,String json,String parm) throws Exception{
		 HttpClient client = new HttpClient(); 
	       GetMethod get = new GetMethod(url);
	       client.setConnectionTimeout(300*1000);
	       String responseString = null;
	       get.setRequestHeader("charset","UTF-8");
	       NameValuePair[] nav =new NameValuePair[1];
	       NameValuePair parametersBody= new NameValuePair();
	       parametersBody.setName(parm);
	       parametersBody.setValue(json);
	       nav[0] = parametersBody;
	       get.setQueryString(nav);
	       try {
				int statusCode = client.executeMethod(get);
				if(statusCode == HttpStatus.SC_OK){    
	               BufferedInputStream bis = new BufferedInputStream(get.getResponseBodyAsStream());    
	               byte[] bytes = new byte[1024];    
	               ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	               int count = 0;    
	               while((count = bis.read(bytes))!= -1){    
	                   bos.write(bytes, 0, count);    
	               }    
	               byte[] strByte = bos.toByteArray();    
	               responseString = new String(strByte,0,strByte.length,"utf-8");    
	               bos.close();    
	               bis.close();    
	               
//	   			json = JSONObject.fromObject(responseString);
//	               log.info(json.toString());
	           }    
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return responseString; 

	}
}

