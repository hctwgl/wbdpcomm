package com.wbdp.wx.entity;

/**
 * 维泽任性刷记录实体类
 * @author wangsaijun
 */
public class Brushcredit {
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
	 *  用户确认在线签名
	 */
	private String onlineSign;
	/**
	 *  刷刷时间
	 */
	private String brushTime;
	
	/**
	 * 还款开始日期
	 */
	private String startTime;
	/**
	 * 还款截止时间 
	 */
	private String endTime;
	/**
	 * 人脸识别状态
	 */
	private Integer faceStatus;
	
	/**
	 * 人脸识别图片地址
	 */
	private String faceImg;
	
	/**
	 * 手机型号
	 */
	private String phoneModel;
	
	
	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getFaceImg() {
		return faceImg;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}

	public Integer getFaceStatus() {
		return faceStatus;
	}

	public void setFaceStatus(Integer faceStatus) {
		this.faceStatus = faceStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
    
    @Override
    public String toString() {
    	return "clientWx"+":"+clientWx+" "+"recomCode"+":"+recomCode+" "+"pacMonthlyPrice"+":"+pacMonthlyPrice+" "+"pacPeriods"+pacPeriods+
    			" "+"pacPrice"+pacPrice+"faceStatus"+":"+faceStatus+" "+"faceImg"+":"+faceImg;
    }
}