package com.wbdp.wx.entity;

/**
 * 
 * @author wangsaijun
 */
public class User {
	/**
	 *  用户ID
	 */
	private Long id;
	/**
	 *  用户名
	 */
	private String userName;
	/**
	 *  密码
	 */
	private String passWord;
	/**
	 *  用户所属公司
	 */
	private String company;
	/**
	 *  联系电话
	 */
	private String phone;
	/**
	 *  办公电话
	 */
	private String officePhone;
	/**
	 *  用户类型：1系统管理员2客服3业务员4业务员管理员
	 */
	private Integer userType;
	/**
	 *  业务员推荐码，仅限业务员使用
	 */
	private String recomCode;
	/**
	 *  用户状态；0正常1注销
	 */
	private Integer userState;
	/**
	 *  创建人
	 */
	private String creatBy;
	/**
	 *  用户创建时间
	 */
	private java.util.Date creatDate;
	/**
	 *  修改人
	 */
	private String upDateBy;
	/**
	 *  修改时间
	 */
	private java.util.Date upDateTime;
	/**
	 * 用户ID
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
    /**
     * 用户ID
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 用户名
	 * @param userName
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	
    /**
     * 用户名
     * @return
     */	
    public String getUserName(){
    	return userName;
    }
	/**
	 * 密码
	 * @param passWord
	 */
	public void setPassWord(String passWord){
		this.passWord = passWord;
	}
	
    /**
     * 密码
     * @return
     */	
    public String getPassWord(){
    	return passWord;
    }
	/**
	 * 用户所属公司
	 * @param company
	 */
	public void setCompany(String company){
		this.company = company;
	}
	
    /**
     * 用户所属公司
     * @return
     */	
    public String getCompany(){
    	return company;
    }
	/**
	 * 联系电话
	 * @param phone
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
    /**
     * 联系电话
     * @return
     */	
    public String getPhone(){
    	return phone;
    }
	/**
	 * 办公电话
	 * @param officePhone
	 */
	public void setOfficePhone(String officePhone){
		this.officePhone = officePhone;
	}
	
    /**
     * 办公电话
     * @return
     */	
    public String getOfficePhone(){
    	return officePhone;
    }
	/**
	 * 用户类型：1系统管理员2客服3业务员4业务员管理员
	 * @param userType
	 */
	public void setUserType(Integer userType){
		this.userType = userType;
	}
	
    /**
     * 用户类型：1系统管理员2客服3业务员4业务员管理员
     * @return
     */	
    public Integer getUserType(){
    	return userType;
    }
	/**
	 * 业务员推荐码，仅限业务员使用
	 * @param recomCode
	 */
	public void setRecomCode(String recomCode){
		this.recomCode = recomCode;
	}
	
    /**
     * 业务员推荐码，仅限业务员使用
     * @return
     */	
    public String getRecomCode(){
    	return recomCode;
    }
	/**
	 * 用户状态；0正常1注销
	 * @param userState
	 */
	public void setUserState(Integer userState){
		this.userState = userState;
	}
	
    /**
     * 用户状态；0正常1注销
     * @return
     */	
    public Integer getUserState(){
    	return userState;
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
	 * 用户创建时间
	 * @param creatDate
	 */
	public void setCreatDate(java.util.Date creatDate){
		this.creatDate = creatDate;
	}
	
    /**
     * 用户创建时间
     * @return
     */	
    public java.util.Date getCreatDate(){
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
	public void setUpDateTime(java.util.Date upDateTime){
		this.upDateTime = upDateTime;
	}
	
    /**
     * 修改时间
     * @return
     */	
    public java.util.Date getUpDateTime(){
    	return upDateTime;
    }
}