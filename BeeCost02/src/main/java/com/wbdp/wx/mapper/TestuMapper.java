package com.wbdp.wx.mapper;

import com.wbdp.wx.entity.Testu;

public interface TestuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Testu record);

    int insertSelective(Testu record);

    Testu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Testu record);

    int updateByPrimaryKey(Testu record);
}