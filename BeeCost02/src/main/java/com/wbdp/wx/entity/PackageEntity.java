package com.wbdp.wx.entity;

/**
 * 
 * @author wangsaijun
 */
public class PackageEntity {
	/**
	 *  id
	 */
	private Long id;
	/**
	 *  套餐类型
	 */
	private String callPackage;
	/**
	 *  套餐对应供应商ID
	 */
	private Long supId;
	/**
	 *  套餐详情
	 */
	private String details;
	/**
	 *  套餐总价
	 */
	private Integer pacPrice;
	/**
	 *  创建时间
	 */
	private java.util.Date createDate;
	/**
	 *  最后修改时间
	 */
	private java.util.Date updateTime;
	/**
	 * id
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
    /**
     * id
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 套餐类型
	 * @param callPackage
	 */
	public void setCallPackage(String callPackage){
		this.callPackage = callPackage;
	}
	
    /**
     * 套餐类型
     * @return
     */	
    public String getCallPackage(){
    	return callPackage;
    }
	/**
	 * 套餐对应供应商ID
	 * @param supId
	 */
	public void setSupId(Long supId){
		this.supId = supId;
	}
	
    /**
     * 套餐对应供应商ID
     * @return
     */	
    public Long getSupId(){
    	return supId;
    }
	/**
	 * 套餐详情
	 * @param details
	 */
	public void setDetails(String details){
		this.details = details;
	}
	
    /**
     * 套餐详情
     * @return
     */	
    public String getDetails(){
    	return details;
    }
	/**
	 * 套餐总价
	 * @param pacPrice
	 */
	public void setPacPrice(Integer pacPrice){
		this.pacPrice = pacPrice;
	}
	
    /**
     * 套餐总价
     * @return
     */	
    public Integer getPacPrice(){
    	return pacPrice;
    }
	/**
	 * 创建时间
	 * @param createDate
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	
    /**
     * 创建时间
     * @return
     */	
    public java.util.Date getCreateDate(){
    	return createDate;
    }
	/**
	 * 最后修改时间
	 * @param updateTime
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	
    /**
     * 最后修改时间
     * @return
     */	
    public java.util.Date getUpdateTime(){
    	return updateTime;
    }
}