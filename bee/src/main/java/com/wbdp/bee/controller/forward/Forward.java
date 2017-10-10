package com.wbdp.bee.controller.forward;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class Forward {

	
	//登录
   	@RequestMapping(value="/login",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public String login(){
   		return "login";
   	}
	
	//注销
   	@RequestMapping(value="/logout",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView logout(HttpSession session){
   		session.invalidate();
   		return new ModelAndView("redirect:/login");
   	}
   	
	//主页面
   	@RequestMapping(value="/main",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public String main(){
   		return "main";
   	}
   	
   	
   	
   	
}
