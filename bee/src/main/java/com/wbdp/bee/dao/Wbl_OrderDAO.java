package com.wbdp.bee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.bee.entity.Wbl_OrderEntity;
import com.wbdp.bee.entity.Wbl_OrderReviewModel;

/**
 * 订单数据接口类
 * @author 汪赛军
 * date:2017年7月17日下午2:52:54
 *
 */
public interface Wbl_OrderDAO {

	/**
	 * 获取订单列表（分页）
	 * @param pageNum
	 * @return
	 */
	public List<Wbl_OrderEntity> listOrder(Integer pageNum);
	
	/**
	 * 获取订单列表（分页）
	 * @param pageNum
	 * @return
	 */
	public Wbl_OrderEntity getOrder(@Param("id")Long id);
	
	/**
	 * 获取订单数据总数
	 * @return
	 */
	public Integer getCount();
	
	/**
	 * ajax定时刷新获取未审核订单数量
	 * @return
	 */
	public Integer getOrderCount();
	
	/**
	 * 审核订单并修改订单状态
	 * @return
	 */
	public Integer updateOrder(Wbl_OrderEntity wbl_OrderEntity);
	
	/**
	 * 获取订单审核推送所需数据
	 * @param id
	 * @return
	 */
	public Wbl_OrderReviewModel listOrderReview(@Param("id")Long id);
} 
