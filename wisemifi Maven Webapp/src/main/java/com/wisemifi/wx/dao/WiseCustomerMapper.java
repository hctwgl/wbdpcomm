package com.wisemifi.wx.dao;

import org.apache.ibatis.annotations.Param;

import com.wisemifi.wx.entity.WiseCustomer;


/**
 * 新版维泽无线客户数据接口类
 * @author 汪赛军
 * date:2017年9月14日上午9:56:00
 *
 */
public interface WiseCustomerMapper {

	/**
	 * 根据openID查询客户信息
	 * @param openid
	 * @return
	 */
	public WiseCustomer selectCustomerByOpenID(@Param("openid")String openid);
	
	/**
	 * 根据ID查询客户信息
	 * @param openid
	 * @return
	 */
	public WiseCustomer selectCustomerByID(@Param("id")Long id);
	/**
	 * 新增客户信息
	 * @param wiseCustomer
	 * @return
	 */
	public Integer insertCustomer(WiseCustomer wiseCustomer);
	
	/**
	 * 获取二维码数据
	 * @param openid
	 * @return
	 */
	public String getQRcode(@Param("openid")String openid);
} 
