package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_ShoppingcartEntity;

/**
 * 购物车数据接口类
 * @author 汪赛军
 * date:2017年7月18日上午9:10:25
 *
 */
public interface Wbl_ShoppingcartDAO {

	/**
	 * 获取购物车以及关联查询列表（分页）
	 * @param pageNum
	 * @return
	 */
	public List<Wbl_ShoppingcartEntity> listShoppingcart(@Param("pageNum")Integer pageNum);
	
	/**
	 * 获取数据总条数
	 * @return
	 */
	public Integer getCount();
} 
