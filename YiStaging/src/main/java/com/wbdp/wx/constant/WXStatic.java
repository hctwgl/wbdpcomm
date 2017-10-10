package com.wbdp.wx.constant;

/**
 * Created by wisedata005 on 2017/7/5.
 */
public class WXStatic {
    /** 临时的access_token */
    public static final String TEMPORARY_ACCESS_TOKEN = "FNnwSR4kmTuiKNA4i9B3c7_YbDlk2LlrJrSK2EE3l5mZ4ajXLuyeX" +
            "5f5dnb1-mSgU86IizyaTyUBhnAHe9jSGWl-AOg35xYqi29sDE1CXQeDSdCukQzPkqcKdIaCIbqKJSUjAHADMO";
    /** 临时ticket */
    public static final String TEMPORARY_TICKET = "kgt8ON7yVITDhtdwci0qeYD-guR4mF2uPDXY_5ZEkNzjNNBJLpbLst-wxWy" +
            "ZdOICYm7_65drYt9mRpV6tuyAgA";


    /** 微信緩存的access_token */
    public static final String WEIXIN_CACHE_KEY_ACCESS_TOKEN = "access_taken";
    /** 微信緩存的JS_API_TICKET */
    public static final String WEIXIN_CACHE_KEY_JS_API_TICKET = "js_api_ticket";


    /** 应用唯一标识，在微信开放平台提交应用审核通过后获得 */
    public static final String APPID = "APPID";
    /** 应用密钥AppSecret，在微信开放平台提交应用审核通过后获得 */
    public static final String APPSECRET = "APPSECRET";
    /** 微信授权后重定向的链接参数 */
    public static final String REDIRECT_URI = "REDIRECT_URI";
    /** 普通获取接口的ACCESS_TOKEN参数 */
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    /** 授权作用域的参数 */
    public static final String SCOPE = "SCOPE";
    /** 授权作用域类型默认拥有scope参数中的snsapi_userinfo*/
    public static final String  SNSAPITYPE1 = "snsapi_userinfo";
    /** 授权作用域类型默认拥有scope参数中的snsapi_base*/
    public static final String  SNSAPITYPE2 = "snsapi_base";




    /** 返回数据获取参数 */
    public static final String ACCESS_TOKENX = "access_token";
    /** 错误返回码 */
    public static final String ERRCODE = "errcode";



    /** 微信基本配置信息 */
    public static final String WEICHATPROP = "properties/WXMessage.properties";
    /** 短信验证配置信息 */
    public static final String MESSAGE = "properties/VerifieCode.properties";

}
