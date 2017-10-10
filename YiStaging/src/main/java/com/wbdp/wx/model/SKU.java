package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/12.
 */
public class SKU {
    /**gskuID*/
    private Long gskuID;
    /**商品价格*/
    private int goodPice;
    /**上下架状态*/
    private int skuStatus;

    public int getSkuStatus() {
		return skuStatus;
	}

	public void setSkuStatus(int skuStatus) {
		this.skuStatus = skuStatus;
	}

	public Long getGskuID() {
        return gskuID;
    }

    public void setGskuID(Long gskuID) {
        this.gskuID = gskuID;
    }

    public int getGoodPice() {
        return goodPice;
    }

    public void setGoodPice(int goodPice) {
        this.goodPice = goodPice;
    }

	@Override
	public String toString() {
		return "[gskuID=" + gskuID + ", goodPice=" + goodPice
				+ ", skuStatus=" + skuStatus + "]";
	}
    
    
}
