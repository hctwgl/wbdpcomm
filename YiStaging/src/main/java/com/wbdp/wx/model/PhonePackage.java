package com.wbdp.wx.model;

public class PhonePackage {
	/**应付金额*/
	private String payable;
	/**套餐类型*/
	private String wpackage;
	/**套餐详情*/
	private String details;
	/**套餐id*/
	private long pID;
	/**套餐总价*/
	private int PacPrice;
	
	public int getPacPrice() {
		return PacPrice;
	}
	public void setPacPrice(int pacPrice) {
		PacPrice = pacPrice;
	}
	public String getPayable() {
		return payable;
	}
	public void setPayable(String payable) {
		this.payable = payable;
	}
	public String getWpackage() {
		return wpackage;
	}
	public void setWpackage(String wpackage) {
		this.wpackage = wpackage;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public long getpID() {
		return pID;
	}
	public void setpID(long pID) {
		this.pID = pID;
	}
	@Override
	public String toString() {
		return "[payable=" + payable + ", wpackage=" + wpackage
				+ ", details=" + details + ", pID=" + pID + ", PacPrice="
				+ PacPrice + "]";
	}
}
