package com.wbdp.tsp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.tsp.entity.TripScoreEntity;
import com.wbdp.tsp.entity.WbdpScore;
import com.wbdp.tsp.entity.WbdpTrip;

public interface TripScoreService {
	void insertWbdpTrip(WbdpTrip wbdpTrip);

	void deleteWbdpTripByTid(Integer tid);

	void updateWbdpTrip(WbdpTrip wbdpTrip);

	

	List<WbdpTrip> searchWbdpTripByParams(@Param("map")Map<String, String> map);
	
	 List<WbdpTrip> searchWbdpTrip();
	 
	 //查询夜间行驶时间
	 List<WbdpTrip> searchNightDrive();
	 
	 //查询疲劳驾驶次数
	 List<WbdpTrip> searchFatigueDriveDrive();
	 
	 //查询超速次数
	 List<WbdpTrip> searchOverSpeed();
	 
	//查询车牌号码
		 String searchPlateNumber(String deviceNumber);
		 
		//查询车辆ID
		 Long serchVehicleID(String plateNumber);
}
