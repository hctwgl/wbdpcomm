package com.wbdp.wx.model;

/**
 * Created by CatalpaFlat on 2017/7/9.
 */
public class UserAddress {
    /**省份*/
    private String province;
    /**市*/
    private String city;
    /**区*/
    private String area;
    /**地址*/
    private String detailsAddress;
    /**类型*/
    private Integer type;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
