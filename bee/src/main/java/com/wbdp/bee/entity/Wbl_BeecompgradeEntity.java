package com.wbdp.bee.entity;

/**
 * 
 * @author wangsaijun
 */
public class Wbl_BeecompgradeEntity {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  客户id
	 */
	private Long beeId;
	/**
	 *  评级
	 */
	private String grade;
	/**
	 *  公司名
	 */
	private String company;
	/**
	 *  创建时间
	 */
	private String creatDate;
	/**
	 *  修改时间
	 */
	private String updateTime;
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
	 * 客户id
	 * @param beeId
	 */
	public void setBeeId(Long beeId){
		this.beeId = beeId;
	}
	
    /**
     * 客户id
     * @return
     */	
    public Long getBeeId(){
    	return beeId;
    }
	/**
	 * 评级
	 * @param grade
	 */
	public void setGrade(String grade){
		this.grade = grade;
	}
	
    /**
     * 评级
     * @return
     */	
    public String getGrade(){
    	return grade;
    }
	/**
	 * 公司名
	 * @param company
	 */
	public void setCompany(String company){
		this.company = company;
	}
	
    /**
     * 公司名
     * @return
     */	
    public String getCompany(){
    	return company;
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