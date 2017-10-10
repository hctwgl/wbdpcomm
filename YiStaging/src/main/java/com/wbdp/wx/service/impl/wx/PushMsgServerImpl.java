package com.wbdp.wx.service.impl.wx;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wbdp.wx.enums.ResultEnum;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.Result;
import com.wbdp.wx.service.wx.PushMsgServer;
import com.wbdp.wx.utils.ResultUtils;
import com.wbdp.wx.utils.wx.GeneratePushMsg;
import com.wbdp.wx.utils.wx.PushMessUtils;

@Service
public class PushMsgServerImpl implements PushMsgServer {

	/**日志log*/
	private static Logger log = Logger.getLogger(PushMsgServerImpl.class);
	

	@SuppressWarnings("rawtypes")
	@Override
	public Result checkAndPushOrder(String orderPush) throws CustomException {
		if(orderPush==null)
			throw new CustomException(ResultEnum.PUSHEMTY_ERROR);
		JSONObject mileageJson = JSONObject.fromObject(orderPush); 
		if(mileageJson.equals("")||mileageJson==null)
			throw new CustomException(ResultEnum.PUSHJSONEMTY_ERROR);
		String ownerwx = mileageJson.getString("ownerwx");
		if(ownerwx.equals("")||ownerwx==null)
        	throw new CustomException(ResultEnum.EMPTVOPPENDID);
		int orderStatus = mileageJson.getInt("orderStatus");
		String goodName = mileageJson.getString("goodName");
		if(goodName.equals("")||goodName==null)
        	throw new CustomException(ResultEnum.GOODNAMEEMPTY);
		String goodAttribute = mileageJson.getString("goodAttribute");
		if(goodAttribute.equals("")||goodAttribute==null)
        	throw new CustomException(ResultEnum.GOODATTRIBUTEEMPTY);
		String reason = mileageJson.getString("reason");
		JSONObject generateMileage = GeneratePushMsg.generateMileage(ownerwx,orderStatus,goodName,goodAttribute,reason);
		log.info("generateMileage为；"+generateMileage.toString());
		
		JSONObject pushMessgToUser = PushMessUtils.pushMessgToUser(generateMileage,0);
		log.info("pushMessgToUser为；"+pushMessgToUser);
		
		return ResultUtils.success(generateMileage);
	}
	
