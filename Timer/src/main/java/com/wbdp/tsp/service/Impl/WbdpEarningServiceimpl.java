package com.wbdp.tsp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbdp.tsp.dao.WbdpEarningMapper;
import com.wbdp.tsp.entity.WbdpDevicebinding;
import com.wbdp.tsp.entity.WbdpEarning;
import com.wbdp.tsp.service.WbdpEarningService;


@Service
public class WbdpEarningServiceimpl implements WbdpEarningService {
	
	@Autowired
	private WbdpEarningMapper wbdpEarningMapper;
	


	@Override
	public void insertWbdpEarning(List<WbdpEarning> list) {
		
		wbdpEarningMapper.insertWbdpEarning(list);
	}

}
