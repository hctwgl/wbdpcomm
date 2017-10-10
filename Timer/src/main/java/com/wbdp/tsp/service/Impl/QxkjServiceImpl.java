package com.wbdp.tsp.service.Impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wbdp.tsp.entity.qxkj.createQuoteB.CResult;
import com.wbdp.tsp.entity.qxkj.querycarmodelInfos.CarModelInfos;
import com.wbdp.tsp.entity.qxkj.querycarmodelInfos.QResult;
import com.wbdp.tsp.service.QxkjService;
import com.wbdp.tsp.util.HttpSend;

@Service
public class QxkjServiceImpl implements QxkjService{

	private Logger logger=Logger.getLogger(QxkjServiceImpl.class);

	
	
	/*
	 * 1.查询车型
	 * @see com.wbdp.tsp.service.QxkjService#queryCarModelInfos(java.lang.String)
	 */
	@Override
	public QResult queryCarModelInfos(String vehicleName) {
		// TODO Auto-generated method stub
		String url="http://api-test.qxnt88.com/open/queryCarModelInfos";
		
	//建立有序的json嵌套Map
		LinkedHashMap<String, Object> json=new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> bizParam=new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> carInfo=new LinkedHashMap<String, Object>();
	//carInfo内嵌套信息
		 carInfo.put("vehicleName", vehicleName);
	//bizParam内嵌套信息
		 bizParam.put("pageSize", "5000");
		 bizParam.put("pageNum", "1");
		 bizParam.put("carInfo", carInfo);
		 String bizParmJson=JSON.toJSONString(bizParam);
	//请求接口的json最外层
		    json.put("channel", "testchannel");
			json.put("bizParam", bizParmJson);
	//序列化json嵌套的MAP		 
			 String apiJson=JSON.toJSONString(json); 	

	//执行接口调用,并将返回的JSON结果集封装成JSONobject对象	
		JSONObject jsonList=JSONObject.parseObject(HttpSend.doPost(url, apiJson));
	//解析结果集里面的"result"内容	
	       JSONObject jsonListResult=(JSONObject)jsonList.get("result");
	       
	  //将"result"里面的内容填充到实体类
	                   QResult resultlist=JSONObject.parseObject(jsonListResult.toJSONString(),QResult.class);
	 //将"result"里面的carModelInfos嵌套内容填充到实体类
	                   List<CarModelInfos> carModelInfos=JSON.parseArray(jsonListResult.getJSONArray("carModelInfos").toJSONString(),CarModelInfos.class); 

		return resultlist;
	}


