package com.wbdp.tsp.service.Impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.tsp.dao.TrafficViolationDAO;
import com.wbdp.tsp.service.TrafficViolationService;
import com.wbdp.tsp.util.HttpTest;
import com.wbdp.tsp.util.UtilJuhe;

@Service
public class TrafficViolationServiceImpl implements TrafficViolationService{

	@Resource
	private TrafficViolationDAO trafficDAO;
	
	private final String url="http://www.wisedp.com/WbdpSSM/wc/push/illegal";

	private final Logger logger=Logger.getLogger(TrafficViolationServiceImpl.class);
		
	/**
	 * 查询所有车辆的违章信息,并且推送给微信
	 */
	public void TrafficViolationPush(){

		//获取需要查询违章的所有车辆
		List<Map<String, String>> vehiceList=trafficDAO.getAllTrafficViolation();
		
		//遍历循环每辆车,以获取每辆车的信息
		   for(Map<String, String> vehiceInfo:vehiceList){
			 //根据城市名称获取 该城市的code
		        Map<String, String> cityCode=UtilJuhe.getRequest1(vehiceInfo.get("City"));
			       if(cityCode!=null){
			    	   //查询得到所有车辆的违章信息
			    	   JSONObject resultJSON=JSON.parseObject(UtilJuhe.getRequest2(vehiceInfo.get("PlateNumber"), vehiceInfo.get("EngineNumber"), vehiceInfo.get("VIN"), cityCode)).getJSONObject("result");
			    	     //解析违章信息
			    	      JSONArray lists=resultJSON.getJSONArray("lists");
			    	      //如果有违章信息则写入结果List,幷发送给微信
			    	       if(!lists.isEmpty()){
			    	    	   for(int i=0;i<lists.size();i++){
			    	    	     JSONObject ob=(JSONObject) lists.get(i);
			    	    	     try {
			    	    	    	//封装每条数据幷推送给微信 vehiceInfo.get("OwnerWX")
			    	    	    	 Map<String, String> map=new HashMap<String, String>();
			    	    	          map.put("ownerwx", vehiceInfo.get("OwnerWX"));  //微信号
			    	    	          map.put("submitDate",ob.get("date").toString()); //违章时间
			    	    	          map.put("place",ob.get("area").toString());  //违章地点
			    	    	          map.put("first", "尊敬的"+vehiceInfo.get("PlateNumber")+"车主，您有"+lists.size()+"条违章记录，扣分"+ob.get("fen").toString()+"，罚款"+ob.get("money").toString()+"元");  //头部
			    	    	          map.put("last", ob.get("act").toString());   //违章详情
			    	    	          
									  HttpTest.pushPost(url, JSON.toJSONString(map), "illegal");
									} catch (Exception e) {
										// TODO Auto-generated catch block
										logger.error("推送违章查询错误");
										e.printStackTrace();
									}
			    	    	   }
			    	     }
			       }   
		   }
	}


	
}
