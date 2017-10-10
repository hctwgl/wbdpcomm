package com.wbdp.wx.mapper;

import com.wbdp.wx.model.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    int insertOrder(@Param("map")Map<String, Object> map);

    List<UserOrder> selectOrdersByBeeID(@Param("period") long period, @Param("beeid") long beeid);
    
    UserOrder selectOrdersByOID(@Param("oID") long oID);
}