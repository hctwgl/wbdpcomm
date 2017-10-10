package com.wisemifi.wx.entity;

import java.io.Serializable;

/**
 * 
 * @author wangsaijun
 */
public class CustomerOrder implements Serializable{
	/**
	 *  id
	 */
	private Long orderId;
	/**
	 *  微信号
	 */
	private String customerOpenId;
	/**
	 *  订单号
	 */
	private String orderNumber;
	/**
	 *  商品ID
	 */
	private Long goodsId;
	/**
	 *  商品属性ID
	 */
	private Long goodsAttributesId;
	/**
	 *  订单价格
	 */
	private Integer price;
	/**
	 *  省份
	 */
	private String goodsProvincial;
	/**
	 *  收货地址ID
	 */
	private Long customerAddressId;
	/**
	 *  订单状态：1表示已支付，2表示未支付，3表示客户收益暂未到账，4表示客户收益已到账
	 */
	private Integer orderStatus;
	/**
	 *  订单生产时间
	 */
	private String orderCreateTime;
	/**
	 *  收货人姓名
	 */
	private String customerName;
	/**
	 *  电话
	 */
	private String phone;
	/**
	 *  收货地址
	 */
	private String address;
	/**
	 *  商品信息
	 */
	private String goodInfo;
	
	/**
	 * 购买此次商品推荐人微信号
	 */
	private String introOpenid;
	/**
	 * 订单类型：1表示购买维泽无线路由，2表示充值，3表示收入
	 */
	private Integer orderType;
	
	
	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getIntroOpenid() {
		return introOpenid;
	}

	public void setIntroOpenid(String introOpenid) {
		this.introOpenid = introOpenid;
	}

	/**
	 * id
	 * @param orderId
	 */
	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}
	
    /**
     * id
     * @return
     */	
    public Long getOrderId(){
    	return orderId;
    }
	/**
	 * 微信号
	 * @param customerOpenId
	 */
	public void setCustomerOpenId(String customerOpenId){
		this.customerOpenId = customerOpenId;
	}
	
    /**
     * 微信号
     * @return
     */	
    public String getCustomerOpenId(){
    	return customerOpenId;
    }
	/**
	 * 订单号
	 * @param orderNumber
	 */
	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
	}
	
    /**
     * 订单号
     * @return
     */	
    public String getOrderNumber(){
    	return orderNumber;
    }
	/**
	 * 商品ID
	 * @param goodsId
	 */
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	
    /**
     * 商品ID
     * @return
     */	
    public Long getGoodsId(){
    	return goodsId;
    }
	/**
	 * 商品属性ID
	 * @param goodsAttributesId
	 */
	public void setGoodsAttributesId(Long goodsAttributesId){
		this.goodsAttributesId = goodsAttributesId;
	}
	
    /**
     * 商品属性ID
     * @return
     */	
    public Long getGoodsAttributesId(){
    	return goodsAttributesId;
    }
	/**
	 * 订单价格
	 * @param price
	 */
	public void setPrice(Integer price){
		this.price = price;
	}
	
    /**
     * 订单价格
     * @return
     */	
    public Integer getPrice(){
    	return price;
    }
	/**
	 * 省份
	 * @param goodsProvincial
	 */
	public void setGoodsProvincial(String goodsProvincial){
		this.goodsProvincial = goodsProvincial;
	}
	
    /**
     * 省份
     * @return
     */	
    public String getGoodsProvincial(){
    	return goodsProvincial;
    }
	/**
	 * 收货地址ID
	 * @param customerAddressId
	 */
	public void setCustomerAddressId(Long customerAddressId){
		this.customerAddressId = customerAddressId;
	}
	
    /**
     * 收货地址ID
     * @return
     */	
    public Long getCustomerAddressId(){
    	return customerAddressId;
    }
	/**
	 * 订单状态:0未支付/1已支付
	 * @param orderStatus
	 */
	public void setOrderStatus(Integer orderStatus){
		this.orderStatus = orderStatus;
	}
	
    /**
     * 订单状态:0未支付/1已支付
     * @return
     */	
    public Integer getOrderStatus(){
    	return orderStatus;
    }
	/**
	 * 订单生产时间
	 * @param orderCreateTime
	 */
	public void setOrderCreateTime(String orderCreateTime){
		this.orderCreateTime = orderCreateTime;
	}
	
    /**
     * 订单生产时间
     * @return
     */	
    public String getOrderCreateTime(){
    	return orderCreateTime;
    }
	/**
	 * 收货人姓名
	 * @param customerName
	 */
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	
    /**
     * 收货人姓名
     * @return
     */	
    public String getCustomerName(){
    	return customerName;
    }
	/**
	 * 电话
	 * @param phone
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
    /**
     * 电话
     * @return
     */	
    public String getPhone(){
    	return phone;
    }
	/**
	 * 收货地址
	 * @param address
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
    /**
     * 收货地址
     * @return
     */	
    public String getAddress(){
    	return address;
    }
	/**
	 * 商品信息
	 * @param goodInfo
	 */
	public void setGoodInfo(String goodInfo){
		this.goodInfo = goodInfo;
	}
	
    /**
     * 商品信息
     * @return
     */	
    public String getGoodInfo(){
    	return goodInfo;
    }
    @Override
    public String toString() {
    	return "customerOrder"+"["+"price"+":"+price+" "+"customerName"+":"+customerName+" "+"phone"+":"+phone+" "+"address"+":"+address+" "+"goodInfo"+":"+goodInfo+		
    " "+"introOpenid"+":"+introOpenid+"orderNumber"+":"+orderNumber+"]";
    }
}