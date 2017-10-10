package com.wisemifi.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wisemifi.wx.entity.CustomerAddress;


/**
 * 维泽无线收货地址数据接口
 * @author 汪赛军
 * date:2017年9月4日上午9:31:22
 *
 */
public interface CustomerAddressMapper {

	/**
	 * 新增维泽无线收货地址
	 * @param customerAddress
	 * @return
	 */
	public Integer addAddress(CustomerAddress customerAddress);
	
	/**
	 * 获取收货地址
	 * @param openid
	 * @return
	 */
	public List<CustomerAddress> getAddress(@Param("openid")String openid);
} 
