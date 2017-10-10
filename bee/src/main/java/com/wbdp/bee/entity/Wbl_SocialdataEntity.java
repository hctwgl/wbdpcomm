package com.wbdp.bee.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Wbl_SocialdataEntity {
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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

    private String creatdate;

    private String updateby;

    private String updatetime;
    
    private Wbl_BeeEntity bee;
    
    private Wbl_BeecompgradeEntity grade;
    
    
    
    public Wbl_BeecompgradeEntity getGrade() {
		return grade;
	}

	public void setGrade(Wbl_BeecompgradeEntity grade) {
		this.grade = grade;
	}

	public Wbl_BeeEntity getBee() {
		return bee;
	}

	public void setBee(Wbl_BeeEntity bee) {
		this.bee = bee;
	}

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

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = df.format(creatdate);
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = df.format(updatetime);
    }
}