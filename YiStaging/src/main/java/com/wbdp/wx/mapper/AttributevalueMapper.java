package com.wbdp.wx.mapper;

import com.wbdp.wx.model.Operator;
import com.wbdp.wx.model.PhoneBrand;

import java.util.List;


public interface AttributevalueMapper {

    List<PhoneBrand> selectPhoneAttribute();

    List<Operator> selectOperator();

   String selectValueByID(long id);


}