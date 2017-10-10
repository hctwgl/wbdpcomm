package com.wisemifi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

import com.wisemifi.wx.util.Access_TokenUtil;
import com.wisemifi.wx.util.RedisDataStore;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = "classpath:applicationContext.xml")  
public class TestAccess_Token {
	/**
	 * 获取临时Access_Token
	 */
	@Test
	public void test(){
		Jedis jedis = RedisDataStore.getconnetion();
		System.out.println(jedis.get("access_token"));
	}
}
