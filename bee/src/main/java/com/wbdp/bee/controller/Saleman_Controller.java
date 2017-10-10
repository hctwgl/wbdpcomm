package com.wbdp.bee.controller;


import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wbdp.bee.entity.Saleman;
import com.wbdp.bee.entity.Wbl_SalemanEntity;
import com.wbdp.bee.entity.Wbl_SupplierEntity;
import com.wbdp.bee.entity.Wbl_UserEntity;
import com.wbdp.bee.service.Wbl_SalemanService;
import com.wbdp.bee.service.Wbl_SupplierService;
import com.wbdp.bee.service.Wbl_UserService;
import com.wbdp.bee.util.UtilPackaging;


@Controller
public class Saleman_Controller {
	
    @Resource
	private Wbl_SalemanService SalemanService;

   /**
    * 方法名: salemanList   
    * 方法描述:  业务员列表
    * 入参描述: pageNum:页码 
    * 创建人:wisedata004  
    * 创建时间: 2017年7月8日 
    */
	@ResponseBody
	@RequestMapping(value="/salemanList",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public ModelAndView salemanList(Integer pageNum,HttpSession session){
	   try {
		   //保存当前页码
		   session.setAttribute("pageNow", pageNum);
		   return new ModelAndView("saleman",SalemanService.SalemanAllList(pageNum));  
	    } catch (Exception e) {
		// TODO: handle exception
	    	return new ModelAndView("异常页面",UtilPackaging.toException(e));
	    }
	}
	
	/**
	    * 方法名: 客服新增客户经理时选择上级管理员获取数据  
	    */
		@ResponseBody
		@RequestMapping(value="/listSaleman",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
		public Map<String, Object> listSaleman(HttpSession session){
		   return SalemanService.listSaleman(session);
		}
	  /**
	    * 方法名: salemanInsertAndUpdate   
	    * 方法描述:  业务员增加与修改
	    * 入参描述: type:1增加 2:修改
	    * 创建人:wisedata004  
	    * 创建时间: 2017年7月8日
	    */
	@ResponseBody
	@RequestMapping(value="/salemanInsertAndUpdate",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map salemanInsertAndUpdate(Integer type,Wbl_SalemanEntity SalemanEntity,HttpSession session){
		Map<String, Object> result=null;
		   try {
			  //判断类型 
			   switch (type) {
			case 1:
				//设置添加人
				SalemanEntity.setCreatby(session.getAttribute("username").toString());
				//执行添加
				result=SalemanService.SalemanInsert(SalemanEntity);
				break;
			case 2:
				//设置修改人
				SalemanEntity.setUpdateby(session.getAttribute("username").toString());
				//执行修改
				result=SalemanService.SalemanUpdate(SalemanEntity); 
				break;
			  }
			   return result;
		    } catch (Exception e) {
			// TODO: handle exception
		    	return UtilPackaging.toException(e);
		    }
		}
  
	

	  /**
	    * 方法名: salemanDelete   
	    * 方法描述:  运营商删除
	    * 入参描述: id:运营商ID
	    * 创建人:wisedata004  
	    * 创建时间: 2017年7月8日 
	    */
	@ResponseBody
	@RequestMapping(value="/salemanDelete",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map salemanDelete(Long id){
		   try {
			   return SalemanService.SalemanDelete(id);
		    } catch (Exception e) {
			// TODO: handle exception
		    	return UtilPackaging.toException(e);
		    }
		}
	
	
	/**
	 * 客户管理员新增客户经理
	 * @param saleman
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertSaleman",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Integer insertSaleman(Saleman saleman,HttpSession session){
		System.out.println("新增客户经理："+saleman.getName());
		return SalemanService.insertSaleman(saleman, session);
	}
	/**
	 * 客服新增客户经理
	 * @param saleman
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertSalemanBykefu",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Integer insertSalemanBykefu(Saleman saleman,HttpSession session){
		System.out.println("新增客户经理："+saleman.getName());
		return SalemanService.insertSalemanBykefu(saleman, session);
	}
	/**
	 * 查询全部客户经理数据
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/selectAllSaleman",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public ModelAndView selectAllSaleman(Integer pageNum,HttpSession session){
		try {
			System.out.println("查询客户经理："+pageNum);
			Integer type = (Integer)session.getAttribute("userType");
			//判断登录的客户类型，客服与系统管理员则查询全部，业务员管理员则查询他名下的所有客户经理
			if(type==1||type==2){
				return new ModelAndView("saleman",SalemanService.selectAllSaleman(pageNum, session));
			}else{
				return new ModelAndView("saleman",SalemanService.selectSalemanByManager(pageNum, session));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 修改客户经理
	 * @param saleman
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateSaleman",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Integer updateSaleman(Saleman saleman,HttpSession session){
		System.out.println("修改客户经理："+saleman.getName());
		return SalemanService.updateSaleman(saleman, session);
	}
	
	/**
	 * 修改客户经理
	 * @param saleman
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/creatCode",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String creatCode(String phone,HttpSession session){
		System.out.println("生成推荐码："+phone);
		return SalemanService.creatCode(phone);
	}
	@ResponseBody
	@RequestMapping(value="/selectSaleman",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public ModelAndView selectSaleman(Long id,HttpSession session){
		return new ModelAndView("salemanupdate",SalemanService.selectSaleman(id, session));
	}
	
	
}
