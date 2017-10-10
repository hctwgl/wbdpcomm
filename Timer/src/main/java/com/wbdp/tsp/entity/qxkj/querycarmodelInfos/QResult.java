package com.wbdp.tsp.entity.qxkj.querycarmodelInfos;

import java.io.Serializable;
import java.util.List;


public class QResult implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String totalSize;                          //查询到的信息总数
	private List<CarModelInfos> carModelInfos;         //查询到的车辆信息内容集合
	
	
	public String getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}
	public List<CarModelInfos> getCarModelInfos() {
		return carModelInfos;
	}
	public void setCarModelInfos(List<CarModelInfos> carModelInfos) {
		this.carModelInfos = carModelInfos;
	}
	

	
}
