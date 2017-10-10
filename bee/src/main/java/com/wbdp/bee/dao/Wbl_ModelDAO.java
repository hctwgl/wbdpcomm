package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_ModelEntity;


/**
 * 权限模块数据接口类
 * @author 汪赛军
 * date:2017年7月28日上午9:52:51
 *
 */
public interface Wbl_ModelDAO {
	/**
	 * 根据用户名查询对应权限模块
	 * @param username
	 * @return
	 */
	public List<Wbl_ModelEntity> listWbl_Model(@Param("username")String username);
	
	/**
	 * 查询权限模块数据
	 * @return
	 */
	public List<Wbl_ModelEntity> selectWbl_Model(@Param("userID")Long userID);
	
	/**
	 * 查询全部权限模块
	 * @return
	 */
	public List<Wbl_ModelEntity> selsectAllWbl_Model();
	
	/**
	 * 系统管理员查询模块
	 * @return
	 */
	public List<Wbl_ModelEntity> selectModelBySys();
	
	/**
	 * 客服查询模块
	 * @return
	 */
	public List<Wbl_ModelEntity> selectModelByCus();
	
	/**
	 * 客户经理查询模块
	 * @return
	 */
	public List<Wbl_ModelEntity> selectModelBySal();
	
	/**
	 * 客户经理管理员查询模块
	 * @return
	 */
	public List<Wbl_ModelEntity> selectModelBySalMan();
} 
