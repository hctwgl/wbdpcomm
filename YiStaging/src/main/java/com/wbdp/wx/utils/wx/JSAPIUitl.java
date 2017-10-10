package com.wbdp.wx.utils.wx;

import java.util.Date;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wbdp.wx.constant.WXStatic;
import com.wbdp.wx.utils.File.FileUitls;
import com.wbdp.wx.utils.string.RandomStrUtils;


/**
 * 微信js_api调用
 * @author wisedata005
 */
public class JSAPIUitl {
	private static Logger log = LoggerFactory.getLogger(JSAPIUitl.class);
	/**
	 * 生成签名
	 * @param ticket
	 * @param url
	 * @return
	 */
	public static JSONObject createWebSignature(String ticket, String url){
		JSONObject returnStr= null;
		if(!ticket.equals("")&&!url.equals("")){

			String noncestr = RandomStrUtils.RandomStr();
			String timestamp = String.valueOf(new Date().getTime() / 1000);
//			String noncestr = "xDq3Iv612x";
//			String timestamp = "1489388401";
			log.info("noncestr:"+noncestr);
			log.info("timestamp:"+timestamp);
			String[] arr = new String[]{ticket,noncestr,timestamp,url};
			
			//生成字符串
			StringBuffer content = new StringBuffer();
			content.append("jsapi_ticket="+arr[0]);
			content.append("&noncestr="+arr[1]);
			content.append("&timestamp="+arr[2]);
			content.append("&url="+arr[3]);
			
			//sha1加密
			String signature = CheckUtil.getSha1(content.toString());
			log.info("signature:"+signature);
			
			returnStr = new JSONObject();
			returnStr.put("timestamp", timestamp);
			returnStr.put("noncestr", noncestr);
			returnStr.put("signature", signature);
			returnStr.put("appId", 
					FileUitls.readWeiChatProperties(WXStatic.APPID, WXStatic.WEICHATPROP));
			returnStr.put("jsapi_ticket", ticket);
			log.info("string1："+returnStr.toString());
		}
		return returnStr;
	}
	@Test
	public void test(){
//		createWebSignature(WeiChatStatic.TEMPORARY_TICKET,"http://www.wisedp.com/WbdpSSM/wc/onlin/illcod?" +
//				"code=0419yL2w1UU0rb0gTR1w12m33w19yL2n&state=123");
//		String noncestr = RandomStrUtils.RandomStr();
//		String timestamp = String.valueOf(new Date().getTime() / 1000);
	}
}