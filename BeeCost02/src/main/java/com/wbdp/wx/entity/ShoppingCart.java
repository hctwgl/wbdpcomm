package com.wbdp.wx.entity;

import java.util.Date;

public class ShoppingCart {
    private Long id;

    private Long beeid;

    private Long goodsid;

    private Integer number;

    private Integer cartstate;

    private String creatby;

    private Date creatdate;

    private String updateby;

    private Date updatetime;

    private Long goodsskuid;

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

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCartstate() {
        return cartstate;
    }

    public void setCartstate(Integer cartstate) {
        this.cartstate = cartstate;
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

    public Long getGoodsskuid() {
        return goodsskuid;
    }

    public void setGoodsskuid(Long goodsskuid) {
        this.goodsskuid = goodsskuid;
    }
}