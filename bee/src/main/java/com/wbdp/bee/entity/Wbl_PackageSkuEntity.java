package com.wbdp.bee.entity;

/**
 * 
 * @author wangsaijun
 */
public class Wbl_PackageSkuEntity {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  SKU对应ID
	 */
	private Long skuId;
	/**
	 *  话费套餐对应ID
	 */
	private Long pacId;
	/**
	 *  sku选择套餐后应付金额
	 */
	private Integer payable;
	/**
	 *  创建时间
	 */
	private String createDate;
	/**
	 *  修改时间
	 */
	private String updateTime;
	/**
	 * 关联数据对应套餐对象
	 */
	private Wbl_PackageEntity pac;
	
	
	public Wbl_PackageEntity getPac() {
		return pac;
	}

	public void setPac(Wbl_PackageEntity pac) {
		this.pac = pac;
	}

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
	 * SKU对应ID
	 * @param skuId
	 */
	public void setSkuId(Long skuId){
		this.skuId = skuId;
	}
	
    /**
     * SKU对应ID
     * @return
     */	
    public Long getSkuId(){
    	return skuId;
    }
	/**
	 * 话费套餐对应ID
	 * @param pacId
	 */
	public void setPacId(Long pacId){
		this.pacId = pacId;
	}
	
    /**
     * 话费套餐对应ID
     * @return
     */	
    public Long getPacId(){
    	return pacId;
    }
	/**
	 * sku选择套餐后应付金额
	 * @param payable
	 */
	public void setPayable(Integer payable){
		this.payable = payable;
	}
	
    /**
     * sku选择套餐后应付金额
     * @return
     */	
    public Integer getPayable(){
    	return payable;
    }
	/**
	 * 创建时间
	 * @param createDate
	 */
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}
	
    /**
     * 创建时间
     * @return
     */	
    public String getCreateDate(){
    	return createDate;
    }
	/**
	 * 修改时间
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}
	
    /**
     * 修改时间
     * @return
     */	
    public String getUpdateTime(){
    	return updateTime;
    }
}