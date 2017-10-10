package com.wbdp.wx.service.impl;

import org.apache.log4j.Logger;

import com.wbdp.wx.enums.ResultEnum;
import com.wbdp.wx.exception.CustomException;
import com.wbdp.wx.service.impl.brush.BrushServiceImpl;
import com.wbdp.wx.utils.wx.AuthorityUtil;

public class BaseServiceImpl {
	 /**日志log*/
    private static Logger log = Logger.getLogger(BaseServiceImpl.class);
	 /**
     * 通过code获取微信用户ID
     * @param wxcode
     * @return
     * @throws CustomException
     */
    public String  getOpenIDByWXCode(String wxcode) throws CustomException {
    	
        String openID = AuthorityUtil.getOpenID(wxcode);
        log.info("获取的openID："+openID);
        if(openID==null)
            throw new CustomException(ResultEnum.NOOPPENDID);

        return openID;
    }
}
