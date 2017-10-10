package com.wbdp.bee.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.service.BrushcreditService;

@Controller
public class BrushcreditController {
	@Autowired
	private BrushcreditService brushcreditService;
	/**
	 * 获取维泽任性刷列表
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/brushList",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView brushList(Integer pageNum,HttpSession session){
		
		return new ModelAndView("brush",brushcreditService.selectAllBrushcredit(pageNum, session));
    }
	/**
	 * 获取本月维泽任性刷列表
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/brushListThisMouth",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView brushListThisMouth(Integer pageNum,HttpSession session){
		
		return new ModelAndView("brush",brushcreditService.brushListTheMouth(pageNum, session));
    }
	/**
	 * 获取上月维泽任性刷列表
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/brushListLastMouth",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView brushListLastMouth(Integer pageNum,HttpSession session){
		
		return new ModelAndView("brush",brushcreditService.brushListLastMouth(pageNum, session));
    }
	/**
	 * 获取维泽任性刷等待审核列表
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/brushListWait",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView brushListWait(Integer pageNum,HttpSession session){
		
		return new ModelAndView("brush",brushcreditService.selectAllBrushcreditWait(pageNum, session));
    }
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/brushCount",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Integer brushCount(HttpSession session){
		
		return brushcreditService.getCountWait();
	}
	
	/**
	 * 跳转审核任性刷页面
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/brushReview",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView brushReview(Long brushid,String beeWX,HttpSession session){
		
		return new ModelAndView("brushreview",brushcreditService.brushReview(brushid, beeWX, session));
    }
	
	/**
	 * 审核任性刷
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/reviewBrush",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Integer reviewBrush(Long brushid,String beeWX,Integer type,HttpSession session){
		
		return brushcreditService.reviewBrush(brushid,beeWX,type,session);
    }
}
