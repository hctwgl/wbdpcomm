package com.wbdp.tsp.entity;

public class TripScoreEntity {
	
	
	//设备ID
	private String deivceNumber;
	//急刹车次数
	private int suddenBrake;
	//急加速次数
	private int rapidSpeed;
	//急转弯次数
	private int suddenTurn;
	//平均速度
	private double meanSpeed;
	//驾驶时长
	private int driveTime;
	//夜间行驶
	private int nightDrive;
	//怠速时长
	private int idleTime;
	//疲劳驾驶
	private int fatigueDrive;
	//超速
	private int overSpeed;
	public String getDeivceNumber() {
		return deivceNumber;
	}
	public void setDeivceNumber(String deivceNumber) {
		this.deivceNumber = deivceNumber;
	}
	public int getSuddenBrake() {
		return suddenBrake;
	}
	public void setSuddenBrake(int suddenBrake) {
		this.suddenBrake = suddenBrake;
	}
	public int getRapidSpeed() {
		return rapidSpeed;
	}
	public void setRapidSpeed(int rapidSpeed) {
		this.rapidSpeed = rapidSpeed;
	}
	public int getSuddenTurn() {
		return suddenTurn;
	}
	public void setSuddenTurn(int suddenTurn) {
		this.suddenTurn = suddenTurn;
	}
	public double getMeanSpeed() {
		return meanSpeed;
	}
	public void setMeanSpeed(double meanSpeed) {
		this.meanSpeed = meanSpeed;
	}
	public int getDriveTime() {
		return driveTime;
	}
	public void setDriveTime(int driveTime) {
		this.driveTime = driveTime;
	}
	public int getNightDrive() {
		return nightDrive;
	}
	public void setNightDrive(int nightDrive) {
		this.nightDrive = nightDrive;
	}
	public int getIdleTime() {
		return idleTime;
	}
	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}
	public int getFatigueDrive() {
		return fatigueDrive;
	}
	public void setFatigueDrive(int fatigueDrive) {
		this.fatigueDrive = fatigueDrive;
	}
	public int getOverSpeed() {
		return overSpeed;
	}
	public void setOverSpeed(int overSpeed) {
		this.overSpeed = overSpeed;
	}
	
}
