package com.wisemifi.wx.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.constant.DeveloInfor;
import com.wisemifi.wx.entity.CustomerOrder;

/**
 * 涂推送消息工具类
 * @author 汪赛军
 * date:2017年8月16日下午2:44:43
 *
 */
public class PushUtil {
	/**
	 * 购买成功后进行消息推送
	 * @param str
	 * @return
	 */
	public static String payPush(CustomerOrder customerOrder){
		Map<String, Object> outMap = new HashMap<String, Object>();
		JSONObject entyJson = new JSONObject();
		JSONObject data_json = new JSONObject();
		JSONObject first = new  JSONObject();
		JSONObject keyword1 = new  JSONObject();
		JSONObject keyword2 = new  JSONObject();
		JSONObject remark = new  JSONObject();
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2"+
		"&redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fwx%2Fmyinfo%2Ftomyinfo?id=12&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		Jedis jedis = null;
		try {
			jedis =  RedisDataStore.getconnetion();
			entyJson.put("touser", customerOrder.getCustomerOpenId());
			entyJson.put("template_id", "qdzALUc3fpvh-8V7UO812YYlXtGkNJdkOs6noUOLqGo");
			entyJson.put("url", url);
			first.put("value", "您的订单订单支付成功");
			first.put("color", "#173177");
			keyword1.put("value", customerOrder.getPrice());
			keyword1.put("color", "#173177");
			keyword2.put("value", customerOrder.getGoodInfo());
			remark.put("value", "请确认您的订单消息");
			remark.put("color", "#173177");
			data_json.put("first", first);
			data_json.put("orderMoneySum", keyword1);
			data_json.put("orderProductName", keyword2);
			data_json.put("Remark", remark);
			entyJson.put("data", data_json);
			String send_push_mess_url = DeveloInfor.SEND_PUSH_MESS_URL.replace("ACCESS_TOKEN",jedis.get("access_token"));
			String httpRequestjson = HttpClientUtil.httpPost(send_push_mess_url, entyJson.toString());
			if("ok".equals(httpRequestjson)){
				outMap.put("status", "SUCCESS");
				outMap.put("errmsg", "");
				return "";
			}else{
				outMap.put("status", "FALSE");
				outMap.put("errmsg", "");
				return "";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("errmsg", "");
			return "";
		}
	}
	@Test
	public void test(){
		Jedis jedis = RedisDataStore.getconnetion();
		Map<String, Object> outMap = new HashMap<String, Object>();
		JSONObject entyJson = new JSONObject();
		JSONObject data_json = new JSONObject();
		JSONObject first = new  JSONObject();
		JSONObject keyword1 = new  JSONObject();
		JSONObject keyword2 = new  JSONObject();
		JSONObject remark = new  JSONObject();
		String url =  "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2"+
				"&redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fmyinfo%2Ftomyinfo&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		try {
			first.put("value", "您的任性刷订单通过审核，请前往任性刷记录查看");
			first.put("color", "#173177");
			keyword1.put("value", 800);
			keyword1.put("color", "#173177");
			keyword2.put("value", "任性刷订单通过审核");
			keyword2.put("color", "#173177");
			remark.put("value", "任性刷订单通过审核");
			remark.put("color", "#173177");
			data_json.put("first", first);
			data_json.put("orderMoneySum", keyword1);
			data_json.put("orderProductName", keyword2);
			data_json.put("Remark", remark);
			entyJson.put("data", data_json);
			entyJson.put("touser", "ojzcuwq6u4dfe8KzZcwuyGtqxc5s");
			entyJson.put("template_id", "emqdETgGLBbqpl8FXS8NPMdAk4betA_ST9Zrbf91iLs");
			entyJson.put("url", url);//jedis.get("access_token")
			String send_push_mess_url = DeveloInfor.SEND_PUSH_MESS_URL.replace("ACCESS_TOKEN","mnIu8XO3jJVoc9PwepGHamupJ8-AiNjiocavaS4oJqHE-dWl3f3lDqS0fgVLTFnZEQwAhN0jjuA9cUAYB0PgUpx8z-HNcfxD6ai129JUvAcZHhT3R0yyoDTjLgTj_LqEJWRcAIAUHC");
			String httpRequestjson = HttpClientUtil.httpPost(send_push_mess_url, entyJson.toString());
			System.out.println(httpRequestjson);
			System.out.println("access:"+jedis.get("access_token"));
			System.out.println(entyJson.toString());
			if("ok".equals(httpRequestjson)){
				outMap.put("status", "SUCCESS");
				outMap.put("errmsg", "");
				
			}else{
				outMap.put("status", "FALSE");
				outMap.put("errmsg", "");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("status", "EXCEPTION");
			outMap.put("errmsg", "");
			
		}
	}
}
