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
import com.wbdp.bee.service.Wbl_PackageSkuService;
/**
 * sku关联套餐控制器类
 * @author 汪赛军
 * date:2017年7月21日下午1:50:25
 *
 */
@Controller
public class PackageSku_Controller {
	@Autowired
	private Wbl_PackageSkuService wbl_PackageSkuService;
		//新增话费套餐数据
		@ResponseBody  
	   	@RequestMapping(value="/insertPackageSku",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	   	public Map<String,Object> insertPackageSku(@RequestBody String json,HttpSession session){
			
			System.out.println(json);
			return wbl_PackageSkuService.insertPackageSku(json);
	    }
		//删除话费套餐数据
		@ResponseBody  
	   	@RequestMapping(value="/deletePackageSku",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	   	public Integer deletePackageSku(@RequestBody String json,HttpSession session){
			JSONObject obj = JSON.parseObject(json);
			String id = obj.getString("id");
			System.out.println(id);
			return wbl_PackageSkuService.deletePackageSku(Long.parseLong(id.toString()));
	    }
		//关联查询话费套餐数据
		@ResponseBody
	   	@RequestMapping(value="/listPackageSku",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	   	public ModelAndView listPackageSku(Integer skuId,HttpSession session){
			try {
				//将skuID存入session，方便下一个页面取值
				session.setAttribute("skuID", skuId);
				return new ModelAndView("packagesku",wbl_PackageSkuService.listPackageSku(skuId));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	    }
}
