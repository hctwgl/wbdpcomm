package com.wbdp.bee.dao;


import java.util.Map;
import org.apache.ibatis.annotations.Param;



/**
 * 用户权限控制数据接口
 * @author 汪赛军
 * date:2017年8月22日上午10:31:06
 *
 */
public interface Wbl_ModelUserDAO {
	
	/**
	 * 批量添加用户权限
	 * @param map
	 * @return
	 */
	public Integer insertModel(@Param("map")Map<String,Object> map);
	
	/**
	 * 批量修改用户权限(赋予权限)
	 * @param map
	 * @return
	 */
	public Integer updateModelGive(@Param("map")Map<String,Object> map);
	/**
	 * 批量修改用户权限(解除权限)
	 * @param map
	 * @return
	 */
	public Integer updateModelRel(@Param("map")Map<String,Object> map);
} 
