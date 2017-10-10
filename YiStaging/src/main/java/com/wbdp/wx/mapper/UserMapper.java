package com.wbdp.wx.mapper;

import org.apache.ibatis.annotations.Param;


/**
 * 一分期用户查询数据接口类
 * @author 汪赛军
 * date:2017年8月30日下午3:32:20
 *
 */
public interface UserMapper {

	/**
	 * 根据业务员推荐码查询业务员手机号码（用于短信验证）
	 * @param recomCode
	 * @return
	 */
	public String getUserPhone(@Param("recomCode")String recomCode);

} 
