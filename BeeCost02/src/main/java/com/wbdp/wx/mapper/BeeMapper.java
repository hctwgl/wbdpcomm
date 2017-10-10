package com.wbdp.wx.mapper;

import com.wbdp.wx.entity.Bee;
import com.wbdp.wx.model.ISBlackBee;
import com.wbdp.wx.model.MyInsurance;
import com.wbdp.wx.model.UserBasicInfo;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface BeeMapper {

    int insertPhoneAndWXID(@Param("phone") String phone, @Param("wxid") String wxid);

    Object selectIDByPhoneAndWXID(@Param("phone") String phone, @Param("wxid") String wxid);

    int updateInfoByWXIDAndPhone(Map<String, Object> map);

    ISBlackBee selectIDByWXID(@Param("wxid") String wxid);

    UserBasicInfo selectUserInfosByWXid(@Param("wxid") String wxid);

    int updateUserInfoByID(Map<String, Object> map);

    String selectCardImageByWXID(@Param("wxid") String wxid);

    int updateCardImageByID(@Param("imgPath") String imgPath, @Param("id") long id);

    MyInsurance selectInsNumByBeeID(@Param("beeid") long beeid);

//    int updateInsNumByBeeID(@Param("insNum") String insNum, @Param("beeid") long beeid);
    
	int updateInsNumByBeeID(@Param("map")Map<String, Object> map);
	
	Bee selectByID(Long id);
	
	/**
	 * 通过openID查询用户是否存在
	 * @param openid
	 * @return
	 */
	public Integer selectBeeByWX(@Param("openid")String openid);
	
	/**
	 * 根据openID查询用户基本信息
	 * @param openid
	 * @return
	 */
	public Bee selectBeeByWx(@Param("openid")String openid);
	
	/**
	 * 查询用户填写公司是否已存在数据库
	 * @param company
	 * @return
	 */
	public Integer selectBeeByCompany(@Param("company")String company);
}