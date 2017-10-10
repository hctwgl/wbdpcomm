package com.wbdp.wx.constant;

/**
 * Created by wisedata005 on 2017/7/5.
 */
public class WXURL {
    /** 通过code换取网页授权的access_token的接口URL */
    public static final String WEB_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?"
            + "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /** 获取网页的TICKET的URL */
    public static final String WEB_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
            + "access_token=ACCESS_TOKEN&type=jsapi";

    /** 获取普通的access_token的URL */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
            + "grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /** 获取code的拼接链接的URL */
    public static final String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?"
            + "appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=123#wechat_redirect ";

}
