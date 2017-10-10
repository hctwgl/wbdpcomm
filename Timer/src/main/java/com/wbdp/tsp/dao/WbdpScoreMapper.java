package com.wbdp.tsp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.tsp.entity.WbdpScore;


public interface WbdpScoreMapper {

	void insertWbdpScore(WbdpScore wbdpScore);

	void deleteWbdpScoreById(Long id);

	void updateWbdpScore(WbdpScore wbdpScore);

	

	List<WbdpScore> searchWbdpScoreByParams(@Param("map")Map<String, String> map);
	
	//批量插入数据
	
	int insertListWbdpScore(List<WbdpScore> list);
	
	//查询评分表里所有车牌
	List<String> searchWbdpVehice();
	
	//查询对应车牌的评分
	int searchDriveScore(String s);
} 
