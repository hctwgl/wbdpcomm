package com.wbdp.util;



import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;
/**
 * 存放对应设备channel
 * @author wisedata003
 *
 */
public class DeviceChannelMap { 
	private static Map<String,Channel> map;
	static{
		 map = new HashMap<String, Channel>();
	}
	
	/**
	 * 获取通道
	 * @param key
	 * @return
	 */
	public static Channel  getChannel(String key){
		return map.get(key);
	}
	
	public static void saveChannel(String key,Channel channel){
		map.put(key, channel);
	}
}
