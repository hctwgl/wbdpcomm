package com.wbdp.tsp.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



/**
*全国车辆违章调用示例代码 － 聚合数据
*在线接口文档：http://www.juhe.cn/docs/36
**/
 
public class UtilJuhe {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 20000;
    public static final int DEF_READ_TIMEOUT = 20000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    //配置您申请的KEY
    public static final String APPKEY ="078b80f2a740778b877ea7ec58cc731a";
 
    
     public static void main(String[] args) {
		Map<String, String> map=getRequest1("武汉");
		System.out.println(getRequest2("鄂A95JT6", "J62692","LSVCZ6A4XFN134398", map));
	}
    
     
     //1.获取支持城市参数接口
     public  static  Map<String,  String>  getRequest1(String cityName){
             String  result  =null;
             String  url  ="http://v.juhe.cn/wz/citys";//请求接口地址
             Map  params  =  new  HashMap();//请求参数
                     params.put("province","");//默认全部，省份简写，如：ZJ、JS
                     params.put("dtype","");//返回数据格式：json或xml或jsonp,默认json
                     params.put("format","2");//格式选择1或2，默认1
                     params.put("callback","");//返回格式选择jsonp时，必须传递
                     params.put("key",APPKEY);//你申请的key

             try  {
                     result  =net(url,  params,  "GET");
                   //获取result内容
                     JSONArray  item=(JSONArray)  JSONArray.parseObject(result).get("result");
               Map<String,  String>  resultMap=new  HashMap<String,  String>();   
               if(item!=null){
            	   int size = item.size();
            	   for(int  i=0;i<size;i++){
                       JSONArray  item2=item.getJSONObject(i).getJSONArray("citys");    
                       for(int  j=0;j<item2.size();j++){
                               if(item2.getJSONObject(j).getString("city_name").equals(cityName)){
                               resultMap.put("city_name",  item2.getJSONObject(j).getString("city_name"));
                               resultMap.put("city_code",  item2.getJSONObject(j).getString("city_code"));
                               resultMap.put("engine",  item2.getJSONObject(j).getString("engine"));
                               resultMap.put("engineno",  item2.getJSONObject(j).getString("engineno"));
                               resultMap.put("classa",  item2.getJSONObject(j).getString("classa"));
                               resultMap.put("class",  item2.getJSONObject(j).getString("class"));
                               resultMap.put("classno",  item2.getJSONObject(j).getString("classno"));
                                   return  resultMap;
                               };
                        }
                       }
               }     
             }  catch  (Exception  e)  {
                     e.printStackTrace();
             }
                 return  null;
     }
     
 
    
    /**
 	 * 请求违章查询接口
 	 * @param cityCode 城市代码
 	 * @param platenumber 车牌号
 	 * @param enginenumber 发动机号
 	 * @param vin 车架号
 	 * @return
 	 */
	public static String getRequest2(String platenumber,String enginenumber,String vin,Map<String, String> parMap){
        String result =null;
        String url ="http://v.juhe.cn/wz/query";//请求接口地址
         Map params = new HashMap();//请求参数
			 params.put("dtype","json");//返回数据格式：json或xml或jsonp,默认json
	         params.put("callback","");//返回格式选择jsonp时，必须传递
	         params.put("key",APPKEY);//你申请的key
	         params.put("city",parMap.get("city_code"));//城市代码 *
	         params.put("hphm",platenumber);//号牌号码 完整7位 ,需要utf8 urlencode*
	         params.put("hpzl","02");//号牌类型，默认02
//	         params.put("engineno",engineNum);//发动机号 (根据城市接口中的参数填写)
//	         params.put("classno",carDriveNum);//车架号 (根据城市接口中的参数填写)
	         // TODO
         if(Integer.parseInt(parMap.get("engine"))==1){
        	 int engineno = Integer.parseInt(parMap.get("engineno"));
        	 if(engineno==0)
                 params.put("engineno",enginenumber);//发动机号 (根据城市接口中的参数填写)
        	 else
        		 params.put("engineno",enginenumber.substring(enginenumber.length()-engineno,
        				 enginenumber.length()));//发动机号 (根据城市接口中的参数填写)
         }
         if(Integer.parseInt(parMap.get("classa"))==1){
        	 int classno = Integer.parseInt(parMap.get("classno")); 
        	 if(classno==0)
                 params.put("classno",vin);//车架号 (根据城市接口中的参数填写)
        	 else
        		 params.put("classno",vin.substring(vin.length()-classno,
        				 vin.length()));//车架号 (根据城市接口中的参数填写)
         }
        try {
            result =net(url, params, "GET");
        //   JSONObject object = JSONObject.parseObject(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
 
    //3.接口剩余请求次数查询
    public static void getRequest3(){
        String result =null;
        String url ="http://v.juhe.cn/wz/status";//请求接口地址
        Map params = new HashMap();//请求参数
            params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
            params.put("dtype","");//返回数据的格式,xml或json，默认json
 
        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(object.getIntValue("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
 
 
  
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                        out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}