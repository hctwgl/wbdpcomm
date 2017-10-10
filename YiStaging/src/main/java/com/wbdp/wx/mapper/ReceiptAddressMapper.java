package com.wbdp.wx.mapper;

import com.wbdp.wx.model.UserGoodAddress;

import java.util.List;
import java.util.Map;

public interface ReceiptAddressMapper {

    int insertReceiptAddress(Map<String, Object> map);

    List<UserGoodAddress> selectReceiptAddressByBeeid(long beeid);

    int updateReceiptAddressByID(Map<String, Object> map);

    UserGoodAddress selectSingleGoodsAddress(long id);





}