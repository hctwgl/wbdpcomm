package com.wbdp.util;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
public class HttpUtil {
	public static JSONObject doPostStr(String url,String outStr){
		@SuppressWarnings("deprecation")
		HttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpost.setEntity(new StringEntity(outStr,"UTF-8"));
			HttpResponse response = client.execute(httpost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			System.out.println("返回值："+result);
			//jsonObject = JSONObject.parseObject(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
 public static String postMethod(String u,String outStr){
	 //String add_url = "http://www.wisedp.com/WbdpSSM/wc/push/faultremind";
     //String query = " {\"mainUser\":{\"name\":\""+name+"\",\"gender\":\""+gender+"\",\"birthDate\":\""+birthDate+"\",\"birthDateAccurate\":\""+birthDateAccurate+"\",\"addrId\":\""+addrId+"\"},\"productId\":\""+productId+"\"}";
     try {
         URL url = new URL(u);
         HttpURLConnection connection = (HttpURLConnection)url.openConnection();
         connection.setDoInput(true);
         connection.setDoOutput(true);
         connection.setRequestMethod("POST");
         connection.setUseCaches(false);
         connection.setInstanceFollowRedirects(true);
         connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
         connection.connect();
         DataOutputStream out = new DataOutputStream(connection.getOutputStream());
         JSONObject obj = new JSONObject();
          
         obj.put("faultremind", outStr);
         out.writeBytes(obj.toString());
         out.flush();
         out.close();
         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         String lines;
         StringBuffer sbf = new StringBuffer();
          while ((lines = reader.readLine()) != null) {
                 lines = new String(lines.getBytes(), "utf-8");
                 sbf.append(lines);
             }
             System.out.println(sbf.toString());
             reader.close();
             // 断开连接
             connection.disconnect();
     } catch (MalformedURLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
	return null;
 }
}
