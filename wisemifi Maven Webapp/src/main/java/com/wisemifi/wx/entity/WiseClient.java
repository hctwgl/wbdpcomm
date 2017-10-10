package com.wisemifi.wx.entity;

/**
 * 
 * @author wangsaijun
 */
public class WiseClient {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  用户微信号
	 */
	private String ownerWx;
	/**
	 *  用户姓名
	 */
	private String name;
	/**
	 *  性别0为女1为男
	 */
	private Integer sex;
	/**
	 *  电话号码
	 */
	private String phone;
	/**
	 *  身份证号
	 */
	private String card;
	/**
	 *  创建时间
	 */
	private java.util.Date creatTime;
	/**
	 *  最后一次修改时间
	 */
	private java.util.Date updateTime;
	/**
	 * ID
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
    /**
     * ID
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 用户微信号
	 * @param ownerWx
	 */
	public void setOwnerWx(String ownerWx){
		this.ownerWx = ownerWx;
	}
	
    /**
     * 用户微信号
     * @return
     */	
    public String getOwnerWx(){
    	return ownerWx;
    }
	/**
	 * 用户姓名
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
    /**
     * 用户姓名
     * @return
     */	
    public String getName(){
    	return name;
    }
	/**
	 * 性别0为女1为男
	 * @param sex
	 */
	public void setSex(Integer sex){
		this.sex = sex;
	}
	
    /**
     * 性别0为女1为男
     * @return
     */	
    public Integer getSex(){
    	return sex;
    }
	/**
	 * 电话号码
	 * @param phone
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
    /**
     * 电话号码
     * @return
     */	
    public String getPhone(){
    	return phone;
    }
	/**
	 * 身份证号
	 * @param card
	 */
	public void setCard(String card){
		this.card = card;
	}
	
    /**
     * 身份证号
     * @return
     */	
    public String getCard(){
    	return card;
    }
	/**
	 * 创建时间
	 * @param creatTime
	 */
	public void setCreatTime(java.util.Date creatTime){
		this.creatTime = creatTime;
	}
	
    /**
     * 创建时间
     * @return
     */	
    public java.util.Date getCreatTime(){
    	return creatTime;
    }
	/**
	 * 最后一次修改时间
	 * @param updateTime
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	
    /**
     * 最后一次修改时间
     * @return
     */	
    public java.util.Date getUpdateTime(){
    	return updateTime;
    }
}