package com.wbdp.bee.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Wbl_BlackbeeEntity {
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    private Long id;

    private Long beeid;

    private String beename;

    private String beecard;

    private String phone;

    private String creatby;

    private String creatdate;

    private String updateby;

    private String updatetime;

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

    public String getBeename() {
        return beename;
    }

    public void setBeename(String beename) {
        this.beename = beename == null ? null : beename.trim();
    }

    public String getBeecard() {
        return beecard;
    }

    public void setBeecard(String beecard) {
        this.beecard = beecard == null ? null : beecard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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