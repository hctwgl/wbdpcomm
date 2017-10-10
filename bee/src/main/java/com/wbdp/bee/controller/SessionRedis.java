package com.wbdp.bee.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SessionRedis {

	 @ResponseBody
	 @RequestMapping(value="setSession")
     public void setSession(HttpSession session,String username,String paswword){
		 session.setAttribute("username", username);
     }
     
	
	 @ResponseBody
	 @RequestMapping(value="getSession")
     public String getSession(HttpSession session,String username,String paswword){
    	 
		 return session.getAttribute("username").toString();
     }
     
}
