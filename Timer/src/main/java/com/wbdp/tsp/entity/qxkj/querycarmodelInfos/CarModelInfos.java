package com.wbdp.tsp.entity.qxkj.querycarmodelInfos;

import java.io.Serializable;

public class CarModelInfos implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String maketDate;       //车辆上市日期
	private String price;           //车辆价格 
	private String seat;            //车辆座位数
	private String taxPrice;        //车辆价格(含税)
	private String vehicleCode;     //车型CODE
	private String vehicleName;     //车型全名
	private String vehicleId;       //车型的ID
	private String yearStyle;       //车型年份
	private String gearbox;         //变速箱类型
	
	public String getMaketDate() {
		return maketDate;
	}
	public void setMaketDate(String maketDate) {
		this.maketDate = maketDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}
	public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getYearStyle() {
		return yearStyle;
	}
	public void setYearStyle(String yearStyle) {
		this.yearStyle = yearStyle;
	}
	public String getGearbox() {
		return gearbox;
	}
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}
	
	
	
	
	
	
}
