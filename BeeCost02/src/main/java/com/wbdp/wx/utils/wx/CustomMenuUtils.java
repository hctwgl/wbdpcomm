package com.wbdp.wx.utils.wx;


import com.wbdp.wx.constant.WXStatic;
import com.wbdp.wx.utils.http.HttpUtil;
import net.sf.json.JSONObject;
import org.junit.Test;


/**
 * 创建微信菜单
 * @author wisedata005
 */
public class CustomMenuUtils {
    public static final String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**日志log*/
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CustomMenuUtils.class);
	
	/**
	 * 创建自定义菜单
	 */
	public static void createMenu(){
		String menuUrl = url.replace("ACCESS_TOKEN", WXStatic.TEMPORARY_ACCESS_TOKEN);
		JSONObject json = createMenuButtonJSON();
		JSONObject httpRequest = HttpUtil.httpRequest(menuUrl, "POST", json.toString());
		
		log.info("httpRequest:"+httpRequest.toString());
	}
	/**
	 * 创建自定义菜单
	 * @return
	 */
	private static JSONObject createMenuButtonJSON(){
		String jsonstr = "{" +
				"\"button\":[" +
				"{" +
						"\"type\":\"view\"," +
						"\"name\":\"我的爱车\"," +
						"\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?" +
						"appid=wx3cf1f550c9404c0e&redirect_uri=http%3A%2F%2Fwww.wisedp.com%" +
						"2FWbdpSSM%2Fwc%2Faddcar%2Ftolovecar&response_type=code&scope=snsapi_base&state=123#wechat_redirect\"" +
				"}," +
				"{	" +
						"\"type\":\"view\"," +
						"\"name\":\"我的wise\"," +
						"\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?" +
						"appid=wx3cf1f550c9404c0e&redirect_uri=http%3A%2F%2Fwww.wisedp.com%" +
						"2FWbdpSSM%2Fwc%2Fmywis%2Ftowis&response_type=code&scope=snsapi_base&state=123#wechat_redirect\"" +
				"}," +
				"{	" +
						"\"type\":\"click\"," +
						"\"name\":\"更多服务\"," +
						"\"sub_button\": [" +
						"{" +
								"\"type\": \"click\"," +
								"\"name\": \"最新优惠\"," +
								"\"key\":\"new_Discount\"" +
						" }," +
						"{	" +
								"\"type\":\"view\"," +
								"\"name\":\"在线车险\"," +
								"\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?" +
								"appid=wx3cf1f550c9404c0e&redirect_uri=http%3A%2F%2Fwww.wisedp.com%" +
								"2FWbdpSSM%2Fwc%2Fonlin%2Fillcod&response_type=code&scope=snsapi_base&state=123#wechat_redirect\"" +
						"}," +
						"{	" +
								"\"type\":\"view\"," +
								"\"name\":\"违章查询\"," +
								"\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?" +
								"appid=wx3cf1f550c9404c0e&redirect_uri=http%3A%2F%2Fwww.wisedp.com%" +
								"2FWbdpSSM%2Fwc%2Fsnicon%2Fillcod&response_type=code&scope=snsapi_base&state=123#wechat_redirect\"" +
						"}," +
						"{" +
								"\"type\":\"click\"," +
								"\"name\": \"故障提醒\"," +
								"\"key\":\"fault_reminder\"" +
						"}," +
						"{	" +
								"\"type\":\"view\"," +
								"\"name\":\"帮助\"," +
								"\"url\":\"http://c.xiumi.us/board/v5/2AP2f/37248517\"" +
						"}" +
						"]" +
				"}" +
				"]" +
				"}";
		JSONObject json = JSONObject.fromObject(jsonstr);
		log.info("json:"+json);
		return json;
	}
	@Test
	public void test(){
//		createMenuButtonJSON();
		createMenu();
	}
}
