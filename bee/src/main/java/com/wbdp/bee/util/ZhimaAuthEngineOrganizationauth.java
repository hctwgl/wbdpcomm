package com.wbdp.bee.util;

import java.text.SimpleDateFormat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.ZhimaAuthEngineOrganizationauthRequest;
import com.antgroup.zmxy.openplatform.api.request.ZhimaCreditEpScoreGetRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaAuthEngineOrganizationauthResponse;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditEpScoreGetResponse;

/**
 * 芝麻信用企业征信
 * @author wisedata004
 *
 */
public class ZhimaAuthEngineOrganizationauth {

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
     * 第一步
     * <p>方法名: testZhimaAuthEngineOrganizationauth</p> 
     * <p>方法描述: 企业协议授权 </p>
     * <p>入参描述: certNo:企业信用码     name:企业名称</p>
     * <p>回调描述: 正确时返回openid标识,示例:{"success":true,"open_id":"226610413909851460431461950"}   </p>
     * <p>        错误时返回示例:{"success":false,"error_code":"ZMCSP.org_certify_failed","error_message":"企业会员认证失败,请确认输入的信息"} </p>
     * <p>创建人:wisedata004  </p>
     * <p>创建时间: 2017年9月6日 </p>
     */                                             
    public static String zhimaAuthEngine(String certNo,String name) {
        ZhimaAuthEngineOrganizationauthRequest req = new ZhimaAuthEngineOrganizationauthRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setIdentityParam("{\"certNo\":\""+certNo+"\",\"certType\":\"NATIONAL_LEGAL\",\"name\":\""+name+"\"}");
        req.setIdentityType("2");// 
        req.setBizParams("{\"auth_code\":\"M_P_COMPANY_UID\"}");// 
        DefaultZhimaClient client = new DefaultZhimaClient(URL, APPID, CHARSET,PRIKEY, PUBKEY);
        try {
            ZhimaAuthEngineOrganizationauthResponse response = client.execute(req);
             return response.getBody();
        } catch (ZhimaApiException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
	

    /**
     * 
     * <p>方法名: ZhimaCreditEpScoreGet</p> 
     * <p>方法描述: 企业信用评分 </p>
     * <p>入参描述: openid:企业授权后返回的openid标识 </p>
     * <p>回调描述: 成功时返回示例:{"success":true,"biz_no":"ZM201709063000000669500719370565","eval_date":"20170904","zm_score":"1337"} </p>
     * <p>       失败时返回示例:{"success":false,"error_code":"ZMCREDIT.openid_parameter_invalid","error_message":"open_id参数错误"}</p>
     * <p>创建人:wisedata004  </p>
     * <p>创建时间: 2017年9月6日 </p>
     */
    public static String zhimaCreditEpScoreGet(String openid) {
        ZhimaCreditEpScoreGetRequest req = new ZhimaCreditEpScoreGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setOpenId(openid);
        req.setTransactionId(dateformat.format(System.currentTimeMillis()));
        req.setProductCode("w1010100003000001418");
        DefaultZhimaClient client = new DefaultZhimaClient(URL, APPID, CHARSET,PRIKEY, PUBKEY);
        try {
            ZhimaCreditEpScoreGetResponse response = client.execute(req);
            return response.getBody();
        } catch (ZhimaApiException e) {
            e.printStackTrace();
            return e.toString();
        }
     
    }
    
    
    public static void main(String[] args) {
    	String result = ZhimaAuthEngineOrganizationauth.zhimaAuthEngine("91440300192","华为技术有限公");
    	JSONObject obj = (JSONObject) JSON.parse(result);
    	System.out.println(obj);
    	//第一步授权
		//System.out.println(ZhimaAuthEngineOrganizationauth.zhimaAuthEngine("91440300MA5DR5D01L","维泽数据有限公司"));
		//第二部查询评分
		//System.out.println(ZhimaAuthEngineOrganizationauth.zhimaCreditEpScoreGet("226610413909851462291073890"));
		//String resultScore = ZhimaAuthEngineOrganizationauth.zhimaCreditEpScoreGet("226610413909851460431461950");
		//JSONObject objScore = (JSONObject) JSON.parse(resultScore);//zm_score
		//System.out.println(objScore.getInteger("zm_score"));
	}
    
}