	/*
	 * 2.创建报价
	 * @see com.wbdp.tsp.service.QxkjService#createQuoteB()
	 */
	@Override
	public CResult createQuoteB(){
		// TODO Auto-generated method stub
		String url="http://api-test.qxnt88.com/open/createQuoteB";

		LinkedHashMap<String, Object> jsonMap=new LinkedHashMap<String, Object>();
		//请求json
		LinkedHashMap<String, Object> bizParam=new LinkedHashMap<String, Object>();
		//汽车具体信息
		LinkedHashMap<String, Object> carInfo=new LinkedHashMap<String, Object>();
		//车主具体信息
		LinkedHashMap<String, Object> carOwner=new LinkedHashMap<String, Object>();
		//险种信息
		LinkedHashMap<String, Object> insureInfo=new LinkedHashMap<String, Object>();
		//保险起止日期
		LinkedHashMap<String, Object> efcInsureInfo =new LinkedHashMap<String, Object>();
		//是否代缴车船税
		LinkedHashMap<String, Object> taxInsureInfo =new LinkedHashMap<String, Object>();
		//险种以及起止日期
		LinkedHashMap<String, Object> bizInsureInfo  =new LinkedHashMap<String, Object>();
		 //商业险种，包含在bizInsureInfo中。
		LinkedHashMap<String, Object> riskKinds01  =new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> riskKinds02  =new LinkedHashMap<String, Object>();
		//存放不同险种信息
		ArrayList<LinkedHashMap> list = new ArrayList<LinkedHashMap>();
		//车型信息
		carInfo.put("isNew", "N");
		carInfo.put("carLicenseNo", "粤AP3322");
		carInfo.put("vinCode", "LGBG122338Y585151");
		carInfo.put("engineNo", "472363B");
		carInfo.put("registDate", "2011-06-20");
		carInfo.put("vehicleId", "402880991134d503011191fb76310b4f");
		//车主信息
		carOwner.put("name", "测试赵");
		carOwner.put("idcardType", "0");
		carOwner.put("idcardNo", "350624198001133017");
		//交强险起止时间
		efcInsureInfo.put("startDate", "2017-07-22");
		efcInsureInfo.put("endDate", "2017-07-23");
		//是否代缴车船税
		taxInsureInfo.put("isPaymentTax", "Y");
		//商业险种起止时间
		bizInsureInfo.put("startDate", "2017-07-22");
		bizInsureInfo.put("endDate", "2017-07-23");
		//商业保险险种
		riskKinds01.put("riskCode", "ThirdPartyIns");
		riskKinds01.put("riskName", "第三者责任险");
		riskKinds01.put("amount", "20000");
		riskKinds01.put("notDeductible", "Y");
		riskKinds02.put("riskCode", "DriverIns");
		riskKinds02.put("riskName", "司机责任险");
		riskKinds02.put("amount", "10000");
		riskKinds02.put("notDeductible", "Y");
		//将不同险种放入集合
		list.add(riskKinds01);
		list.add(riskKinds02);
		//将险种集合放入bizInsureInfo中
		bizInsureInfo.put("riskKinds", list);
		//请求json,按照顺序分别加入列表
		insureInfo.put("efcInsureInfo", efcInsureInfo);
		insureInfo.put("taxInsureInfo", taxInsureInfo);
		insureInfo.put("bizInsureInfo", bizInsureInfo);
		//将请求json信息按顺序放入其中
		bizParam.put("insureAreaCode", "441900");
		bizParam.put("carInfo", carInfo);
		bizParam.put("carOwner", carOwner);
		bizParam.put("insureInfo", insureInfo);
		//请求接口的json最外层 
		String bizJson=JSON.toJSONString(bizParam);
		jsonMap.put("channel", "testchannel");
		jsonMap.put("bizParam", bizJson);  
	    //序列化json嵌套的MAP
		 String apiJson=JSON.toJSONString(jsonMap); 
	
		//执行接口调用,并将返回的JSON结果集封装成JSONobject对象	
			JSONObject jsonList=JSONObject.parseObject(HttpSend.doPost(url, apiJson));
		//解析结果集里面的"result"内容	
		       JSONObject jsonListResult=(JSONObject)jsonList.get("result");
		       
	    //将"result"里面的内容填充到实体类
               CResult resultlist=JSONObject.parseObject(jsonListResult.toJSONString(),CResult.class);
               
		return resultlist;
	}

    /*
     * 3.提交报价
     * @see com.wbdp.tsp.service.QxkjService#submitQuote()
     */
	@Override
	public String submitQuote(String taskId) {
		// TODO Auto-generated method stub
		String url="http://api-test.qxnt88.com/open/submitQuote";
		
		  //建立有序的json嵌套Map
		    LinkedHashMap<String, Object> json=new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> bizParam=new LinkedHashMap<String, Object>();
			    //taskId内嵌信息
			        bizParam.put("taskId", taskId);
			         String bizParmJson=JSON.toJSONString(bizParam);
			     //请求接口的json最外层    
			        json.put("channel", "testchannel");
			        json.put("bizParam", bizParmJson);
			     //  序列化json嵌套的MAP 
			       String Json=JSON.toJSONString(json);
			       
	      //执行接口调用	       		 
		return HttpSend.doPost(url, Json);
	}


	/*
	 * 4.报价回调
	 * @see com.wbdp.tsp.service.QxkjService#querySubmitQuoteResult(java.lang.String)
	 */
	@Override
	public String querySubmitQuoteResult(String taskId) {
		// TODO Auto-generated method stub
		String url="http://api-test.qxnt88.com/open/querySubmitQuoteResult";
	System.out.println("报价回调taskId:"+taskId);
		  //建立有序的json嵌套Map
		    LinkedHashMap<String, Object> json=new LinkedHashMap<String, Object>();
		    
			LinkedHashMap<String, Object> bizParam=new LinkedHashMap<String, Object>();
			    //taskId内嵌信息
			        bizParam.put("taskId", taskId);
			         String bizParmJson=JSON.toJSONString(bizParam);
			     //请求接口的json最外层    
			        json.put("channel", "testchannel");
			        json.put("bizParam", bizParmJson);
			     //  序列化json嵌套的MAP 
			       String Json=JSON.toJSONString(json);
	System.out.println("报价回调请求JSON:"+Json);		       
	      //执行接口调用	       		 
		return HttpSend.doPost(url, Json);
	}
	

	
}
