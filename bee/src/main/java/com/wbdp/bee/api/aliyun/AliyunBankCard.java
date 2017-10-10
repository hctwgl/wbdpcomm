package com.wbdp.bee.api.aliyun;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class AliyunBankCard {

	private static final String HOST = "http://aliyuncardby4element.haoservice.com";
	
	private static final String PATH = "/creditop/BankCardQuery/QryBankCardBy3Element";
	
	private static final String APPCODE="bb5dc31153bd4e3f9d7f337763e01329";
	
	private static Logger logger=Logger.getLogger(AliyunBankCard.class);
	

	/**
	 * 方法名: query   
	 * 方法描述: 银行卡三元素认证 
	 * 入参描述: accountNo:银行卡,idCardCode:身份证,name:姓名
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年8月3日
	 */
	@SuppressWarnings("finally")
	public static boolean query(String accountNo,String idCardCode,String name){
        //封装头信息
	    Map<String, String> headers = new HashMap<String, String>();
	       headers.put("Authorization", "APPCODE " + APPCODE);
	    //封装内容信息   
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("accountNo", accountNo);
	    querys.put("idCardCode", idCardCode);
	    //querys.put("bankPreMobile",bankPreMobile);
	    querys.put("name", name);	
			try {
				//执行查询
				JSONObject response = (JSONObject) JSON.parse(AliyunHttp.doGet(HOST, PATH, "GET", headers, querys));
				System.out.println(response);
				//如果查询成功
				 if(response.getInteger("error_code")==0){
					  JSONObject result=(JSONObject) response.get("result");
					//如果验证成功  
				     if(result.getString("result").equals("T")){
				    	 return true;
				     }else{
				    	 return false;
				     }
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("银行卡三元素认证异常"+e);
			}
			return false;
	}
	
	
	public static void main(String[] args) {
		System.out.println(AliyunBankCard.query("6228481628168884377","421124199410192056","蒋宴炜"));
		
		//{"result":{"message":"银行卡鉴权成功","result":"T","idCardCore":"421124199410192057","name":"蒋宴炜","messagetype":0,"accountNo":"6228481628168884374"},"reason":"成功","error_code":0}
	}
}
