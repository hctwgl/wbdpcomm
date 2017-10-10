package com.wbdp.wx.entity;

import java.util.Date;

public class Order {
    private Long id;

    private Long beeid;

    private Integer money;

    private Integer actualmoney;

    private Date orderdate;

    private Integer orderstate;

    private String salesmancode;

    private Integer number;

    private Long skuid;

    private String address;

    private String creatby;

    private Date creatdate;

    private String updateby;

    private Date updatetime;

    private Long goodsid;

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

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getActualmoney() {
        return actualmoney;
    }

    public void setActualmoney(Integer actualmoney) {
        this.actualmoney = actualmoney;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Integer getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(Integer orderstate) {
        this.orderstate = orderstate;
    }

    public String getSalesmancode() {
        return salesmancode;
    }

    public void setSalesmancode(String salesmancode) {
        this.salesmancode = salesmancode == null ? null : salesmancode.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getSkuid() {
        return skuid;
    }

    public void setSkuid(Long skuid) {
        this.skuid = skuid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCreatby() {
        return creatby;
    }

    public void setCreatby(String creatby) {
        this.creatby = creatby == null ? null : creatby.trim();
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }
}