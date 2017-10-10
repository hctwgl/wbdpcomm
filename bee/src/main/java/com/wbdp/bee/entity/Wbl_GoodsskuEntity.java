package com.wbdp.bee.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Wbl_GoodsskuEntity {
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    private Long id;

    private Long goodsid;
    
    private Long supid;

    private String valueidstr;

    private Integer price;
    
    private Integer skustatus;

    private String createdate;

    
    
    public Integer getSkustatus() {
		return skustatus;
	}

	public void setSkustatus(Integer skustatus) {
		this.skustatus = skustatus;
	}

	public Long getSupid() {
		return supid;
	}

	public void setSupid(Long supid) {
		this.supid = supid;
	}

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

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = df.format(createdate);
    }
}