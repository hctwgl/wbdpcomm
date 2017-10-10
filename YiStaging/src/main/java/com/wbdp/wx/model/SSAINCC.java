package com.wbdp.wx.model;

public class SSAINCC {
	private SocialSecurityAccount ssa;
    /**社保账户*/
    private String SSA;
    /**密码*/
    private String password;
	private String insuranceNum;
	private UserCreditCard cc;
	private int id;
	/**信用卡号*/
    private String creditcard;
    /**银行卡名*/
    private String bankname;
    
	public String getSSA() {
		return SSA;
	}
	public void setSSA(String sSA) {
		SSA = sSA;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreditcard() {
		return creditcard;
	}
	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public SocialSecurityAccount getSsa() {
		return ssa;
	}
	public void setSsa(SocialSecurityAccount ssa) {
		this.ssa = ssa;
	}
	public String getInsuranceNum() {
		return insuranceNum;
	}
	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}
	public UserCreditCard getCc() {
		return cc;
	}
	public void setCc(UserCreditCard cc) {
		this.cc = cc;
	}
	@Override
	public String toString() {
		return "[ssa=" + ssa + ", SSA=" + SSA + ", password="
				+ password + ", insuranceNum=" + insuranceNum + ", cc=" + cc
				+ ", id=" + id + ", creditcard=" + creditcard + ", bankname="
				+ bankname + "]";
	}
	
}
