package com.wbdp.wx.domain;

/**
 * Created by CatalpaFlat on 2017/7/9.
 */
public class MineM {
    /**微信code*/
    private String code;
    /**微信用户id*/
    private String openid;
    /**用户id*/
    private long beeID;

    
    public long getBeeID() {
		return beeID;
	}

	public void setBeeID(long beeID) {
		this.beeID = beeID;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
