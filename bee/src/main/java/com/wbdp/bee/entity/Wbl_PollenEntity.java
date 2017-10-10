package com.wbdp.bee.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Wbl_PollenEntity {
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    private Long id;

    private Long beeid;

    private Integer creditlimit;

    private String creditdate;

    private String creatdate;

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

    public Integer getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(Integer creditlimit) {
        this.creditlimit = creditlimit;
    }

    public String getCreditdate() {
        return creditdate;
    }

    public void setCreditdate(Date creditdate) {
        this.creditdate = df.format(creditdate);
    }

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = df.format(creatdate);
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = df.format(updatetime);
    }
}