package com.wbdp.tsp.entity;

/**
 * 
 * @author wangsaijun
 */
public class WbdpEarning {
	/**
	 *  ID
	 */
	private Long id;
	/**
	 *  车辆ID
	 */
	private Long vehicleId;
	/**
	 *  车牌号码
	 */
	private String plateNumber;
	/**
	 *  日期
	 */
	private java.util.Date earningDate;
	/**
	 *  评分
	 */
	private Integer driveScore;
	/**
	 *  驾驶收益
	 */
	private Integer scoreEarning;
	/**
	 *  绿色出行收益
	 */
	private Integer greenDrive;
	/**
	 *  绑定收益
	 */
	private Integer binding;
	/**
	 *  续保收益
	 */
	private Integer renewal;
	/**
	 *  录入时间
	 */
	private java.util.Date saveDate;
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
	 * 车辆ID
	 * @param vehicleId
	 */
	public void setVehicleId(Long vehicleId){
		this.vehicleId = vehicleId;
	}
	
    /**
     * 车辆ID
     * @return
     */	
    public Long getVehicleId(){
    	return vehicleId;
    }
	/**
	 * 车牌号码
	 * @param plateNumber
	 */
	public void setPlateNumber(String plateNumber){
		this.plateNumber = plateNumber;
	}
	
    /**
     * 车牌号码
     * @return
     */	
    public String getPlateNumber(){
    	return plateNumber;
    }
	/**
	 * 日期
	 * @param earningDate
	 */
	public void setEarningDate(java.util.Date earningDate){
		this.earningDate = earningDate;
	}
	
    /**
     * 日期
     * @return
     */	
    public java.util.Date getEarningDate(){
    	return earningDate;
    }
	/**
	 * 评分
	 * @param driveScore
	 */
	public void setDriveScore(Integer driveScore){
		this.driveScore = driveScore;
	}
	
    /**
     * 评分
     * @return
     */	
    public Integer getDriveScore(){
    	return driveScore;
    }
	/**
	 * 驾驶收益
	 * @param scoreEarning
	 */
	public void setScoreEarning(Integer scoreEarning){
		this.scoreEarning = scoreEarning;
	}
	
    /**
     * 驾驶收益
     * @return
     */	
    public Integer getScoreEarning(){
    	return scoreEarning;
    }
	/**
	 * 绿色出行收益
	 * @param greenDrive
	 */
	public void setGreenDrive(Integer greenDrive){
		this.greenDrive = greenDrive;
	}
	
    /**
     * 绿色出行收益
     * @return
     */	
    public Integer getGreenDrive(){
    	return greenDrive;
    }
	/**
	 * 绑定收益
	 * @param binding
	 */
	public void setBinding(Integer binding){
		this.binding = binding;
	}
	
    /**
     * 绑定收益
     * @return
     */	
    public Integer getBinding(){
    	return binding;
    }
	/**
	 * 续保收益
	 * @param renewal
	 */
	public void setRenewal(Integer renewal){
		this.renewal = renewal;
	}
	
    /**
     * 续保收益
     * @return
     */	
    public Integer getRenewal(){
    	return renewal;
    }
	/**
	 * 录入时间
	 * @param saveDate
	 */
	public void setSaveDate(java.util.Date saveDate){
		this.saveDate = saveDate;
	}
	
    /**
     * 录入时间
     * @return
     */	
    public java.util.Date getSaveDate(){
    	return saveDate;
    }
}