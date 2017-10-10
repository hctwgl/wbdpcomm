package com.wbdp.wx.model;

public class UserCreditCard {
	private int id;
	/**信用卡号*/
    private String creditcard;
    /**银行卡名*/
    private String bankname;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", creditcard=" + creditcard
				+ ", bankname=" + bankname + "]";
	}
}
