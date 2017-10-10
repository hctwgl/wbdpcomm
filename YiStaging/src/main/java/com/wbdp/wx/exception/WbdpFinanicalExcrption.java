package com.wbdp.wx.exception;

import com.wbdp.wx.enums.ResultEnum;

/**
 * Created by wisedata005 on 2017/7/4.
 */
public class WbdpFinanicalExcrption extends  RuntimeException{
    private Integer code;

    public WbdpFinanicalExcrption(ResultEnum resultEnum){
        super((resultEnum.getMsg()));
        this.code=resultEnum.getCode();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
