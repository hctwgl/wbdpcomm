package com.wbdp.wx.entity;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Goodssku {
    private Long id;

    private Long goodsid;

    private String valueidstr;

    private Integer price;

    private Date createdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public String getValueidstr() {
        return valueidstr;
    }

    public void setValueidstr(String valueidstr) {
        this.valueidstr = valueidstr == null ? null : valueidstr.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}