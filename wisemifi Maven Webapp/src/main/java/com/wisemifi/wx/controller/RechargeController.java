package com.wisemifi.wx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sun.tools.internal.ws.processor.model.Request;
import com.wisemifi.wx.config.controller.ConfigController;
import com.wisemifi.wx.entity.CustomerOrder;
import com.wisemifi.wx.service.RechargeService;
import com.wisemifi.wx.service.WiseMIFIService;

/**
 * 维泽mifi公众号充值控制器类
 * @author 汪赛军
 * date:2017年8月10日上午9:00:50
 *
 */
@Controller
@RequestMapping(value="/recharge")
public class RechargeController {
	private static Logger log = Logger.getLogger(RechargeController.class);
	@Autowired
	private RechargeService rechargeService;
	/**
	 * 获取code与用户openID,成功后跳转至充值页面
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/torecharge",method=RequestMethod.GET)
	public String getCode(@PathParam("code") String code,HttpSession session){
		log.info("获取的code："+code);
		return rechargeService.getCodeAndopenID(code, session);	
	}
	
	/**
	 * 支付入口，统一下单，并返回支付所需参数
	 * @param url
	 */
	@RequestMapping(value="/wxpay",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> topay(HttpSession session,CustomerOrder customerOrder,String userip){
		log.info("开始唤起支付");
		log.info("获取的money："+customerOrder.getPrice());
		log.info("获取的用户IP："+userip);
		//money = 1;
		return rechargeService.wxPay(session,customerOrder,userip);
	}
	
	/**
	 * 支付回调接口，收到微信成功回复后进行下一步操作
	 * @param url
	 */
	@RequestMapping(value="/topay_back",method=RequestMethod.POST)
	@ResponseBody
	public void topay_back(HttpSession session,HttpServletRequest request,
			HttpServletResponse response, Model model){
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			//String return_code =  request.getParameter("return_code");//返回状态码
			//String return_msg =  request.getParameter("return_msg");//返回信息
//			JSONObject payJson =  server.pagy_test(money,userIP,onlin);
			rechargeService.payback(session, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	/**
	 * 后台获取ticket并且生成签名
	 * @param url
	 */
	@RequestMapping(value="/gettic",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getticket(@RequestParam("url") String url){
		log.info("开始获取ticket签名");
		return rechargeService.getticket(url);
	} 
}
