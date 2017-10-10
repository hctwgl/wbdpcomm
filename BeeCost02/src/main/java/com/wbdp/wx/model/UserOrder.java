package com.wbdp.wx.model;

/**
 * Created by wisedata005 on 2017/7/10.
 */
public class UserOrder {

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
    /**商品数量*/
    private int numer;
    /**商品状态*/
    private int state;
    /**属性串值*/
    private String valueIDStr;
    /**运营商*/
    private String operator;
    /**颜色*/
    private String color;
    /**存储*/
    private String storage;
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
    /**提供商*/
    private String supplier;
    /**套餐id*/
    private long pID;
    /**套餐内容*/
    private String wpackage;
    /**供应商id*/
    private long supID;
    /**套餐总价*/
    private int pacPrice;
    /**日期*/
    private Object orderDate;
    /**订单ID*/
    private long oID;
    /**商品详情*/
    private Object details;
    
    
    public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

	public long getoID() {
		return oID;
	}

	public void setoID(long oID) {
		this.oID = oID;
	}

	public Object getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Object orderDate) {
		this.orderDate = orderDate;
	}

	public int getPacPrice() {
		return pacPrice;
	}

	public void setPacPrice(int pacPrice) {
		this.pacPrice = pacPrice;
	}

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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
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

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getValueIDStr() {
        return valueIDStr;
    }

    public void setValueIDStr(String valueIDStr) {
        this.valueIDStr = valueIDStr;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

	public long getSupID() {
		return supID;
	}

	public void setSupID(long supID) {
		this.supID = supID;
	}

	@Override
	public String toString() {
		return "[goodID=" + goodID + ", goodName=" + goodName
				+ ", goodImg=" + goodImg + ", goodPrice=" + goodPrice
				+ ", onePeriod=" + onePeriod + ", numer=" + numer + ", state="
				+ state + ", valueIDStr=" + valueIDStr + ", operator="
				+ operator + ", color=" + color + ", storage=" + storage
				+ ", ctype=" + ctype + ", stype=" + stype + ", otype=" + otype
				+ ", periods=" + periods + ", shopcartID=" + shopcartID
				+ ", supplier=" + supplier + ", pID=" + pID + ", wpackage="
				+ wpackage + ", supID=" + supID + ", pacPrice=" + pacPrice
				+ ", orderDate=" + orderDate + ", oID=" + oID + ", details="
				+ details + "]";
	}


	
   
}
