package com.wbdp.wx.utils.http;


import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.FileItem;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditAntifraudScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditAntifraudScoreGetResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditScoreGetResponse;

public class ZhimaCreditAntifraudScore {

    //芝麻开放平台地址
    private static final String URL  = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Idf
    private static final  String APPID  = "1004091";
    //商户 RSA 私钥
    private static final  String PRIKEY  ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALvw0wT8Lyd9VMSGRNmc8HPlM9VeOv1D3qBPEe1vSZgkQwVl8ygDkMP96gr/xi2d7r7NHYqUilzNvvhUzWQSXdneXRsLYcMcCEAYuTGmuClL7IQERKGg1vaHDAjPpJyekN9i7XU8hsFOeYxWpddeQBqE/UgG9D6SFt+82fG1oMCbAgMBAAECgYEAqDDbCAkPwg1dgQqQLxdN8Mnb20UbEXBdmaVpa80+VVOOTxRvyQ13zo47l2BS4JwH/uTdXRmVwXu3PdHWhYoSRdDvDhhAsJ+Z6EBAY/JIKJGol9+VpyeTnaZWIXtm3XIUYRrCxveNZjbeTehaiHNM/jDleOls66xXqL9hT8wcKmECQQDjC6BhbvS/kD6JFU66rRJ1a5812wAshDTUlUrNBuTmEUXVmYgx1qQFxmP5BFJ7HFu19zHyA2aSS9sDx+SlWGHzAkEA0+iLBIifTns8m8NUgrokqqhYd0zsjRPWALNqMG2l33pAhWScFKEpTb5NXAyGXz6Fjzgv6MKfpt/r5BcpWbwouQJBALGD3ZlQTzuXY816Q1vhBZWY15ob6D7l/whW0W0L/tuClb8zkzCkSQna2z9PCc4HlgzngL1fu0qW1F5YY4SDXdECQDfzdoFD8YwvTdlywaWisHu+51zYmrzFOAQHW0peKSXQiaiu3+cyhk1YqeRcCj6rq2gdVckHWlAdX5Nh6jEfVUECQAdYaI0i6C0/p/ntRd9PTfKH5k27gaQYIfFtooKxJLJjM2rA0Ck+hfG0WxchTk74gl04Wg561PTJzJjGYswAVkg=";
    //芝麻 RSA 公钥
    private static final  String PUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDB0cGA8lB1zbzFFRKW/OMpwvxkTArLIjy6EChLz3WZReA23+sweqb+Mvt6NPX21e1wW2OU39en6fP9Lzix1NDudw6UeO4VWY/hzYOflX3dco21uDobAn1+ykED6ar+9xU1xJO3mogfvB0HO+gKWAayotTFg/0bPf1L094jGzuEXQIDAQAB";    
    //统一字符集
    private static final String CHARSET  = "UTF-8";
    //时间格式化
    private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");


    /**
     * 
     * 方法名: ZhimaCreditAntifraudScoreGet   
     * 方法描述: 芝麻欺诈分数查询
     * 入参描述: card:身份证,name:姓名,phone:手机号
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年8月18日
     */
     public static String ZhimaCreditAntifraudScoreGet(String card,String name) {
     	 ZhimaCreditAntifraudScoreGetRequest req = new ZhimaCreditAntifraudScoreGetRequest();
          req.setChannel("apppc");
          req.setPlatform("zmop");
          req.setProductCode("w1010100003000001100");// 必要参数 
          req.setTransactionId(dateformat.format(System.currentTimeMillis()));// 必要参数 
          req.setCertType("IDENTITY_CARD");// 必要参数 
          req.setCertNo(card);// 必要参数 (身份证号)
          req.setName(name);// 必要参数 (姓名)
          //req.setMobile(phone);//  (手机号)
          DefaultZhimaClient client = new DefaultZhimaClient(URL,APPID,CHARSET,PRIKEY,PUBKEY);
          try {
              ZhimaCreditAntifraudScoreGetResponse response = client.execute(req);
              return JSON.toJSON(response).toString();
          } catch (ZhimaApiException e) {
              e.printStackTrace();
          }
          return null;
     }
    

    public static void main(String[] args) throws InterruptedException {     
    	String result = ZhimaCreditAntifraudScoreGet("430423199408131417","姜艳伟");
    	JSONObject obj = JSONObject.fromObject(result);
    	
    	System.out.println(obj.getString("success"));
    	}
    
}
