package com.wbdp.wx.mapper;

import com.wbdp.wx.model.UserAddress;
import com.wbdp.wx.model.UserGoodAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BeeAddressMapper {

    int insertAddressInfo(Map<String, Object> map);

    List<UserAddress> selectUserAddressByBeeid(@Param("beeid") long beeid);

    List<UserGoodAddress> selectUserGoodsAddressByBeeid(long beeid);
    
    int updateAddressInfo(Map<String, Object> map);

}