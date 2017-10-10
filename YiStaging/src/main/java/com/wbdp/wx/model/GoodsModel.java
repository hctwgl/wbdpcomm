package com.wbdp.wx.model;

/**
 * Created by CatalpaFlat on 2017/7/9.
 */
public class GoodsModel {
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
    /**供应商*/
    private String supplier;
    

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
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

	@Override
	public String toString() {
		return "[goodID=" + goodID + ", goodName=" + goodName
				+ ", goodImg=" + goodImg + ", goodPrice=" + goodPrice
				+ ", onePeriod=" + onePeriod + ", supplier=" + supplier + "]";
	}

 
}
