package com.wbdp.bee.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wbdp.bee.service.Wbl_ShoppingcartService;

/**
 * 购物车控制器类
 * @author 汪赛军
 * date:2017年7月18日上午9:45:01
 *
 */
@Controller
public class ShoppingCartController {
	@Autowired
	private Wbl_ShoppingcartService wbl_ShoppingcartService;

	/**
	 * 获取购物车以及关联列表
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/listShoping",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView listShoping(Integer pageNum,HttpSession session){
		return new ModelAndView("shopingcartceshi",wbl_ShoppingcartService.listShoppingcart(pageNum, session));
    }
	
	/**
	 * 获取购物车以及关联列表总页数
	 * @param id
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value="/getShopPage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Integer getPage(Integer pageNum,HttpSession session){
		return wbl_ShoppingcartService.getPage();
    }
}
