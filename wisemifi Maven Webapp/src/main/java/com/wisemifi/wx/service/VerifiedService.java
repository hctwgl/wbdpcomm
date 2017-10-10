package com.wisemifi.wx.service;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.model.UserIDCard;

/**
 * 维泽mifi实名认证业务接口类
 * @author 汪赛军
 * date:2017年8月10日上午10:42:21
 *
 */
public interface VerifiedService {
	/**
	 * 网页授权获取code与用户openID
	 * @param code
	 * @param session
	 * @return
	 */
	public String getCodeAndopenID(String code,HttpSession session);
	
	/**
	 * 保存图片到数据库
	 * @param session
	 * @param userIDCard
	 * @return
	 */
	public Map<String,Object> saveImg(HttpSession session,@Valid UserIDCard userIDCard);
	/**
	 * 获取ticket
	 * @param url
	 * @return
	 */
	public JSONObject getticket(String url) ;
}
