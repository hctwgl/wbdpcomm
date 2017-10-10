package com.wbdp.wx.model;

/**
 * s商品详情
 * Created by wisedata005 on 2017/7/10.
 */
public class CommodityDetails {

    /**商品id*/
    private Long goodID;
    /**商品名*/
    private String goodName;
    /**商品图片链接*/
    private String goodImg;
    /**商品价格*/
    private int goodPrice;
    /**一期价格*/
    private int onePeriod;
    /**商品类型*/
    private String goodType;
    /**skuID*/
    private long skuID;
    /**供应商*/
    private String supplier;
    /**供应商ID*/
    private long supID;
    /**供应商手机对应付款方*/
    private int payer;
    
	public int getPayer() {
		return payer;
	}

	public void setPayer(int payer) {
		this.payer = payer;
	}

	public long getSupID() {
		return supID;
	}

	public void setSupID(long supID) {
		this.supID = supID;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public long getSkuID() {
        return skuID;
    }

    public void setSkuID(long skuID) {
        this.skuID = skuID;
    }

    public Long getGoodID() {
        return goodID;
    }

    public void setGoodID(Long goodID) {
        this.goodID = goodID;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public int getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(int goodPrice) {
        this.goodPrice = goodPrice;
    }

    public int getOnePeriod() {
        return onePeriod;
    }

    public void setOnePeriod(int onePeriod) {
        this.onePeriod = onePeriod;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;

    }

	@Override
	public String toString() {
		return "[goodID=" + goodID + ", goodName=" + goodName
				+ ", goodImg=" + goodImg + ", goodPrice=" + goodPrice
				+ ", onePeriod=" + onePeriod + ", goodType=" + goodType
				+ ", skuID=" + skuID + ", supplier=" + supplier + ", supID="
				+ supID + ", payer=" + payer + "]";
	}
    
    
}
	
