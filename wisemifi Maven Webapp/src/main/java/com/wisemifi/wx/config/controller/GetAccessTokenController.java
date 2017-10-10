package com.wisemifi.wx.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wisemifi.wx.util.Access_TokenUtil;

@Controller
@RequestMapping(value="getAccessToken")
public class GetAccessTokenController {
	@RequestMapping(value="accesstoken",method=RequestMethod.GET)
	public String getAccessToken(){
		//获取普通access_token
		Access_TokenUtil.getAccess_Token();
		return null;
	}
}
