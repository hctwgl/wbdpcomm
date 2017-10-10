package com.wbdp.wx.controller.wx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wbdp.wx.model.Result;
import com.wbdp.wx.service.wx.PushMsgServer;

@Controller
@RequestMapping("/push")
public class PushMsgController {
	@Resource
	private PushMsgServer pushMsgServer;

	/**日志log*/
	private static Logger log = Logger.getLogger(PushMsgController.class);
	
	
	@RequestMapping("/testpush")
	public String testpush( Model model){
		return "test";
	}
	/**
	 * 订单推送
	 * @param request
	 * @param model
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/orderPush",method=RequestMethod.GET)  
	@ResponseBody
    public Result mileage(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String orderPush = request.getParameter("orderPush");
		log.info("orderPush:"+orderPush);
		return pushMsgServer.checkAndPushOrder(orderPush);
    }
	
	/**
	 * 推送任性刷审核结果
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/faceCheckPush",method=RequestMethod.POST)  
	@ResponseBody
    public Map<String,Object> faceCheckPush(@RequestBody String json,HttpServletRequest request) throws Exception{
		log.info("审核通过获取json："+json);
		return pushMsgServer.faceCheckPush(json, request);//pushMsgServer.checkAndPushOrder(orderPush);
    }

	/**
	 * 推送任性刷审核未通过结果
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/faceCheckPushNO",method=RequestMethod.POST)  
	@ResponseBody
    public Map<String,Object> faceCheckPushNO(@RequestBody String json,HttpServletRequest request) throws Exception{
		log.info("审核未通过获取json："+json);
		return pushMsgServer.faceCheckPushNO(json, request);//pushMsgServer.checkAndPushOrder(orderPush);
    }
	
	/**
	 * 推送套餐确认通知
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/pushPacInfo",method=RequestMethod.POST)  
	@ResponseBody
    public Map<String,Object> pushPacInfo(@RequestBody String json,HttpServletRequest request) throws Exception{
		log.info("通知套餐json："+json);
		return pushMsgServer.pushPacInfo(json, request);//pushMsgServer.checkAndPushOrder(orderPush);
    }
}
