package com.wbdp.wx.model;
/**
 * 判断是否黑名单
 * @author wisedata005
 */
public class ISBlackBee {
	/**是否为黑名单*/
	private int beeStatus;
	/**id*/
	private int id;
	
	public int getBeeStatus() {
		return beeStatus;
	}
	public void setBeeStatus(int beeStatus) {
		this.beeStatus = beeStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "[beeStatus=" + beeStatus + ", id=" + id + "]";
	}
	
}
