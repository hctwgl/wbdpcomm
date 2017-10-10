package com.wbdp.wx.entity;

import java.util.Date;

public class Pollen {
    private Long id;

    private Long beeid;

    private Integer creditlimit;

    private Integer usedcredit;

    private Date creditdate;

    private Date creatdate;

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

    public Integer getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(Integer creditlimit) {
        this.creditlimit = creditlimit;
    }

    public Integer getUsedcredit() {
        return usedcredit;
    }

    public void setUsedcredit(Integer usedcredit) {
        this.usedcredit = usedcredit;
    }

    public Date getCreditdate() {
        return creditdate;
    }

    public void setCreditdate(Date creditdate) {
        this.creditdate = creditdate;
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}