package com.wbdp.wx.button.model;
/**
 * click类型按钮类，该类按钮有三种属性type、name、key
 * @author 汪赛军
 * date:2017年7月31日下午5:13:41
 *
 */
public class ClickButton extends Button {
	private String type;
	private String key;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
