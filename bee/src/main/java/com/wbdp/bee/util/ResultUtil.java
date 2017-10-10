package com.wbdp.bee.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装返回结果集
 * @author 汪赛军
 * date:2017年9月5日下午2:57:47
 *
 */
public class ResultUtil {
	/**
	 * 封装成功结果集,无参数
	 * @return
	 */
	public static Map<String,Object> success(){
		Map<String,Object> outMap = new HashMap<String, Object>();
		outMap.put("status", "SUCCESS");
		outMap.put("msg", "成功");
		outMap.put("data", "");
		return outMap;
	}
	
	/**
	 * 封装成功结果集,有参数
	 * @return
	 */
	public static Map<String,Object> success(String msg){
		Map<String,Object> outMap = new HashMap<String, Object>();
		outMap.put("status", "SUCCESS");
		outMap.put("msg", msg);
		outMap.put("data", "");
		return outMap;
	}
	/**
	 * 封装成功结果集,有参数
	 * @return
	 */
	public static Map<String,Object> success(Object obj,String msg){
		Map<String,Object> outMap = new HashMap<String, Object>();
		outMap.put("status", "SUCCESS");
		outMap.put("msg", msg);
		outMap.put("data", obj);
		return outMap;
	}
	
	/**
	 * 封装失败结果集,无参数
	 * @return
	 */
	public static Map<String,Object> error(){
		Map<String,Object> outMap = new HashMap<String, Object>();
		outMap.put("status", "FALSE");
		outMap.put("msg", "失败");
		outMap.put("data", "");
		return outMap;
	}
	/**
	 * 封装失败结果集,有一个参数
	 * @return
	 */
	public static Map<String,Object> error(String msg){
		Map<String,Object> outMap = new HashMap<String, Object>();
		outMap.put("status", "FALSE");
		outMap.put("msg", msg);
		outMap.put("data", "");
		return outMap;
	}
	
	/**
	 * 封装失败结果集,有两个参数
	 * @return
	 */
	public static Map<String,Object> error(Object obj,String msg){
		Map<String,Object> outMap = new HashMap<String, Object>();
		outMap.put("status", "FALSE");
		outMap.put("msg", msg);
		outMap.put("data", obj);
		return outMap;
	}
}
