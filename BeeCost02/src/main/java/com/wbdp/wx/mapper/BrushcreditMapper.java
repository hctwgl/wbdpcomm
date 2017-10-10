package com.wbdp.wx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wbdp.wx.entity.Brushcredit;


/**
 * 维泽任性刷数据接口类
 * @author 汪赛军
 * date:2017年8月30日下午3:43:34
 *
 */
public interface BrushcreditMapper {

	/**
	 * 新增维泽任性刷到数据库
	 * @param brushcredit
	 * @return
	 */
	public Integer saveBrush(Brushcredit brushcredit);
	
	/**
	 * 获取维泽任性刷借款合同中所需数据
	 * @param openid
	 * @return
	 */
	public Map<String,Object> getContract(@Param("openid")String openid);

	/**
	 * 获取用户任性刷记录
	 * @param openid
	 * @return
	 */
	public List<Brushcredit> getUserBrush(@Param("openid")String openid);
	
	/**
	 * 根据推送链接中携带的参数查询用户套餐信息
	 * @param openid
	 * @param time
	 * @return
	 */
	public Brushcredit getPushBrush(@Param("openid")String openid,@Param("time")String time);
	
	/**
	 * 修改任性刷状态
	 * @param brushcredit
	 * @return
	 */
	public Integer updatePhshBrush(Brushcredit brushcredit);
} 
