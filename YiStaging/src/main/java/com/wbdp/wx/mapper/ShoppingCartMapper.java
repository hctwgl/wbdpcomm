package com.wbdp.wx.mapper;

import com.wbdp.wx.entity.PackageEntity;
import com.wbdp.wx.model.UserOrder;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShoppingCartMapper {


    int insertOrderByCSO(Map<String, Object> map);

    List<UserOrder> selectOrderByBeeid(@Param("beeid") long beeid, @Param("period") int period);

    UserOrder selectOrderByBeeidAndGoodsID(@Param("beeid") long beeid, @Param("goodid") long goodid, @Param("period") int period);

    int updateStatefoByID(@Param("id") long id, @Param("cartState") int cartState);

    public Integer selectPriceByID(@Param("pid")Long pid);
}