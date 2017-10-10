package com.wisemifi.wx.dao;

import com.wisemifi.wx.entity.Customer;


/**
 * 维泽mifi客户信息数据接口类
 * @author 汪赛军
 * date:2017年8月11日上午11:30:08
 *
 */
public interface CustomerMapper {
	/**
	 * 保存客户实名认证身份证图片信息
	 * @param customer
	 * @return
	 */
	public Integer insertCard(Customer customer);
	
} 
