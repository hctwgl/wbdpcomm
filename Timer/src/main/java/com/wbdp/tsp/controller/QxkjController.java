package com.wbdp.tsp.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wbdp.tsp.entity.qxkj.createQuoteB.CResult;
import com.wbdp.tsp.entity.qxkj.querycarmodelInfos.QResult;
import com.wbdp.tsp.service.QxkjService;

@Controller
public class QxkjController {

	@Resource
	private QxkjService service;
	
	/*
	 * 1.查询车型，入参为车型或品牌。回调为实体类
	 */
	@RequestMapping(value="test")
	public void queryCarModelInfos(){
        //返回查询车型的内容
         QResult r=service.queryCarModelInfos("奥迪");

	}
	
	/*
	 * 1.查询车型，入参为车型或品牌。回调为实体类
	 */
	@RequestMapping(value="test2")
	public void createQuoteB(){

           CResult c=service.createQuoteB();
           System.out.println(c.getTaskId());
	}
}
