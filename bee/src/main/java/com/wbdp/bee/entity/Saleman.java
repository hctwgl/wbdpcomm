package com.wbdp.bee.entity;

/**
 * 一分期新版客户经理实体类
 * @author wangsaijun
 */
public class Saleman {
	/**
	 *  销售者ID
	 */
	private Long id;
	/**
	 *  客户经理姓名
	 */
	private String name;
	/**
	 *  手机号
	 */
	private String phone;
	/**
	 *  客户经理身份证号
	 */
	private String saleManCard;
	/**
	 *  客户经理推荐码
	 */
	private String recommend;
	/**
	 *  运营商：移动、联通、电信
	 */
	private String operator;
	/**
	 *  城市
	 */
	private String city;
	/**
	 * 类型
	 */
	private Integer saleManType;
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
	 *  供应商ID
	 */
	private Long supplierId;
	/**
	 *  销售人微信
	 */
	private String saleManWx;
	/**
	 *  性别：0女1男
	 */
	private Integer sex;
	
	/**
	 * 密码
	 */
	private String passWord;
	
	
	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	/**
	 * 销售者ID
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
	
    public Integer getSaleManType() {
		return saleManType;
	}


	public void setSaleManType(Integer saleManType) {
		this.saleManType = saleManType;
	}


	/**
     * 销售者ID
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 客户经理姓名
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
    /**
     * 客户经理姓名
     * @return
     */	
    public String getName(){
    	return name;
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
	 * 客户经理身份证号
	 * @param saleManCard
	 */
	public void setSaleManCard(String saleManCard){
		this.saleManCard = saleManCard;
	}
	
    /**
     * 客户经理身份证号
     * @return
     */	
    public String getSaleManCard(){
    	return saleManCard;
    }
	/**
	 * 客户经理推荐码
	 * @param recommend
	 */
	public void setRecommend(String recommend){
		this.recommend = recommend;
	}
	
    /**
     * 客户经理推荐码
     * @return
     */	
    public String getRecommend(){
    	return recommend;
    }
	/**
	 * 运营商：移动、联通、电信
	 * @param operator
	 */
	public void setOperator(String operator){
		this.operator = operator;
	}
	
    /**
     * 运营商：移动、联通、电信
     * @return
     */	
    public String getOperator(){
    	return operator;
    }
	/**
	 * 城市
	 * @param city
	 */
	public void setCity(String city){
		this.city = city;
	}
	
    /**
     * 城市
     * @return
     */	
    public String getCity(){
    	return city;
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
}