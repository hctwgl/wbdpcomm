package com.wisemifi.wx.entity;

/**
 * 
 * @author wangsaijun
 */
public class CustomerAddress {
	/**
	 *  ID
	 */
	private Long addressId;
	/**
	 *  微信号
	 */
	private String customerOpenid;
	/**
	 *  收货人姓名
	 */
	private String receiveName;
	/**
	 *  收货地址
	 */
	private String receiveAddress;
	/**
	 *  收货人手机号
	 */
	private String receivePhone;
	/**
	 * ID
	 * @param addressId
	 */
	public void setAddressId(Long addressId){
		this.addressId = addressId;
	}
	
    /**
     * ID
     * @return
     */	
    public Long getAddressId(){
    	return addressId;
    }
	/**
	 * 微信号
	 * @param customerOpenid
	 */
	public void setCustomerOpenid(String customerOpenid){
		this.customerOpenid = customerOpenid;
	}
	
    /**
     * 微信号
     * @return
     */	
    public String getCustomerOpenid(){
    	return customerOpenid;
    }
	/**
	 * 收货人姓名
	 * @param receiveName
	 */
	public void setReceiveName(String receiveName){
		this.receiveName = receiveName;
	}
	
    /**
     * 收货人姓名
     * @return
     */	
    public String getReceiveName(){
    	return receiveName;
    }
	/**
	 * 收货地址
	 * @param receiveAddress
	 */
	public void setReceiveAddress(String receiveAddress){
		this.receiveAddress = receiveAddress;
	}
	
    /**
     * 收货地址
     * @return
     */	
    public String getReceiveAddress(){
    	return receiveAddress;
    }
	/**
	 * 收货人手机号
	 * @param receivePhone
	 */
	public void setReceivePhone(String receivePhone){
		this.receivePhone = receivePhone;
	}
	
    /**
     * 收货人手机号
     * @return
     */	
    public String getReceivePhone(){
    	return receivePhone;
    }
    
    @Override
    public String toString() {
    	
    	return "customerAddress"+"["+"receiveName"+":"+receiveName+" "+"receiveAddress"+":"+receiveAddress+" "+"receivePhone"+":"+receivePhone+"]";
    }
}