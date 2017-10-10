package com.wbdp.bee.entity;

/**
 * 
 * @author wangsaijun
 */
public class Compre {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  总金额
	 */
	private Integer price;
	/**
	 *  手机数量
	 */
	private Integer phoneNumber;
	/**
	 *  期数
	 */
	private Integer periods;
	/**
	 *  客户经理
	 */
	private String manager;
	/**
	 * 所属公司
	 */
	private String company;
	/**
	 *  导入人
	 */
	private String createBy;
	/**
	 *  导入时间
	 */
	private String createDate;
	
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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
	 * 总金额
	 * @param price
	 */
	public void setPrice(Integer price){
		this.price = price;
	}
	
    /**
     * 总金额
     * @return
     */	
    public Integer getPrice(){
    	return price;
    }
	/**
	 * 手机数量
	 * @param phoneNumber
	 */
	public void setPhoneNumber(Integer phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
    /**
     * 手机数量
     * @return
     */	
    public Integer getPhoneNumber(){
    	return phoneNumber;
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
	 * 客户经理
	 * @param manager
	 */
	public void setManager(String manager){
		this.manager = manager;
	}
	
    /**
     * 客户经理
     * @return
     */	
    public String getManager(){
    	return manager;
    }
	/**
	 * 导入人
	 * @param createBy
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	
    /**
     * 导入人
     * @return
     */	
    public String getCreateBy(){
    	return createBy;
    }
	/**
	 * 导入时间
	 * @param createDate
	 */
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}
	
    /**
     * 导入时间
     * @return
     */	
    public String getCreateDate(){
    	return createDate;
    }
}