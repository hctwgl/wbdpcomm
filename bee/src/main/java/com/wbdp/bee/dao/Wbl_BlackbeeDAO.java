package com.wbdp.bee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_BlackbeeEntity;


public interface Wbl_BlackbeeDAO {

	/**
	 * 方法名: blackbeeAllList   
	 * 方法描述: 客户黑名单列表
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
	public List<Wbl_BlackbeeEntity> blackbeeAllList(@Param("id")Long id,@Param("pageNum")Integer pageNum);
	public Integer blackbeeAllListSize(@Param("id")Long id);
	
	
	/**
	 * 方法名: insertBlackbee   
	 * 方法描述: 添加客户黑名单 
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月14日
	 */
	public Integer insertBlackbee(Wbl_BlackbeeEntity BlackbeeEntity);
	/**
	 * 方法名: updateBeeStatus   
	 * 方法描述: 修改客户状态为拉黑状态 
	 * 入参描述: 
	 * 回调描述:   
	 * 创建人:wisedata004  
	 * 创建时间: 2017年7月17日
	 */
	public Integer updateBeeStatus(@Param("beeid")Long id);
}