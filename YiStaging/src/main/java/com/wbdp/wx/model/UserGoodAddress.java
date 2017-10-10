package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/10.
 */
public class UserGoodAddress {
    /**收货地址id*/
    private long id;
    /**省份*/
    private String province;
    /**市*/
    private String city;
    /**区*/
    private String area;
    /**地址*/
    private String detailsAddress;
    /**用户名*/
    private String name;
    /**用户手机号*/
    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", detailsAddress='" + detailsAddress + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
