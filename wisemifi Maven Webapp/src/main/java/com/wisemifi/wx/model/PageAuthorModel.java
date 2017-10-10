package com.wisemifi.wx.model;

/**
 * 网页授权模型类
 * @author 汪赛军
 * date:2017年8月10日上午9:17:11
 *
 */
public class PageAuthorModel {
	private String code;
	private String openid;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
