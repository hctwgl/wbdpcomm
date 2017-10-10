package com.wisemifi.wx.listener;
import java.util.TimerTask;

import redis.clients.jedis.Jedis;

import com.wisemifi.wx.util.Access_TokenUtil;
import com.wisemifi.wx.util.RedisDataStore;


/**
 * 定时更新accesstoke和JS_API_TICKET
 * @author 汪赛军
 * date:2017年8月9日上午11:20:16
 *
 */
public class AccesstokenRunnable extends TimerTask {
	Jedis jedis = null;
	public void run() {
		//获取Access_Token并存入缓存
		Access_TokenUtil.getAccess_Token();
		jedis = RedisDataStore.getconnetion();
		String access_token = jedis.get("access_token");
		//获取Jsapi_ticket并存入缓存中
		Access_TokenUtil.getJsapi_ticket();
	}

}