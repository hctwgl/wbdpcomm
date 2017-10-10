package com.wbdp.bee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.wbdp.bee.util.UtilPOI;

@Controller
public class Test {

	
	
	/*
	 * 上传Excel
	 
   @ResponseBody
   @RequestMapping(value="/excel",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
   public String Excel(@RequestParam("excel")MultipartFile excel,HttpServletRequest request){	
	   System.err.println("进入Controller");
	   System.out.println("进入Controller");
	    try {
	    	List<String[]> result=UtilPOI.readExcel(excel);
	    	System.out.println("数据读取完成");

             return JSON.toJSONString(result); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return null;
   }*/
   
}
