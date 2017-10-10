package com.wbdp.tsp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbdp.tsp.dao.WbdpDevicebindingMapper;
import com.wbdp.tsp.entity.WbdpDevicebinding;
import com.wbdp.tsp.service.WbdpDevicebindingService;


@Service
public class WbdpDevicebindingServiceimpl implements WbdpDevicebindingService {
	@Autowired
	private WbdpDevicebindingMapper wbdpDevicebindingMapper;
	@Override
	public List<String> searchWbdpVehice() {
		
		return wbdpDevicebindingMapper.searchWbdpVehice();
	}

}
