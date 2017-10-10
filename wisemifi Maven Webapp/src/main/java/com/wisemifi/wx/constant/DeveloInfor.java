package com.wisemifi.wx.constant;

/**
 * 微信公众号开发信息常量类
 * @author 汪赛军
 * date:2017年8月8日下午5:56:33
 *
 */
public class DeveloInfor {
	/**
	 * 开发者ID是公众号开发识别码，配合开发者密码可调用公众号的接口能力。
	 */
	public static final String AppID = "wxb125fba57e2524e2";
	/**
	 * 开发者密码是校验公众号开发者身份的密码，具有极高的安全性。
	 */
	public static final String AppSecret = "83193acf3f705103d9116d226b7f0640";
	/**
	 * 获取普通access_token接口
	 */
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * 创建自定义按钮接口
	 */
	public static final String CREATE_BUTTON_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 网页授权获取access_token与openID接口
	 */
	public static final String CODE_ACCESS_TOKEN= "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	/**
	 * 获取jsapi_ticket接口
	 */
	public static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	/**
	 * 统一下单接口
	 */
	public static final String UNIFORM_ORDERS = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**
	 * 商户号
	 */
	public static final String BUSINESS_NUMBER = "1487331152";
	
	/**
	 * 支付API秘钥
	 */
	public static final String API_KEY = "427fb629fa01161bda6399db19d1f9b6";
	
	/**
	 * 维泽MIFI购买支付成功后回调路径
	 */
	public static final String MIFI_CALLBACK_URL = "http://www.wisedp.com/wisemifi/wx/wisemifi/topay_back/";
	
	/**
	 * 维泽MIFI流量充值支付成功后回调路径
	 */
	public static final String RECHAR_CALLBACK_URL = "http://www.wisedp.com/wisemifi/wx/recharge/topay_back/";
	
	/**微信推送信息url*/
	public static final String SEND_PUSH_MESS_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
}
