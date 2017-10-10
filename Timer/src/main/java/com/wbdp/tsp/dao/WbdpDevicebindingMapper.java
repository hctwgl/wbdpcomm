package com.wbdp.tsp.dao;

import java.util.List;

import com.wbdp.tsp.entity.WbdpDevicebinding;


public interface WbdpDevicebindingMapper {
	//查询所有车牌
	List<String> searchWbdpVehice();
	
	//查询绑定状态
	int serchBinding(String plateNumber);
}
