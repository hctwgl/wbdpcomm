package com.wbdp.bee.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.bee.service.Wbl_PackageService;



/**
 * 话费套餐控制器类
 * @author 汪赛军
 * date:2017年7月20日下午3:52:29
 *
 */
@Controller
public class Package_Controller {
	@Autowired
	private Wbl_PackageService wbl_PackageService;
	
	//新增话费套餐数据
	@ResponseBody  
   	@RequestMapping(value="/insertPackage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> insertPackage(@RequestBody String json,HttpSession session){
		
		System.out.println(json);
		return wbl_PackageService.insertPackage(json);
    }
		
	//删除话费套餐数据
	@ResponseBody  
   	@RequestMapping(value="/deletePackage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> deletePackage(@RequestBody String json,HttpSession session){
		
		System.out.println(json);
		return wbl_PackageService.deletePackage(json);
    }
	//修改话费套餐数据
	@ResponseBody  
   	@RequestMapping(value="/updatePackage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> updatePackage(@RequestBody String json,HttpSession session){
		
		System.out.println(json);
		return wbl_PackageService.updatePackage(json);
    }
	//关联查询话费套餐数据
	@ResponseBody
   	@RequestMapping(value="/listPackage",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
   	public ModelAndView listPackage(Integer pageNum,HttpSession session){
		try {
			
			return new ModelAndView("package",wbl_PackageService.listPackage(pageNum));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
		
	//根据skuID查询套餐数据，用于下拉框显示
	@ResponseBody  
   	@RequestMapping(value="/getPackage",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> getPackage(@RequestBody String  json,HttpSession session){
		
		System.out.println(json);
		try {
			JSONObject obj = JSON.parseObject(json);
			String skuID= obj.getString("skuID");
			return wbl_PackageService.getPackage(Long.parseLong(skuID));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
    }
}
