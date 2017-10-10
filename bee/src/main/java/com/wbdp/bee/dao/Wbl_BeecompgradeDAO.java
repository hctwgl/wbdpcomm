package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_BeecompgradeEntity;

/**
 * 客户公司评级数据接口类
 * @author 汪赛军
 * date:2017年7月18日下午3:15:16
 *
 */
public interface Wbl_BeecompgradeDAO {

	/**
	 * 新增公司评级数据
	 * @param Wbl_BeecompgradeEntity
	 * @return
	 */
	public Integer insertBeecompgrade(Wbl_BeecompgradeEntity wbl_BeecompgradeEntity);
	
	/**
	 * 修改公司评级数据
	 * @param wbl_BeecompgradeEntity
	 * @return
	 */
	public Integer updateBeecompgrade(Wbl_BeecompgradeEntity wbl_BeecompgradeEntity);
} 
