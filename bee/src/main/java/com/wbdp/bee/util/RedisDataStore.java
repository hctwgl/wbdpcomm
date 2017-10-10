package com.wbdp.bee.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDataStore {
	//Redis服务器IP
    private static String ADDR = "120.76.138.73";
    
    //Redis的端口号
    private static int PORT = 6379;
    
    //访问密码,若你的redis服务器没有设置密码，就不需要用密码去连接
    private static String AUTH = "wisedata1222";
    
    //可用连接实例的最大数目，默认值为8；
    private static int MAX_TOTAL = 512;
    
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 50;
    
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
    private static int MAX_WAIT = 10000;
    
    private static int TIMEOUT = 10000;
    
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    
    private static JedisPool jedisPool = null;
    
    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT,AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

	/**
	 * 获取redis连接
	 * @return
	 */
	public static Jedis getconnetion(){
		try {
			if(jedisPool!=null){
				Jedis jedis = jedisPool.getResource();
				return jedis;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 释放redis资源
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	public static void close(Jedis jedis){
		if(jedis!=null){
			jedisPool.returnResourceObject(jedis);
		}
	}
	
	@Test
	public void test(){
		Jedis jedis = getconnetion();
		jedis.flushDB();
		Map map = new HashMap<String, String>();
		map.put("devicenuber", "51300500");
		map.put("time", "2017-06-05 13:03:00");
		map.put("tripid", "1234");
		map.put("msgType", "1");
		map.put("longitude", "113.1456");
		map.put("dimension", "22.2356");
		Map map1 = new HashMap<String, String>();
		map1.put("devicenuber", "51300500");
		map1.put("time", "2017-06-05 13:03:00");
		map1.put("tripid", "4321");
		map1.put("msgType", "1");
		map1.put("longitude", "113.1456");
		map1.put("dimension", "22.2356");
		String json = JSONObject.toJSON(map).toString();
		String strjson = JSONObject.toJSON(map1).toString();
		//jedis.sadd("ghh", "lkkl");
		//jedis.hmset("ceshi", map);
		//jedis.hmset("ceshi", map1);
		//jedis.flushDB();
		//String s = jedis.get("531610100016");
		//设置键的过期时间
		// jedis.expire("ceshi", 100);
		// List<String> list = jedis.hvals("ceshi");
		 //long time = jedis.ttl("ceshi");
		jedis.lpush("ceshi", json);
		jedis.lpush("ceshi", strjson);
		//jedis.rpush("ceshi", "heihei","hehe","haha");
		List<String> list = jedis.brpop( 0,"ceshi");
		//String str = jedis.lpop("ceshi");
		//System.out.println("最右侧："+str);
		// System.out.println(time);
		JSONObject obj = JSONObject.parseObject(list.get(1));
		 System.out.println(obj.get("devicenuber"));
		close(jedis);
	}
}
