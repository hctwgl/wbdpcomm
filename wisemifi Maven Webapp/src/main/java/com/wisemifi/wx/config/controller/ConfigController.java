package com.wisemifi.wx.config.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wisemifi.wx.config.service.MsgReplyService;
import com.wisemifi.wx.util.CheckUtil;



	

/**
 * 微信服务器配置控制器
 * @author 汪赛军
 * date:2017年8月8日下午5:41:24
 *
 */
@Controller
@RequestMapping(value="config")
public class ConfigController {
	/**微信加密签名，signature结合了开发者填写的
	 * token参数和请求中的timestamp参数、nonce参数。*/
	private String signature;
	/**时间戳*/
	private String timestamp;
	/**随机数*/
	private String nonce;
	/**随机字符串*/
	private String echostr;
	/**日志log*/
	private static Logger log = Logger.getLogger(ConfigController.class);
	@Autowired
	private MsgReplyService msgReplyService;
	/**
	 * 成为开发者
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/tomeautype",method=RequestMethod.GET)
	public void todeveloperGet(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		signature = request.getParameter("signature");
		timestamp = request.getParameter("timestamp");
		nonce = request.getParameter("nonce");
		echostr = request.getParameter("echostr");
		System.out.println("这里成为开发者");
		if (signature != null) {
			boolean checkSignature = CheckUtil.checkSignature(
					signature,timestamp, nonce);
			if (checkSignature) {
				try {
					log.info("echostr成功:" + echostr);
					response.getWriter().write(echostr);
					log.info("校验成功，成功接入");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else
				log.error("校验失败");
		} else
			log.error("signature为空，校验失败");
	}
	
	/**
	 * 转发微信服务发送的消息，可对用户发送的消息进行回复
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/tomeautype",method=RequestMethod.POST)
	public void todeveloperPost(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
			String meauType = msgReplyService.MeauType(request);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(meauType);
		}catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
