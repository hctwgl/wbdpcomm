package com.wbdp.wx.entity;

import java.util.Date;

public class ReceiptAddress {
    private Long id;

    private Long beeid;

    private String phone;

    private String receiptname;

    private String province;

    private String city;

    private String area;

    private String detaddress;

    private Date creatdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBeeid() {
        return beeid;
    }

    public void setBeeid(Long beeid) {
        this.beeid = beeid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getReceiptname() {
        return receiptname;
    }

    public void setReceiptname(String receiptname) {
        this.receiptname = receiptname == null ? null : receiptname.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getDetaddress() {
        return detaddress;
    }

    public void setDetaddress(String detaddress) {
        this.detaddress = detaddress == null ? null : detaddress.trim();
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }
}