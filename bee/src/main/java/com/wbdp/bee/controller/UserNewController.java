package com.wbdp.bee.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.entity.UserNewEntity;
import com.wbdp.bee.entity.Wbl_UserEntity;
import com.wbdp.bee.service.UserNewService;
import com.wbdp.bee.service.Wbl_UserService;

/**
 * 新版一分期用户控制器类
 * @author 汪赛军
 * date:2017年9月5日上午11:26:17
 *
 */
@Controller
public class UserNewController {
	@Autowired
	private UserNewService userNewService;
	 @Resource
		private Wbl_UserService UserService;
	 
	 /**
	  * 用户登录
	  * @param username
	  * @param password
	  * @param session
	  * @return
	  */
	 @ResponseBody
	@RequestMapping(value="/userLogin",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map<String,Object> userLogin(String userName,Integer userType,String passWord,HttpSession session){
		 System.out.println("开始登录");
		System.out.println(userName);
		return userNewService.userLogin(userName, userType, passWord, session);
	}
	 
	 /**
	  * 用户重置密码
	  * @param username
	  * @param password
	  * @param session
	  * @return
	  */
	 @ResponseBody
	@RequestMapping(value="/resetPassWord",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map<String,Object> resetPassWord(String passWordold,String passWordnew,HttpSession session){
		
		return userNewService.resetPassWord(passWordold, passWordnew, session);
	}
	/**
	 * 登陆后跳转页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/toPage",method=RequestMethod.GET)
	public ModelAndView toLogin(HttpSession session){
	   
		 return new ModelAndView("main", userNewService.toPage(session));
	}
	/**
	 * 登陆后跳转页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/toLogin",method=RequestMethod.GET)
	public ModelAndView checkUser(HttpSession session){
	
		 return new ModelAndView("login", userNewService.toPage(session));
	}
	/**
	  * 用户列表
	  * @param username
	  * @param password
	  * @param session
	  * @return
	  */
	@ResponseBody
	@RequestMapping(value="/newBeeList",method=RequestMethod.POST)
	public Map<String,Object> beeList(Integer pageNum,HttpSession session){
		
		 return userNewService.beeList(pageNum, session);
	}
	
	 /**
	  * 新增用户
	  * @param username
	  * @param password
	  * @param session
	  * @return
	  */
	@ResponseBody
	@RequestMapping(value="/userAdd",method=RequestMethod.POST)
	public Integer userAdd(UserNewEntity userNewEntity,HttpSession session){
		 System.out.println(userNewEntity.getUserName()+":"+userNewEntity.getPassWord());
		 return userNewService.userAdd(userNewEntity);//userNewService.userLogin(userName, userType, passWord, session);
	}
	
	/**
	 * 跳转到修改页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/toUpdatePage",method=RequestMethod.GET)
	public ModelAndView toUpdatePage(Long id,HttpSession session){
	   
		 return new ModelAndView("updateuser", userNewService.toUpdatePage(id, session));
	}
	
	 /**
	  * 修改用户
	  * @param username
	  * @param password
	  * @param session
	  * @return
	  */
	@ResponseBody
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public Integer updateUser(UserNewEntity userNewEntity,HttpSession session){
		 System.out.println(userNewEntity.getUserName()+":"+userNewEntity.getPassWord());
		 return userNewService.updateUser(userNewEntity, session);//userNewService.userLogin(userName, userType, passWord, session);
	}
	
	/**
	 * 跳转到登录页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/toLogout",method=RequestMethod.GET)
	public String toLogin(Long id,HttpSession session){
		session.removeAttribute("username");
		session.removeAttribute("userType");
		session.removeAttribute("recomCode");
		 return "login";
	}
}
