package com.wbdp.wx.model;

public class MyInsurance {
	/**保单号*/
	private String insuranceNum;
	/**车牌号*/
	private String plateNum;
	/**保险公司*/
	private String insuranceComp;
	/**是否宽带老用户*/
	private Integer isNoOlder;
	
	
	public Integer getIsNoOlder() {
		return isNoOlder;
	}
	public void setIsNoOlder(Integer isNoOlder) {
		this.isNoOlder = isNoOlder;
	}
	public String getInsuranceNum() {
		return insuranceNum;
	}
	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public String getInsuranceComp() {
		return insuranceComp;
	}
	public void setInsuranceComp(String insuranceComp) {
		this.insuranceComp = insuranceComp;
	}
	@Override
	public String toString() {
		return "[insuranceNum=" + insuranceNum + ", plateNum="
				+ plateNum + ", insuranceComp=" + insuranceComp +", isNoOlder="+isNoOlder+ "]";
	}
	
}
