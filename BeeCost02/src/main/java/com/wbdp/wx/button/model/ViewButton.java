package com.wbdp.wx.button.model;
/**
 * view类型的按钮，有三种属性type、name、url
 * @author 汪赛军
 * date:2017年7月31日下午5:15:29
 *
 */
public class ViewButton extends Button {
	private String type;
	private String url;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
