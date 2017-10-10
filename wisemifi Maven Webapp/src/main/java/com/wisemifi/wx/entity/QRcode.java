package com.wisemifi.wx.entity;

/**
 * 客户生成二维码实体类
 * @author 汪赛军
 * date:2017年9月14日上午9:34:20
 *
 */
public class QRcode {
	/**
	 * 姓名
	 */
	private String customerName;
	
	/**
	 * 电话
	 */
	private String customerPhone;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	@Override
	public String toString() {
		return "customerName"+"="+customerName+","+"customerPhone"+"="+customerPhone;
	}
}
