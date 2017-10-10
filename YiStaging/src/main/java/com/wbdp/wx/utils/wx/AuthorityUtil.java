package com.wbdp.wx.utils.wx;

import com.wbdp.wx.WXEntity.WebAccessToken;
import com.wbdp.wx.constant.WXStatic;
import com.wbdp.wx.constant.WXURL;
import com.wbdp.wx.utils.File.FileUitls;
import com.wbdp.wx.utils.http.HttpUtil;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信授权接口
 * Created by wisedata005 on 2017/7/5.
 */
public class AuthorityUtil {
    /**日志log*/
    private static Logger log = LoggerFactory.getLogger(AuthorityUtil.class);

    /**
     * 获取用户的oppendid
     * @param code
     * @return
     */
    public static String getOpenID(String code){
        WebAccessToken globalAccess_Token = getGlobalAccess_Token(code);
        if(globalAccess_Token!=null){
            String openid = globalAccess_Token.getOpenid();
            return openid;
        }else
            return null;
    }

    /**
     * 通过code换取网页授权的access_token
     * @param code
     * @return
     */
    public static WebAccessToken getGlobalAccess_Token(String code){
        String url = WXURL.WEB_ACCESS_TOKEN_URL.replace(WXStatic.APPID, FileUitls.
                readWeiChatProperties(WXStatic.APPID, WXStatic.WEICHATPROP)).replace(
                "SECRET", FileUitls.readWeiChatProperties(WXStatic.APPSECRET,
                        WXStatic.WEICHATPROP)).replace("CODE", code);
        JSONObject globalAccTokjson = HttpUtil.doGetStr(url);
        log.info("一分期中的APPID:"+FileUitls.
                readWeiChatProperties(WXStatic.APPID, WXStatic.WEICHATPROP));
        WebAccessToken token = null;
        if(!globalAccTokjson.has(WXStatic.ERRCODE)&&!globalAccTokjson.equals("")){
            token = new WebAccessToken();
            token.setAccessToken(globalAccTokjson.getString(WXStatic.ACCESS_TOKENX));
            token.setExpiresIn(globalAccTokjson.getInt("expires_in"));
            token.setRefreshToken(globalAccTokjson.getString("refresh_token"));
            token.setOpenid(globalAccTokjson.getString("openid"));
            token.setScope(globalAccTokjson.getString("scope"));
            return token;
        }else{
            int errcode = globalAccTokjson.getInt(WXStatic.ERRCODE);
            log.error("errcode:"+errcode);
            return null;
        }
    }
    /**
     * 拼接成获取授權code的codeurl
     * @param redirectUrl 重定向的鏈接
     * @param scop_type “0 為 snsapi_userinfo；1為 snsapi_base”
     * @return
     */
    public static String JointCodeUrl(String redirectUrl,int scop_type){
        if(redirectUrl!=null){
            String codeUrl = null;
            if(scop_type==0){
                codeUrl = WXURL.CODE_URL.replace(WXStatic.APPID,
                        FileUitls.readWeiChatProperties(WXStatic.APPID, WXStatic.WEICHATPROP)).
                        replace(WXStatic.REDIRECT_URI, redirectUrl).replace(WXStatic.SCOPE,
                        WXStatic.SNSAPITYPE1);
            } else if(scop_type==1){
                codeUrl = WXURL.CODE_URL.replace(WXStatic.APPID,
                        FileUitls.readWeiChatProperties(WXStatic.APPID, WXStatic.WEICHATPROP)).
                        replace(WXStatic.REDIRECT_URI, redirectUrl).replace(WXStatic.SCOPE,
                        WXStatic.SNSAPITYPE2);
            }
            return codeUrl;
        }else
            return null;
    }
    @Test
    public void test(){

        try {
//            String encode = URLEncoder.encode("http://www.wisedp.com/BeeCost/tomine", "UTF-8");
//            String encode = URLEncoder.encode("http://www.wisedp.com/BeeCost/toPhoneShop", "UTF-8");toShopCart
//          String encode = URLEncoder.encode("http://www.wisedp.com/BeeCost/toregister", "UTF-8");
          String encode = URLEncoder.encode("http://www.wisedp.com/YiStaging/tomine", "UTF-8");
            String jointCodeUrl = AuthorityUtil.JointCodeUrl(encode,1);
            System.out.println("jointCodeUrl:"+jointCodeUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
