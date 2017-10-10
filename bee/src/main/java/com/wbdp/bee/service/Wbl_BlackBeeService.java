package com.wbdp.bee.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_BlackbeeEntity;
import com.wbdp.bee.entity.Wbl_UserEntity;

public interface Wbl_BlackBeeService {

    
	/**
	 * 方法名: blackbeeAllList   
	 * 方法描述: 客户黑名单列表
	 * 入参描述: id:黑名单主键id, pageNum:页码
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
	public Map<String,Object> blackbeeAllList(Long id,Integer pageNum);
	
	
	/**
	 * 方法名: insertBlackbee   
	 * 方法描述: 添加客户黑名单 
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
	public Map<String,Object> insertBlackbee(Wbl_BlackbeeEntity BlackbeeEntity);
   
}
