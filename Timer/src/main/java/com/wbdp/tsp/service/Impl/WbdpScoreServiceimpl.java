package com.wbdp.tsp.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wbdp.tsp.dao.WbdpScoreMapper;
import com.wbdp.tsp.entity.WbdpScore;
import com.wbdp.tsp.service.WbdpScoreService;

@Service
public class WbdpScoreServiceimpl implements WbdpScoreService {
	@Resource
	private WbdpScoreMapper wbdpScoreMapper;
	@Override
	public int insertListWbdpScore(List<WbdpScore> list) {
		
		return wbdpScoreMapper.insertListWbdpScore(list);
	}
	@Override
	public List<String> searchWbdpVehice() {
		
		return wbdpScoreMapper.searchWbdpVehice();
	}
	@Override
	public int searchDriveScore(String s) {
	
		return wbdpScoreMapper.searchDriveScore(s);
	}

}
