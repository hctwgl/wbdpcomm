package com.wbdp.bee.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wbdp.bee.entity.Wbl_UserEntity;
import com.wbdp.bee.service.Wbl_UserService;
import com.wbdp.bee.util.UtilPackaging;


@Controller
public class User_Controller {
	

    @Resource
	private Wbl_UserService UserService;

    /**
     * 方法名: checkUser   
     * 方法描述: 用户登录
     * 入参描述: username(用户名),password(密码)
     * 回调描述: 该用户信息(返回为null为没有该用户) 
     * 创建人:wisedata004  
     * 创建时间: 2017年7月6日 下午4:20:41
     * @throws IOException 
     */
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	public ModelAndView checkUser(String username,String password,Integer type,HttpSession session){
	   //查询登录结果,幷设置session信息
		Map<String, Object> resultMap=UserService.UserLogin(username,password);
		System.out.println("用户类型:"+type);
        //如果该用户存在 
		 if(resultMap!=null){
			   //如果该用户账户为正常状态
			   if(resultMap.get("UserState").toString().equals("0")){
	        	  session.setAttribute("username",resultMap.get("UserName"));
	        	  session.setAttribute("userType",type);
	        	 return new ModelAndView("main",UserService.listModel(username));
			     }else{
			    	//当用户类型为业务员时将推荐码存入session中
		        	  if(type==3){
		        		  session.setAttribute("username", username);
		        		  session.setAttribute("recomCode", password);
		        		  return new ModelAndView("main",UserService.listModel(username));
		        	  } 
			     }
			   return new ModelAndView("login", "msg", "用户已停用!");
           }
		 return new ModelAndView("login", "msg", "用户名或密码错误!");
	}
	

    /**
     * 方法名: userList   
     * 方法描述: 获取所有用户
     * 入参描述: pageNum:页码
     * 回调描述:  
     * 创建人:wisedata004  
     * 创建时间: 2017年7月6日 下午5:50:47
     */
	@ResponseBody
   	@RequestMapping(value="/userList",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView userList(Integer pageNum,HttpSession session){
    try {    
    	 //保存当前页码
    	session.setAttribute("pageNow", pageNum);
         return new ModelAndView("user",UserService.UserAllList(pageNum));
		  } 
     catch (Exception e) {
		 return new ModelAndView("",UtilPackaging.toException(e));	  
		}

    	
    }
    
    /**
     * 方法名: userInsertAndUpdate   
     * 方法描述: 增加与修改用户 
     * 入参描述: type(1:增加     2:修改)
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月6日 下午6:05:54
     */
    @ResponseBody
   	@RequestMapping(value="/userInsertAndUpdate",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map userInsertAndUpdate(Wbl_UserEntity UserEntity,Integer type,HttpSession session){
     try {
    	Map<String,Object> result=null;
        //判断操作类型
        switch (type) {
		    case 1:
		    	//设置添加人
		    	UserEntity.setCreatby(session.getAttribute("username").toString());
		    	//设置创建时间
		    	UserEntity.setCreatdate(new Date());
		    	//执行添加
		    	result=UserService.UserInsert(UserEntity);
			break;
		case 2:
			   //设置修改人
	    	   UserEntity.setUpdateby(session.getAttribute("username").toString());
	    	   //设置创建时间
	    	   UserEntity.setUpdatetime(new Date());
	    	   //执行添加
		       result=UserService.UserUpdate(UserEntity);
			break;
		   }
        return result;
	} catch (Exception e) {
		// TODO: handle exception
		return UtilPackaging.toException(e);
	 }	
    }

    
    /**
     * 方法名: userDeleteAndStop   
     * 方法描述: 删除与停用用户 
     * 入参描述: id(用户ID),type(1:删除  2:停用)
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月6日 下午6:24:18
     */
    @ResponseBody
   	@RequestMapping(value="/userDeleteAndStop",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
   	public Map userDeleteAndStop(Long id,Integer type){
    try {
    	Map<String,Object> result=null;
          //判断操作类型	
            switch (type) {
	             case 1:
	            	 result=UserService.UserDelete(id);
			        break;
	          	 case 2:
	          		 result=UserService.UserStop(id);
			        break;
		        }
    	    return result;
	 } catch (Exception e) {
		
		return UtilPackaging.toException(e);
	  }	
    }
    /**
     * 跳转到设置用户权限页面
     * @param userID
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/tousermodel",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
    public ModelAndView userModel(Long userID){
    	System.out.println("参数："+userID);
    	return new ModelAndView("userModel",UserService.selsectModel(userID));
    }
    /**
     * 权限设置成功后跳转到用户管理页面
     * @param userID
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/setModel",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    public ModelAndView toUserlist(HttpServletRequest request){
    	try {
    		request.setCharacterEncoding("utf-8");
    		 String[] list = request.getParameterValues("check-box");
    		 String userID = request.getParameter("userID");
    		 System.out.println("表单提交的用户ID："+userID);
    		 Integer result = UserService.setModel(list,userID);
    		 if(result==1){
    			 return new ModelAndView("user",UserService.UserAllList(1));
    		 }else{
    			 return null;
    		 }
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("user",UserService.UserAllList(1));
		}
    }
}
