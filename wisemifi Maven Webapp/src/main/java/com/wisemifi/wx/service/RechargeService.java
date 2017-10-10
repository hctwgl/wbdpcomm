package com.wisemifi.wx.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.entity.CustomerOrder;

/**
 * 维泽mifi充值业务接口类
 * @author 汪赛军
 * date:2017年8月10日上午10:42:21
 *
 */
public interface RechargeService {
	/**
	 * 网页授权获取code与用户openID
	 * @param code
	 * @param session
	 * @return
	 */
	public String getCodeAndopenID(String code,HttpSession session);
	
	/**
	 * 获取ticket签名
	 * @param url
	 * @return
	 */
	public JSONObject getticket(String url);
	
	/**
	 * 微信支付
	 * @param openId
	 * @param orderId
	 * @param money
	 * @param callbackUrl
	 * @return
	 */
	public Map<String,Object> wxPay(HttpSession session,CustomerOrder customerOrder,String userip);
	
	/**
	 * 微信支付成功后回调函数
	 * @param session
	 * @param request
	 */
	public void payback(HttpSession session,HttpServletRequest request,HttpServletResponse response);
}
