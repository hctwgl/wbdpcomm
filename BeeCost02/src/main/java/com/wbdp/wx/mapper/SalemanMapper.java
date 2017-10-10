package com.wbdp.wx.mapper;

import org.apache.ibatis.annotations.Param;

import com.wbdp.wx.model.Saleman;


/**
 * 业务员数据接口类
 * @author 汪赛军
 * date:2017年9月6日下午2:10:07
 *
 */
public interface SalemanMapper {

	/**
	 * 根据手机号查询业务员推荐码
	 * @param phone
	 * @return
	 */
	public Saleman selectSalemanByPhone(@Param("phone")String phone);

} 
