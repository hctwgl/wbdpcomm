package com.wbdp.tsp.service;

import java.util.List;

import com.wbdp.tsp.entity.WbdpScore;

public interface WbdpScoreService {
		//批量插入数据
		int insertListWbdpScore(List<WbdpScore> list);
		
		//查询评分表里所有车牌
		List<String> searchWbdpVehice();
		
		//查询对应车牌的评分
		int searchDriveScore(String s);
}
