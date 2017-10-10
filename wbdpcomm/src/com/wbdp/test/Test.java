package com.wbdp.test;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.cy.CyData;
import com.wbdp.cy.CyDataUtil;
import com.wbdp.cy.MessageBack;
import com.wbdp.util.Constants;
import com.wbdp.util.Converts;
import com.wbdp.util.DeviceChannelMap;
import com.wbdp.util.DeviceData;
import com.wbdp.util.GapMapUtils;
import com.wbdp.util.HexUtils;
import com.wbdp.util.HttpTest;
import com.wbdp.util.HttpUtil;
import com.wbdp.util.PositionUtil;
import com.wbdp.util.PushUtil;
import com.wbdp.util.RedisDataStore;
import com.wbdp.util.ThreadUtil;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	/*public static void main(String[] args) throws Exception {
		String asc = "2830140414000100880012013d150414090004224388161134912397005A3e29";
		String[] s = CyDataUtil.splitStr(asc);
		int len = asc.length();
		if(len>0){
			byte[] buffer = new byte[len/2];
			for(int i=0;i<len;i++){
				//Integer j = Integer.parseInt(s[i]);
				buffer[i] = 0;
			}
		}
		
		byte[] b =  Converts.str2Bcd(asc);
		String str = new String(b);
		System.out.println(str);
		
		String alarm = "2830140414000100880009013d150214230004005Af629";
		String del = CyDataUtil.delStr(alarm);
		String escape = CyDataUtil.escapeStr(del);
		String[] split = CyDataUtil.splitStr(escape);
		int i =  CyDataUtil.xorStr(split);
		System.out.println(i);
	}*/
	@org.junit.Test
	public void testClear(){
		String str = MessageBack.clearCode("53160801004930870012D021015012010113010102020118");
		System.out.println(str);
	}
	@org.junit.Test
	public void testOrderip(){
		String str = MessageBack.orderIP("28053160801066710011922E1682E12E10");
		System.out.println(str);
	}
	@org.junit.Test
	public void test(){
		String motor = HexUtils.hexString2binaryString("81");
		System.out.println(motor);
	}
	
	@org.junit.Test
	public void test02(){
		String s = "28053160801066610011922E1682E12E103529";
		//String s = "2853161010001620840040010017270317021417224393561141356257075E040E0000001700200100605B1538094BFF8F3D0001ABFF83014722FF000EFFFF0000B3B6253B6A6700000002014D";
		String del = CyDataUtil.delStr(s);
		String escape = CyDataUtil.escapeStr(del);
		String[] split = CyDataUtil.splitStr(escape);
		int i = CyDataUtil.xorStr(split);
				//^ Integer.parseInt(escape.substring(escape.length() - 2), 16);
		System.out.println("校验码：" + i);
	}
	
	public static  void  tt(String str,ByteBuf resp){
		Channel channel = DeviceChannelMap.getChannel(CyData.findDeviceid(str)+Constants.CHANNEL);
		if(channel.isActive()==true){
			channel.writeAndFlush(resp);
		}
		
	}
	@org.junit.Test
	public void ttt(){
		int i = 0x80;
		int j = 0x82;
		System.out.println((i&j)==0x80);
	}
	@org.junit.Test
	public void ditu(){
		//lat 小  log  大  
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)  
		String log = "114.208900";
		String lat = "22.721600";
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";  
        String res = "";     
        try {     
            URL url = new URL(urlString);    
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
            conn.setDoOutput(true);    
            conn.setRequestMethod("POST");    
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
            String line;    
           while ((line = in.readLine()) != null) {    
               res += line+"\n";    
         }    
            in.close();    
        } catch (Exception e) {    
            System.out.println("error in wapaction,and e is " + e.getMessage());    
        }
        JSONObject obj = JSON.parseObject(res);
        JSONArray arr = obj.getJSONArray("addrList");
        // 根据Bean类的到每一个json数组的项
        List<String> listBeans = JSON.parseArray(arr.toString(), String.class);
        for(String str:listBeans){
        	JSONObject sobj = JSON.parseObject(str);
            String name = sobj.getString("name");
            String admName = sobj.getString("admName").replaceAll(",", "");
            String addr = sobj.getString("addr");
           System.out.println(admName+addr+name); 
        }
	}
	/**
	 * 高德
	 */
	@org.junit.Test
	public void ditu2(){
		double log = 114.208900;
		double lat = 22.721600;
		double[] d = GapMapUtils.transform(lat, log);
		double dlog = d[0];
		double dlat = d[1];
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
        System.out.println(message);
        JSONObject obj = JSON.parseObject(message);
        String arr = obj.getString("regeocode");
        JSONObject aobj = JSON.parseObject(arr);
        System.out.println(arr);
        String str = aobj.getString("formatted_address");
      
       System.out.println(str);
       
	}
	
	@org.junit.Test
	public void test01() throws MalformedURLException{
		String log = "114.213400";
		String lat = "22.721600";
		BufferedReader in = null;
	    URL tirc = new URL("http://api.map.baidu.com/geocoder?location="+ lat+","+log+"&output=json&key="+"E4805d16520de693a3fe707cdc962045");  
	         try {
	in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
	String res;  
	        StringBuilder sb = new StringBuilder("");  
	        while((res = in.readLine())!=null){  
	            sb.append(res.trim());  
	        }  
	        String str = sb.toString();
	        System.out.println(str);
	       
	           /*if(StringUtils.isNotEmpty(str)){  
	             JsonNode jsonNode = mapper.readTree(str);
	             jsonNode.findValue("status").toString();
	             JsonNode resultNode = jsonNode.findValue("result");
	             JsonNode locationNode = resultNode.findValue("formatted_address");
	             System.out.println(locationNode);
	           }  */
	   } catch (Exception e) {
		   e.printStackTrace();
	} 
  }
	@org.junit.Test
	public void push() throws Exception{
		String url = "http://localhost:8080/HttpServerImpl/getTrip";
		Map<String,String> map = new HashMap<String, String>();
		map.put("id", "513");
		map.put("name", "你好");
		//String json = JSONObject.toJSON(map).toString();
		//JSONObject obj = new JSONObject();
		//obj.putAll(map);
		//String str = HttpTest.pushPost(url,obj, "json");
		//System.out.println(str);
	}
	
	@org.junit.Test
	public void testpush(){
		//查询设备对应的UUID
				Map<String,String> m = DeviceData.searchAlert();
				//获取redis连接
				Jedis jedis = RedisDataStore.getconnetion();
				int size = m.size();
				for (Map.Entry<String, String> entry : m.entrySet()) {  
					// jedis.set(entry.getKey(), entry.getValue()); 
				}  
				//jedis.flushDB();
				System.out.println(jedis.get("531608010591"));
	}
	@org.junit.Test
	public void testBCD()  {
		String str = "CMNET";
		try {
			System.out.println(HexUtils.str2HexStr(str, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] b = Converts.str2Bcd(str);
		//System.out.println(b[0]);
	}
	//将设备UUID存入缓存
	@org.junit.Test
	public void testmsg() {
		Jedis jedis = null;
		try {
			/*ThreadUtil t = new ThreadUtil();
			t.start();*/
			//查询设备对应的UUID
			Map<String,String> m = DeviceData.searchAlert();
			/*String uuid = DeviceData.searchUUID("531608010591");
			System.out.println(uuid);*/
			//获取redis连接
			 jedis = RedisDataStore.getconnetion();
			for (Map.Entry<String, String> entry : m.entrySet()) {  
				System.out.println(entry.getKey()+" "+entry.getValue());
				  jedis.set(entry.getKey(), entry.getValue()); 
			}  
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
