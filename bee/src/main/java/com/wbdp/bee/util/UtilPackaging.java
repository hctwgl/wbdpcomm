package com.wbdp.bee.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class UtilPackaging {

	
	/*
	 * MAP
	 */
	public static Map<String,Object> toResultMap(int pages,List<?> data){
	System.out.println(pages);	
	System.out.println(((pages+10)-1)/10);
		Map<String,Object> map=new HashMap<String, Object>();
			map.put("stutus","SUCCESS");
			map.put("msg", "");
			map.put("pages",((pages+10)-1)/10);
			map.put("data", data);
	System.out.println(JSON.toJSONString(map,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));		
			return map;  
	}
	
	/*
	 * MAP
	 */
	public static Map<String,Object> toResultMap(List<?> data){
		Map<String,Object> map=new HashMap<String, Object>();
			map.put("stutus","SUCCESS");
			map.put("msg", "");
			map.put("data", data);
	System.out.println(JSON.toJSONString(map,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));		
			return map;  
	}
	
	/*
	 * MAP
	 */
	public static Map<String,Object> toResultMap(Map<String,Object> data){
		Map<String,Object> map=new HashMap<String, Object>();
			map.put("stutus","SUCCESS");
			map.put("msg", "");
			map.put("data", data);
	System.out.println(JSON.toJSONString(map,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));		
			return map;  
	}
	
	/*
	 * String
	 */
	public static Map<String,Object> toResultString(String data){
		Map<String,Object> map=new HashMap<String, Object>();
			map.put("stutus","SUCCESS");
			map.put("msg", "");
			map.put("data", data);
	System.out.println(JSON.toJSONString(map,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));		
			return map;  
	}
	
	/*
	 * Integer
	 */
	public static Map<String,Object> toResultInteger(Integer flag){
		Map<String,Object> map=new HashMap<String, Object>();
		      map.put("stutus","SUCCESS");
		      map.put("msg", "");
		      map.put("data", flag);
		      System.out.println(JSON.toJSONString(map,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));	      
		   return map; 
	}
	
	/*
	 * 异常状态
	 */
	public static Map<String,Object> toException(Exception msg){
		Map<String,Object> map=new HashMap<String, Object>();
		    map.put("stutus", "EXCEPTION");
		    map.put("msg", msg.getClass().toString());
		    return map; //JSON.toJSONString(map,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);            
	  }
}
