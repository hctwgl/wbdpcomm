package com.wbdp.wx.service.wx;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;

import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.model.Result;


public interface PushMsgServer {

	@SuppressWarnings("rawtypes")
	Result checkAndPushOrder(String orderPush)throws CustomException;
	
	/**
	 * 推送任性刷审核结果
	 * @param json
	 * @param request
	 * @return
	 */
	public Map<String,Object> faceCheckPush(String json,HttpServletRequest request);
	
	/**
	 * 推送任性刷审核未通过结果
	 * @param json
	 * @param request
	 * @return
	 */
	public Map<String,Object> faceCheckPushNO(String json,HttpServletRequest request);
	
	/**
	 * 推送套餐确认通知
	 * @param json
	 * @param request
	 * @return
	 */
	 public Map<String,Object> pushPacInfo(String json,HttpServletRequest request);
}
