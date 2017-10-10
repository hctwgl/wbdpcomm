package com.wisemifi.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wisemifi.wx.entity.CustomerOrder;


/**
 * 客户订单数据接口
 * @author 汪赛军
 * date:2017年8月12日下午5:02:12
 *
 */
public interface CustomerOrderMapper {

	/**
	 * 客户支付成功后保存客户订单数据
	 * @param customerOrder
	 * @return
	 */
	public Integer insertOrder(CustomerOrder customerOrder);
	
	/**
	 * 获取用户订单列表
	 * @param openid
	 * @return
	 */
	public List<CustomerOrder> getCustomerOrder(@Param("openid")String openid);
	
	/**
	 * 根据openID查询客户是否购买了维泽无线路由
	 * @param openid
	 * @return
	 */
	public Integer selectCustomerOrder(@Param("openid")String openid);
	
	/**
	 * 查询客户购买无线路由最新的一条记录
	 * @param openid
	 * @return
	 */
	public CustomerOrder selectOrderNew(@Param("openid")String openid);
	
	/**
	 * 根据订单号查询该订单是否存在
	 * @param orderNumber
	 * @return
	 */
	public CustomerOrder selectOrderByOrderNumber(@Param("orderNumber")String orderNumber);
	
} 
