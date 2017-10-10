package com.wbdp.wx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbdp.wx.model.Qrimg;


/**
 * 业务员推荐码数据接口类
 * @author 汪赛军
 * date:2017年9月6日下午3:04:40
 *
 */
public interface QrimgMapper {

	/**
	 * 保存业务员套餐二维码信息
	 * @param qrimg
	 * @return
	 */
	public Integer saveQRCode(Qrimg qrimg);
	
	/**
	 * 根据openid查询业务员套餐二维码数据
	 * @param phone
	 * @return
	 */
	public List<Qrimg> selectQrimgByOpenid(@Param("openid")String openid);
	
	/**
	 * 删除二维码信息
	 * @param id
	 * @return
	 */
	public Integer deleteQrimgByID(@Param("id")Long id);
} 
