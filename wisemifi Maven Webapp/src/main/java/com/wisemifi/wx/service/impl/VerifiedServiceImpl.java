package com.wisemifi.wx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.constant.DeveloInfor;
import com.wisemifi.wx.dao.CustomerMapper;
import com.wisemifi.wx.entity.Customer;
import com.wisemifi.wx.model.PageAuthorModel;
import com.wisemifi.wx.model.UserIDCard;
import com.wisemifi.wx.service.VerifiedService;
import com.wisemifi.wx.util.DloadImgUtil;
import com.wisemifi.wx.util.HttpClientUtil;
import com.wisemifi.wx.util.JSAPIUitl;
import com.wisemifi.wx.util.RedisDataStore;

/**
 * 维泽mifi实名认证业务实现类
 * @author 汪赛军
 * date:2017年8月10日上午10:42:21
 *
 */
@Service
public class VerifiedServiceImpl implements VerifiedService {
	private static Logger log = Logger.getLogger(VerifiedServiceImpl.class);
	@Autowired
	private CustomerMapper customerMapper;
	@Override
	public String getCodeAndopenID(String code, HttpSession session) {
		String result = "";
		String pathUrl = "card";
		PageAuthorModel pageModel = new PageAuthorModel();
		try {
			//拼接获取网页授权access_token接口
			String codeUrl = DeveloInfor.CODE_ACCESS_TOKEN.replace("APPID", DeveloInfor.AppID).replace("SECRET", DeveloInfor.AppSecret).replace("CODE",code);
			//获取微信服务端回复消息
			 result = HttpClientUtil.httpGet(codeUrl);
			 log.info("网页授权接收的数据："+result);
			//解析json数据
			 if(result!=null&&result!=""){
				 JSONObject obj = JSON.parseObject(result);
				 String access_token = obj.getString("access_token");
				 String openid = obj.getString("openid");
				 //获取sessionID
				 String sessionid = session.getId();
				 //将code与openID封装到模型中
				 pageModel.setCode(code);
				 pageModel.setOpenid(openid);
				 session.setAttribute(sessionid+"pageModel", pageModel);
			 }
			return pathUrl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将客户信息连同图片地址存入数据库
	 */
	@Override
	public Map<String, Object> saveImg(HttpSession session,
			UserIDCard userIDCard) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Customer customer = new Customer();
		Map<String, Object> outMap = new HashMap<String, Object>();
		String openid = "";
			try {
				String sessionid = session.getId();
				//从session中去除封装的openID
				PageAuthorModel pageModel = (PageAuthorModel)session.getAttribute(sessionid+"pageModel");
				if(pageModel!=null){
					openid = pageModel.getOpenid();
				}else{
					return null;
				}
				//得到图片保存地址
				String pathUrl = dowloadWXImg(userIDCard,openid);
				customer.setCustomerOpenid(openid);
				customer.setCustomerCardPic(pathUrl);
				customer.setCustomerCreateTime(format.format(new Date()));
				Integer result = customerMapper.insertCard(customer);
				outMap.put("data", result);
				outMap.put("status", "SUCCESS");
				return outMap;
			} catch (Exception e) {
				e.printStackTrace();
				outMap.put("data", "");
				outMap.put("status", "EXCEPTION");
				return outMap;
			}
	}


	/**
	 * 下载微信图片
	 * @param userIDCard
	 * @param openid
	 * @return
	 */
	public static String dowloadWXImg(UserIDCard userIDCard, String openid) {
		Jedis jedis = RedisDataStore.getconnetion();
	     String savePath = "/usr/local/tomcat7/webapps/wisemifi/downimg";
	        String oneserverId1 = userIDCard.getOneserverId1();
	        String oneserverId2 = userIDCard.getOneserverId2();
	        String oneserverId3 = userIDCard.getOneserverId3();
	        int type = userIDCard.getType();
	        log.info("type:"+type);
	        String timestamp = String.valueOf(new Date().getTime() / 1000);
	        String firstPath = "";
	        //获取普通access_token
	        String ordinaryAccess_Token = jedis.get("access_token");
	        if(type==1||type==3){
	        	 String firstPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                     oneserverId1, savePath,openid,1,timestamp);
	             if(!firstPathDload.equals(null))
	                 firstPath = "http://wisedp.com/wisemifi/downimg/"+openid+"-"+timestamp+"-01"+".png";
	        }
	        String secondPath = "";
	        if(type==2||type==3){
	            String secondPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                    oneserverId2, savePath,openid,2,timestamp);
	            if(!secondPathDload.equals(null))
	                secondPath = "http://wisedp.com/wisemifi/downimg/"+openid+"-"+timestamp+"-02"+".png";
	        }
	        String threePath = "";
	        if(type==4||type==3){
	       	 String threePathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	       			oneserverId3, savePath,openid,3,timestamp);
	            if(!threePathDload.equals(null))
	            	threePath = "http://wisedp.com/wisemifi/downimg/"+openid+"-"+timestamp+"-03"+".png";
	        }
	        
	        if(type==5){

	       	   String firstPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                    oneserverId1, savePath,openid,1,timestamp);
	            if(!firstPathDload.equals(null))
	                firstPath = "http://wisedp.com/wisemifi/downimg/"+openid+"-"+timestamp+"-01"+".png";
	            String secondPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                    oneserverId2, savePath,openid,2,timestamp);
	            if(!secondPathDload.equals(null))
	                secondPath = "http://wisedp.com/wisemifi/downimg/"+openid+"-"+timestamp+"-02"+".png";
	        }
	        if(type==6){
	        	  String firstPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                     oneserverId1, savePath,openid,1,timestamp);
	             if(!firstPathDload.equals(null))
	                 firstPath = "http://wisedp.com/wisemifi/downimg/"+openid+"-"+timestamp+"-01"+".png";
	           	 String threePathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	            			oneserverId3, savePath,openid,3,timestamp);
	                 if(!threePathDload.equals(null))
	                 	threePath = "http://wisedp.com/wisemifi/downimg/"+openid+"-"+timestamp+"-03"+".png";
	        }
	        if(type==7){
	            String secondPathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	                    oneserverId2, savePath,openid,2,timestamp);
	            if(!secondPathDload.equals(null))
	                secondPath = "http://wisedp.com/wisemifi/downimg/"+openid+"-"+timestamp+"-02"+".png";
	         	String threePathDload = DloadImgUtil.downloadMedia(ordinaryAccess_Token,
	          			oneserverId3, savePath,openid,3,timestamp);
	            if(!threePathDload.equals(null))
	               	threePath = "http://wisedp.com/wisemifi/downimg/"+openid+"-"+timestamp+"-03"+".png";
	      }
	        JSONObject imgpathJson = null;

	        if(!oneserverId1.equals("")&&!oneserverId2.equals("")){
	            imgpathJson = new JSONObject();
	            if(type==1||type==3||type==6||type==5)
	            	imgpathJson.put("firstPath",firstPath);
	            else
	            	imgpathJson.put("firstPath",oneserverId1);
	            if(type==2||type==3||type==7||type==5)
	            	imgpathJson.put("secondPath",secondPath);
	            else
	                imgpathJson.put("secondPath",oneserverId2);
	            if(type==4||type==3||type==7||type==6)
	            	imgpathJson.put("threePath",threePath);
	            else
	                imgpathJson.put("threePath",oneserverId3);
	        }

	        log.info("imgpathJson:"+imgpathJson);
	        return  imgpathJson.toString();
	}
	/**
	 * 获取ticket
	 */
	@Override
	public JSONObject getticket(String url) {
		Jedis jedis = null;
		try {
			jedis = RedisDataStore.getconnetion();
			String ticket = jedis.get("ticket");
			//生成ticket签名
			JSONObject js_tiket = JSAPIUitl.createWebSignature(ticket, url);
			return js_tiket;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
