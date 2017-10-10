package com.wbdp.bee.entity;

/**
 * 
 * @author wangsaijun
 */
public class CompblacklistNewEntity {
	/**
	 *  公司黑名单ID
	 */
	private Long id;
	/**
	 * 公司ID
	 */
	private Long compID;
	/**
	 *  公司名称
	 */
	private String comPany;
	/**
	 *  拉黑原因
	 */
	private String reason;
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
	 * 公司黑名单ID
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
    /**
     * 公司黑名单ID
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 公司名称
	 * @param comPany
	 */
	public void setComPany(String comPany){
		this.comPany = comPany;
	}
	
    /**
     * 公司名称
     * @return
     */	
    public String getComPany(){
    	return comPany;
    }
	/**
	 * 拉黑原因
	 * @param reason
	 */
	public void setReason(String reason){
		this.reason = reason;
	}
	
    /**
     * 拉黑原因
     * @return
     */	
    public String getReason(){
    	return reason;
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
}