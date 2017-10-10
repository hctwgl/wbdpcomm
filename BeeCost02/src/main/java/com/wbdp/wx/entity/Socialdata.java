package com.wbdp.wx.entity;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Socialdata {
    private Long id;

    private Long beeid;

    private String socialaccount;

    private String socialpassword;

    private Integer base;

    private Integer balance;

    private String company;

    private Integer paytime;

    private Integer socialstate;

    private String creatby;

    private Date creatdate;

    private String updateby;

    private Date updatetime;

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

    public String getSocialaccount() {
        return socialaccount;
    }

    public void setSocialaccount(String socialaccount) {
        this.socialaccount = socialaccount == null ? null : socialaccount.trim();
    }

    public String getSocialpassword() {
        return socialpassword;
    }

    public void setSocialpassword(String socialpassword) {
        this.socialpassword = socialpassword == null ? null : socialpassword.trim();
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Integer getPaytime() {
        return paytime;
    }

    public void setPaytime(Integer paytime) {
        this.paytime = paytime;
    }

    public Integer getSocialstate() {
        return socialstate;
    }

    public void setSocialstate(Integer socialstate) {
        this.socialstate = socialstate;
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
}