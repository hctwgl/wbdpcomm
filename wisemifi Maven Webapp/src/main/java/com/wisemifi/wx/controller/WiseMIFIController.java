package com.wisemifi.wx.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.wisemifi.wx.entity.CustomerAddress;
import com.wisemifi.wx.entity.CustomerOrder;
import com.wisemifi.wx.service.WiseMIFIService;

/**
 * 维泽mifi公众号控制器类
 * @author 汪赛军
 * date:2017年8月10日上午9:00:50
 *
 */
@Controller
@RequestMapping(value="/wisemifi")
public class WiseMIFIController {
	private static Logger log = Logger.getLogger(WiseMIFIController.class);
	@Autowired
	private WiseMIFIService wiseMIFIService;
	/**
	 * 获取code与用户openID,成功后跳转至申购页面
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/tobuypage",method=RequestMethod.GET)
	public String getCode(@PathParam("code") String code,@PathParam("openid") String openid,HttpSession session){
		log.info("获取的code与openID："+code+","+openid);
		return wiseMIFIService.getCodeAndopenID(code,openid, session);	
	}
	/**
	 * 跳转至申购维泽mifi成功后页面
	 * @return
	 */
	@RequestMapping(value="topaysuccess",method=RequestMethod.GET)
	public String toBuyPage(){
		log.info("跳转到申购页面");
		return "buy";
	}
	/**
	 * 跳转至申购维泽mifi填写地址页面
	 * @return
	 */
	@RequestMapping(value="toaddress",method=RequestMethod.GET)
	public String toaddress(){
		log.info("跳转到填写地址页面");
		return "addaddress";
	}
	/**
	 * 跳转至申购维泽mifi确认订单页面页面
	 * @return
	 */
	@RequestMapping(value="toorder",method=RequestMethod.GET)
	public String toorder(){
		log.info("跳转到填写地址页面");
		return "order";
	}
	/**
	 * 支付入口，统一下单，并返回支付所需参数
	 * @param url
	 */
	@RequestMapping(value="/wxpay",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> topay(HttpSession session,String userip,CustomerOrder customerOrder){
		log.info("开始唤起支付");
		log.info("获取的用户订单数据："+customerOrder.toString());
		log.info("获取的用户IP："+userip);
		//money = 1;
		return wiseMIFIService.wxPay(session,userip,customerOrder);
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
			wiseMIFIService.payback(session, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	/**
	 * 新增收货地址
	 * @param url
	 */
	@RequestMapping(value="/addAddress",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addAddress(HttpSession session,CustomerAddress customerAddress){
		log.info("开始保存收货地址");
		log.info("获取的地址数据："+customerAddress.toString());
		//money = 1;
		return wiseMIFIService.addAddress(session, customerAddress);//wiseMIFIService.wxPay(session,money,userip);
	}
	
	/**
	 * 用户购买无线路由前获取地址信息
	 * @param url
	 */
	@RequestMapping(value="/getAddress",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getAddress(HttpSession session){
		log.info("购买前收货地址");
		return wiseMIFIService.getAddress(session);//wiseMIFIService.addAddress(session, customerAddress);//wiseMIFIService.wxPay(session,money,userip);
	}
	/**
	 * 后台获取ticket并且生成签名
	 * @param url
	 */
	@RequestMapping(value="/gettic",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getticket(@RequestParam("url") String url){
		log.info("开始获取ticket签名");
		return wiseMIFIService.getticket(url);
	} 
}
