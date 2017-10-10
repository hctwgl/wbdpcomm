package com.wbdp.wx.service;

import com.wbdp.wx.mapper.BeeMapper;
import com.wbdp.wx.mapper.TestuMapper;
import com.wbdp.wx.model.Result;
import com.wbdp.wx.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wisedata005 on 2017/7/5.
 */
@Service("demoS")
public class DemoInfoService {

    @Autowired
    private TestuMapper testuMapper;

    @Autowired
    private BeeMapper deemoBeeMapper;

    public Result gettestuMapper(Integer id){
       return ResultUtils.success(testuMapper.selectByPrimaryKey(id));
    }

    public Result insertPhoneAndWXID(String phone, String wxid) {
        int i = deemoBeeMapper.insertPhoneAndWXID(phone, wxid);
        return ResultUtils.success(i);
    }
}
