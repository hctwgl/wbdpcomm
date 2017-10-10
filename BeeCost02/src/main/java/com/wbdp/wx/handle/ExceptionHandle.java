package com.wbdp.wx.handle;

import com.wbdp.wx.model.Result;
import com.wbdp.wx.exception.WbdpFinanicalExcrption;
import com.wbdp.wx.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class ExceptionHandle {

    /**日志log*/
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof WbdpFinanicalExcrption){
            WbdpFinanicalExcrption girlException = (WbdpFinanicalExcrption)e;
            return ResultUtils.error(girlException.getCode(),e.getMessage());
        }
        logger.error("[系统异常{}]",e);//异常保存
        return ResultUtils.error(-1,e.getMessage());
    }


}