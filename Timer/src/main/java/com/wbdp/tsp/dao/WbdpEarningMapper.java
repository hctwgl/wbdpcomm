package com.wbdp.tsp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.tsp.entity.WbdpEarning;

public interface WbdpEarningMapper {

	//void insertWbdpEarning(WbdpEarning wbdpEarning);

	void deleteWbdpEarningById(Long id);

	void updateWbdpEarning(WbdpEarning wbdpEarning);

	

	List<WbdpEarning> searchWbdpEarningByParams(@Param("map")Map<String, String> map);
	
	//批量插入数据
	void insertWbdpEarning(List<WbdpEarning> list);

} 
