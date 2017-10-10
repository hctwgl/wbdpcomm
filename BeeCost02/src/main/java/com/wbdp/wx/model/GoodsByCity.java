package com.wbdp.wx.model;

public class GoodsByCity {

    /**当前起始*/
    private int limit ;
    /**手机型号*/
    private String city;
    /**运营商*/
    private String otype;
    /**价格类型*/
    private int type;
    
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getOtype() {
		return otype;
	}
	public void setOtype(String otype) {
		this.otype = otype;
	}
	@Override
	public String toString() {
		return "[limit=" + limit + ", city=" + city + ", otype="
				+ otype + ", type=" + type + "]";
	}
}
