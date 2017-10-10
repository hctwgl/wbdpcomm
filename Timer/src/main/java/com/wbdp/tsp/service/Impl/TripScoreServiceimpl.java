package com.wbdp.tsp.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbdp.tsp.dao.TripScoreMapper;
import com.wbdp.tsp.entity.TripScoreEntity;
import com.wbdp.tsp.entity.WbdpScore;
import com.wbdp.tsp.entity.WbdpTrip;
import com.wbdp.tsp.service.TripScoreService;

@Service
public class TripScoreServiceimpl implements TripScoreService{
	@Resource
	private TripScoreMapper wbdpTripMapper;

	@Override
	public void insertWbdpTrip(WbdpTrip wbdpTrip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWbdpTripByTid(Integer tid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWbdpTrip(WbdpTrip wbdpTrip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WbdpTrip> searchWbdpTripByParams(Map<String, String> map) {
		//List<WbdpTrip> list = wbdpTripMapper.searchWbdpTrip();
		return null;
	}

	@Override
	public List<WbdpTrip> searchWbdpTrip() {
		List<WbdpTrip> list = wbdpTripMapper.searchWbdpTrip();
		return list;
	}

	@Override
	public List<WbdpTrip> searchNightDrive() {
		
		return wbdpTripMapper.searchNightDrive();
	}

	@Override
	public List<WbdpTrip> searchFatigueDriveDrive() {
		
		return wbdpTripMapper.searchFatigueDriveDrive();
	}

	@Override
	public List<WbdpTrip> searchOverSpeed() {
		
		return wbdpTripMapper.searchOverSpeed();
	}

	@Override
	public String searchPlateNumber(String deviceNumber) {
		
		return wbdpTripMapper.searchPlateNumber(deviceNumber);
	}

	@Override
	public Long serchVehicleID(String plateNumber) {
		
		return wbdpTripMapper.serchVehicleID(plateNumber);
	}

}
