package com.wbdp.tsp.dao;

import java.util.List;
import java.util.Map;

public interface TrafficViolationDAO {

	/**
	 * 获取所有车辆信息(微信号,车辆所在城市,车牌号,车架号,发动机号)
	 */
	public List<Map<String, String>> getAllTrafficViolation();
}
