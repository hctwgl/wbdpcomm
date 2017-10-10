package com.wbdp.bee.api.limu;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:社保
 * @author kx
 * @version v1.2.0
 */
public class LimuSocialSecurity extends AbstractCredit {

    //业务参数
    public static final String method = "api.socialsecurity.get";//请求接口
    public static final String bizType = "socialsecurity";//业务类型
    public static final String callBackUrl = "";//回调地址

   // public static final String username = "Jianff";//用户名---需客户指定
   // public static final String password = "Jian123";//密码---需客户指定
    public static final String area = "4403";//地区码---需客户指定
    public static final String realName = "";//真实姓名-非必填---需客户指定
    public static final String otherInfo = "";//其他信息-非必填---需客户指定

    public static void main(String[] args) throws Exception {

        //启动服务
        LimuSocialSecurity service  = new LimuSocialSecurity();
        
        System.err.println("结果集为:"+service.startQuery("jjj123", "123654"));

    }

    /**
     * 方法名: startQuery   
     * 方法描述: 查询社保信息
     * 入参描述: username:社保账号,password:社保密码 
     * 回调描述:   
     * 创建人:wisedata004  
     * 创建时间: 2017年7月11日
     */
	public String startQuery(String username,String password) throws Exception{
        System.out.println("开始获取社保信息");

        try {

            //提交受理请求对象
            List<BasicNameValuePair> reqParam = new ArrayList<BasicNameValuePair>();
            reqParam.add(new BasicNameValuePair("apiKey", apiKey));//API授权
            reqParam.add(new BasicNameValuePair("version", version));//调用的接口版本
            reqParam.add(new BasicNameValuePair("callBackUrl", callBackUrl));//callBackUrl
            reqParam.add(new BasicNameValuePair("method", method));//接口名称

            reqParam.add(new BasicNameValuePair("username", username));    //社保账号
            reqParam.add(new BasicNameValuePair("password",  new String(Base64.encodeBase64(password.getBytes("UTF-8")))));//社保密码
            reqParam.add(new BasicNameValuePair("area", area));//地区
            reqParam.add(new BasicNameValuePair("realName", realName));//真实姓名
            reqParam.add(new BasicNameValuePair("otherInfo", otherInfo));//其他信息
            reqParam.add(new BasicNameValuePair("sign", getSign(reqParam)));//请求参数签名

            //提交受理请求
            return doProcess(reqParam);

        }catch (Exception ex){
            System.out.println("开始获取社保信息异常：" + ex);
        }
        return null;
	}


    /**
     * 获取业务类型
     */
    @Override
    public String getBizType(){
        return bizType;
    }
}
