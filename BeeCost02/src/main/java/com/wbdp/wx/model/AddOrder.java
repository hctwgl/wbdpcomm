package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/11.
 */
public class AddOrder {
    /**商品ID*/
    private long goodsID;
    /**额度id*/
    private long pollenID;
    /**信用额度*/
    private int quota;
    /**金额*/
    private int goodsMoney;
    /**商品skuID*/
    private int skuID;
    /**业务员推荐码*/
    private String salesMancode;
    /**期数*/
    private int periods;
    /**购物车ID*/
    private long shopcartID;
    /**套餐ID*/
	private long pID;
	/**
	 * 客户提交订单后在线签名
	 */
    private String onlineSign;
    
    public String getOnlineSign() {
		return onlineSign;
	}

	public void setOnlineSign(String onlineSign) {
		this.onlineSign = onlineSign;
	}

	public long getpID() {
		return pID;
	}

	public void setpID(long pID) {
		this.pID = pID;
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

	public long getPollenID() {
        return pollenID;
    }

    public void setPollenID(long pollenID) {
        this.pollenID = pollenID;
    }

    public String getSalesMancode() {
        return salesMancode;
    }

    public void setSalesMancode(String salesMancode) {
        this.salesMancode = salesMancode;
    }

    public long getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(long goodsID) {
        this.goodsID = goodsID;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(int goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public int getSkuID() {
        return skuID;
    }

    public void setSkuID(int skuID) {
        this.skuID = skuID;
    }

	@Override
	public String toString() {
		return "[goodsID=" + goodsID + ", pollenID=" + pollenID
				+ ", quota=" + quota + ", goodsMoney=" + goodsMoney
				+ ", skuID=" + skuID + ", salesMancode=" + salesMancode
				+ ", periods=" + periods + ", shopcartID=" + shopcartID
				+ ", pID=" + pID + "]";
	}

    
}
