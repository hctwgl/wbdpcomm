package com.wbdp.bee.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.service.Wbl_OrderService;

/**
 * 订单控制器类
 * @author 汪赛军
 * date:2017年7月17日下午3:41:18
 *
 */
@Controller
public class Order_Controller {
	@Autowired
	private Wbl_OrderService wbl_OrderService;
	
	/**
	 * 获取订单以及关联列表
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/listOrder",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView listOrder(Integer pageNum,HttpSession session){
		return new ModelAndView("orderceshi",wbl_OrderService.listOrder(pageNum, session));
    }
	
	/**
	 * ajax定时刷新获取未审核订单数量
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/getOrderCount",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Integer getOrderCount(){
		return wbl_OrderService.getOrderCount();
    }
	
	/**
	 * 审核订单并修改订单状态
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/updateOrder",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Integer updateOrder(@RequestBody String json){
		System.out.println(json);
		return wbl_OrderService.updateOrder(json);
    }
	
	/**
	 * 对订单进行审核
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
   	@RequestMapping(value="/reviewOrder",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView reviewOrder( String beeid,String id){
   		System.out.println(beeid);
		return new ModelAndView("customereview",wbl_OrderService.reviewOrder(beeid,id));
    }
}
