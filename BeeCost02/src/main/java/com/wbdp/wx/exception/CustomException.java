package com.wbdp.wx.exception;

import com.wbdp.wx.enums.ResultEnum;

/**
 * 自定义异常类型
 * @author CatalpaFlat
 */
@SuppressWarnings("serial")
public class CustomException extends Exception {
    
    private Integer code;
    public CustomException(){}
    
    public CustomException(ResultEnum resultEnum) {
        super((resultEnum.getMsg()));
        this.code = resultEnum.getCode();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}