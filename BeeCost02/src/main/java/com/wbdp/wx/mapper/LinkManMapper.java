package com.wbdp.wx.mapper;

import com.wbdp.wx.model.UserContacts;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface LinkManMapper {

    int insertLinkMan(Map<String, Object> map);

    UserContacts selectUserContactsByBeeID(@Param("beeid") long beeid);

    int updateUserContactsByBeeId(Map<String, Object> map);

}