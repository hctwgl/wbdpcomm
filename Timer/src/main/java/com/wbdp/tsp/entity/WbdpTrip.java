package com.wbdp.tsp.entity;

/**
 * 
 * @author wangsaijun
 */
public class WbdpTrip {
	/**
	 *  主键
	 */
	private Integer tid;
	/**
	 *  设备编号
	 */
	private String deviceNumber;
	/**
	 *  行程ID
	 */
	private String tripid;
	/**
	 *  点火时间
	 */
	private java.util.Date starttime;
	/**
	 *  熄火时间
	 */
	private java.util.Date endtime;
	/**
	 *  本次行驶时长，单位s
	 */
	private Integer drivertime;
	/**
	 *  本次行程油耗，单位ml
	 */
	private Integer oilwear;
	/**
	 *  里程，单位m
	 */
	private Integer mail;
	/**
	 *  最高速度，单位km/h
	 */
	private Integer hspeed;
	/**
	 *  转速，单位rmp
	 */
	private Integer rmp;
	/**
	 *  冷却液最高温度
	 */
	private Integer temp;
	/**
	 *  急加速次数
	 */
	private Integer rapidSpeed;
	/**
	 *  急减速次数
	 */
	private Integer suddenBrake;
	/**
	 *  超速行驶时间>120km/h，单位s
	 */
	private Integer overSpeedTime;
	/**
	 *  超速行驶里程，单位m
	 */
	private Integer overSpeedMail;
	/**
	 *  超速行驶耗油量，单位ml
	 */
	private Integer overSpeedOilWear;
	/**
	 *  高速行驶时间>80km/h，
	 */
	private Integer hspeedTime;
	/**
	 *  高速行驶里程，单位m
	 */
	private Integer hspeedMail;
	/**
	 *  高速行驶油耗，单位ml
	 */
	private Integer hspeedOilWear;
	/**
	 *  中速行驶时间>60,单位s
	 */
	private Integer mspeedTime;
	/**
	 *  中速行驶里程，单位m
	 */
	private Integer mspeedMail;
	/**
	 *  中速行驶油耗，单位ml
	 */
	private Integer mspeedOilWear;
	/**
	 *  低速行驶时间<60，单位s
	 */
	private Integer lspeedTime;
	/**
	 *  低速行驶里程，单位m
	 */
	private Integer lspeedMail;
	/**
	 *  低速行驶油耗，单位ml
	 */
	private Integer lspeedOilWear;
	/**
	 *  怠速时间，单位s
	 */
	private Integer idleTime;
	/**
	 *  怠速油耗，单位ml
	 */
	private Integer idleTimeOilWear;
	/**
	 *  急转弯次数
	 */
	private Integer suddenTurn;
	/**
	 * 主键
	 * @param tid
	 */
	public void setTid(Integer tid){
		this.tid = tid;
	}
	
    /**
     * 主键
     * @return
     */	
    public Integer getTid(){
    	return tid;
    }
	/**
	 * 设备编号
	 * @param deviceNumber
	 */
	public void setDeviceNumber(String deviceNumber){
		this.deviceNumber = deviceNumber;
	}
	
    /**
     * 设备编号
     * @return
     */	
    public String getDeviceNumber(){
    	return deviceNumber;
    }
	/**
	 * 行程ID
	 * @param tripid
	 */
	public void setTripid(String tripid){
		this.tripid = tripid;
	}
	
    /**
     * 行程ID
     * @return
     */	
    public String getTripid(){
    	return tripid;
    }
	/**
	 * 点火时间
	 * @param starttime
	 */
	public void setStarttime(java.util.Date starttime){
		this.starttime = starttime;
	}
	
    /**
     * 点火时间
     * @return
     */	
    public java.util.Date getStarttime(){
    	return starttime;
    }
	/**
	 * 熄火时间
	 * @param endtime
	 */
	public void setEndtime(java.util.Date endtime){
		this.endtime = endtime;
	}
	
