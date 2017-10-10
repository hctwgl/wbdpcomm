package com.wbdp.wx.mapper;

import com.wbdp.wx.model.UserBankCard;

import java.util.Map;

public interface BankCardMapper {

    int insertbankcard(Map<String, Object> map);

    UserBankCard selectUserBankCardByBeeID(long beeid);

    int updateUserBankCarByBeeID(Map<String, Object> map);



}