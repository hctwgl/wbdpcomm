package com.wisemifi.wx.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.entity.CustomerAddress;
import com.wisemifi.wx.entity.CustomerOrder;

/**
 * 维泽mifi业务接口类
 * @author 汪赛军
 * date:2017年8月10日上午9:08:59
 *
 */
public interface WiseMIFIService {
	/**
	 * 网页授权获取code与用户openID
	 * @param code
	 * @param session
	 * @return
	 */
	public String getCodeAndopenID(String code,String openid,HttpSession session);
	
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
	public Map<String,Object> wxPay(HttpSession session,String userip,CustomerOrder customerOrder);
	/**
	 * 微信支付成功后回调函数
	 * @param session
	 * @param request
	 */
	public void payback(HttpSession session,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 新增收货地址
	 * @param session
	 * @param customerAddress
	 * @return
	 */
	public Map<String,Object> addAddress(HttpSession session,CustomerAddress customerAddress);
	
	/**
	 * 用户购买无线路由前获取地址信息
	 * @param session
	 * @return
	 */
	public Map<String,Object> getAddress(HttpSession session);
}
