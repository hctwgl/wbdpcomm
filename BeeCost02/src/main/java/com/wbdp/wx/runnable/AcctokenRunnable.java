package com.wbdp.wx.runnable;

import com.wbdp.wx.utils.wx.WXCacheUnit;

import java.util.TimerTask;

/**
 * 定时更新accesstoke和JS_API_TICKET
 * @author wisedata005
 */
public class AcctokenRunnable extends TimerTask {

    public void run() {
		 WXCacheUnit.updateAccessTokenAndJs_Api_Ticket();
	}

}