package com.wbdp.wx.utils.wx;

import org.junit.Test;

import com.wbdp.wx.WXEntity.JsapiTicket;
import com.wbdp.wx.constant.WXStatic;
import com.wbdp.wx.constant.WXURL;
import com.wbdp.wx.exception.WeiChatExcption;
import com.wbdp.wx.manager.CacheManager;
import com.wbdp.wx.utils.File.FileUitls;
import com.wbdp.wx.utils.http.HttpUtil;
import net.sf.json.JSONObject;

/**
 * Created by wisedata005 on 2017/7/5.
 */
public class WXCacheUnit {

    /**日志log*/
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(WXCacheUnit.class);

    /**
     * 更新acctoken和JS_API_TICKE
     * @return
     */
    public static void updateAccessTokenAndJs_Api_Ticket() {
//		boolean updateRes = Boolean.FALSE;
        try {
            String accessTokenFromWeixin = getOrdinaryAccess_Token();
            JsapiTicket jsApiTicketFromWeiXin = getWebJsapiTicket(accessTokenFromWeixin);
            CacheManager.putCache(WXStatic.WEIXIN_CACHE_KEY_ACCESS_TOKEN,
                    accessTokenFromWeixin);
            CacheManager.putCache(WXStatic.WEIXIN_CACHE_KEY_JS_API_TICKET,
                    jsApiTicketFromWeiXin.getTicket());
//			updateRes = Boolean.TRUE;
        } catch (Exception e) {
        	e.printStackTrace();
            log.error("定时任务更新 微信 端 access token 和 js api ticket 异常：", e);
            try {
                throw e;
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
//		return updateRes;
    }

    /**
     * 从缓存获取accesstoken
     * @return
     */
    public  String getAccessTokenFromCache() {
        String accesstoken = "";
        try {
            accesstoken = (String) CacheManager
                    .getCache(WXStatic.WEIXIN_CACHE_KEY_ACCESS_TOKEN);
            if (accesstoken == null) {
                updateAccessTokenAndJs_Api_Ticket();
                accesstoken = (String) CacheManager
                        .getCache(WXStatic.WEIXIN_CACHE_KEY_ACCESS_TOKEN);
            }
        } catch (Exception e) {
            log.error(" 获取 微信 AccessToken 异常：", e);
            e.printStackTrace();
        }
        return accesstoken;
    }

    /**
     * 从缓存获取accesstoken
     * @return
     */
    public static String getJS_API_TICKETFromCache() {
        String jsApiTicket = "";
        try {
            jsApiTicket = (String) CacheManager
                    .getCache(WXStatic.WEIXIN_CACHE_KEY_JS_API_TICKET);
            if (jsApiTicket == null) {
                updateAccessTokenAndJs_Api_Ticket();
                jsApiTicket = (String) CacheManager
                        .getCache(WXStatic.WEIXIN_CACHE_KEY_JS_API_TICKET);
            }
        } catch (Exception e) {
            log.error(" 获取 微信 jsApiTicket 异常：", e);
            e.printStackTrace();
        }
        return jsApiTicket;
    }

    /**
     * 获取JsapiTicket
     * @param accessToken
     * @return
     */
    public static JsapiTicket getWebJsapiTicket(String accessToken) {
        JsapiTicket ticket = new JsapiTicket();
        String url = WXURL.WEB_JSAPI_TICKET_URL.replace("ACCESS_TOKEN",
                accessToken);
        JSONObject jsonObject = HttpUtil.httpRequest(url, "GET", null);
        if (!jsonObject.equals("")
                && jsonObject.getString("errcode").equals("0")) {
            ticket.setErrcode(jsonObject.getString("errcode"));
            ticket.setErrmsg(jsonObject.getString("errmsg"));
            ticket.setTicket(jsonObject.getString("ticket"));
            ticket.setExpiresIn(jsonObject.getInt("expires_in"));
            System.out.println(jsonObject);
        } else {
            int errcode = jsonObject.getInt("errcode");
            try {
                log.error("获取ticket不成功：" + jsonObject);
                throw new WeiChatExcption(errcode);
            } catch (WeiChatExcption e) {
                e.printStackTrace();
            }
        }

        return ticket;
    }
    /**
     * 获取普通的access_token
     * @return
     */
    public static String getOrdinaryAccess_Token(){
        String access_token_url = WXURL.ACCESS_TOKEN_URL.
                replace(WXStatic.APPID, FileUitls.readWeiChatProperties
                        (WXStatic.APPID, WXStatic.WEICHATPROP))
                .replace(WXStatic.APPSECRET, FileUitls.readWeiChatProperties
                        (WXStatic.APPSECRET, WXStatic.WEICHATPROP));//替换URL参数
        JSONObject ordinaryAccessTokenJson = HttpUtil.doGetStr(access_token_url);//请求微信后台，并获取数据
        String access_token = null;
        System.out.println("一分期的token："+ordinaryAccessTokenJson);
        if(!ordinaryAccessTokenJson.has(WXStatic.ERRCODE)&&!ordinaryAccessTokenJson.equals("")){
            access_token = ordinaryAccessTokenJson.getString(WXStatic.ACCESS_TOKENX);
        }else{
            String errcode = ordinaryAccessTokenJson.getString(WXStatic.ERRCODE);
            Integer.parseInt(errcode);
        }
        return access_token;
    }
    @Test
    public void tet(){
    	String ordinaryAccess_Token = getOrdinaryAccess_Token();
    	System.out.println("ordinaryAccess_Token："+ordinaryAccess_Token);
    }
}
