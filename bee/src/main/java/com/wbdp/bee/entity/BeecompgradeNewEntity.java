package com.wbdp.bee.entity;

/**
 * 公司信用管理实体类
 * @author wangsaijun
 */
public class BeecompgradeNewEntity {
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
	private double grade;
	/**
	 *  公司名称
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
	 *  公司统一社会信用代码
	 */
	private String companyCode;
	/**
	 *  地址
	 */
	private String address;
	/**
	 *  联系人
	 */
	private String linkMan;
	/**
	 *  手机号
	 */
	private String phone;
	/**
	 *  邮箱
	 */
	private String email;
	/**
	 *  营业执照图片地址
	 */
	private String licenseImg;
	/**
	 *  合同
	 */
	private String contract;
	/**
	 * 录入人
	 */
	private String creatBy;
	
	/**
	 * 公司状态，0为正常，1为已拉黑
	 */
	private Integer compStatus;
	
	/**
	 * 拉黑该公司用户
	 */
	private String blackBy;
	/**
	 * 拉黑原因，被拉黑后存在
	 */
	private String blackReason;
	
	/**
	 * 公司审核状态，1:表示公司录入，客服未审核评级，2表示客服已审核评级等待客户经理管理员确认，3，客户经理管理员已确认，等待客户经理上传客户资料
	 */
	private Integer checkStatus;
	
	/**
	 * 客服审核不通过原因
	 */
	private String checkReason;
	
	/**
	 * 公司最高额度
	 */
	private Integer maxAmount;
	/**
	 * 企业芝麻信用评分
	 */
	private Integer aliScore;
	
	
	public Integer getAliScore() {
		return aliScore;
	}

	public void setAliScore(Integer aliScore) {
		this.aliScore = aliScore;
	}

	public Integer getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getBlackBy() {
		return blackBy;
	}

	public void setBlackBy(String blackBy) {
		this.blackBy = blackBy;
	}

	public Integer getCompStatus() {
		return compStatus;
	}

	public void setCompStatus(Integer compStatus) {
		this.compStatus = compStatus;
	}

	public String getBlackReason() {
		return blackReason;
	}

	public void setBlackReason(String blackReason) {
		this.blackReason = blackReason;
	}

	public String getCreatBy() {
		return creatBy;
	}

	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
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
	public void setGrade(double grade){
		this.grade = grade;
	}
	
    /**
     * 评级
     * @return
     */	
    public double getGrade(){
    	return grade;
    }
	/**
	 * 公司名称
	 * @param company
	 */
	public void setCompany(String company){
		this.company = company;
	}
	
    /**
     * 公司名称
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
	/**
	 * 公司统一社会信用代码
	 * @param companyCode
	 */
	public void setCompanyCode(String companyCode){
		this.companyCode = companyCode;
	}
	
    /**
     * 公司统一社会信用代码
     * @return
     */	
    public String getCompanyCode(){
    	return companyCode;
    }
	/**
	 * 地址
	 * @param address
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
    /**
     * 地址
     * @return
     */	
    public String getAddress(){
    	return address;
    }
	/**
	 * 联系人
	 * @param linkMan
	 */
	public void setLinkMan(String linkMan){
		this.linkMan = linkMan;
	}
	
    /**
     * 联系人
     * @return
     */	
    public String getLinkMan(){
    	return linkMan;
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
	 * 邮箱
	 * @param email
	 */
	public void setEmail(String email){
		this.email = email;
	}
	
    /**
     * 邮箱
     * @return
     */	
    public String getEmail(){
    	return email;
    }
	/**
	 * 营业执照图片地址
	 * @param licenseImg
	 */
	public void setLicenseImg(String licenseImg){
		this.licenseImg = licenseImg;
	}
	
    /**
     * 营业执照图片地址
     * @return
     */	
    public String getLicenseImg(){
    	return licenseImg;
    }
	/**
	 * 合同
	 * @param contract
	 */
	public void setContract(String contract){
		this.contract = contract;
	}
	
    /**
     * 合同
     * @return
     */	
    public String getContract(){
    	return contract;
    }
}