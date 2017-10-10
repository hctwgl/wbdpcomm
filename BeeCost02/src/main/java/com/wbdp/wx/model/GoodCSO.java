package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/10.
 */
public class GoodCSO {
    /**商品id*/
    private int goodID;
    /**颜色id*/
    private int ctype;
    /**存储id*/
    private int stype;
    /**运营商id*/
    private int otype;
    /**期数*/
    private int periods;
    /**购物车ID*/
    private long shopcartID;
    /**供应商ID*/
    private long supID;
    /**套餐ID*/
	private long pID;
    /**套餐内容*/
    private String wpackage;
    
    public String getWpackage() {
		return wpackage;
	}

	public void setWpackage(String wpackage) {
		this.wpackage = wpackage;
	}

	public long getpID() {
		return pID;
	}

	public void setpID(long pID) {
		this.pID = pID;
	}

	public long getSupID() {
		return supID;
	}

	public void setSupID(long supID) {
		this.supID = supID;
	}

	public long getShopcartID() {
		return shopcartID;
	}

	public void setShopcartID(long shopcartID) {
		this.shopcartID = shopcartID;
	}

	public int getPeriods() {
		return periods;
	}

	public void setPeriods(int periods) {
		this.periods = periods;
	}


    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public int getStype() {
        return stype;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }

    public int getOtype() {
        return otype;
    }

    public void setOtype(int otype) {
        this.otype = otype;
    }

    public int getGoodID() {
        return goodID;
    }

    public void setGoodID(int goodID) {
        this.goodID = goodID;
    }

	@Override
	public String toString() {
		return "[goodID=" + goodID + ", ctype=" + ctype + ", stype="
				+ stype + ", otype=" + otype + ", periods=" + periods
				+ ", shopcartID=" + shopcartID + ", supID=" + supID + ", pID="
				+ pID + ", wpackage=" + wpackage + "]";
	}

	
	

    
}
