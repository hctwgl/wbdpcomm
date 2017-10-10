package com.wbdp.wx.model;


/**
 * Created by wisedata005 on 2017/7/10.
 */
public class SelectPhoneTypeSort {
    /**当前起始*/
    private int limit ;
    /**手机型号*/
    private int phoneTypeID;
    /**价格类型*/
    private int type;

    public int getPhoneTypeID() {
        return phoneTypeID;
    }

    public void setPhoneTypeID(int phoneTypeID) {
        this.phoneTypeID = phoneTypeID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLimit() {

        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
