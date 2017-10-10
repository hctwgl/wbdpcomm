package com.wbdp.wx.mapper;

import java.util.Map;

import com.wbdp.wx.model.UserCreditCard;

public interface CreditCardMapper {
	int insertCreditCard(Map<String, Object> map);
	
	UserCreditCard selectCreditCardByBeeID(long beeid);
	
	int updateCreditCardByBeeID(Map<String, Object> map);
}