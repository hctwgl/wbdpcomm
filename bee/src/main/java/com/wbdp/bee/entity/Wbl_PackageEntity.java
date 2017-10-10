package com.wbdp.bee.entity;

/**
 * 
 * @author wangsaijun
 */
public class Wbl_PackageEntity {
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
	 *  创建时间
	 */
	private String createDate;
	/**
	 *  最后修改时间
	 */
	private Integer pacPrice;
	
	/**
	 *  最后修改时间
	 */
	private String updateTime;
	
	/**
	 *  关联的供应商
	 */
	private Wbl_SupplierEntity supplier;
	
	
	
	public Integer getPacPrice() {
		return pacPrice;
	}

	public void setPacPrice(Integer pacPrice) {
		this.pacPrice = pacPrice;
	}

	public Wbl_SupplierEntity getSupplier() {
		return supplier;
	}

	public void setSupplier(Wbl_SupplierEntity supplier) {
		this.supplier = supplier;
	}

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
	 * 最后修改时间
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}
	
    /**
     * 最后修改时间
     * @return
     */	
    public String getUpdateTime(){
    	return updateTime;
    }
}