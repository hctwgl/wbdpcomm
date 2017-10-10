package com.wbdp.wx.mapper;

import com.wbdp.wx.model.SocialSecurityAccount;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SocialdataMapper {


    int insertSocialAccount(Map<String, Object> map);

    SocialSecurityAccount selectSSAByBeeID(@Param("beeid") long beeid);

    int updateSSAByBeeID(Map<String, Object> map);



}