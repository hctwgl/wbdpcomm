package com.wbdp.wx.entity;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Bee {
    private Long id;

    private String beewx;

    private String phone;

    private String beename;

    private Integer sex;

    private String beecard;

    private String cardimage;

    private Integer marriage;

    private String education;

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

    public String getBeewx() {
        return beewx;
    }

    public void setBeewx(String beewx) {
        this.beewx = beewx == null ? null : beewx.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBeename() {
        return beename;
    }

    public void setBeename(String beename) {
        this.beename = beename == null ? null : beename.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBeecard() {
        return beecard;
    }

    public void setBeecard(String beecard) {
        this.beecard = beecard == null ? null : beecard.trim();
    }

    public String getCardimage() {
        return cardimage;
    }

    public void setCardimage(String cardimage) {
        this.cardimage = cardimage == null ? null : cardimage.trim();
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
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