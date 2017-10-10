package com.wbdp.wx.model;

import java.util.Arrays;

public class ScreenGoods {

    /**当前起始*/
    private int limit ;
    /**城市*/
    private String[] cityAndotype;
    /**手机类型*/
    private int[] phoneTypeID;
    /**价格类型*/
    private int type;
    
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String[] getCityAndotype() {
		return cityAndotype;
	}
	public void setCityAndotype(String[] cityAndotype) {
		this.cityAndotype = cityAndotype;
	}
	public int[] getPhoneTypeID() {
		return phoneTypeID;
	}
	public void setPhoneTypeID(int[] phoneTypeID) {
		this.phoneTypeID = phoneTypeID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "[limit=" + limit + ", cityAndotype="
				+ Arrays.toString(cityAndotype) + ", phoneTypeID="
				+ Arrays.toString(phoneTypeID) + ", type=" + type + "]";
	}
    
    
}
