package com.wbdp.bee.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Wbl_GoodsEntity {
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    private Long id;

    private String goodsname;

    private Integer goodsstate;

    private String creatby;

    private String creatdate;

    private String updateby;

    private String updatetime;

    private Long goodstypeid;
    
    private Long supid;
    
    

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

    public Long getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(Long goodstypeid) {
        this.goodstypeid = goodstypeid;
    }
}