    /**
     * 熄火时间
     * @return
     */	
    public java.util.Date getEndtime(){
    	return endtime;
    }
	/**
	 * 本次行驶时长，单位s
	 * @param drivertime
	 */
	public void setDrivertime(Integer drivertime){
		this.drivertime = drivertime;
	}
	
    /**
     * 本次行驶时长，单位s
     * @return
     */	
    public Integer getDrivertime(){
    	return drivertime;
    }
	/**
	 * 本次行程油耗，单位ml
	 * @param oilwear
	 */
	public void setOilwear(Integer oilwear){
		this.oilwear = oilwear;
	}
	
    /**
     * 本次行程油耗，单位ml
     * @return
     */	
    public Integer getOilwear(){
    	return oilwear;
    }
	/**
	 * 里程，单位m
	 * @param mail
	 */
	public void setMail(Integer mail){
		this.mail = mail;
	}
	
    /**
     * 里程，单位m
     * @return
     */	
    public Integer getMail(){
    	return mail;
    }
	/**
	 * 最高速度，单位km/h
	 * @param hspeed
	 */
	public void setHspeed(Integer hspeed){
		this.hspeed = hspeed;
	}
	
    /**
     * 最高速度，单位km/h
     * @return
     */	
    public Integer getHspeed(){
    	return hspeed;
    }
	/**
	 * 转速，单位rmp
	 * @param rmp
	 */
	public void setRmp(Integer rmp){
		this.rmp = rmp;
	}
	
    /**
     * 转速，单位rmp
     * @return
     */	
    public Integer getRmp(){
    	return rmp;
    }
	/**
	 * 冷却液最高温度
	 * @param temp
	 */
	public void setTemp(Integer temp){
		this.temp = temp;
	}
	
    /**
     * 冷却液最高温度
     * @return
     */	
    public Integer getTemp(){
    	return temp;
    }
	/**
	 * 急加速次数
	 * @param rapidSpeed
	 */
	public void setRapidSpeed(Integer rapidSpeed){
		this.rapidSpeed = rapidSpeed;
	}
	
    /**
     * 急加速次数
     * @return
     */	
    public Integer getRapidSpeed(){
    	return rapidSpeed;
    }
	/**
	 * 急减速次数
	 * @param suddenBrake
	 */
	public void setSuddenBrake(Integer suddenBrake){
		this.suddenBrake = suddenBrake;
	}
	
    /**
     * 急减速次数
     * @return
     */	
    public Integer getSuddenBrake(){
    	return suddenBrake;
    }
	/**
	 * 超速行驶时间>120km/h，单位s
	 * @param overSpeedTime
	 */
	public void setOverSpeedTime(Integer overSpeedTime){
		this.overSpeedTime = overSpeedTime;
	}
	
    /**
     * 超速行驶时间>120km/h，单位s
     * @return
     */	
    public Integer getOverSpeedTime(){
    	return overSpeedTime;
    }
	/**
	 * 超速行驶里程，单位m
	 * @param overSpeedMail
	 */
	public void setOverSpeedMail(Integer overSpeedMail){
		this.overSpeedMail = overSpeedMail;
	}
	
    /**
     * 超速行驶里程，单位m
     * @return
     */	
    public Integer getOverSpeedMail(){
    	return overSpeedMail;
    }
	/**
	 * 超速行驶耗油量，单位ml
	 * @param overSpeedOilWear
	 */
	public void setOverSpeedOilWear(Integer overSpeedOilWear){
		this.overSpeedOilWear = overSpeedOilWear;
	}
	
    /**
     * 超速行驶耗油量，单位ml
     * @return
     */	
    public Integer getOverSpeedOilWear(){
    	return overSpeedOilWear;
    }
	/**
	 * 高速行驶时间>80km/h，
	 * @param hspeedTime
	 */
	public void setHspeedTime(Integer hspeedTime){
		this.hspeedTime = hspeedTime;
	}
	
