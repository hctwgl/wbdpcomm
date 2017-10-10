package com.wisemifi.wx.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.entity.QRcode;

/**
 * 维泽mifi我的信息业务接口类
 * @author 汪赛军
 * date:2017年8月10日上午10:42:21
 *
 */
public interface MyInfoService {
	/**
	 * 网页授权获取code与用户openID
	 * @param code
	 * @param session
	 * @return
	 */
	public String getCodeAndopenID(String code,HttpSession session);
	
	/**
	 * 获取我的订单列表
	 * @param openid
	 * @return
	 */
	public Map<String,Object> getMyself(HttpSession session);
	
	/**
	 * 客户生成二维码
	 * @param qRcode
	 * @param session
	 * @return
	 */
	public Map<String, Object> creatQRcode(QRcode qRcode,HttpSession session,HttpServletRequest request);
	
	/**
	 * 获取二维码数据
	 * @param session
	 * @return
	 */
	public Map<String, Object> getQRcode(HttpSession session);
	
	/**
	 * 获取ticket签名
	 * @param url
	 * @return
	 */
	public JSONObject getticket(String url);
}
