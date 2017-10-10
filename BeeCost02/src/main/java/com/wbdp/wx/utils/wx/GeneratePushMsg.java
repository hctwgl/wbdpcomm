package com.wbdp.wx.utils.wx;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.wbdp.wx.constant.WXStatic;
import com.wbdp.wx.constant.WXURL;
import com.wbdp.wx.utils.File.FileUitls;
import com.wbdp.wx.utils.http.HttpUtil;

import net.sf.json.JSONObject;
/**
 * 微信推送消息
 * @author wisedata005
 */
public class GeneratePushMsg {
	/**日志log*/
	private static Logger log = Logger.getLogger(GeneratePushMsg.class);
	@Test
	public void test(){
		String url = "http://desktop-h6v9k51:8080/BeeCost/push/orderPush";
		JSONObject json = new JSONObject();
			json.put("ownerwx", "odXEdwUQf65snohyZAkjTbn7qqfc");
			json.put("goodName", "华为P10");
			json.put("orderStatus", 1);
			json.put("goodAttribute", "星空灰，32G，深圳电信，套餐A");
			json.put("reason", "身份证资料错误，需重新提交");
			
		String doPostStr = HttpUtil.pushGet(url, json);
		System.out.println(doPostStr);
	}
	
	/**
	 * 转换字符串
	 * @param errjson
	 * @return
	 */
	public static JSONObject ChangeToBePay(JSONObject errjson){
		if(errjson!=null){
			JSONObject trueJons = new JSONObject();

    		JSONObject ownerwx = (JSONObject) errjson.get("ownerwx");//微信号
    		trueJons.put("ownerwx", ownerwx.get("ownerwx"));
    		JSONObject platenumber = (JSONObject) errjson.get("platenumber");//车牌号
    		trueJons.put("platenumber", platenumber.get("platenumber"));
    		JSONObject submitDate = (JSONObject) errjson.get("submitDate");//时间
    		trueJons.put("submitDate", submitDate.get("submitDate"));
    		JSONObject reason = (JSONObject) errjson.get("reason");//原因
    		trueJons.put("reason", reason.get("reason"));

    		log.info("trueJons:"+trueJons);
    		return trueJons;
		}
		
		return null;
	}
	
	/**
	 * 订单推送
	 * @param ownerwx
	 * @param orderStatus
	 * @param goodName
	 * @param goodAttribute
	 * @param reason
	 * @return
	 */
	public static JSONObject generateMileage(String ownerwx,
			int orderStatus, String goodName, String goodAttribute,
			String reason) {
		JSONObject entyJson = new JSONObject();
		JSONObject data_json = new JSONObject();
		entyJson.put("openID", ownerwx);
		entyJson.put("template_id","9vFC0Q-0LYbwwCHMPXvl9x0WsjpRoV5x_aL-JAl27FE");
			
		JSONObject first = new  JSONObject();
			if(orderStatus==0)
				first.put("value", "您提交的订单已通过审核");
			else
				first.put("value", "您提交的订单未通过审核");
			first.put("color", "#173177");
		data_json.put("first", first);
		
		JSONObject keyword1 = new  JSONObject();
			if(orderStatus==0)
				keyword1.put("value", "已通过");
			else
				keyword1.put("value", "未通过");
			keyword1.put("color", "#173177");
		data_json.put("keyword1", keyword1);
		
		JSONObject keyword2 = new  JSONObject();
			keyword2.put("value", goodName);
			keyword2.put("color", "#173177");
		data_json.put("keyword2", keyword2);
		

		
		JSONObject keyword3 = new  JSONObject();
			keyword3.put("value", goodName);
			keyword3.put("color", "#173177");
		data_json.put("keyword3", keyword3);
		

		
		JSONObject remark = new  JSONObject();
			if(orderStatus==0){
				remark.put("value", "感谢您的使用，期待下次的服务。");
			}else{
				remark.put("value", "未通过原因："+reason+"\r\n感谢您的使用，期待下次的服务。");
			}
			remark.put("color", "#173177");
		data_json.put("remark", remark);
		
		entyJson.put("data_json", data_json);
		log.info("entyJson:"+entyJson);
		
		return entyJson;
	}
}
