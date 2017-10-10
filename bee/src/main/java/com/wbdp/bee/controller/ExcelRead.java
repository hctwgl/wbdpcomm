package com.wbdp.bee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.wbdp.bee.util.UtilPOI;

@Controller
public class ExcelRead {


	/*
	 * 上传Excel
	 */
   @ResponseBody
   @RequestMapping(value="/excel",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
   public String Excel(@RequestParam("excel")MultipartFile excel,HttpServletRequest request,HttpSession session){	
	   System.out.println("开始上传集团客户");
	    try {
	    	List<String[]> result=UtilPOI.readExcel(excel);
	    	String sessionid = session.getId();
	    	//将读取Excel的数据存入session中，等待用户确认
	    	session.setAttribute(sessionid+"groupClient",result);
	    	System.out.println(JSON.toJSONString(result));
             return JSON.toJSONString(result); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	   return null;
   }
}
