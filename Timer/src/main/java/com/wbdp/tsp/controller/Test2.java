package com.wbdp.tsp.controller;

import java.util.LinkedHashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.tsp.entity.qxkj.createQuoteB.CResult;
import com.wbdp.tsp.entity.qxkj.querycarmodelInfos.CarModelInfos;
import com.wbdp.tsp.entity.qxkj.querycarmodelInfos.QResult;
import com.wbdp.tsp.service.QxkjService;
import com.wbdp.tsp.service.Impl.QxkjServiceImpl;
import com.wbdp.tsp.util.HttpSend;

public class Test2 {

	 
	
	public static void main(String[] args) {
		QxkjServiceImpl impl=new QxkjServiceImpl(); 
        CResult c=impl.createQuoteB();
         String re=impl.submitQuote(c.getTaskId());
         String re2=impl.querySubmitQuoteResult(c.getTaskId());
        
         System.out.println(re);
         System.out.println(re2);
         
//		  QResult list=impl.queryCarModelInfos("大众FV7160BBMBG");
//		  
//		        List<CarModelInfos> i=list.getCarModelInfos();
//		          
//		   System.out.println(list.getTotalSize());
//		   int temp=1;
//		    for(CarModelInfos a:i){
//		    	System.out.println(temp++);
//		    	 System.out.println(a.getVehicleName());
//		    	 System.out.println(a.getVehicleId());
//		    	System.out.println(a.getPrice());
//		    	System.out.println(a.getGearbox());
//		    	System.out.println(a.getMaketDate());
		    	
		    	
		    
	}
}
