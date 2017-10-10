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
import com.wbdp.bee.entity.Wbl_SupplierEntity;
import com.wbdp.bee.entity.Wbl_UserEntity;
import com.wbdp.bee.service.Wbl_SupplierService;
import com.wbdp.bee.service.Wbl_UserService;
import com.wbdp.bee.util.UtilPackaging;


@Controller
public class Supplier_Controller {
	

    @Resource
	private Wbl_SupplierService SupplierService;

   /**
    * 方法名: supplierList   
    * 方法描述:  运营商列表
    * 入参描述: pageNum:页码 
    * 创建人:wisedata004  
    * 创建时间: 2017年7月8日 
    */
	@ResponseBody
	@RequestMapping(value="/supplierList",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public ModelAndView supplierList(Integer pageNum,HttpSession session){
	   try {
		 //保存当前页码
		   session.setAttribute("pageNow", pageNum);
		   return new ModelAndView("supplierceshi",SupplierService.SupplierAllList(pageNum));  
	    } catch (Exception e) {
		// TODO: handle exception
	    	return new ModelAndView("异常页面",UtilPackaging.toException(e));
	    }
	}
	
	
	
	
	  /**
	    * 方法名: supplierInsertAndUpdate   
	    * 方法描述:  运营商增加与修改
	    * 入参描述: type:1增加 2:修改
	    * 创建人:wisedata004  
	    * 创建时间: 2017年7月8日
	    */
	@ResponseBody
	@RequestMapping(value="/supplierInsertAndUpdate",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map supplierInsertAndUpdate(Integer type,HttpSession session,Wbl_SupplierEntity SupplierEntity){
		Map<String, Object> result=null;
		   try {
			  //判断类型 
			   switch (type) {
			case 1:
				  //设置添加人
				  SupplierEntity.setCreatby(session.getAttribute("username").toString());
				  //执行添加
				result=SupplierService.SupplierInsert(SupplierEntity); 
				break;
			case 2:
				  //设置修改人
				  SupplierEntity.setUpdateby(session.getAttribute("username").toString());
				  //执行添加 
				result= SupplierService.SupplierUpdate(SupplierEntity); 
				break;
			  }
			   return result;
		    } catch (Exception e) {
			// TODO: handle exception
		    	return UtilPackaging.toException(e);
		    }
		}
  
	

	  /**
	    * 方法名: supplierDelete   
	    * 方法描述:  运营商删除
	    * 入参描述: id:运营商ID
	    * 创建人:wisedata004  
	    * 创建时间: 2017年7月8日 
	    */
	@ResponseBody
	@RequestMapping(value="/supplierDelete",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map supplierDelete(Long id){
		   try {
			   return SupplierService.SupplierDelete(id);
		    } catch (Exception e) {
			// TODO: handle exception
		    	return UtilPackaging.toException(e);
		    }
		}
	
	 /**
	    * 方法名: listSupplier   
	    * 方法描述: 用于添加商品时ajax调用
	    * 创建时间: 2017年7月17日 
	    */
	@ResponseBody
	@RequestMapping(value="/listSupplier",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map<String,Object> listSupplier(){
		System.out.println("访问了供应商控制器");
		 return SupplierService.listSupplier();
		}
}