	/**
	 * 推送任性刷审核通过结果
	 */
	@Override
	public Map<String, Object> faceCheckPush(String json,
			HttpServletRequest request) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			com.alibaba.fastjson.JSONObject obj = JSON.parseObject(json);
			String openid = obj.getString("openid");
			String time = obj.getString("time");
			JSONObject entyJson = new JSONObject();
			JSONObject data_json = new JSONObject();
			entyJson.put("openID", openid);
			entyJson.put("template_id","emqdETgGLBbqpl8FXS8NPMdAk4betA_ST9Zrbf91iLs");
			entyJson.put("details_url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&"+
					"redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftomine&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
			JSONObject first = new  JSONObject();
			first.put("value", "您的任性刷订单通过审核，请前往任性刷记录查看");
			first.put("color", "#173177");
			data_json.put("first", first);
			
			JSONObject keyword1 = new  JSONObject();
				keyword1.put("value", "任性刷订单通过审核");
				keyword1.put("color", "#173177");
			data_json.put("keyword1", keyword1);
			
			JSONObject keyword2 = new  JSONObject();
				keyword2.put("value", time);
				keyword2.put("color", "#173177");
			data_json.put("keyword2", keyword2);
			JSONObject remark = new  JSONObject();
				remark.put("value", "点击查看详情!!");
				remark.put("color", "#173177");
			data_json.put("remark", remark);
			entyJson.put("data_json", data_json);
			JSONObject pushMessgToUser = PushMessUtils.pushMessgToUser(entyJson,1);
			String msg = pushMessgToUser.getString("errmsg");
			if("ok".equals(msg)){
				log.info("推送审核结果成功");
				outMap.put("msg", "ok");
				outMap.put("status", "SUCCESS");
			}
			log.info("pushMessgToUser:"+pushMessgToUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String, Object> faceCheckPushNO(String json,
			HttpServletRequest request) {
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			com.alibaba.fastjson.JSONObject obj = JSON.parseObject(json);
			String openid = obj.getString("openid");
			//String time = obj.getString("time");
			String reason = obj.getString("reason");
			JSONObject entyJson = new JSONObject();
			JSONObject data_json = new JSONObject();
			entyJson.put("openID", openid);
			entyJson.put("template_id","Id572Qe2trBZb__Pv1mhNK_OWCcG_1yAcIxMfEil6B8");
			entyJson.put("details_url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&"+
					"redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2Ftomine&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
			JSONObject first = new  JSONObject();
			first.put("value", "您的任性刷订单未通过审核，请前往我的信息中查看身份证照片是否清晰");
			first.put("color", "#173177");
			data_json.put("first", first);
			JSONObject keyword1 = new  JSONObject();
				keyword1.put("value", "任性刷订单未通过审核");
				keyword1.put("color", "#173177");
			data_json.put("keyword1", keyword1);
			
			JSONObject keyword2 = new  JSONObject();
				keyword2.put("value", "身份证审核未通过");
				keyword2.put("color", "#173177");
			data_json.put("keyword2", keyword2);
			JSONObject remark = new  JSONObject();
				remark.put("value", "点击查看详情!!");
				remark.put("color", "#173177");
			data_json.put("remark", remark);
			entyJson.put("data_json", data_json);
			JSONObject pushMessgToUser = PushMessUtils.pushMessgToUser(entyJson,1);
			String msg = pushMessgToUser.getString("errmsg");
			log.info("pushMessgToUser:"+pushMessgToUser);
			if("ok".equals(msg)){
				log.info("推送审核结果成功");
				outMap.put("msg", "ok");
				outMap.put("status", "SUCCESS");
				return outMap;
			}else{
				log.info("推送审核结果失败");
				outMap.put("msg", "error");
				outMap.put("status", "FALSE");
				return outMap;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 推送套餐确认通知
	 */
	@Override
	public Map<String, Object> pushPacInfo(String json,
			HttpServletRequest request) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//解析json数据
			com.alibaba.fastjson.JSONObject obj = JSON.parseObject(json);
			String openid = obj.getString("openid");
			Integer pacPeriods = obj.getInteger("pacPeriods");
			Integer pacMonthlyPrice = obj.getInteger("pacMonthlyPrice"); 
			Integer pacPrice = obj.getInteger("pacPrice");
			String phoneModel = obj.getString("phoneModel");
			String time = obj.getString("time");
			JSONObject entyJson = new JSONObject();
			
			JSONObject data_json = new JSONObject();
			entyJson.put("openID", openid);
			entyJson.put("template_id","00-OkjwXozH9a4B_R_mX_D1-Qc6gMac-6xlDJVOEPWE");
			entyJson.put("details_url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx535b458cce1aa572&"+
					"redirect_uri=http%3A%2F%2Fwww.wisedp.com%2FYiStaging%2FtobrushPush?time="+time+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
			JSONObject first = new  JSONObject();
			first.put("value", "你好，你的套餐已生成，请及时确认。(七天后失效)");
			first.put("color", "#173177");
			data_json.put("first", first);
			
			JSONObject keyword1 = new  JSONObject();
				keyword1.put("value", pacMonthlyPrice+"元");
				keyword1.put("color", "#173177");
			data_json.put("keyword1", keyword1);
			
			JSONObject keyword2 = new  JSONObject();
				keyword2.put("value", pacPeriods);
				keyword2.put("color", "#173177");
			data_json.put("keyword2", keyword2);
			
			JSONObject keyword3 = new  JSONObject();
			keyword3.put("value", pacPrice+"元");
			keyword3.put("color", "#173177");
		data_json.put("keyword3", keyword3);
		
		JSONObject keyword4 = new  JSONObject();
		keyword4.put("value", phoneModel);
		keyword4.put("color", "#173177");
		data_json.put("keyword4", keyword4);

			JSONObject remark = new  JSONObject();
				remark.put("value", "请点击确认，进行签名及人脸识别操作。");
				remark.put("color", "#173177");
			data_json.put("remark", remark);
			entyJson.put("data_json", data_json);
			
			log.info("entyJson:"+entyJson.toString());
			System.out.println(entyJson.toString());
			JSONObject pushMessgToUser = PushMessUtils.pushMessgToUser(entyJson,1);
			String msg = pushMessgToUser.getString("errmsg");
			log.info("pushMessgToUser:"+pushMessgToUser);
			if("ok".equals(msg)){
				log.info("推送审核结果成功");
				outMap.put("msg", "ok");
				outMap.put("status", "SUCCESS");
				return outMap;
			}else{
				log.info("推送审核结果失败");
				outMap.put("msg", "error");
				outMap.put("status", "FALSE");
				return outMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
