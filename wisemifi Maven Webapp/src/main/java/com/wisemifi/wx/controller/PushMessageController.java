package com.wisemifi.wx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 推送消息控制器类
 * @author 汪赛军
 * date:2017年8月16日下午2:45:51
 *
 */
@Controller
public class PushMessageController {
	
	/**
	 * 购买成功推送
	 * @param request
	 * @param model
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/pushReview",method=RequestMethod.GET)  
	@ResponseBody
    public Map<String, Object> pushReview(HttpServletRequest request) throws Exception{
//	public Result faultRemind(@RequestParam("faultremind")String faultremind) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String review = request.getParameter("review");
		System.out.println("收到的推送json："+review);
		return null;//pushMsgServer.pushReview(review);
    }
}
