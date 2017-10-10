package com.wbdp.util;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
/**
 * 将设备与UUID放入redis
 * @author wisedata003
 * 2017-6-9
 */
public class ThreadUtil extends Thread{
	private static Logger logger = Logger.getLogger(ThreadUtil.class);
	@Override
	public void run() {
		Jedis jedis = null;
		try {
			//查询设备对应的UUID
			Map<String,String> m = DeviceData.searchAlert();
			//获取redis连接
			 jedis = RedisDataStore.getconnetion();
			int size = m.size();
				while(true){
					for (Map.Entry<String, String> entry : m.entrySet()) {  
						System.out.println(entry.getKey()+" "+entry.getValue());
						 jedis.set(entry.getKey(), entry.getValue()); 
					}  
					Thread.sleep(1000*3600);
				}
			
		} catch (Exception e) {
			logger.error(e);
		}finally{
			RedisDataStore.close(jedis);
		}
		
	}
}
