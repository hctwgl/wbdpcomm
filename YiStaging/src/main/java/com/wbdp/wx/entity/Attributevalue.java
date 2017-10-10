package com.wbdp.wx.entity;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Attributevalue {
    private Long id;

    private Long attributeid;

    private String attributevalue;

    private Date creatdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttributeid() {
        return attributeid;
    }

    public void setAttributeid(Long attributeid) {
        this.attributeid = attributeid;
    }

    public String getAttributevalue() {
        return attributevalue;
    }

    public void setAttributevalue(String attributevalue) {
        this.attributevalue = attributevalue == null ? null : attributevalue.trim();
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }
}