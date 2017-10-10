package com.wbdp.wx.mapper;

import com.wbdp.wx.entity.Pollen;
import com.wbdp.wx.model.Quota;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PollenMapper {

    Quota selectPollenByBeeID(long beeid);

    /**
     * 修改剩余额度
     * @param map
     * @return
     */
    int updatePollenByID(Map<String, Object> map);

    /**
     * 查询已用额度
     * @param id
     * @return
     */
    int selectUsedCreditByID(long id);

    /**
     * 根据用户微信查询用户花粉额度
     * @param openid
     * @return
     */
    public Pollen selectPollenByOpenid(@Param("openid")String openid);
    
    /**
     * 根据用户微信修改用户额度
     * @param openid
     * @return
     */
    public Integer updatePollenByOpenid(@Param("map")Map<String,Object> map);

}