package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/10.
 */
public class PhoneBrand {
    /**类型id*/
    private long phoneTypeID;
    /**手机类型品牌名*/
    private String attributeValue;

    public long getPhoneTypeID() {
        return phoneTypeID;
    }

    public void setPhoneTypeID(long phoneTypeID) {
        this.phoneTypeID = phoneTypeID;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Override
    public String toString() {
        return "{" +
                "phoneTypeID=" + phoneTypeID +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}
