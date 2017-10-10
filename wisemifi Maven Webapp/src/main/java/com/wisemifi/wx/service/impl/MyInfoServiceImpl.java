package com.wisemifi.wx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.constant.DeveloInfor;
import com.wisemifi.wx.dao.CustomerOrderMapper;
import com.wisemifi.wx.dao.WiseCustomerMapper;
import com.wisemifi.wx.entity.CustomerOrder;
import com.wisemifi.wx.entity.QRcode;
import com.wisemifi.wx.entity.WiseCustomer;
import com.wisemifi.wx.model.PageAuthorModel;
import com.wisemifi.wx.service.MyInfoService;
import com.wisemifi.wx.util.CreateQRCode;
import com.wisemifi.wx.util.HttpClientUtil;
import com.wisemifi.wx.util.JSAPIUitl;
import com.wisemifi.wx.util.RedisDataStore;
/**
 * 维泽mifi充值业务实现类
 * @author 汪赛军
 * date:2017年8月10日上午10:42:21
 * 
 */
@Service
public class MyInfoServiceImpl implements MyInfoService {
	private static Logger log = Logger.getLogger(MyInfoServiceImpl.class);
	@Autowired
	private CustomerOrderMapper customerOrderMapper;
	@Autowired
	private WiseCustomerMapper wiseCustomerMapper;
	/**
	 * 获取网页授权
	 */
	@Override
	public String getCodeAndopenID(String code, HttpSession session) {
		String result = "";
		String pathUrl = "myself";
		PageAuthorModel pageModel = new PageAuthorModel();
		try {
			//拼接获取网页授权access_token接口
			String codeUrl = DeveloInfor.CODE_ACCESS_TOKEN.replace("APPID", DeveloInfor.AppID).replace("SECRET", DeveloInfor.AppSecret).replace("CODE",code);
			//获取微信服务端回复消息
			 result = HttpClientUtil.httpGet(codeUrl);
			 log.info("网页授权接收的数据："+result);
			 String openid = "";
			//解析json数据
			 if(result!=null&&result!=""){
				 JSONObject obj = JSON.parseObject(result);
				 String access_token = obj.getString("access_token");
				 openid = obj.getString("openid");
				 //获取sessionID
				 String sessionid = session.getId();
				 //将code与openID封装到模型中
				 pageModel.setCode(code);
				 pageModel.setOpenid(openid);
				 session.setAttribute(sessionid+"pageModel", pageModel);
				 //判断用户是否购买了无线路由 
				 //Integer count = customerOrderMapper.selectCustomerOrder(openid);
				 /*if(count>0){//用户购买了无线路由
					 pathUrl = "myself";
				 }else{
					 pathUrl = "myself";
				 }*/
			 }
			return pathUrl;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成二维码，并保存相关客户信息
	 */
	@Override
	public Map<String, Object> creatQRcode(QRcode qRcode, HttpSession session,HttpServletRequest request) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		log.info("生成二维码获取的用户信息："+qRcode.toString());
		String openid = "";
		WiseCustomer wiseCustomer = new WiseCustomer();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb125fba57e2524e2&"+
		"redirect_uri=http%3A%2F%2Fwisedp.com%2Fwisemifi%2Fwx%2Fwisemifi%2Ftobuypage?openid=openID&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		try {
			 //获取项目根目录路径
			  String sysPath = request.getSession().getServletContext().getRealPath("/downimg");
			  String id = session.getId();
			  PageAuthorModel pageModel = (PageAuthorModel)session.getAttribute(id+"pageModel");
			  if(pageModel!=null){
				  openid = pageModel.getOpenid();
			  }else{
				  log.info("session中的openID对象不存在");
			  }
			  //将静默地址中的openID字段替换成获取到的openID
			  content = content.replace("openID", openid);
			  //生成二维码并返回保存地址
			 String resultPath = CreateQRCode.createQRCode(content, sysPath, openid);
			 wiseCustomer.setCustomerName(qRcode.getCustomerName());
			 wiseCustomer.setCustomerPhone(qRcode.getCustomerPhone());
			 wiseCustomer.setQrcodeImg(resultPath);
			 wiseCustomer.setCustomerOpenid(openid);
			 wiseCustomer.setCustomerCreateTime(format.format(new Date()));
			 //查询该客户最新一条购买记录,并查询出订单中介绍人微信的ID
			 CustomerOrder order = customerOrderMapper.selectOrderNew(openid);
			 log.info("查询的介绍人微信："+order.getIntroOpenid());
			 //判断该订单中是否有介绍人微信
			 if(order.getIntroOpenid()!=null&&!"".equals(order.getIntroOpenid())){
				 //获得介绍人数据
				 WiseCustomer customer = wiseCustomerMapper.selectCustomerByOpenID(order.getIntroOpenid());
				 //将介绍人ID赋值到该客户对象中
				 wiseCustomer.setPid(customer.getCustomerId());
			 }else{
				 wiseCustomer.setPid(0l);
			 }
			 //新增客户信息
			 Integer result = wiseCustomerMapper.insertCustomer(wiseCustomer);
			 if(result==1){
				 log.info("用户信息保存成功");
				 outMap.put("data",resultPath);
				 outMap.put("status","SUCCESS");
				 return outMap;
			 }else{
				 log.info("用户信息保存失败");
				 outMap.put("data","");
				 outMap.put("status","FALSE");
				 return outMap;
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取我的订单信息
	 */
	@Override
	public Map<String, Object> getMyself(HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			String sessionid = session.getId();
			PageAuthorModel pageModel = (PageAuthorModel)session.getAttribute(sessionid+"pageModel");
			String openid = "";
			if(pageModel!=null){
				log.info("我的中的openID："+pageModel.getOpenid());
				openid = pageModel.getOpenid();
			}else{
				log.info("进入我的获取openID失败");
				outMap.put("msg", "获取微信号失败");
				outMap.put("status", "FALSE");
				outMap.put("data", "");
				return outMap;
			}
			List<CustomerOrder> list = customerOrderMapper.getCustomerOrder(openid);
			outMap.put("data", list);
			outMap.put("msg", "获取数据成功");
			outMap.put("status", "SUCCESS");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("data", "");
			outMap.put("msg", "获取数据异常");
			outMap.put("status", "EXCEPTION");
			return outMap;
		}
	}
	/**
	 * 获取二维码数据
	 */
	@Override
	public Map<String, Object> getQRcode(HttpSession session) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		try {
			String sessionid = session.getId();
			PageAuthorModel pageModel = (PageAuthorModel)session.getAttribute(sessionid+"pageModel");
			String openid = "";
			if(pageModel!=null){
				log.info("我的中的openID："+pageModel.getOpenid());
				openid = pageModel.getOpenid();
			}else{
				log.info("进入我的获取openID失败");
				outMap.put("data", "");
				outMap.put("status", "FALSE");
				outMap.put("msg", "获取openID失败");
				return outMap;
			}
			String QRcode = wiseCustomerMapper.getQRcode(openid);
			if(QRcode==null||"".equals(QRcode)){
				outMap.put("data", QRcode);
				outMap.put("status", "SUCCESS");
				outMap.put("msg", "二维码为空");
				return outMap;
			}
			outMap.put("data", QRcode);
			outMap.put("status", "SUCCESS");
			outMap.put("msg", "二维码不为空");
			return outMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取ticket签名验证
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
