package com.wbdp.wx.model;

/**
 * 
 * @author wangsaijun
 */
public class Saleman {
	/**
	 *  销售者ID
	 */
	private Long id;
	/**
	 *  销售人微信
	 */
	private String saleManWx;
	/**
	 *  手机号
	 */
	private String phone;
	/**
	 *  性别：0女1男
	 */
	private Integer sex;
	/**
	 *  销售人身份证号
	 */
	private String saleManCard;
	/**
	 *  销售者推荐码
	 */
	private String recommend;
	/**
	 *  供应商ID
	 */
	private Long supplierId;
	/**
	 *  创建者
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
	 * 销售者ID
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
    /**
     * 销售者ID
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 销售人微信
	 * @param saleManWx
	 */
	public void setSaleManWx(String saleManWx){
		this.saleManWx = saleManWx;
	}
	
    /**
     * 销售人微信
     * @return
     */	
    public String getSaleManWx(){
    	return saleManWx;
    }
	/**
	 * 手机号
	 * @param phone
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
    /**
     * 手机号
     * @return
     */	
    public String getPhone(){
    	return phone;
    }
	/**
	 * 性别：0女1男
	 * @param sex
	 */
	public void setSex(Integer sex){
		this.sex = sex;
	}
	
    /**
     * 性别：0女1男
     * @return
     */	
    public Integer getSex(){
    	return sex;
    }
	/**
	 * 销售人身份证号
	 * @param saleManCard
	 */
	public void setSaleManCard(String saleManCard){
		this.saleManCard = saleManCard;
	}
	
    /**
     * 销售人身份证号
     * @return
     */	
    public String getSaleManCard(){
    	return saleManCard;
    }
	/**
	 * 销售者推荐码
	 * @param recommend
	 */
	public void setRecommend(String recommend){
		this.recommend = recommend;
	}
	
    /**
     * 销售者推荐码
     * @return
     */	
    public String getRecommend(){
    	return recommend;
    }
	/**
	 * 供应商ID
	 * @param supplierId
	 */
	public void setSupplierId(Long supplierId){
		this.supplierId = supplierId;
	}
	
    /**
     * 供应商ID
     * @return
     */	
    public Long getSupplierId(){
    	return supplierId;
    }
	/**
	 * 创建者
	 * @param creatBy
	 */
	public void setCreatBy(String creatBy){
		this.creatBy = creatBy;
	}
	
    /**
     * 创建者
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