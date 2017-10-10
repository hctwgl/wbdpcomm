package com.wbdp.wx.utils.wx;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.wbdp.wx.utils.http.HttpUtil;




import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 实现微信信息推送
 * @author wisedata005
 */
public class PushMessUtils {
	/**日志log*/
	private static Logger log = Logger.getLogger(PushMessUtils.class);
	/**微信推送信息url*/
	public static final String SEND_PUSH_MESS_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	/**获取模板id的url*/
	public static final String GET_TEMPLATE_ID_URL = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
	/**
	 * 推送信息给用户
	 * @param entyJson 推送内容封装
	 * @param type 
	 * @return
	 */
	public static JSONObject pushMessgToUser(JSONObject entyJson, int type){
		JSONObject json = new JSONObject();
		if(entyJson!=null){
			
//			String template_id_short = entyJson.getString("template_id_short");
//			if(template_id_short==null){
//				log.error("template_id_short:为空");
//				json.put("errcode", "503");
//				json.put("errmsg", "template_id_short为空");
//				return json;
//			}
//			
//			JSONObject templateID = getTemplateID(template_id_short);
//			if(templateID==null){
//				log.error("获取推送模板id失败");
//				json.put("errcode", "503");
//				json.put("errmsg", "获取推送模板id失败");
//				return json;
//			}
			
			String template_id = entyJson.getString("template_id");//获得模板ID
			if(template_id==null){
				log.error("template_id:为空");
				json.put("errcode", "503");
				json.put("errmsg", "template_id为空");
				return json;
			}
				
			String openID = entyJson.getString("openID");//用户微信ID
			if(openID==null){
				log.error("openID:为空");
				json.put("errcode", "504");
				json.put("errmsg", "openID为空");
				return json;
			}
			
			
			JSONObject data_json = entyJson.getJSONObject("data_json");//推送内容
			if(data_json==null){
				log.error("data_json:为空");
				json.put("errcode", "506");
				json.put("errmsg", "data_json为空");
				return json;
			}
			
			json.put("touser", openID);
			json.put("template_id", template_id);
			if(type==1){
				String details_url = entyJson.getString("details_url");//查看详情的链接
				json.put("url", details_url);
			}
			json.put("data", data_json);
			
			log.info("json:"+json);
			//获取access_token
			String send_push_mess_url = SEND_PUSH_MESS_URL.replace("ACCESS_TOKEN",WXCacheUnit.getOrdinaryAccess_Token());
			//String send_push_mess_url = SEND_PUSH_MESS_URL.replace("ACCESS_TOKEN","JQLEKYSWKolRBMLx2wJnCrVhHkI6C1R9soStbJJml4CZpiyFB4AXOrs91uQO5qm3xR7lNv9V3lJZlL9KlmyduUa_bOVH4EhU4ZB86AB_3sM6K4AILDYy7Q2ZKD3n6ggjYXTiAHAGFB");
//			JSONObject httpRequestjson = null;
//				return null;
			JSONObject httpRequestjson = HttpUtil.httpRequest(send_push_mess_url, "POST", json.toString());
			
			if(httpRequestjson!=null){
				log.info("pushMessgToUser-httpRequestjson:不为空");
				log.info("httpRequestjson:"+httpRequestjson.toString());
				int errcode = (Integer) httpRequestjson.get("errcode");
				
				if(errcode==0){
					log.info("推送成功");
					return null;
				}else{
					String errmsg = (String) httpRequestjson.get("errmsg");
					log.error("推送失败");
					JSONObject newjson = new JSONObject();
					newjson.put("err_msg", "推送失败:"+errmsg);
					return newjson;
				}
			}else{
				log.error("pushMessgToUser-httpRequestjson:为空");
				JSONObject newjson = new JSONObject();
				newjson.put("errmsg", "httpRequestjson请求失败，返回空");
				return newjson;
			}
		}else{
			log.error("entyJson:为空");
			JSONObject newjson = new JSONObject();
			newjson.put("errmsg", "EntyJson为空");
			return newjson;
		}
	}
	
	/**
	 * 获取公众号模板详细id
	 * @param template_id_short
	 * @return
	 */
	@SuppressWarnings("unused")
	private static  JSONObject getTemplateID(String template_id_short){
		String ordinaryAccess_Token = WXCacheUnit.getOrdinaryAccess_Token();
		String template_id_url = GET_TEMPLATE_ID_URL.replace("ACCESS_TOKEN",ordinaryAccess_Token);
		JSONObject json = new JSONObject();
		json.put("template_id_short", template_id_short);
		JSONObject httpRequestjson = HttpUtil.httpRequest(template_id_url, "POST", json.toString());
		if(httpRequestjson!=null){
			log.info("getTemplateID-httpRequestjson:不为空");
			int errcode = (Integer) httpRequestjson.get("errcode");
			if(errcode==0){
				log.info("获取模板id成功");
				String template_id = (String)httpRequestjson.get("template_id");
				log.info("template_id:"+template_id);
				return httpRequestjson;
			}else{
				log.error("获取模板id失败");
				return null;
			}
		}else{
			log.error("getTemplateID-httpRequestjson:为空");
			return null;
		}
	}
	
	@Test
	public void test(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject entyJson = new JSONObject();

		JSONObject data_json = new JSONObject();
		entyJson.put("openID", "ojzcuwq6u4dfe8KzZcwuyGtqxc5s");
		entyJson.put("template_id","00-OkjwXozH9a4B_R_mX_D1-Qc6gMac-6xlDJVOEPWE");
		entyJson.put("details_url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&"+
				"redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2FtobrushPush?time="+"2017-09-10 17:16:09"+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		JSONObject first = new  JSONObject();
		first.put("value", "你好，你的套餐已生成，请及时确认。(七天后失效)");
		first.put("color", "#173177");
		data_json.put("first", first);
		
		JSONObject keyword1 = new  JSONObject();
			keyword1.put("value", 99+"元");
			keyword1.put("color", "#173177");
		data_json.put("keyword1", keyword1);
		
		JSONObject keyword2 = new  JSONObject();
			keyword2.put("value", 12);
			keyword2.put("color", "#173177");
		data_json.put("keyword2", keyword2);
		
		JSONObject keyword3 = new  JSONObject();
		keyword3.put("value", 1199+"元");
		keyword3.put("color", "#173177");
	data_json.put("keyword3", keyword3);
	
	JSONObject keyword4 = new  JSONObject();
	keyword4.put("value", "华为荣耀8");
	keyword4.put("color", "#173177");
	data_json.put("keyword4", keyword4);

		JSONObject remark = new  JSONObject();
			remark.put("value", "请点击确认，进行签名及人脸识别操作。");
			remark.put("color", "#173177");
		data_json.put("remark", remark);
		entyJson.put("data_json", data_json);
		log.info("entyJson:"+entyJson.toString());
		System.out.println(entyJson.toString());
		JSONObject pushMessgToUser = pushMessgToUser(entyJson,1);
		log.info("pushMessgToUser:"+pushMessgToUser);
	}
	
	
}
