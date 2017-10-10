package com.wisemifi.wx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wisemifi.wx.entity.QRcode;
import com.wisemifi.wx.service.MyInfoService;


/**
 * 维泽mifi公众号我的控制器类
 * @author 汪赛军
 * date:2017年8月10日上午9:00:50
 *
 */
@Controller
@RequestMapping(value="/myinfo")
public class MyInfoController {
	private static Logger log = Logger.getLogger(MyInfoController.class);
	@Autowired
	private MyInfoService myInfoService;
	/**
	 * 获取code与用户openID,成功后跳转至我的页面
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/tomyinfo",method=RequestMethod.GET)
	public String getCode(@PathParam("code") String code,@PathParam("id") Integer id,HttpSession session){
		log.info("获取的code："+code+":"+id);
		return myInfoService.getCodeAndopenID(code, session);	
	}
	/**
	 * 客户生成二维码
	 * @param qRcode
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/creatQRcode",method=RequestMethod.POST)
	public Map<String, Object> creatQRcode(QRcode qRcode,HttpSession session,HttpServletRequest request){
		return myInfoService.creatQRcode(qRcode, session, request);
	}
	/**
	 * 我的页面获取数据
	 * @param url
	 */
	@RequestMapping(value="/getMyself",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getMyself(HttpSession session){
		log.info("查看我的订单信息");
		return myInfoService.getMyself(session);
	}
	
	/**
	 * 我的页面获取二维码数据
	 */
	@RequestMapping(value="/getQRcode",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getQRcode(HttpSession session){
		log.info("查看我的二维码");
		return myInfoService.getQRcode(session);
	}
	/**
	 * 后台获取ticket并且生成签名
	 * @param url
	 */
	@RequestMapping(value="/gettic",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getticket(@RequestParam("url") String url){
		log.info("开始获取ticket签名");
		return myInfoService.getticket(url);
	} 
}
