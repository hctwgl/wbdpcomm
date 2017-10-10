package com.wbdp.bee.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.entity.CompblacklistNewEntity;
import com.wbdp.bee.service.Wbl_CompblacklistService;

/**
 * 公司黑名单控制器类
 * @author 汪赛军
 * date:2017年7月22日上午10:30:13
 *
 */
@Controller
public class Compblack_Controller {
		@Autowired
		private Wbl_CompblacklistService wbl_CompblacklistService;
		//新增公司黑名单数据
		@ResponseBody  
	   	@RequestMapping(value="/insertcompanyblacklist",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	   	public Integer insertcompanyblacklist(@RequestBody String json,HttpSession session){
			System.out.println(json);
			return wbl_CompblacklistService.insertCompblack(json,session);
	    }
		//删除公司黑名单数据
		@ResponseBody  
	   	@RequestMapping(value="/deletecompanyblacklist",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	   	public Integer deletecompanyblacklist(@RequestBody String json,HttpSession session){
			System.out.println(json);
			return wbl_CompblacklistService.deleteCompblack(json);
	    }
		//获取公司黑名单列表
		@ResponseBody
	   	@RequestMapping(value="/companyblacklist",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	   	public ModelAndView companyblacklist(Integer pageNum,HttpSession session){
				System.out.println(pageNum);
				return new ModelAndView("companyblacklistceshi",wbl_CompblacklistService.CompblackAllList(pageNum));
	    }
		
		
		
		
		//新增公司黑名单数据
		@ResponseBody  
	   	@RequestMapping(value="/insertCompBlack",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	   	public Integer insertCompBlack(CompblacklistNewEntity compblacklistNewEntity,HttpSession session){
			System.out.println(compblacklistNewEntity.getComPany());
			return wbl_CompblacklistService.insertCompBlack(compblacklistNewEntity, session);
	    }
		
		//获取公司黑名单列表
		@ResponseBody
	   	@RequestMapping(value="/selectCompBlack",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	   	public ModelAndView selectCompBlack(Integer pageNum,HttpSession session){
				System.out.println(pageNum);
				return new ModelAndView("companyblacklistceshi",wbl_CompblacklistService.CompblackAllList(pageNum));
	    }
}	
