package com.wbdp.wx.entity;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Goods {
    private Long id;

    private String goodsname;

    private Integer goodsstate;

    private String creatby;

    private Date creatdate;

    private String updateby;

    private Date updatetime;

    private Long goodstypeid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Integer getGoodsstate() {
        return goodsstate;
    }

    public void setGoodsstate(Integer goodsstate) {
        this.goodsstate = goodsstate;
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

    public Long getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(Long goodstypeid) {
        this.goodstypeid = goodstypeid;
    }
}