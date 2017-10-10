package com.wbdp.bee.entity;

/**
 * 
 * @author wangsaijun
 */
public class BrushcreditEntity {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  客户微信
	 */
	private String clientWx;
	/**
	 *  业务员推荐码
	 */
	private String recomCode;
	/**
	 *  用户选择套餐月付金额
	 */
	private Integer pacMonthlyPrice;
	/**
	 *  套餐期数
	 */
	private Integer pacPeriods;
	/**
	 *  套餐总价
	 */
	private Integer pacPrice;
	/**
	 *  手机型号，仅限于公司客户
	 */
	private String phoneModel;
	/**
	 *  用户确认在线签名
	 */
	private String onlineSign;
	/**
	 *  人脸识别状态，0为未通过，1为通过，2为后台审核中
	 */
	private Integer faceStatus;
	/**
	 *  刷刷时间
	 */
	private String brushTime;
	/**
	 *  维泽任性刷状态：0未审核，1已审核，2审核未通过
	 */
	private Integer brushStatus;
	/**
	 *  还款开始时间
	 */
	private String startTime;
	/**
	 *  还款截止时间
	 */
	private String endTime;
	/**
	 *  人脸识别图片地址
	 */
	private String faceImg;
	/**
	 * 客户手机号，只在导入集团客户时使用
	 */
	private String phone;
	/**
	 * 客户ID
	 */
	private Long beeID;
	
	
	public Long getBeeID() {
		return beeID;
	}


	public void setBeeID(Long beeID) {
		this.beeID = beeID;
	}


	/**
	 * ID
	 * @param id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
	
    public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
     * ID
     * @return
     */	
    public Long getId(){
    	return id;
    }
	/**
	 * 客户微信
	 * @param clientWx
	 */
	public void setClientWx(String clientWx){
		this.clientWx = clientWx;
	}
	
    /**
     * 客户微信
     * @return
     */	
    public String getClientWx(){
    	return clientWx;
    }
	/**
	 * 业务员推荐码
	 * @param recomCode
	 */
	public void setRecomCode(String recomCode){
		this.recomCode = recomCode;
	}
	
    /**
     * 业务员推荐码
     * @return
     */	
    public String getRecomCode(){
    	return recomCode;
    }
	/**
	 * 用户选择套餐月付金额
	 * @param pacMonthlyPrice
	 */
	public void setPacMonthlyPrice(Integer pacMonthlyPrice){
		this.pacMonthlyPrice = pacMonthlyPrice;
	}
	
    /**
     * 用户选择套餐月付金额
     * @return
     */	
    public Integer getPacMonthlyPrice(){
    	return pacMonthlyPrice;
    }
	/**
	 * 套餐期数
	 * @param pacPeriods
	 */
	public void setPacPeriods(Integer pacPeriods){
		this.pacPeriods = pacPeriods;
	}
	
    /**
     * 套餐期数
     * @return
     */	
    public Integer getPacPeriods(){
    	return pacPeriods;
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
	 * 手机型号，仅限于公司客户
	 * @param phoneModel
	 */
	public void setPhoneModel(String phoneModel){
		this.phoneModel = phoneModel;
	}
	
    /**
     * 手机型号，仅限于公司客户
     * @return
     */	
    public String getPhoneModel(){
    	return phoneModel;
    }
	/**
	 * 用户确认在线签名
	 * @param onlineSign
	 */
	public void setOnlineSign(String onlineSign){
		this.onlineSign = onlineSign;
	}
	
    /**
     * 用户确认在线签名
     * @return
     */	
    public String getOnlineSign(){
    	return onlineSign;
    }
	/**
	 * 人脸识别状态，0为未通过，1为通过，2为后台审核中
	 * @param faceStatus
	 */
	public void setFaceStatus(Integer faceStatus){
		this.faceStatus = faceStatus;
	}
	
    /**
     * 人脸识别状态，0为未通过，1为通过，2为后台审核中
     * @return
     */	
    public Integer getFaceStatus(){
    	return faceStatus;
    }
	/**
	 * 刷刷时间
	 * @param brushTime
	 */
	public void setBrushTime(String brushTime){
		this.brushTime = brushTime;
	}
	
    /**
     * 刷刷时间
     * @return
     */	
    public String getBrushTime(){
    	return brushTime;
    }
	/**
	 * 维泽任性刷状态：0未审核，1已审核，2审核未通过
	 * @param brushStatus
	 */
	public void setBrushStatus(Integer brushStatus){
		this.brushStatus = brushStatus;
	}
	
    /**
     * 维泽任性刷状态：0未审核，1已审核，2审核未通过
     * @return
     */	
    public Integer getBrushStatus(){
    	return brushStatus;
    }
	/**
	 * 还款开始时间
	 * @param startTime
	 */
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
	
    /**
     * 还款开始时间
     * @return
     */	
    public String getStartTime(){
    	return startTime;
    }
	/**
	 * 还款截止时间
	 * @param endTime
	 */
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	
    /**
     * 还款截止时间
     * @return
     */	
    public String getEndTime(){
    	return endTime;
    }
	/**
	 * 人脸识别图片地址
	 * @param faceImg
	 */
	public void setFaceImg(String faceImg){
		this.faceImg = faceImg;
	}
	
    /**
     * 人脸识别图片地址
     * @return
     */	
    public String getFaceImg(){
    	return faceImg;
    }
}