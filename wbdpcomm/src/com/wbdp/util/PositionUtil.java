package com.wbdp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 经纬度位置信息工具类
 * @author wisedata003
 *
 */
public class PositionUtil {
	/**
	 * 通过经纬度获取地址
	 * @param lat
	 * @param log
	 * @return
	 */
 public static String getPosition(double log,double lat){
	 double [] d = GapMapUtils.transform(lat, log);
	 String message = "";
     //String url = String.format("http://maps.google.cn/maps/api/geocode/json?latlng=%s,%s&language=CN",lat,log);
     String url = String.format("http://restapi.amap.com/v3/geocode/regeo?output=json&location=%s,%s&key=0667e2f44d2d7e7cca65cfe1e614399e&radius=1000&extensions=all",d[1],d[0]);
     URL myURL = null;
     URLConnection httpsConn = null;
     try {
         myURL = new URL(url);
     } catch (MalformedURLException e) {
       e.printStackTrace();
     }
     try {
         httpsConn = (URLConnection) myURL.openConnection();
         if (httpsConn != null) {
             InputStreamReader insr = new InputStreamReader(
                     httpsConn.getInputStream(), "UTF-8");
             BufferedReader br = new BufferedReader(insr);
             String data = null;
             while ((data = br.readLine()) != null) {
             message = message+data;
             }
             insr.close();
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
     JSONObject obj = JSON.parseObject(message);
     String arr = obj.getString("regeocode");
     JSONObject aobj = JSON.parseObject(arr);
     String str = aobj.getString("formatted_address");
	 return str;
 }
}
