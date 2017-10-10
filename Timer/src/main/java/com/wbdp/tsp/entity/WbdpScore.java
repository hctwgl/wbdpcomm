package com.wbdp.tsp.entity;

/**
 * 
 * @author wangsaijun
 */
public class WbdpScore {
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
	 *  评分日期
	 */
	private java.util.Date scoreDate;
	/**
	 *  急刹车次数
	 */
	private Integer suddenBrake;
	/**
	 *  急刹车得分
	 */
	private Integer sbScore;
	/**
	 *  急加速次数
	 */
	private Integer rapidSpeed;
	/**
	 *  急加速得分
	 */
	private Integer rsScore;
	/**
	 *  急转弯次数
	 */
	private Integer suddenTurn;
	/**
	 *  急转弯得分
	 */
	private Integer stScore;
	/**
	 *  超速次数
	 */
	private Integer overSpeed;
	/**
	 *  超速得分
	 */
	private Integer osScore;
	/**
	 *  平均速度
	 */
	private double meanSpeed;
	/**
	 *  平均速度得分
	 */
	private Integer msScore;
	/**
	 *  驾驶时长
	 */
	private Integer driveTime;
	/**
	 *  驾驶时长得分
	 */
	private Integer dtScore;
	/**
	 *  夜间行驶时长
	 */
	private Double nightDrive;
	/**
	 *  夜间行驶得分
	 */
	private Integer ndScore;
	/**
	 *  疲劳驾驶次数
	 */
	private Integer fatigueDrive;
	/**
	 *  疲劳驾驶得分
	 */
	private Integer fdScore;
	/**
	 *  怠速时长
	 */
	private Integer idleTime;
	/**
	 *  怠速时长得分
	 */
	private Integer itScore;
	/**
	 *  总分
	 */
	private Integer driveScore;
	/**
	 *  录入时间
	 */
	private java.util.Date saveDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public java.util.Date getScoreDate() {
		return scoreDate;
	}
	public void setScoreDate(java.util.Date scoreDate) {
		this.scoreDate = scoreDate;
	}
	public Integer getSuddenBrake() {
		return suddenBrake;
	}
	public void setSuddenBrake(Integer suddenBrake) {
		this.suddenBrake = suddenBrake;
	}
	public Integer getSbScore() {
		return sbScore;
	}
	public void setSbScore(Integer sbScore) {
		this.sbScore = sbScore;
	}
	public Integer getRapidSpeed() {
		return rapidSpeed;
	}
	public void setRapidSpeed(Integer rapidSpeed) {
		this.rapidSpeed = rapidSpeed;
	}
	public Integer getRsScore() {
		return rsScore;
	}
	public void setRsScore(Integer rsScore) {
		this.rsScore = rsScore;
	}
	public Integer getSuddenTurn() {
		return suddenTurn;
	}
	public void setSuddenTurn(Integer suddenTurn) {
		this.suddenTurn = suddenTurn;
	}
	public Integer getStScore() {
		return stScore;
	}
	public void setStScore(Integer stScore) {
		this.stScore = stScore;
	}
	public Integer getOverSpeed() {
		return overSpeed;
	}
	public void setOverSpeed(Integer overSpeed) {
		this.overSpeed = overSpeed;
	}
	public Integer getOsScore() {
		return osScore;
	}
	public void setOsScore(Integer osScore) {
		this.osScore = osScore;
	}
	
	public double getMeanSpeed() {
		return meanSpeed;
	}
	public void setMeanSpeed(double meanSpeed) {
		this.meanSpeed = meanSpeed;
	}
	public Integer getMsScore() {
		return msScore;
	}
	public void setMsScore(Integer msScore) {
		this.msScore = msScore;
	}
	
	public Integer getDriveTime() {
		return driveTime;
	}
	public void setDriveTime(Integer driveTime) {
		this.driveTime = driveTime;
	}
	public Integer getDtScore() {
		return dtScore;
	}
	public void setDtScore(Integer dtScore) {
		this.dtScore = dtScore;
	}
	
	public Integer getNdScore() {
		return ndScore;
	}
	public void setNdScore(Integer ndScore) {
		this.ndScore = ndScore;
	}
	public Integer getFatigueDrive() {
		return fatigueDrive;
	}
	public void setFatigueDrive(Integer fatigueDrive) {
		this.fatigueDrive = fatigueDrive;
	}
	public Integer getFdScore() {
		return fdScore;
	}
	public void setFdScore(Integer fdScore) {
		this.fdScore = fdScore;
	}
	
	public Double getNightDrive() {
		return nightDrive;
	}
	public void setNightDrive(Double nightDrive) {
		this.nightDrive = nightDrive;
	}
	public Integer getIdleTime() {
		return idleTime;
	}
	public void setIdleTime(Integer idleTime) {
		this.idleTime = idleTime;
	}
	public Integer getItScore() {
		return itScore;
	}
	public void setItScore(Integer itScore) {
		this.itScore = itScore;
	}
	public Integer getDriveScore() {
		return driveScore;
	}
	public void setDriveScore(Integer driveScore) {
		this.driveScore = driveScore;
	}
	public java.util.Date getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(java.util.Date saveDate) {
		this.saveDate = saveDate;
	}
	
}