    /**
     * 高速行驶时间>80km/h，
     * @return
     */	
    public Integer getHspeedTime(){
    	return hspeedTime;
    }
	/**
	 * 高速行驶里程，单位m
	 * @param hspeedMail
	 */
	public void setHspeedMail(Integer hspeedMail){
		this.hspeedMail = hspeedMail;
	}
	
    /**
     * 高速行驶里程，单位m
     * @return
     */	
    public Integer getHspeedMail(){
    	return hspeedMail;
    }
	/**
	 * 高速行驶油耗，单位ml
	 * @param hspeedOilWear
	 */
	public void setHspeedOilWear(Integer hspeedOilWear){
		this.hspeedOilWear = hspeedOilWear;
	}
	
    /**
     * 高速行驶油耗，单位ml
     * @return
     */	
    public Integer getHspeedOilWear(){
    	return hspeedOilWear;
    }
	/**
	 * 中速行驶时间>60,单位s
	 * @param mspeedTime
	 */
	public void setMspeedTime(Integer mspeedTime){
		this.mspeedTime = mspeedTime;
	}
	
    /**
     * 中速行驶时间>60,单位s
     * @return
     */	
    public Integer getMspeedTime(){
    	return mspeedTime;
    }
	/**
	 * 中速行驶里程，单位m
	 * @param mspeedMail
	 */
	public void setMspeedMail(Integer mspeedMail){
		this.mspeedMail = mspeedMail;
	}
	
    /**
     * 中速行驶里程，单位m
     * @return
     */	
    public Integer getMspeedMail(){
    	return mspeedMail;
    }
	/**
	 * 中速行驶油耗，单位ml
	 * @param mspeedOilWear
	 */
	public void setMspeedOilWear(Integer mspeedOilWear){
		this.mspeedOilWear = mspeedOilWear;
	}
	
    /**
     * 中速行驶油耗，单位ml
     * @return
     */	
    public Integer getMspeedOilWear(){
    	return mspeedOilWear;
    }
	/**
	 * 低速行驶时间<60，单位s
	 * @param lspeedTime
	 */
	public void setLspeedTime(Integer lspeedTime){
		this.lspeedTime = lspeedTime;
	}
	
    /**
     * 低速行驶时间<60，单位s
     * @return
     */	
    public Integer getLspeedTime(){
    	return lspeedTime;
    }
	/**
	 * 低速行驶里程，单位m
	 * @param lspeedMail
	 */
	public void setLspeedMail(Integer lspeedMail){
		this.lspeedMail = lspeedMail;
	}
	
    /**
     * 低速行驶里程，单位m
     * @return
     */	
    public Integer getLspeedMail(){
    	return lspeedMail;
    }
	/**
	 * 低速行驶油耗，单位ml
	 * @param lspeedOilWear
	 */
	public void setLspeedOilWear(Integer lspeedOilWear){
		this.lspeedOilWear = lspeedOilWear;
	}
	
    /**
     * 低速行驶油耗，单位ml
     * @return
     */	
    public Integer getLspeedOilWear(){
    	return lspeedOilWear;
    }
	/**
	 * 怠速时间，单位s
	 * @param idleTime
	 */
	public void setIdleTime(Integer idleTime){
		this.idleTime = idleTime;
	}
	
    /**
     * 怠速时间，单位s
     * @return
     */	
    public Integer getIdleTime(){
    	return idleTime;
    }
	/**
	 * 怠速油耗，单位ml
	 * @param idleTimeOilWear
	 */
	public void setIdleTimeOilWear(Integer idleTimeOilWear){
		this.idleTimeOilWear = idleTimeOilWear;
	}
	
    /**
     * 怠速油耗，单位ml
     * @return
     */	
    public Integer getIdleTimeOilWear(){
    	return idleTimeOilWear;
    }
	/**
	 * 急转弯次数
	 * @param suddenTurn
	 */
	public void setSuddenTurn(Integer suddenTurn){
		this.suddenTurn = suddenTurn;
	}
	
    /**
     * 急转弯次数
     * @return
     */	
    public Integer getSuddenTurn(){
    	return suddenTurn;
    }
}