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

import com.wbdp.bee.entity.Wbl_AttributeView;
import com.wbdp.bee.service.Wbl_AttributeAndValueService;

@Controller
public class AttributeAndValueController {
	
	@Autowired
	private Wbl_AttributeAndValueService wbl_AttributeAndValueService;
	@ResponseBody
   	@RequestMapping(value="/attributeAndValue",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> attributeAndValue(@RequestBody String json,HttpSession session){
		System.out.println(json);
		return wbl_AttributeAndValueService.getWbl_AttributeAndValue(json);
    }
	//获取所有的属性名称
	@ResponseBody
   	@RequestMapping(value="/getattribute",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
   	public Map<String,Object> getattribute(){
		
		return wbl_AttributeAndValueService.getWbl_Attribute();
    }
	@ResponseBody
   	@RequestMapping(value="/attributeView",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public ModelAndView attributeView(Integer pageNum){
		return new ModelAndView("attribute",wbl_AttributeAndValueService.selectAttributeView(pageNum));
	}
	
	@ResponseBody
   	@RequestMapping(value="/addattributeView",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public Map<String,Object> addattributeView(Wbl_AttributeView wbl_AttributeView,Integer type){
		System.out.println("新增属性："+wbl_AttributeView.getAttribute()+""+type);
		return wbl_AttributeAndValueService.insertAttributeAndValue(wbl_AttributeView, type);
	}
}
