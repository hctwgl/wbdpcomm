package com.wbdp.wx.utils;

import com.wbdp.wx.enums.ResultEnum;
import com.wbdp.wx.model.Result;

/**
 * Created by wisedata005 on 2017/7/4.
 */
public class ResultUtils {
    private static final String SUCCESS_MSG = "成功";
    private static final String ERROR_MSG = "失败";
    /**
     * http回调成功
     * @param object
     * @return
     */
    public  static Result success(Object object){
        Result result = new Result();
        result.setCode(1);
        result.setMsg(SUCCESS_MSG);
        result.setData(object);
        return result;
    }
    /**
     * http回调失败
     * @param object
     * @return
     */
    public  static Result error(Object object){
        Result result = new Result();
        result.setCode(2);
        result.setMsg(ERROR_MSG);
        result.setData(object);
        return result;
    }
    /**
     *
     * @param resultEnum
     * @return
     */
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return  result;
    }
    /**
    *
    * @param resultEnum
    * @return
    */
   public static Result error(){
	  return error(null);
   }
    /**
     * 无object返回
     * @return
     */
    public  static Result success(){
        return success(null);
    }

    /**
     * http回调错误
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return  result;
    }
}
