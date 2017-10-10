package com.wbdp.wx.button.model;
/**
 * 复合型按钮模型，
 * @author 汪赛军
 * date:2017年7月31日下午5:18:18
 *
 */
public class ComplexButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}

	
	
}
