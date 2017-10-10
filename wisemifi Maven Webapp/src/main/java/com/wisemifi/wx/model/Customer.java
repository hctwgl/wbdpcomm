package com.wisemifi.wx.model;

/**
 * 
 * @author wangsaijun
 */
public class Customer {
	/**
	 *  ID
	 */
	private Long customerId;
	/**
	 *  微信号
	 */
	private String customerOpenid;
	/**
	 *  姓名
	 */
	private String customerName;
	/**
	 *  身份证号
	 */
	private String customerCard;
	/**
	 *  身份证照片
	 */
	private String customerCardPic;
	/**
	 *  实名认证,1:认证通过 2:认证未通过 0:未认证
	 */
	private Integer customerConfirmation;
	/**
	 *  创建时间
	 */
	private String customerCreateTime;
	/**
	 * ID
	 * @param customerId
	 */
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}
	
    /**
     * ID
     * @return
     */	
    public Long getCustomerId(){
    	return customerId;
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
	 * 姓名
	 * @param customerName
	 */
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	
    /**
     * 姓名
     * @return
     */	
    public String getCustomerName(){
    	return customerName;
    }
	/**
	 * 身份证号
	 * @param customerCard
	 */
	public void setCustomerCard(String customerCard){
		this.customerCard = customerCard;
	}
	
    /**
     * 身份证号
     * @return
     */	
    public String getCustomerCard(){
    	return customerCard;
    }
	/**
	 * 身份证照片
	 * @param customerCardPic
	 */
	public void setCustomerCardPic(String customerCardPic){
		this.customerCardPic = customerCardPic;
	}
	
    /**
     * 身份证照片
     * @return
     */	
    public String getCustomerCardPic(){
    	return customerCardPic;
    }
	/**
	 * 实名认证,1:认证通过 2:认证未通过 0:未认证
	 * @param customerConfirmation
	 */
	public void setCustomerConfirmation(Integer customerConfirmation){
		this.customerConfirmation = customerConfirmation;
	}
	
    /**
     * 实名认证,1:认证通过 2:认证未通过 0:未认证
     * @return
     */	
    public Integer getCustomerConfirmation(){
    	return customerConfirmation;
    }
	/**
	 * 创建时间
	 * @param customerCreateTime
	 */
	public void setCustomerCreateTime(String customerCreateTime){
		this.customerCreateTime = customerCreateTime;
	}
	
    /**
     * 创建时间
     * @return
     */	
    public String getCustomerCreateTime(){
    	return customerCreateTime;
    }
}