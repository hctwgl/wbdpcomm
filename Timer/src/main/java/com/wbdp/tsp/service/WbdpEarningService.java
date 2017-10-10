package com.wbdp.tsp.service;

import java.util.List;


import com.wbdp.tsp.entity.WbdpEarning;

public interface WbdpEarningService {
	
		
		//批量插入数据
		void insertWbdpEarning(List<WbdpEarning> list);
}
