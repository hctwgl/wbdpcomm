package com.wbdp.bee.entity;

import java.text.SimpleDateFormat;

/**
 * 
 * @author wangsaijun
 */
public class Wbl_OrderEntity {
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 *  订单ID
	 */
	private Long id;
	/**
	 *  客户ID
	 */
	private Long beeId;
	/**
	 *  期数
	 */
	private Integer periods;
	/**
	 *  订单金额
	 */
	private Integer money;
	/**
	 *  实际支付金额
	 */
	private Integer actualMoney;
	/**
	 *  下单时间
	 */
	private String orderDate;
	/**
	 *  订单状态：0审核中1已审核2已提交3已取消
	 */
	private Integer orderState;
	/**
	 *  业务员推荐码
	 */
	private String salesManCode;
	/**
	 *  数量
	 */
	private Integer number;
	/**
	 *  skuID
	 */
	private Long skuId;
	/**
	 *  收货地址（保留字段）
	 */
	private String address;
	/**
	 *  创建人
	 */
	private String creatBy;
	/**
	 *  创建时间
	 */
	private String creatDate;
	/**
	 *  修改人
	 */
	private String upDateBy;
	/**
	 *  修改时间
	 */
	private String upDateTime;
	/**
	 *  商品ID
	 */
	private Long goodsId;
	/**
	 * 在线签名
	 */
	private String onlineSign;
	/**
	 *  订单客户
	 */
	private Wbl_BeeEntity bee;
	/**
	 *  商品
	 */
	private Wbl_GoodsEntity goods;
	
	/**
	 *  社保资料（用于订单审核）
	 */
	private Wbl_SocialdataEntity social;
	
	/**
	 * 银行卡资料
	 */
	private Wbl_BankcardEntity bankcard;
	
	
	public Wbl_BankcardEntity getBankcard() {
		return bankcard;
	}

	public void setBankcard(Wbl_BankcardEntity bankcard) {
		this.bankcard = bankcard;
	}

	public Wbl_SocialdataEntity getSocial() {
		return social;
	}

	public void setSocial(Wbl_SocialdataEntity social) {
		this.social = social;
	}

	public Wbl_BeeEntity getBee() {
		return bee;
	}

	public void setBee(Wbl_BeeEntity bee) {
		this.bee = bee;
	}

	public Wbl_GoodsEntity getGoods() {
		return goods;
	}

	public void setGoods(Wbl_GoodsEntity goods) {
		this.goods = goods;
	}

	/**
	 * 订单ID
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
    /**
     * 订单ID
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 客户ID
	 * @param beeId
	 */
	public void setBeeId(Long beeId){
		this.beeId = beeId;
	}
	
    /**
     * 客户ID
     * @return
     */	
    public Long getBeeId(){
    	return beeId;
    }
	/**
	 * 期数
	 * @param periods
	 */
	public void setPeriods(Integer periods){
		this.periods = periods;
	}
	
    /**
     * 期数
     * @return
     */	
    public Integer getPeriods(){
    	return periods;
    }
	/**
	 * 订单金额
	 * @param money
	 */
	public void setMoney(Integer money){
		this.money = money;
	}
	
    /**
     * 订单金额
     * @return
     */	
    public Integer getMoney(){
    	return money;
    }
	/**
	 * 实际支付金额
	 * @param actualMoney
	 */
	public void setActualMoney(Integer actualMoney){
		this.actualMoney = actualMoney;
	}
	
    /**
     * 实际支付金额
     * @return
     */	
    public Integer getActualMoney(){
    	return actualMoney;
    }
	/**
	 * 下单时间
	 * @param orderDate
	 */
	public void setOrderDate(String orderDate){
		this.orderDate =orderDate;
	}
	
    /**
     * 下单时间
     * @return
     */	
    public String getOrderDate(){
    	return orderDate;
    }
	/**
	 * 订单状态：0审核中1已审核2已提交3已取消
	 * @param orderState
	 */
	public void setOrderState(Integer orderState){
		this.orderState = orderState;
	}
	
    /**
     * 订单状态：0审核中1已审核2已提交3已取消
     * @return
     */	
    public Integer getOrderState(){
    	return orderState;
    }
	/**
	 * 业务员推荐码
	 * @param salesManCode
	 */
	public void setSalesManCode(String salesManCode){
		this.salesManCode = salesManCode;
	}
	
    /**
     * 业务员推荐码
     * @return
     */	
    public String getSalesManCode(){
    	return salesManCode;
    }
	/**
	 * 数量
	 * @param number
	 */
	public void setNumber(Integer number){
		this.number = number;
	}
	
    /**
     * 数量
     * @return
     */	
    public Integer getNumber(){
    	return number;
    }
	/**
	 * skuID
	 * @param skuId
	 */
	public void setSkuId(Long skuId){
		this.skuId = skuId;
	}
	
    /**
     * skuID
     * @return
     */	
    public Long getSkuId(){
    	return skuId;
    }
	/**
	 * 收货地址（保留字段）
	 * @param address
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
    /**
     * 收货地址（保留字段）
     * @return
     */	
    public String getAddress(){
    	return address;
    }
	/**
	 * 创建人
	 * @param creatBy
	 */
	public void setCreatBy(String creatBy){
		this.creatBy = creatBy;
	}
	
    /**
     * 创建人
     * @return
     */	
    public String getCreatBy(){
    	return creatBy;
    }
	/**
	 * 创建时间
	 * @param creatDate
	 */
	public void setCreatDate(String creatDate){
		this.creatDate = creatDate;
	}
	
    /**
     * 创建时间
     * @return
     */	
    public String getCreatDate(){
    	return creatDate;
    }
	/**
	 * 修改人
	 * @param upDateBy
	 */
	public void setUpDateBy(String upDateBy){
		this.upDateBy = upDateBy;
	}
	
    /**
     * 修改人
     * @return
     */	
    public String getUpDateBy(){
    	return upDateBy;
    }
	/**
	 * 修改时间
	 * @param upDateTime
	 */
	public void setUpDateTime(String upDateTime){
		this.upDateTime = upDateTime;
	}
	
    /**
     * 修改时间
     * @return
     */	
    public String getUpDateTime(){
    	return upDateTime;
    }
	/**
	 * 
	 * @param goodsId
	 */
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	
    /**
     * 
     * @return
     */	
    public Long getGoodsId(){
    	return goodsId;
    }

	public String getOnlineSign() {
		return onlineSign;
	}

	public void setOnlineSign(String onlineSign) {
		this.onlineSign = onlineSign;
	}

    
}