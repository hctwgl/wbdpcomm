package com.wbdp.bee.controller;



import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wbdp.bee.entity.Wbl_BlackbeeEntity;
import com.wbdp.bee.service.Wbl_BeeService;
import com.wbdp.bee.service.Wbl_BlackBeeService;
import com.wbdp.bee.util.UtilPackaging;


@Controller
public class BlackBee_Controller {
	

    @Resource
	private Wbl_BlackBeeService BlackBeeService;


	/**
	 * 方法名: blackbeeAllList   
	 * 方法描述: 客户黑名单列表
	 * 入参描述: id:黑名单主键id, pageNum:页码
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
    @ResponseBody
   	@RequestMapping(value="/blackbeeAllList",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView blackbeeAllList(Long id,Integer pageNum,HttpSession session){
    	try {
        	//保存当前页码
 		   session.setAttribute("pageNow", pageNum);
 		 /* Map<String,Object> map = BlackBeeService.blackbeeAllList(id, pageNum);
 		  System.out.println(JSON.toJSON(map).toString());*/
     	 return new ModelAndView("customerblacklistceshi",BlackBeeService.blackbeeAllList(id, pageNum));			
		} catch (Exception e) {
		  return new ModelAndView("异常页面",UtilPackaging.toException(e));
		}
    }
    
    
    /**
     * 方法名: insertBlackbee   
     * 方法描述: Wbl_BlackbeeEntity:黑名单实体类 
     * 入参描述: 
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月14日
     */
    @ResponseBody
   	@RequestMapping(value="/insertBlackbee",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> insertBlackbee(Wbl_BlackbeeEntity BlackbeeEntity,HttpSession session){
    	try {
    		//获取增加人
    		BlackbeeEntity.setCreatby(session.getAttribute("username").toString());
        	//执行添加
        	return BlackBeeService.insertBlackbee(BlackbeeEntity);
		} catch (Exception e) {
			return UtilPackaging.toException(e);
		}

    }
  
}
